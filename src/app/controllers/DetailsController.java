package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class DetailsController implements Controllable {
    @FXML private Pane pane;
    private Scene scene;

    @Override
    public Scene getScene() {
        if (scene == null) {
            scene = new Scene(pane);
        }
        return scene;
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    public void goBack() {
        ControllerInfoNode.stage.setScene(ControllerInfoNode.controllers[2].getScene());
    }
}
