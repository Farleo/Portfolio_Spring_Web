package tk.lutsiuk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.lutsiuk.web.models.User;
import tk.lutsiuk.web.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String getLoginHands(@AuthenticationPrincipal User currentUser, Model model) {
		model.addAttribute("title", "Login page");
		if (currentUser != null) {
			return "redirect:/";
		}
		return "login/login";
	}
	
	@GetMapping("/registration")
	public String getRegistration(Model model) {
		model.addAttribute("title", "Registration page");
		return "login/registration";
	}
	
	@PostMapping("/registration")
	public String addRegistrationUser(User user, Model model) {
		boolean userSucceedRegister = userService.registrationNewUser(user);
		if (!userSucceedRegister) {
			model.addAttribute("message", "User exist!");
			return "login/registration";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/user/edit/")
	public String editUserAccount(Model model) {
		User user = userService.getLoggedUser();
		model.addAttribute("user", user);
		model.addAttribute("title", "Edit - " + user.getEmail());
		return "account/account-edit";
	}
	
	@PostMapping("/user/edit/save")
	public String editUserAccountSave(@ModelAttribute User user, Model model) {
		model.addAttribute("user", user);
		userService.editUser(user);
		return "redirect:";
	}
}
