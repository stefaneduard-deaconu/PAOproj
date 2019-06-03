package ui;

import database.DB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.User;
import model.machine.Reader;
import model.machine.Writer;

import java.util.Random;

public class WriterController {
    private DB db;
    private User user = null;

    // part of multithreading:
    private Reader reader = null;
    private Writer writer = null;

    public WriterController(DB database) {
        db = database;
    }

    @FXML
    private Label writerLabel, feedbackLabel;

    @FXML
    private Button countButton;
    private Boolean counting = false;

    @FXML
    private ChoiceBox writerChoiceBox;


    @FXML
    private ListView<Text> writerListView;


    public void initialize() {
        writerChoiceBox.setItems(db.getPapersObservable());

        countButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (counting == false) {
                    counting = true;
                }
                else {
                    counting = false;
                }
            }
        });



    }

    public static void Main(String []args) {

    }
}
