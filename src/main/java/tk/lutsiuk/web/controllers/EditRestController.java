package tk.lutsiuk.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.lutsiuk.web.models.Article;
import tk.lutsiuk.web.service.ArticleService;

@RestController
public class EditRestController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/blog/like/")
	public boolean setLike(Article art) {
		Article article = articleService.findByid(art.getId());
		return articleService.addLike(article);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/blog/like_show/")
	public int showLikesByArticleId(Article art) {
		Article article = articleService.findByid(art.getId());
		return articleService.findByid(article.getId()).getLikes_count();
	}
}
