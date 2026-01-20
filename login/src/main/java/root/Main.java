package root;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/view/auth/Login.fxml"))
        ));
        stage.setTitle("Restaurant POS System");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}