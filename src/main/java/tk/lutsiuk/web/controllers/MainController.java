package tk.lutsiuk.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("title", "Lutsiuk Taras");
		return "main";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About me");
		return "about";
	}
	
}
