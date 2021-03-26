package tk.lutsiuk.web.service;

import tk.lutsiuk.web.models.User;

import java.util.List;

public interface UserService {
	
	boolean registrationNewUser(User user);
	
	void editUser(User user);
	
	User findByid(Long id);
	
	User findByEmail(String email);
	
	User getLoggedUser();
	
	List<User> getUserList();
}
