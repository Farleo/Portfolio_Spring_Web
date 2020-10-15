package tk.lutsiuk.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.lutsiuk.web.models.Project;
import tk.lutsiuk.web.repository.ProjectRepository;
import tk.lutsiuk.web.service.ProjectService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ImageStorageServiceImpl imageStorageService;
	
	@Override
	public List<Project> findAllReverse() {
		List<Project> reverseList = projectRepository.findAll();
		Collections.reverse(reverseList);
		return reverseList;
	}
	
	@Override
	public void createProject(Project project, byte[] photo, String originalPhotoName) throws IOException {
		LocalDateTime dateTime = LocalDateTime.now();
		project.setTimeCreation(dateTime);
		Optional<String> newCoverPhotoPath = imageStorageService.saveAndReturnImageLink(photo, originalPhotoName);
		project.setCoverPhoto(newCoverPhotoPath.orElse(null));
		projectRepository.save(project);
		
	}
	
	@Override
	public Project findByTitle(String title) {
		return projectRepository.findByTitle(title);
	}
	
	@Override
	public Project findByid(Long id) {
		return projectRepository.getOne(id);
	}
	
	@Override
	public void addView(Project project) {
		project.setViews(project.getViews() + 1);
		projectRepository.save(project);
	}
	
	@Override
	public void updateProjectById(Project project, byte[] bytes, String originalFilename) throws IOException {
		Optional<String> newCoverPhoto = imageStorageService.saveAndReturnImageLink(bytes, originalFilename);
		project.setCoverPhoto(newCoverPhoto.orElse(project.getCoverPhoto()));
		projectRepository.save(project);
	}
}
