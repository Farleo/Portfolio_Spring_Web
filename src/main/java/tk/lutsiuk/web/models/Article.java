package tk.lutsiuk.web.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "cover_photo")
	private String coverPhoto;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "info", nullable = false, columnDefinition="TINYTEXT")
	private String info;
	
	@Column(name = "full_text", nullable = false, columnDefinition="TEXT")
	private String fullText;
	
	@Column(name = "views")
	private int views;
	
	@Column(name = "likes_count")
	private int likes_count;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "article")
	private Set<Likes> likes = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getFullText() {
		return fullText;
	}
	
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}
	
	public int getViews() {
		return views;
	}
	
	public void setViews(int views) {
		this.views = views;
	}
	
	public int getLikes_count() {
		return likes_count;
	}
	
	public void setLikes_count(int likes) {
		this.likes_count = likes;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getCoverPhoto() {
		return coverPhoto;
	}
	
	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}
	
	public Set<Likes> getLikes() {
		return likes;
	}
	
	public void setLikes(Set<Likes> likes) {
		this.likes = likes;
	}
}
