package tk.lutsiuk.web.service;

import tk.lutsiuk.web.models.User;

public interface UserService {
	
	boolean registrationNewUser(User user);
	
	void editUser(User user);
	
	User findByid(Long id);
}
