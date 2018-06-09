package app;

import app.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {

    private StartMenuController startMenuController;
    private CharsetSelectController charsetSelectController;
    private WordCloudController wordCloudController;
    private DetailsController detailsController;
    private FXMLLoader startMenuLoader;
    private FXMLLoader charsetSelectLoader;
    private FXMLLoader wordCloudLoader;
    private FXMLLoader detailsLoader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("中文字雲");
        ControllerInfoNode.stage = primaryStage;
        startMenuLoader = new FXMLLoader(getClass().getResource("startMenu.fxml"));
        charsetSelectLoader = new FXMLLoader(getClass().getResource("charsetSelect.fxml"));
        wordCloudLoader = new FXMLLoader(getClass().getResource("wordCloud.fxml"));
        detailsLoader = new FXMLLoader(getClass().getResource("details.fxml"));
        startMenuLoader.load();
        charsetSelectLoader.load();
        wordCloudLoader.load();
        detailsLoader.load();
        startMenuController = startMenuLoader.getController();
        charsetSelectController = charsetSelectLoader.getController();
        wordCloudController = wordCloudLoader.getController();
        detailsController = detailsLoader.getController();


        ControllerInfoNode.controllers = new Controllable[]{
                startMenuController,
                charsetSelectController,
                wordCloudController,
                detailsController
        };
        primaryStage.setScene(startMenuController.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
