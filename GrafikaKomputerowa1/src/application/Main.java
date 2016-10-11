package application;

import controller.GkController;
import controller.Settings;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Group group = new Group();
    private Scene scene = new Scene(group, Settings.WINDOW_SIZE, Settings.WINDOW_SIZE);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Grafika Komputerowa 1");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        initSomeLogic();
    }

    private void initSomeLogic() {
        GkController gkController = new GkController(group, scene);
        gkController.init();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
