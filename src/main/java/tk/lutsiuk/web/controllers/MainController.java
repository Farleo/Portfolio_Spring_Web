package tk.lutsiuk.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("title", "Головна сторінка");
		return "main";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "Про мене");
		return "about";
	}
	
	@GetMapping("/database")
	public String database(Model model) {
		model.addAttribute("title", "База знань");
		return "database";
	}
}
