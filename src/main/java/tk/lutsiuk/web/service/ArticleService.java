package tk.lutsiuk.web.service;

import tk.lutsiuk.web.models.Article;

import java.io.IOException;

public interface ArticleService {
	
	void createArticle(Article article, byte[] photo, String originalPhotoName) throws IOException;
	
	Iterable<Article> findAll();
	
	Article findByid(Long id);
	
	void updateArticleById(Article article, byte[] photo, String originalPhotoName) throws IOException;
	
	void addView(Article article);
	
	boolean addLike(Article article);
}
