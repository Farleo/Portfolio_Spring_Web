package tk.lutsiuk.web.models;

import javax.persistence.*;
import java.time.LocalDateTime;

import static tk.lutsiuk.web.constant.Constants.NO_IMAGE;

@Entity
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "cover_photo")
	private String coverPhoto;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "info", nullable = false, columnDefinition = "TINYTEXT")
	private String info;
	
	@Column(name = "full_text", nullable = false, columnDefinition = "TEXT")
	private String fullText;
	
	@Column(name = "views")
	private int views;
	
	@Column(name = "time_creation", nullable = false)
	private LocalDateTime timeCreation;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCoverPhoto() {
		if(coverPhoto==null || coverPhoto.isEmpty()){
			coverPhoto=NO_IMAGE;
		}
		return coverPhoto;
	}
	
	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
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
	
	public LocalDateTime getTimeCreation() {
		return timeCreation;
	}
	
	public void setTimeCreation(LocalDateTime timeCreation) {
		this.timeCreation = timeCreation;
	}
}
