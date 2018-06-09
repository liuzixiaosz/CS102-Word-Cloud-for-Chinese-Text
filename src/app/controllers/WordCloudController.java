package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class WordCloudController implements Controllable {

    @FXML private AnchorPane anchorPane;
    private Scene scene;
    @Override
    public Scene getScene() {
        if (scene == null) {
            scene = new Scene(anchorPane);
        }
        return scene;
    }
    @Override
    public Pane getPane() {
        return anchorPane;
    }
    public void back() {

    }
    public void save() {

    }
}
