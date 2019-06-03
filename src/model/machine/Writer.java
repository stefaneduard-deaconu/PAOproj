package model.machine;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Writer implements Runnable{
    Integer writerId, userId, paperId, findEndId;


    @Override
    public void run() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WriterPanel.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Stage writerStage = new Stage();
        writerStage.setTitle("Writer Panel: " + userId.toString());
        writerStage.setScene(new Scene(root));

        writerStage.setWidth(800);
        writerStage.setHeight(600);
        writerStage.setResizable(false);


        writerStage.show();
        writerStage.centerOnScreen();

        // here i could get a controller as to set up stuff


    }

    public Writer(Integer id) {
        writerId = id;

    }
    public Writer(Integer readerId, Integer userId) {
        this.writerId = readerId;
        this.userId = userId;
    }
    public Writer(Integer readerId, Integer userId, Integer paperId, Integer findEndId) {
        this.writerId = readerId;
        this.userId = userId;
        this.paperId = paperId;
        this.findEndId = findEndId;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
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

    public Integer getFindEndId() {
        return findEndId;
    }

    public void setFindEndId(Integer findEndId) {
        this.findEndId = findEndId;
    }

}
