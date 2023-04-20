package com.example.herewegoagain;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class win_lost {
    @FXML
    private AnchorPane lost_pane;

    @FXML
    private AnchorPane won_pane;
    private Stage stage;


    @FXML
    protected void re_lost() throws IOException {
        Application a = new Application();
        Stage stage = new Stage();
        a.start(stage);
        this.stage = (Stage) lost_pane.getScene().getWindow();
        this.stage.close();
    }
    @FXML
    protected void ex_lost() {
        this.stage = (Stage) lost_pane.getScene().getWindow();
        this.stage.close();
    }

    @FXML
    protected void re_won() throws IOException {
        Application a = new Application();
        Stage stage = new Stage();
        a.start(stage);
        this.stage = (Stage) won_pane.getScene().getWindow();
        this.stage.close();
    }
    @FXML
    protected void ex_won() {
        this.stage = (Stage) won_pane.getScene().getWindow();
        this.stage.close();
    }
}
