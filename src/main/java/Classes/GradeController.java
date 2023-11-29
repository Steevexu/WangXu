package Classes;

import Infra.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static java.util.Date.*;

public class GradeController extends Node implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private Button btnClear11;

    @FXML
    private Button btnDelete11;

    @FXML
    private Button btnSave11;

    @FXML
    private Button btnUpdate11;

    @FXML
    private TextField tidProject;

    @FXML
    private TextField tNumberPair;

    @FXML
    private TextField tReportGrade;

    @FXML
    private TextField tOralGrade1;

    @FXML
    private TextField tOralGrade2;

    @FXML
    private TextField tDateReturn;

    @FXML
    private TableColumn<Grade, Integer> colidproject;

    @FXML
    private TableColumn<Grade, Integer> colNumberPair;

    @FXML
    private TableColumn<Grade, Float> colReportGrade;

    @FXML
    private TableColumn<Grade, Float> colOralGrade1;

    @FXML
    private TableColumn<Grade, Float> colOralGrade2;

    @FXML
    private TableColumn<Grade, Date> colDateReturn;

    @FXML
    private TableView<Grade> table2;

    int idProject = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showGrades();
    }

    public ObservableList<Grade> getGrade() {
        ObservableList<Grade> grades = FXCollections.observableArrayList();

        String query = "select * from data.Grade;";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setIdProject(rs.getInt("IdProject"));
                grade.setNumberPair(rs.getInt("NumberPair"));
                grade.setGradeReport(rs.getFloat("gradeReport"));
                grade.setGradeOral1(rs.getFloat("gradeOral1"));
                grade.setGradeOral2(rs.getFloat("gradeOral2"));
                grade.setDateReturnEffectiveReport(rs.getDate("dateReturnEffectiveReport"));
                grades.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return grades;
    }

    public void showGrades() {
        ObservableList<Grade> list = getGrade();
        table2.setItems(list);
        colidproject.setCellValueFactory(new PropertyValueFactory<>("idProject"));
        colNumberPair.setCellValueFactory(new PropertyValueFactory<>("numberPair"));
        colReportGrade.setCellValueFactory(new PropertyValueFactory<>("gradeReport"));
        colOralGrade1.setCellValueFactory(new PropertyValueFactory<>("gradeOral1"));
        colOralGrade2.setCellValueFactory(new PropertyValueFactory<>("gradeOral2"));
        colDateReturn.setCellValueFactory(new PropertyValueFactory<>("dateReturnEffectiveReport"));
    }

    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void creatStudent(ActionEvent event) {
        String insert = "INSERT INTO data.Grade (idProject, NumberPair, gradeReport, gradeOral1, gradeOral2, dateReturnEffectiveReport) VALUES (?, ?, ?, ?, ?, ?)";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(insert);
            st.setInt(1, Integer.parseInt(tidProject.getText()));
            st.setInt(2, Integer.parseInt(tNumberPair.getText()));
            st.setFloat(3, Float.parseFloat(tReportGrade.getText()));
            st.setFloat(4, Float.parseFloat(tOralGrade1.getText()));
            st.setFloat(5, Float.parseFloat(tOralGrade2.getText()));
            st.setDate(6, java.sql.Date.valueOf(tDateReturn.getText()));
            st.executeUpdate();
            clear();
            showGrades();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getData(MouseEvent event) {
        Grade grade = table2.getSelectionModel().getSelectedItem();
        idProject = grade.getIdProject();
        tidProject.setText(String.valueOf(grade.getIdProject()));
        tNumberPair.setText(String.valueOf(grade.getNumberPair()));
        tReportGrade.setText(String.valueOf(grade.getGradeReport()));
        tOralGrade1.setText(String.valueOf(grade.getGradeOral1()));
        tOralGrade2.setText(String.valueOf(grade.getGradeOral2()));
        tDateReturn.setText(String.valueOf(grade.getDateReturnEffectiveReport()));
        btnSave11.setDisable(true);
    }

    void clear() {
        tidProject.setText(null);
        tNumberPair.setText(null);
        tReportGrade.setText(null);
        tOralGrade1.setText(null);
        tOralGrade2.setText(null);
        tDateReturn.setText(null);
        btnSave11.setDisable(false);
    }

    @FXML
    void deleteStudent(ActionEvent event) {
        String delete = "delete from data.Grade where idProject = ?";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, idProject);
            st.executeUpdate();
            showGrades();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateStudent(ActionEvent event) {
        String update = "update data.Grade set NumberPair = ?, gradeReport = ?, gradeOral1 = ?, gradeOral2 = ?, dateReturnEffectiveReport = ? where idProject = ?";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(update);
            st.setInt(1, Integer.parseInt(tNumberPair.getText()));
            st.setFloat(2, Float.parseFloat(tReportGrade.getText()));
            st.setFloat(3, Float.parseFloat(tOralGrade1.getText()));
            st.setFloat(4, Float.parseFloat(tOralGrade2.getText()));
            st.setDate(5, java.sql.Date.valueOf(tDateReturn.getText()));
            st.setInt(6, idProject);
            st.executeUpdate();
            showGrades();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
