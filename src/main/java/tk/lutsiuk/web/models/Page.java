package tk.lutsiuk.web.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "page")
public class Page {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "page_id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "title", unique = true, nullable = false)
	private String title;
	
	@Column(name = "text", columnDefinition = "TEXT")
	private String fullText;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getFullText() {
		return fullText;
	}
	
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
