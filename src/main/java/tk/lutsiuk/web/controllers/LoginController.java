package tk.lutsiuk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tk.lutsiuk.web.models.User;
import tk.lutsiuk.web.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

import static tk.lutsiuk.web.models.Role.USER;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("authenticationManager")
	protected AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

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
	public String addRegistrationUser(User user, Map<String, Object> model, HttpServletRequest request) {
		User userFromDb = userRepository.findByEmail(user.getEmail());
		if (userFromDb != null) {
			model.put("message", "User exist!");
			return "login/registration";
		}
		user.setActive(true);
		user.setRoles(Collections.singleton(USER));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		authenticateUserAndSetSession(user);
		return "redirect:/";
	}

	private void authenticateUserAndSetSession(User user) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
				AuthorityUtils.createAuthorityList("USER"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
