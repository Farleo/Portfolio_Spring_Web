package tk.lutsiuk.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lutsiuk.web.models.Page;

public interface PageRepository extends JpaRepository<Page, Long> {
	Page findByTitle (String title);
}
