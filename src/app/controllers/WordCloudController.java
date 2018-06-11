package app.controllers;

import app.LabelCreator;
import app.Main;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class WordCloudController implements Controllable {

    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane cloudPane;
    private Scene scene;
    public static Color backGroundColor = Color.BLACK;

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

    public Pane getCloudPane() {
        return cloudPane;
    }

    public void back() {
        ControllerInfoNode.stage.close();
        try {
            new Main().start(ControllerInfoNode.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        FileChooser chooser = new FileChooser();
        try {

            chooser.setInitialDirectory(new File(getClass().getResource("").getPath()));
            File f = chooser.showSaveDialog(null);

            if (f != null) {
                try {
                    WritableImage writableImage = new WritableImage((int) getCloudPane().getWidth(),
                            (int) getCloudPane().getHeight());
                    getPane().snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    //Write the snapshot to the chosen file
                    ImageIO.write(renderedImage, "png", f);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert information = new Alert(Alert.AlertType.INFORMATION, "出錯，請重試");
            information.showAndWait();
        }

    }

    public void refresh() {
        Random r = new Random();
        backGroundColor = backGroundColors[r.nextInt(backGroundColors.length)];
        BackgroundFill bg = new BackgroundFill(backGroundColor, null, null);
        getPane().setBackground(new Background(bg));
        List<Label> labelList = LabelCreator.getLabels(ControllerInfoNode.wordFreqList, ControllerInfoNode.circles);
        getCloudPane().getChildren().clear();
        getCloudPane().getChildren().addAll(labelList);
    }

    public void showDetail() {
        Pane p = ControllerInfoNode.controllers[3].getPane();
        FilteredList lvs = p.getChildren().filtered(e -> e instanceof ListView);
        ListView lv = (ListView) lvs.get(0);
        lv.getItems().clear();
        lv.getItems().addAll(ControllerInfoNode.wordFreqList);
        ControllerInfoNode.stage.setScene(ControllerInfoNode.controllers[3].getScene());
    }

    public static Color[] backGroundColors = {
            Color.BLACK,
            Color.WHITE,
            Color.rgb(255,255, 200),
            Color.rgb(200,255, 255),
            Color.rgb(255,200, 255),
            Color.rgb(230, 230, 255),
            Color.rgb(230, 255, 230),
            Color.rgb(255, 230, 230),
            Color.rgb(30, 0, 0),
            Color.rgb(0, 30, 0),
            Color.rgb(0, 0, 30),
            Color.rgb(20, 10, 20),
            Color.rgb(20, 20, 10),
            Color.rgb(10, 20, 20),
    };
}
