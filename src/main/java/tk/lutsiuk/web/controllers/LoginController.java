package tk.lutsiuk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tk.lutsiuk.web.models.Role;
import tk.lutsiuk.web.models.User;
import tk.lutsiuk.web.repository.UserRepository;

import java.util.Collections;
import java.util.Map;

@Controller
public class LoginController {

@Autowired
private UserRepository userRepository;

@GetMapping("/login")
public String getLoginHands(@AuthenticationPrincipal User currentUser) {
	if (currentUser != null) {
		return "redirect:/";
	}
	return "login";
}

@GetMapping("/registration")
public String getRegistration() {
	return "registration";
}

@PostMapping("/registration")
public String addRegistrationUser(User user, Map<String, Object> model) {
	User userFromDb = userRepository.findByEmail(user.getEmail());
	if (userFromDb != null) {
		model.put("message", "User exist!");
		return "registration";
	}
	user.setActive(true);
	user.setRoles(Collections.singleton(Role.USER));
	userRepository.save(user);
	return "redirect:/login";
}
}
