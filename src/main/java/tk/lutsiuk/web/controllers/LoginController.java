package tk.lutsiuk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tk.lutsiuk.web.models.User;
import tk.lutsiuk.web.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String getLoginHands(@AuthenticationPrincipal User currentUser, Model model) {
		model.addAttribute("title", "Сторінка входу");
		if (currentUser != null) {
			return "redirect:/";
		}
		return "login/login";
	}
	
	@GetMapping("/registration")
	public String getRegistration(Model model) {
		model.addAttribute("title", "Сторінка реєстрації");
		return "login/registration";
	}
	
	@PostMapping("/registration")
	public String addRegistrationUser(User user, Model model) {
		boolean userFromDb = userService.registrationNewUser(user);
		if (userFromDb) {
			model.addAttribute("message", "User exist!");
			return "login/registration";
		}
		return "redirect:/";
	}
}
