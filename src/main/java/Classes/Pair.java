package Classes;

import java.time.LocalDate;
public class Pair {

    private int idProject;
    private int numberRelative;
    private int student1;
    private int student2;

    private LocalDate dateRemiseReport;

    // Getters et Setters

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public int getNumberRelative() {
        return numberRelative;
    }

    public void setNumberRelative(int numberRelative) {
        this.numberRelative = numberRelative;
    }

    public int getStudent1() {
        return student1;
    }

    public void setStudent1(int student1) {
        this.student1 = student1;
    }

    public int getStudent2() {
        return student2;
    }

    public void setStudent2(int student2) {
        this.student2 = student2;
    }

    public void setDateRemiseReport(LocalDate dateRemiseReport) {
        this.dateRemiseReport = dateRemiseReport;
    }

    public LocalDate getdateRemiseReport() {
        return dateRemiseReport;
    }

    public int calculatePointsPenalty() {
        // Exemple
        if (dateRemiseReport != null) { // Penality 1 point / day
            LocalDate datePrevue = LocalDate.of(2023, 12, 31);
            int Late = (int) datePrevue.until(dateRemiseReport).getDays();
            return Math.max(0, Late);  // Penality minimale 0 points
        } else {
            return 0;  // not late, no penality
        }
    }
}

