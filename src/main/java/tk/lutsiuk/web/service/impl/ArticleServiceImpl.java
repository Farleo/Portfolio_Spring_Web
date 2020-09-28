package tk.lutsiuk.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.lutsiuk.web.models.Article;
import tk.lutsiuk.web.repository.ArticleRepository;
import tk.lutsiuk.web.service.ArticleService;
import tk.lutsiuk.web.service.ImageStorageService;

import java.io.IOException;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

@Autowired
ArticleRepository articleRepository;

@Autowired
private ImageStorageService imageStorageService;

@Override
public void createArticle(Article article, byte[] photo, String originalPhotoName) throws IOException {
	Article articleFromDb = articleRepository.findByTitle(article.getTitle());
	if (articleFromDb != null) {
	
	}
	Optional<String> newCoverPhotoPath = imageStorageService.saveAndReturnImageLink(photo, originalPhotoName);
	article.setCoverPhoto(newCoverPhotoPath.orElse(null));
	articleRepository.save(article);
}

	@Override
	public Iterable<Article> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public Article findByid(Long id) {
		Optional<Article> optionalArticle = articleRepository.findById(id);
		return optionalArticle.orElse(null);
	}
	
	
	@Override
	@Transactional
	public void updateArticleById(Article article, byte[] photo, String originalPhotoName) throws IOException {
		Optional<String> newCoverPhoto = imageStorageService.saveAndReturnImageLink(photo, originalPhotoName);
		article.setCoverPhoto(newCoverPhoto.orElse(article.getCoverPhoto()));
		articleRepository.save(article);
	}
}
