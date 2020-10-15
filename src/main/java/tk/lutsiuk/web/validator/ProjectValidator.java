package tk.lutsiuk.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tk.lutsiuk.web.models.Project;
import tk.lutsiuk.web.service.ProjectService;

@Service
public class ProjectValidator implements Validator {
	@Autowired
	ProjectService projectService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Project.class.equals(aClass);
	}
	
	@Override
	public void validate(Object o, Errors errors) {
		Project project = (Project) o;
		Project byTitle = projectService.findByTitle(project.getTitle());
		
		if (byTitle != null && !project.getId().equals(byTitle.getId())) {
			errors.rejectValue("title", "title.unique", "This title already exist");
		}
		if (project.getTitle().isEmpty()) {
			errors.rejectValue("title", "title.unique", "Title is empty");
		}
		if (project.getInfo().equals("<div><br></div>") || project.getInfo().isEmpty()) {
			errors.rejectValue("info", "info.unique", "Info is empty");
		}
		if (project.getFullText().equals("<div><br></div>") || project.getFullText().isEmpty()) {
			errors.rejectValue("fullText", "fulltext.unique", "Full Text is empty");
		}
		
	}
}
