package tk.lutsiuk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tk.lutsiuk.web.models.Article;
import tk.lutsiuk.web.repository.ArticleRepository;

import java.util.Map;

@Controller
public class AdminController {
@Autowired
ArticleRepository articleRepository;

@GetMapping("/admin/blog/addnew")
public String adminBlogAdd(Article article, Map<String, Object> m, Model model) {
	model.addAttribute("title", "Додання нового запису");
	model.addAttribute(new Article());
	return "admin/blog-add";
}

@PostMapping("/admin/blog/addnew")
public String addRegistrationUser(Article article, Map<String, Object> m) {
	Article articleFromDb = articleRepository.findByTitle(article.getTitle());
	if (articleFromDb != null) {
		m.put("message", "Title exist!");
		return "admin/blog-add";
	}
	articleRepository.save(article);
	return "redirect:/";
}
	
}
