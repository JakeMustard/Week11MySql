package projects.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import projects.entity.Project;

public class ProjectDao {

    // Existing code...

    // Insert a project into the database and return the inserted project
    public Project insertProject(Project project) {
        String sql = "INSERT INTO projects (projectName, estimatedHours, actualHours, difficulty, notes) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setBigDecimal(2, project.getEstimatedHours());
            preparedStatement.setBigDecimal(3, project.getActualHours());
            preparedStatement.setInt(4, project.getDifficulty());
            preparedStatement.setString(5, project.getNotes());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    project.setId(generatedId); // Set the ID of the project
                    return project;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
        

        return null; // Return null if the project insertion fails
    }
    
    public boolean updateProject1(Project updatedProject) {
      String sql = "UPDATE projects SET projectName = ?, estimatedHours = ?, actualHours = ?, difficulty = ?, notes = ? WHERE id = ?";

      try (Connection connection = DbConnection.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

          preparedStatement.setString(1, updatedProject.getProjectName());
          preparedStatement.setBigDecimal(2, updatedProject.getEstimatedHours());
          preparedStatement.setBigDecimal(3, updatedProject.getActualHours());
          preparedStatement.setInt(4, updatedProject.getDifficulty());
          preparedStatement.setString(5, updatedProject.getNotes());
          preparedStatement.setInt(6, updatedProject.getId());

          System.out.println("Executing SQL: " + preparedStatement.toString()); // Log SQL statement

          int rowsAffected = preparedStatement.executeUpdate();

          if (rowsAffected == 1) {
              System.out.println("Project with ID=" + updatedProject.getId() + " has been updated.");
              return true; // Return true if one row was updated (success)
          } else {
              System.out.println("Project with ID=" + updatedProject.getId() + " was not updated.");
          }
      } catch (SQLException e) {
          e.printStackTrace(); // Log exceptions
      }

      return false; // Return false if the update fails
  }




    // Fetch all projects from the database
    public List<Project> fetchAllProjects() {
        List<Project> projects = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM projects");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Project project = mapResultSetToProject(resultSet);
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return projects;
    }

    // Retrieve a project by its ID from the database
    public Project getProjectById(int projectId) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM projects WHERE id = ?")) {

            preparedStatement.setInt(1, projectId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToProject(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return null; // Return null if no project with the given ID is found
    }

    // Delete a project by its ID from the database
    public boolean deleteProject(int projectToDeleteId) {
        // Check if the project with the specified ID exists
        if (projectExists(projectToDeleteId)) {
            String sql = "DELETE FROM projects WHERE id = ?";

            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, projectToDeleteId);

                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the deletion was successful
                return rowsAffected == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions
            }
        }

        return false; // Return false if the project does not exist or deletion fails
    }

    // Check if a project with the specified ID exists in the database
    public boolean projectExists(int projectId) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM projects WHERE id = ?")) {

            preparedStatement.setInt(1, projectId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int rowCount = resultSet.getInt(1);
                    return rowCount > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return false; // Return false if no project with the given ID is found
    }

    // Helper method to map a ResultSet row to a Project object
    private static Project mapResultSetToProject(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getInt("id"));
        project.setProjectName(resultSet.getString("projectName"));
        project.setEstimatedHours(resultSet.getBigDecimal("estimatedHours"));
        project.setActualHours(resultSet.getBigDecimal("actualHours"));
        project.setDifficulty(resultSet.getInt("difficulty"));
        project.setNotes(resultSet.getString("notes"));
        return project;
    }

    public void updateProject(Project updatedProject) {
      // TODO Auto-generated method stub
      
    }

    
}
