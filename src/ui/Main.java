package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPanel.fxml"));
        Parent root = (Parent) loader.load();

        primaryStage.setTitle("User Panel");
        primaryStage.setScene(new Scene(root));


        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);


        primaryStage.show();
        primaryStage.centerOnScreen();

//        UserController controller = loader.getController(); // this is the one defined as a controller
    }


    public static void main(String[] args) {

        launch(args);
    }
}
