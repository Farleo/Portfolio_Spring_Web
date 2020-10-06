package tk.lutsiuk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tk.lutsiuk.web.models.Article;
import tk.lutsiuk.web.service.ArticleService;

import java.io.IOException;

@Controller
public class BlogController {
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping("/blog")
	public String blog(Model model) {
		model.addAttribute("title", "Блог");
		Iterable<Article> articles = articleService.findAll();
		model.addAttribute("articles", articles);
		return "blog/blog-main";
	}
	
	@RequestMapping("blog/{id}")
	public String blogView(@PathVariable Long id, Model model) {
		Article article = articleService.findByid(id);
		articleService.addView(article);
		model.addAttribute("title", "Блог - " + article.getTitle());
		model.addAttribute("article", article);
		return "blog/blog-view";
	}
	
	@PostMapping("/blog/like/{id}")
	public String setLike(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		Article article = articleService.findByid(id);
		boolean checkLikeExist = articleService.addLike(article);
		if (checkLikeExist) {
			redirectAttributes.addFlashAttribute("error_like", "You already like this post! Or someone liked this post from your IP address=)");
		}
		return "redirect:/blog/{id}";
	}
	
	@PostMapping("blog/edit/{id}")
	public String blogEdit(@PathVariable Long id, Model model) {
		Article article = articleService.findByid(id);
		model.addAttribute("title", "Редагування - " + article.getTitle());
		model.addAttribute("article", article);
		return "blog/blog-edit";
	}
	
	@PostMapping("blog/edit/{id}/save")
	public String blogEditSave(@ModelAttribute Article article, @RequestParam("inpFile") MultipartFile photo, @PathVariable Long id, Model model) throws IOException {
		articleService.updateArticleById(article, photo.getBytes(), photo.getOriginalFilename());
		return "redirect:/blog/{id}";
	}
}
