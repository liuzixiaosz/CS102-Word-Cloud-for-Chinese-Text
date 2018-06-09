package app.controllers;

import fileutils.FileCharsetDetector;
import fileutils.FileHeadPiece;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class StartMenuController implements Controllable {

    private final FileChooser FILE_CHOOSER = new FileChooser();
    @FXML private AnchorPane startPane;
    @FXML private Button chooseFile;
    @FXML private Button start;
    @FXML private Label fileName;
    private Scene scene;
    private Stage stage;
    private String[] charsets;
    private String testFilePath;

    public File getFile() {
        return file;
    }

    private File file;

    @Override
    public Scene getScene() {
        if (scene == null) {
            scene = new Scene(startPane);
        }
        return scene;
    }

    @Override
    public Pane getPane() {
        return startPane;
    }

    public void chooseFile(Event e) {
        File file = FILE_CHOOSER.showOpenDialog(getScene().getWindow());
        if (file.canRead()) {
            this.file = file;
        }
        fileName.setText(file.getName());
        fileName.setFont(new Font( "SongTi TC", 10));
    }

    public void startPlay(Event event) {
        if (this.file != null) {
            ControllerInfoNode.file = file;
            String path;
            try {
                path = file.getPath();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            String dir = getClass().getResource("").toString().replace("file:", "").replace("%20", " ");
            testFilePath = dir + "test.txt";
            FileHeadPiece.extractTo(path, testFilePath);
            charsets = FileCharsetDetector.getProbableCharset(testFilePath);
            ListView<Text> lv = (ListView<Text>) ControllerInfoNode.controllers[1]
                    .getPane().getChildren().filtered(e -> e instanceof ListView)
                    .get(0);
            for (String c : charsets) {
                Text t = new Text(c);
                t.setFill(Paint.valueOf("#00ff00"));
                lv.getItems().add(t);
            }
            ControllerInfoNode.stage.setScene(ControllerInfoNode.controllers[1].getScene());
            ControllerInfoNode.testFilePath = testFilePath;
        } else {
            Alert information = new Alert(Alert.AlertType.INFORMATION, "無有效文件選擇");
            information.showAndWait();
        }
    }


}
