package tk.lutsiuk.web.repository;

import org.springframework.data.repository.CrudRepository;
import tk.lutsiuk.web.models.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	Article findByTitle(String title);
}
