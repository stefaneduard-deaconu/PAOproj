package ui;

import database.DB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import model.Auth;
import model.User;
import model.machine.Reader;
import model.machine.Writer;





import java.util.Random;

public class UserController {
    private DB db;
    private User user = null;

    // part of multithreading:
    private Reader reader = null;
    private Writer writer = null;

    public UserController() {
        db = new DB();
    }

    @FXML
    private TextField upName, upPass, upConfirmPass,
                      inName, inPass;
    @FXML
    private Label userLabel;

    @FXML
    private Button upButton, inButton;

    private Boolean isSignedIn = false;

    public void initialize() {
        upButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!upPass.getText().equals(upConfirmPass.getText()))
                    upName.setText("Passwords don't match");
                else if(db.isUsernameTaken(upName.getText()))
                    upName.setText("The name is taken");
                    else {
//                        System.out.println("User is ready to auth"); // (*)
                        Boolean createdUser = false;
                        while (!createdUser) {
                                createdUser = db.createUser( new User(
                                    new Random().nextInt(), new Random().nextInt(), new Random().nextInt(), upName.getText(), upPass.getText()
                            ));

                            isSignedIn = true;

                            // implements the multithreading:

                            reader = db.readReader(user.getReaderId());
                            writer = db.readWriter(user.getWriterId());
                            Thread read = new Thread(reader);
                            Thread write = new Thread(writer);
                            read.run();
                            write.run();
                        }
                }
            }
        });
        inButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (db.isUser(inName.getText(), inPass.getText())) {
//                    System.out.println("Old user is ready to auth");
                    isSignedIn = true;
                    // implements the multithreading:
                    reader = db.readReader(user.getReaderId());
                    writer = db.readWriter(user.getWriterId());
                    reader.run();
                    writer.run();
                }
                else
                    inName.setText("Check your credentials");
            }
        });


    }

    public static void Main(String []args) {

    }
}
