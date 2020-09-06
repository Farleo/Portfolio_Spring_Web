package tk.lutsiuk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.lutsiuk.web.models.Article;
import tk.lutsiuk.web.repository.ArticleRepository;

@Controller
public class BlogController {

@Autowired
private ArticleRepository articleRepository;

@GetMapping("/blog")
public String blog(Model model) {
	model.addAttribute("title", "Блог");
	Iterable<Article> articles = articleRepository.findAll();
	model.addAttribute("articles", articles);
	return "blog-main";
}
}
