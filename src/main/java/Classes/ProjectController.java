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
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectController extends Node implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;


    @FXML
    private Button btnClear12;

    @FXML
    private Button btnDelete12;

    @FXML
    private Button btnSave12;

    @FXML
    private Button btnUpdate12;

    @FXML
    private TextField tNameSubject;

    @FXML
    private TextField tTopic;

    @FXML
    private TextField tDateRemisePreview;

    @FXML
    private TableColumn<Project, Integer> colidproject2;

    @FXML
    private TableColumn<Project, String> colNameSubject;

    @FXML
    private TableColumn<Project, String> colTopic;

    @FXML
    private TableColumn<Project, Date> colDate;

    @FXML
    private TableView<Project> table4;
    int idProject = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showProjects();
    }

    public ObservableList<Project> getProject(){
        ObservableList<Project> projects = FXCollections.observableArrayList();

        String query = "select * from sql11666264.Project;";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Project st = new Project();
                st.setIdProject(rs.getInt("idProject"));
                st.setNameSubject(rs.getString("nameSubject"));
                st.setTopic(rs.getString("topic"));
                st.setDateRemisePreview(rs.getDate("dateRemisePreview"));
                projects.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return projects;
    }

    public void showProjects(){
        ObservableList<Project> list = getProject();
        table4.setItems(list);
        colidproject2.setCellValueFactory(new PropertyValueFactory<Project,Integer>("idProject"));
        colNameSubject.setCellValueFactory(new PropertyValueFactory<Project,String>("nameSubject"));
        colTopic.setCellValueFactory(new PropertyValueFactory<Project,String>("topic"));
        colDate.setCellValueFactory(new PropertyValueFactory<Project,Date>("dateRemisePreview"));
    }



    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void creatStudent(ActionEvent event) {

        String insert = "insert into sql11666264.Project(nameSubject,topic,dateRemisePreview) values(?,?,?)";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,tNameSubject.getText());
            st.setString(2,tTopic.getText());
            st.setString(3,tDateRemisePreview.getText());
            st.executeUpdate();
            clear();
            showProjects();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    void getData(MouseEvent event) {
        Project project = table4.getSelectionModel().getSelectedItem();
        idProject = project.getIdProject();
        tNameSubject.setText(project.getNameSubject());
        tTopic.setText(project.getTopic());
        tDateRemisePreview.setText(String.valueOf(project.getDateRemisePreview()));
        btnSave12.setDisable(true);
    }

    void clear() {
        tNameSubject.setText(null);
        tTopic.setText(null);
        tDateRemisePreview.setText(null);
        btnSave12.setDisable(false);
    }


    @FXML
    void deleteStudent(ActionEvent event) {
        String delete = "delete from sql11666264.Project where idProject = ?";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(delete);
            st.setInt(1,idProject);
            st.executeUpdate();
            showProjects();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void updateStudent(ActionEvent event) {

        String update = "update sql11666264.Project set nameSubject = ?, topic = ?, dateRemisePreview = ? where idProject = ?";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(update);
            st.setString(1,tNameSubject.getText());
            st.setString(2,tTopic.getText());
            st.setString(3,tDateRemisePreview.getText());
            st.executeUpdate();
            showProjects();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
