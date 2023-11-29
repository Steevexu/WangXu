package Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/MainTab.fxml")));
            Scene scene = new Scene(parent);
            stage.setTitle("CRUD Application");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
