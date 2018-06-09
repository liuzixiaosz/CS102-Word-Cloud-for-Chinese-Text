package app.controllers;

import app.LabelCreator;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class WordCloudController implements Controllable {

    @FXML
    private AnchorPane anchorPane;
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
        ControllerInfoNode.stage.setScene(ControllerInfoNode.controllers[0].getScene());
    }

    public void save() {

    }

    public void refresh() {
        List<Label> labelList = LabelCreator.getLabels(ControllerInfoNode.wordFreqList, ControllerInfoNode.circles);
        getPane().getChildren().clear();
        getPane().getChildren().addAll(labelList);
    }
}
