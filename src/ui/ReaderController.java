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

public class ReaderController {
    private DB db;
    private User user = null;


    public ReaderController(DB database) {
        db = database;
    }

    @FXML
    private Label readerLabel, feedbackLabel;

    @FXML
    private Button stopStartButton;
    private Boolean start = false;

    @FXML
    private ChoiceBox readerChoiceBox;


    @FXML
    private ListView<String> readerListView;



    public void initialize() {
        readerChoiceBox.setItems(db.getPapersObservable());

        stopStartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start == false) {
                    start = true;
                }
                else {
                    start = false;
                }
            }
        });



    }

    public static void Main(String []args) {

    }
}
