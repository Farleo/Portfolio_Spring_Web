package tk.lutsiuk.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.lutsiuk.web.models.Project;
import tk.lutsiuk.web.service.ProjectService;

import java.io.IOException;
import java.util.List;

@Controller
public class ProjectController {
	@Autowired
	ProjectService projectService;
	
	@GetMapping("/project")
	public String project(Model model) {
		model.addAttribute("title", "My projects");
		List<Project> projects = projectService.findAllReverse();
		model.addAttribute("projects", projects);
		return "project/main";
	}
	
	@RequestMapping("project/{id}")
	public String projectView(@PathVariable Long id, Model model) {
		Project project = projectService.findByid(id);
		projectService.addView(project);
		model.addAttribute("title", "Lutsiuk Taras - Project - " + project.getTitle());
		model.addAttribute("project", project);
		return "project/project-view";
	}
	
	@PostMapping("project/edit/{id}")
	public String projectEdit(@PathVariable Long id, Model model) {
		Project project = projectService.findByid(id);
		model.addAttribute("title", "Edit - " + project.getTitle());
		model.addAttribute("project", project);
		return "project/project-edit";
	}
	
	@PostMapping("admin/project/delete/{id}")
	public String projectDelete(@PathVariable Long id) {
		projectService.delete(id);
		return "redirect:/admin/project-list";
	}
	
	@PostMapping("project/edit/{id}/save")
	public String projectEditSave(@ModelAttribute Project project, @RequestParam("inpFile") MultipartFile photo) throws IOException {
		projectService.updateProjectById(project, photo.getBytes(), photo.getOriginalFilename());
		return "redirect:/project/{id}";
	}
}
