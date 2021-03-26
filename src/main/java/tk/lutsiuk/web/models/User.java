package tk.lutsiuk.web.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import static tk.lutsiuk.web.constant.Constants.NO_IMAGE;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "first_name", length = 45)
	private String firstName;
	
	@Column(name = "last_name", length = 45)
	private String lastName;
	
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	
	@Column(name = "email", nullable = false, length = 100, unique = true)
	private String email;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "info", length = 1000)
	private String info;
	@Column(name = "is_active", nullable = false)
	private Boolean isActive;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted = false;
	
	@Column(name = "user_creation_ip")
	private String userCreationIp;
	
	@Column(name = "time_creation")
	private LocalDateTime timeCreation;
	
	@ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoto() {
		if(photo==null || photo.isEmpty()){
			photo=NO_IMAGE;
		}
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public Boolean getActive() {
		return isActive;
	}
	
	public void setActive(Boolean active) {
		isActive = active;
	}
	
	public Boolean getDeleted() {
		return isDeleted;
	}
	
	public void setDeleted(Boolean deleted) {
		isDeleted = deleted;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getUserCreationIp() {
		return userCreationIp;
	}
	
	public void setUserCreationIp(String userCreationIp) {
		this.userCreationIp = userCreationIp;
	}
	
	public LocalDateTime getTimeCreation() {
		return timeCreation;
	}
	
	public void setTimeCreation(LocalDateTime timeCreation) {
		this.timeCreation = timeCreation;
	}
	
	@Override
	public String toString() {
		return "User{" +
				       "id=" + id +
				       ", firstName='" + firstName + '\'' +
				       ", lastName='" + lastName + '\'' +
				       ", password='" + password + '\'' +
				       ", email='" + email + '\'' +
				       ", photo='" + photo + '\'' +
				       ", info='" + info + '\'' +
				       ", isActive=" + isActive +
				       ", isDeleted=" + isDeleted +
				       ", userCreationIp='" + userCreationIp + '\'' +
				       ", timeCreation=" + timeCreation +
				       ", roles=" + roles +
				       '}';
	}
}
