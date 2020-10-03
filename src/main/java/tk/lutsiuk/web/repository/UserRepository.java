package tk.lutsiuk.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lutsiuk.web.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findByEmail(String email);
	
}
