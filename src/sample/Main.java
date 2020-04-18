package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       UIlogic uIlogic = new UIlogic();
       Scene scene = new Scene(uIlogic, 640,480);
       primaryStage.setScene(scene);
       primaryStage.show();

       uIlogic.draw();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
