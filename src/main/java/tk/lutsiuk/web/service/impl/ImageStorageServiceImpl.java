package tk.lutsiuk.web.service.impl;

import org.springframework.stereotype.Service;
import tk.lutsiuk.web.service.ImageStorageService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {
	
	private String uploadPath = System.getProperty("user.dir");
	
	@Override
	public Optional<String> saveAndReturnImageLink(byte[] bytes, String originalFileName) throws IOException {
		if (bytes == null || bytes.length <= 0) {
			return Optional.empty();
		}
		
		String uuIdFile = UUID.randomUUID().toString();
		String resultFileName = uuIdFile + originalFileName;
		String mediaDir = File.separator + "media" + File.separator;
		File uploadFile = new File(uploadPath + mediaDir);
		if (!uploadFile.exists()) {
			uploadFile.mkdir();
		}
		
		File newPhoto = new File(uploadFile.toString() + File.separator + resultFileName);
		
		try (FileOutputStream fos = new FileOutputStream(newPhoto)) {
			fos.write(bytes);
		}
		return Optional.of(mediaDir + resultFileName);
	}
	
	@Override
	public void removeOldImage(Path imageLink) {
		try {
			if (Files.exists(imageLink)) {
				Files.delete(imageLink);
			}
		} catch (IOException e) {
		
		}
	}
}
