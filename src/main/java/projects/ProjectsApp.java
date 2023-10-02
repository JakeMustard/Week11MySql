package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import projects.entity.Project;
import projects.service.ProjectService;
import projects.dao.ProjectDao;

public class ProjectsApp {
    private final ProjectService projectService;

    private Project curProject;

    private ProjectsApp() {
        projectService = new ProjectService(new ProjectDao());
    }

    public static void main(String[] args) {
        ProjectsApp app = new ProjectsApp();
        app.processUserSelections();
    }

    private void processUserSelections() {
        boolean done = false;
        Scanner scanner = new Scanner(System.in);

        while (!done) {
            printOperations();

            int selection = getUserSelection(scanner);

            switch (selection) {
                case 1:
                    addProject(scanner);
                    break;
                case 2:
                    listProjects();
                    break;
                case 3:
                    selectProject(scanner);
                    break;
                case 4:
                    updateProjectDetails(scanner);
                    break;
                case 5:
                    deleteProject(scanner);
                    break;
                case 0:
                    done = true;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("\nError: " + selection + " is not a valid selection. Try again.");
                    break;
            }
        }

        scanner.close();
    }

    private void printOperations() {
        System.out.println("\nThese are the available selections. Enter the number to choose an option:");
        System.out.println("1) Add Project");
        System.out.println("2) List Projects");
        System.out.println("3) Select Project");
        System.out.println("4) Update Project Details");
        System.out.println("5) Delete a Project");
        System.out.println("0) Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserSelection(Scanner scanner) {
        int selection = -1;
        try {
            selection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer selection.");
        }
        return selection;
    }

    private void addProject(Scanner scanner) {
        System.out.println("Enter project details:");
        System.out.print("Project Name: ");
        String projectName = scanner.nextLine();
        System.out.print("Estimated Hours: ");
        BigDecimal estimatedHours = new BigDecimal(scanner.nextLine());
        System.out.print("Actual Hours: ");
        BigDecimal actualHours = new BigDecimal(scanner.nextLine());
        System.out.print("Difficulty: ");
        int difficulty = Integer.parseInt(scanner.nextLine());
        System.out.print("Notes: ");
        String notes = scanner.nextLine();

        Project project = new Project(projectName, estimatedHours, actualHours, difficulty, notes);

        Project addedProject = projectService.addProject(project);

        if (addedProject != null) {
            System.out.println("Project added successfully with ID: " + addedProject.getId());
        } else {
            System.out.println("Failed to add the project.");
        }
    }

    private void listProjects() {
        List<Project> projects = projectService.getAllProjects();

        if (projects.isEmpty()) {
            System.out.println("\nNo projects found.");
        } else {
            System.out.println("\nProjects:");
            for (Project project : projects) {
                System.out.println("ID: " + project.getId() + ", Name: " + project.getProjectName());
            }
        }
    }

    private void selectProject(Scanner scanner) {
        System.out.print("\nEnter a project ID to select a project: ");
        int projectId = getUserSelection(scanner);

        curProject = projectService.getProjectById(projectId);

        if (curProject != null) {
            System.out.println("\nYou are working with project:");
            System.out.println("ID: " + curProject.getId());
            System.out.println("Name: " + curProject.getProjectName());
            System.out.println("Estimated Hours: " + curProject.getEstimatedHours());
            System.out.println("Actual Hours: " + curProject.getActualHours());
            System.out.println("Difficulty: " + curProject.getDifficulty());
            System.out.println("Notes: " + curProject.getNotes());
        } else {
            System.out.println("\nInvalid project ID selected.");
        }
    }

    private void updateProjectDetails(Scanner scanner) {
        if (curProject == null) {
            System.out.println("\nPlease select a project.");
            return;
        }

        System.out.println("\nCurrent Project Details:");
        System.out.println("ID: " + curProject.getId());
        System.out.println("Name: " + curProject.getProjectName());
        System.out.println("Estimated Hours: " + curProject.getEstimatedHours());
        System.out.println("Actual Hours: " + curProject.getActualHours());
        System.out.println("Difficulty: " + curProject.getDifficulty());
        System.out.println("Notes: " + curProject.getNotes());

        System.out.println("\nEnter updated project details (leave empty for no change):");
        System.out.print("Project Name: ");
        String updatedName = scanner.nextLine();
        System.out.print("Estimated Hours: ");
        String updatedEstimatedHours = scanner.nextLine();
        System.out.print("Actual Hours: ");
        String updatedActualHours = scanner.nextLine();
        System.out.print("Difficulty: ");
        String updatedDifficulty = scanner.nextLine();
        System.out.print("Notes: ");
        String updatedNotes = scanner.nextLine();

        Project updatedProject = new Project();
        updatedProject.setId(curProject.getId());
        updatedProject.setProjectName(updatedName.isEmpty() ? curProject.getProjectName() : updatedName);
        updatedProject.setEstimatedHours(updatedEstimatedHours.isEmpty() ? curProject.getEstimatedHours() :
                new BigDecimal(updatedEstimatedHours));
        updatedProject.setActualHours(updatedActualHours.isEmpty() ? curProject.getActualHours() :
                new BigDecimal(updatedActualHours));
        updatedProject.setDifficulty(updatedDifficulty.isEmpty() ? curProject.getDifficulty() :
                Integer.parseInt(updatedDifficulty));
        updatedProject.setNotes(updatedNotes.isEmpty() ? curProject.getNotes() : updatedNotes);

        projectService.modifyProjectDetails(updatedProject);

        curProject = updatedProject;
    }

    private void deleteProject(Scanner scanner) {
        if (curProject == null) {
            System.out.println("\nPlease select a project.");
            return;
        }

        System.out.print("Are you sure you want to delete the current project? (yes/no): ");
        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("yes")) {
            int projectIdToDelete = curProject.getId();

            boolean projectDeleted = projectService.deleteProject(projectIdToDelete);

            if (projectDeleted) {
                System.out.println("Project with ID " + projectIdToDelete + " has been deleted.");
                curProject = null;
            } else {
                System.out.println("Failed to delete the project.");
            }
        } else {
            System.out.println("Deletion canceled.");
        }
    }
}
