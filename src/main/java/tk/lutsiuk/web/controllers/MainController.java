package tk.lutsiuk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tk.lutsiuk.web.models.Page;
import tk.lutsiuk.web.service.PageService;

@Controller
public class MainController {
	
	public static final String ABOUT_ME_PAGE = "Lutsiuk Taras - About me";
	
	@Autowired
	private PageService pageService;
	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("title", "Lutsiuk Taras");
		return "main";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("page", pageService.findByTitle(ABOUT_ME_PAGE));
		return "about";
	}
	
	@PostMapping("/page/edit/{id}")
	public String pageEdit(@PathVariable Long id, Model model) {
		model.addAttribute("page", pageService.findById(id));
		return "about-edit";
	}
	
	@PostMapping("/page/edit/{id}/save")
	public String saveEditedPage(@ModelAttribute Page page) {
		pageService.updatePageById(page);
		return "redirect:/";
	}
	
}
