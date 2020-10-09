package tk.lutsiuk.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.lutsiuk.web.models.User;
import tk.lutsiuk.web.repository.UserRepository;
import tk.lutsiuk.web.service.UserService;
import tk.lutsiuk.web.utils.HttpReqRespUtils;

import java.time.LocalDateTime;
import java.util.Collections;

import static tk.lutsiuk.web.models.Role.USER;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean registrationNewUser(User user) {
		User userFromDb = userRepository.findByEmail(user.getEmail());
		if (userFromDb != null) {
			return false;
		}
		user.setActive(true);
		user.setRoles(Collections.singleton(USER));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		LocalDateTime dateTime = LocalDateTime.now();
		user.setTimeCreation(dateTime);
		String ip = HttpReqRespUtils.getClientIpAddressIfServletRequestExist();
		user.setUserCreationIp(ip);
		userRepository.save(user);
		authenticateUserAndSetSession(user);
		return true;
	}
	
	@Override
	public void editUser(User user) {
		user.setActive(true);
		userRepository.save(user);
	}
	
	@Override
	public User findByid(Long id) {
		return userRepository.getOne(id);
	}
	
	private void authenticateUserAndSetSession(User user) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
				AuthorityUtils.createAuthorityList("USER"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
