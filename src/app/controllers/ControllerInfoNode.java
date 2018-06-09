package app.controllers;

import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import wordcloud.StringFreqType;

import java.io.File;
import java.util.List;

public class ControllerInfoNode {
    public static File file;
    public static String testFilePath;
    public static Controllable[] controllers;
    public static Stage stage;
    public static String content;
    public static List<StringFreqType> wordFreqList;
    public static List<Circle> circles;
}
