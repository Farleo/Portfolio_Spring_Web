package tk.lutsiuk.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lutsiuk.web.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	Project findByTitle(String title);
}
