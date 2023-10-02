package projects.entity;

import java.math.BigDecimal;

public class Project {
    private int id; // Unique identifier for the project
    private String projectName;
    private BigDecimal estimatedHours;
    private BigDecimal actualHours;
    private Integer difficulty;
    private String notes;

    // Constructors (you can have multiple constructors if needed)
    public Project() {
        // Default constructor
    }

    public Project(String projectName, BigDecimal estimatedHours, BigDecimal actualHours,
                   Integer difficulty, String notes) {
        this.projectName = projectName;
        this.estimatedHours = estimatedHours;
        this.actualHours = actualHours;
        this.difficulty = difficulty;
        this.notes = notes;
    }

    // Getter and Setter methods for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(BigDecimal estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public BigDecimal getActualHours() {
        return actualHours;
    }

    public void setActualHours(BigDecimal actualHours) {
        this.actualHours = actualHours;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", estimatedHours=" + estimatedHours +
                ", actualHours=" + actualHours +
                ", difficulty=" + difficulty +
                ", notes='" + notes + '\'' +
                '}';
    }
}
