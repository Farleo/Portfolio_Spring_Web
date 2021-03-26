package tk.lutsiuk.web.service;

import tk.lutsiuk.web.models.Project;

import java.io.IOException;
import java.util.List;

public interface ProjectService {
	List<Project> findAllReverse();
	
	void createProject(Project project, byte[] photo, String originalPhotoName) throws IOException;
	
	Project findByTitle(String title);
	
	Project findByid(Long id);
	
	void addView(Project project);
	
	void updateProjectById(Project project, byte[] bytes, String originalFilename) throws IOException;
	
	List<Project> getListProject();
	
	void delete(Long id);
}
