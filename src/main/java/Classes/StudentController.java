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
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentController extends Node implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;


    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField tFName;

    @FXML
    private TextField tLastName;

    @FXML
    private TextField tidFormation;

    @FXML
    private TableColumn<Student, String> colfName;

    @FXML
    private TableColumn<Student, Integer> colid;

    @FXML
    private TableColumn<Student, Integer> colidForm;

    @FXML
    private TableColumn<Student, String> collName;

    @FXML
    private TableView<Student> table;
    int idStudent = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showStudents();
    }

    public ObservableList<Student> getStudent(){
        ObservableList<Student> student = FXCollections.observableArrayList();

        String query = "select * from sql11666264.Student;";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Student st = new Student();
                st.setIdStudent(rs.getInt("IdStudent"));
                st.setFirstName(rs.getString("firstName"));
                st.setLastName(rs.getString("lastName"));
                st.setFormation(rs.getInt("idFormation"));
                student.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return student;
    }

    public void showStudents(){
        ObservableList<Student> list = getStudent();
        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Student,Integer>("idStudent"));
        colfName.setCellValueFactory(new PropertyValueFactory<Student,String>("FirstName"));
        collName.setCellValueFactory(new PropertyValueFactory<Student,String>("LastName"));
        colidForm.setCellValueFactory(new PropertyValueFactory<Student,Integer>("formation"));
    }



    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void creatStudent(ActionEvent event) {

        String insert = "insert into sql11666264.Student(firstname,lastname,idFormation) values(?,?,?)";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,tFName.getText());
            st.setString(2,tLastName.getText());
            st.setInt(3, Integer.parseInt(tidFormation.getText()));
            st.executeUpdate();
            clear();
            showStudents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    void getData(MouseEvent event) {
        Student student = table.getSelectionModel().getSelectedItem();
        idStudent = student.getIdStudent();
        tFName.setText(student.getFirstName());
        tLastName.setText(student.getLastName());
        tidFormation.setText(String.valueOf(student.getFormation()));
        btnSave.setDisable(true);

    }

    void clear() {
        tFName.setText(null);
        tLastName.setText(null);
        tidFormation.setText(null);
        btnSave.setDisable(false);

    }


    @FXML
    void deleteStudent(ActionEvent event) {
        String delete = "delete from sql11666264.Student where idStudent = ?";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(delete);
            st.setInt(1,idStudent);
            st.executeUpdate();
            showStudents();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void updateStudent(ActionEvent event) {

        String update = "update sql11666264.Student set FirstName = ?, LastName = ?, idFormation = ? where idStudent = ?";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(update);
            st.setString(1,tFName.getText());
            st.setString(2,tLastName.getText());
            st.setInt(3, Integer.parseInt(tidFormation.getText()));
            st.setInt(4,idStudent);
            st.executeUpdate();
            showStudents();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
