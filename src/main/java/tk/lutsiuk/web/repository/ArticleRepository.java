package tk.lutsiuk.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lutsiuk.web.models.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	Article findByTitle(String title);
	
}
