package Classes;

import java.util.Date;

public class Grade {
    private int idProject;
    private int NumberPair;
    private float gradeReport;
    private float gradeOral1;
    private float gradeOral2;
    private Date dateReturnEffectiveReport;

    // Getters et Setters


    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public int getNumberPair() {
        return NumberPair;
    }

    public void setNumberPair(int numberPair) {
        NumberPair = numberPair;
    }

    public float getGradeReport() {
        return gradeReport;
    }

    public void setGradeReport(float gradeReport) {
        this.gradeReport = gradeReport;
    }

    public float getGradeOral1() {
        return gradeOral1;
    }

    public void setGradeOral1(float gradeOral1) {
        this.gradeOral1 = gradeOral1;
    }

    public float getGradeOral2() {
        return gradeOral2;
    }

    public void setGradeOral2(float gradeOral2) {
        this.gradeOral2 = gradeOral2;
    }

    public Date getDateReturnEffectiveReport() {
        return dateReturnEffectiveReport;
    }

    public void setDateReturnEffectiveReport(Date dateReturnEffectiveReport) {
        this.dateReturnEffectiveReport = dateReturnEffectiveReport;
    }
}