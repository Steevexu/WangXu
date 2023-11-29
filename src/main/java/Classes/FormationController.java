package Classes;

import Infra.DBConnect;
import com.mysql.cj.util.StringInspector;
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
import java.util.SimpleTimeZone;

public class FormationController extends Node implements Initializable{

    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;


    @FXML
    private Button btnClear1;

    @FXML
    private Button btnDelete1;

    @FXML
    private Button btnSave1;

    @FXML
    private Button btnUpdate1;
    @FXML
    private TextField tNameFormation1;

    @FXML
    private TextField tPromotion;

    @FXML
    private TableColumn<Formation, Integer> colid1;

    @FXML
    private TableColumn<Formation, String> colPromotion;

    @FXML
    private TableColumn<Formation, String> colNameForm1;

    @FXML
    private TableView<Formation> table1;
    int idFormation = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showFormations();
    }

    public ObservableList<Formation> getFormation(){
        ObservableList<Formation> formations = FXCollections.observableArrayList();

        String query = "select * from sql11666264.Formation;";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Formation st = new Formation();
                st.setIdFormation(rs.getInt("idFormation"));
                st.setName(rs.getString("name"));
                st.setPromotion(rs.getString("promotion"));
                formations.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return formations;
    }

    public void showFormations(){
        ObservableList<Formation> list = getFormation();
        table1.setItems(list);
        colid1.setCellValueFactory(new PropertyValueFactory<Formation,Integer>("idFormation"));
        colNameForm1.setCellValueFactory(new PropertyValueFactory<Formation,String>("name"));
        colPromotion.setCellValueFactory(new PropertyValueFactory<Formation,String>("Promotion"));
    }



    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void creatFormation(ActionEvent event) {

        String insert = "insert into sql11666264.Formation(name,promotion) values(?,?)";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,tNameFormation1.getText());
            st.setString(2,tPromotion.getText());
            st.executeUpdate();
            clear();
            showFormations();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    void getData(MouseEvent event) {
        Formation formation = table1.getSelectionModel().getSelectedItem();
        idFormation = formation.getIdFormation();
        tNameFormation1.setText(formation.getName());
        tPromotion.setText(formation.getPromotion());
        btnSave1.setDisable(true);
    }

    void clear() {
        tNameFormation1.setText(null);
        tPromotion.setText(null);
        btnSave1.setDisable(false);
    }


    @FXML
    void deleteFormation(ActionEvent event) {
        String delete = "delete from sql11666264.Formation where idFormation = ?";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(delete);
            st.setInt(1,idFormation);
            st.executeUpdate();
            showFormations();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void updateFormation(ActionEvent event) {

        String update = "update sql11666264.Formation set name = ?, promotion = ? where idFormation = ?";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(update);
            st.setString(1,tNameFormation1.getText());
            st.setString(2,tPromotion.getText());
            st.setInt(3,idFormation);
            st.executeUpdate();
            showFormations();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
