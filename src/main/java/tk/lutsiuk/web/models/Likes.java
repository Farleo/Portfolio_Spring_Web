package tk.lutsiuk.web.models;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "likes_id", unique = true, nullable = false)
	private Long likes_id;
	
	@Column(name = "ip_adress")
	private String ipAdress;

	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;

public Long getLikes_id() {
	return likes_id;
}

public void setLikes_id(Long likes_id) {
	this.likes_id = likes_id;
}

public String getIpAdress() {
	return ipAdress;
}

public void setIpAdress(String ipAdress) {
	this.ipAdress = ipAdress;
}

public Article getArticle() {
	return article;
}

public void setArticle(Article article) {
	this.article = article;
}
}
