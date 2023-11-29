package Classes;

import Infra.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PairController implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private Label lblPointsPenality;

    @FXML
    private Button btnSave111;

    @FXML
    private Button btnUpdate111;

    @FXML
    private Button btnDelete111;

    @FXML
    private Button btnClear111;

    @FXML
    private TextField tidProject1;

    @FXML
    private TextField tNumberRelative;

    @FXML
    private TextField tStudent1;

    @FXML
    private TextField tStudent2;

    @FXML
    private TableColumn<Pair, Integer> colidproject1;

    @FXML
    private TableColumn<Pair, Integer> colNumberRelative;

    @FXML
    private TableColumn<Pair, Integer> colStudent1;

    @FXML
    private TableColumn<Pair, Integer> colStudent2;

    @FXML
    private TableView<Pair> table3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showPairs();
    }
    public ObservableList<Pair> getPairs() {
        ObservableList<Pair> pairs = FXCollections.observableArrayList();

        String query = "SELECT * FROM sql11666264.Pair;";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Pair st = new Pair();
                st.setIdProject(rs.getInt("idProject"));
                st.setNumberRelative(rs.getInt("numberRelative"));
                st.setStudent1(rs.getInt("student1"));
                st.setStudent2(rs.getInt("student2"));
                pairs.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return pairs;
    }

    public void showPairs() {
        ObservableList<Pair> list = getPairs();
        table3.setItems(list);
        colidproject1.setCellValueFactory(new PropertyValueFactory<>("idProject"));
        colNumberRelative.setCellValueFactory(new PropertyValueFactory<>("numberRelative"));
        colStudent1.setCellValueFactory(new PropertyValueFactory<>("student1"));
        colStudent2.setCellValueFactory(new PropertyValueFactory<>("student2"));
    }

    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void creatStudent(ActionEvent event) {
        String insert = "INSERT INTO sql11666264.Pair(idProject, numberRelative, student1, student2) VALUES (?,?,?,?)";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(insert);
            st.setInt(1, Integer.parseInt(tidProject1.getText()));
            st.setInt(2, Integer.parseInt(tNumberRelative.getText()));
            st.setInt(3, Integer.parseInt(tStudent1.getText()));
            st.setInt(4, Integer.parseInt(tStudent2.getText()));
            st.executeUpdate();
            clear();
            showPairs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getData(ActionEvent event) {
        Pair pair = table3.getSelectionModel().getSelectedItem();
        tidProject1.setText(String.valueOf(pair.getIdProject()));
        tNumberRelative.setText(String.valueOf(pair.getNumberRelative()));
        tStudent1.setText(String.valueOf(pair.getStudent1()));
        tStudent2.setText(String.valueOf(pair.getStudent2()));
        btnSave111.setDisable(true);
        int pointsPenality = pair.calculatePointsPenalty();
        lblPointsPenality.setText("Penality : " + pointsPenality);
    }


    @FXML
    void deleteStudent(ActionEvent event) {
        String delete = "DELETE FROM sql11666264.Pair WHERE idProject = ? AND numberRelative = ?";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(tidProject1.getText()));
            st.setInt(2, Integer.parseInt(tNumberRelative.getText()));
            st.executeUpdate();
            showPairs();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void clear() {
        tidProject1.setText(null);
        tNumberRelative.setText(null);
        tStudent1.setText(null);
        tStudent2.setText(null);
    }

    @FXML
    void updateStudent(ActionEvent event) {
        String update = "UPDATE sql11666264.Pair SET student1 = ?, student2 = ? WHERE idProject = ? AND numberRelative = ?";
        con = DBConnect.getCon();
        try {
            st = con.prepareStatement(update);
            st.setInt(1, Integer.parseInt(tStudent1.getText()));
            st.setInt(2, Integer.parseInt(tStudent2.getText()));
            st.setInt(3, Integer.parseInt(tidProject1.getText()));
            st.setInt(4, Integer.parseInt(tNumberRelative.getText()));
            st.executeUpdate();
            showPairs();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
