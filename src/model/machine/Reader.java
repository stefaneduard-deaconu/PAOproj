package model.machine;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class Reader implements Runnable{
    Integer readerId, userId, paperId;



    @Override
    public void run() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReaderPanel.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Stage readerStage = new Stage();
        readerStage.setTitle("Reader Panel: " + userId.toString());
        readerStage.setScene(new Scene(root));

        readerStage.setWidth(800);
        readerStage.setHeight(600);
        readerStage.setResizable(false);


        readerStage.show();
        readerStage.centerOnScreen();

        // here i could get a controller as to set up stuff


    }

    public Reader(Integer id) {
        readerId = id;

    }
    public Reader(Integer readerId, Integer userId) {
        this.readerId = readerId;
        this.userId = userId;
        this.paperId = null;
    }
    public Reader(Integer readerId, Integer userId, Integer paperId) {
        this.readerId = readerId;
        this.userId = userId;
        this.paperId = paperId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
}
