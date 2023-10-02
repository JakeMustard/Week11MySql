package projects.service;

import java.util.List;
import projects.dao.ProjectDao;
import projects.entity.Project;

public class ProjectService {
    private final ProjectDao projectDao;

    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public Project addProject(Project project) {
        return projectDao.insertProject(project);
    }

    public Project getProjectById(int projectId) {
        return projectDao.getProjectById(projectId);
    }

    public List<Project> getAllProjects() {
        return projectDao.fetchAllProjects();
    }

    public boolean deleteProject(int projectToDeleteId) {
        return projectDao.deleteProject(projectToDeleteId);
    }

    public void modifyProjectDetails(Project updatedProject) {
        if (!projectExists(updatedProject.getId())) {
            System.out.println("Project with ID=" + updatedProject.getId() + " does not exist.");
            return; // Exit the method gracefully
        }

        // Update the project details
        boolean success = projectDao.updateProject1(updatedProject); // Use updateProject1
        if (success) {
            System.out.println("Project with ID=" + updatedProject.getId() + " has been updated.");
        } else {
            System.out.println("Failed to update project with ID=" + updatedProject.getId());
        }
    }

    private boolean projectExists(int id) {
        // TODO: Implement this method
        // You can check if a project with the given ID exists in your service or dao layer
        return false;
    }
}
