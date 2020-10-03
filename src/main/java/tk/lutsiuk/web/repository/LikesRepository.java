package tk.lutsiuk.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lutsiuk.web.models.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long> {

	Boolean existsByIpAdressAndArticle_Id(String ip, Long article_Id);
	
}
