package tk.lutsiuk.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.lutsiuk.web.models.Article;
import tk.lutsiuk.web.models.Likes;
import tk.lutsiuk.web.repository.ArticleRepository;
import tk.lutsiuk.web.repository.LikesRepository;
import tk.lutsiuk.web.service.ArticleService;
import tk.lutsiuk.web.service.ImageStorageService;
import tk.lutsiuk.web.utils.HttpReqRespUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	LikesRepository likesRepository;
	@Autowired
	private ImageStorageService imageStorageService;
	
	@Override
	public void createArticle(Article article, byte[] photo, String originalPhotoName) throws IOException {
		Article articleFromDb = articleRepository.findByTitle(article.getTitle());
		if (articleFromDb != null) {
		
		}
		LocalDateTime dateTime = LocalDateTime.now();
		article.setTimeCreation(dateTime);
		Optional<String> newCoverPhotoPath = imageStorageService.saveAndReturnImageLink(photo, originalPhotoName);
		article.setCoverPhoto(newCoverPhotoPath.orElse(null));
		articleRepository.save(article);
	}
	
	@Override
	public List<Article> findAllReverse() {
		List<Article> reverseList = articleRepository.findAll();
		Collections.reverse(reverseList);
		return reverseList;
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
	
	@Override
	public void addView(Article article) {
		article.setViews(article.getViews() + 1);
		articleRepository.save(article);
	}
	
	@Override
	@Transactional
	public boolean addLike(Article article) {
		String ip = HttpReqRespUtils.getClientIpAddressIfServletRequestExist();
		boolean ipFromDb = likesRepository.existsByIpAdressAndArticle_Id(ip, article.getId());
		if (!ipFromDb) {
			Likes likes = new Likes();
			likes.setIpAdress(ip);
			likes.setArticle(article);
			likesRepository.save(likes);
			article.setLikes_count(article.getLikes_count() + 1);
			articleRepository.save(article);
			return false;
		}
		return true;
	}
	
	@Override
	public void deleteArticle(Long id) {
		Optional<Article> optionalArticle = articleRepository.findById(id);
		if (optionalArticle.isPresent()) {
			Article article = optionalArticle.get();
			articleRepository.delete(article);
		}
	}
	
	@Override
	public List<Article> getListArticle() {
		return articleRepository.findAll();
	}
}
