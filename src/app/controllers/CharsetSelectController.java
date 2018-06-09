package app.controllers;

import app.LabelCreator;
import fileutils.FileContent;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import wordcloud.StringFreqType;
import wordcloud.WordFreq;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CharsetSelectController implements Controllable {

    @FXML private ListView<Text> listView;
    @FXML private AnchorPane pane;
    @FXML private Label text;
    private Scene scene;
    private String testCtnt;
    private File file;
    private FileContent fileContent;
    private String charset;

    public void displayHead(Event event) {
        charset = listView.getSelectionModel().getSelectedItem().getText();
        fileContent = new FileContent();
        fileContent.readFile(ControllerInfoNode.testFilePath, charset);
        testCtnt = fileContent.getContent();
        StringBuilder sb = new StringBuilder(testCtnt);
        for (int i = 0; i < testCtnt.length(); i += 20)
            sb.insert(i, "\n");
        text.setText(sb.toString());

    }

    public void displayCloud() {
        Pane cloudPane = ControllerInfoNode.controllers[2].getPane();
        FilteredList<Circle> circles_filtered = ((ObservableList)
                cloudPane.getChildren()).filtered(e -> e instanceof Circle);
        List<Circle> circles = new LinkedList<>();
        circles_filtered.forEach(circles::add);
        Collections.shuffle(circles);
        fileContent.readFile(ControllerInfoNode.file.getPath(), charset);
        WordFreq fq = new WordFreq(fileContent.getContent());
        List<StringFreqType> wordFreqList = fq.getStringFreqList();
        ControllerInfoNode.wordFreqList = wordFreqList;
        ControllerInfoNode.circles = circles;
        List<Label> labelList = LabelCreator.getLabels(wordFreqList, circles);
        ControllerInfoNode.controllers[2].getPane().getChildren().addAll(labelList);
        ControllerInfoNode.stage.setScene(ControllerInfoNode.controllers[2].getScene());
    }


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
}
