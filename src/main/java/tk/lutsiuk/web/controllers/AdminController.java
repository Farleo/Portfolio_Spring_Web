package tk.lutsiuk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tk.lutsiuk.web.models.Article;
import tk.lutsiuk.web.models.Project;
import tk.lutsiuk.web.service.ArticleService;
import tk.lutsiuk.web.service.ProjectService;
import tk.lutsiuk.web.validator.ArticleValidator;
import tk.lutsiuk.web.validator.ProjectValidator;

import java.io.IOException;

@Controller
public class AdminController {
	@Autowired
	ArticleService articleService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ArticleValidator articleValidator;
	
	@Autowired
	ProjectValidator projectValidator;
	
	@GetMapping("/admin/blog/addnew")
	public String adminAddBlogView(Model model) {
		model.addAttribute("title", "Add new article");
		model.addAttribute(new Article());
		return "blog/blog-add";
	}
	
	@PostMapping("/admin/blog/addnew")
	public String adminNewBlogSave(Article article, @RequestParam("inpFile") MultipartFile photo, Model model, BindingResult bindingResult) throws IOException {
		articleValidator.validate(article, bindingResult);
		if (bindingResult.hasErrors()) {
			return "blog/blog-add";
		}
		articleService.createArticle(article, photo.getBytes(), photo.getOriginalFilename());
		return "redirect:/";
	}
	
	@GetMapping("/admin/project/addnew")
	public String adminAddProjectView(Model model) {
		model.addAttribute("title", "Add new project");
		model.addAttribute(new Project());
		return "project/project-add";
	}
	
	@PostMapping("/admin/project/addnew")
	public String adminNewProjectSave(Project project, @RequestParam("inpFile") MultipartFile photo, Model model, BindingResult bindingResult) throws IOException {
		projectValidator.validate(project, bindingResult);
		if (bindingResult.hasErrors()) {
			return "project/project-add";
		}
		projectService.createProject(project, photo.getBytes(), photo.getOriginalFilename());
		return "redirect:/";
	}
	
}
