package tk.lutsiuk.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.lutsiuk.web.models.Article;
import tk.lutsiuk.web.service.ArticleService;

@RestController
public class EditRestController {
	@Autowired
	private ArticleService articleService;
	
	//TODO Change like button with js
	@RequestMapping("/blog/like/{articleId}")
	public boolean setLike(@PathVariable("articleId") Long articleId) {
		Article article = articleService.findByid(articleId);
		return articleService.addLike(article);
	}
	
	//TODO
	@RequestMapping("/blog/like-show/{articleId}")
	public int showViewsByArticleId(@PathVariable("articleId") Long articleId) {
		Article article = articleService.findByid(articleId);
		return articleService.findByid(article.getId()).getViews();
	}
}
