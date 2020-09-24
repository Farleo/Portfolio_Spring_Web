package tk.lutsiuk.web.service;

import tk.lutsiuk.web.models.Article;

import java.io.IOException;

public interface ArticleService {

void createArticle(Article article, byte[] photo, String originalPhotoName) throws IOException;
	
}
