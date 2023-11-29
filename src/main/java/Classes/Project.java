package Classes;

import java.util.Date;

public class Project {
    private int idProject;
    private String nameSubject;
    private String topic;
    private Date dateRemisePreview;


    // Getters et Setters

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDateRemisePreview() {
        return dateRemisePreview;
    }

    public void setDateRemisePreview(Date dateRemisePreview) {
        this.dateRemisePreview = dateRemisePreview;
    }
}

