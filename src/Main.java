import rubide.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	VBox vLayout = new VBox();
        HBox hLayout = new HBox();
        
        hLayout.setPrefHeight(380);
        
        final FileTabPane files = new FileTabPane();
        
        ButtonLayout buttons = new ButtonLayout(files);
        
        hLayout.getChildren().addAll(files, buttons);
        
        MenuLayout menus = new MenuLayout(files);
        
        vLayout.getChildren().addAll(menus, hLayout);
      
        Scene scene = new Scene(vLayout, 551, 400);

        SceneEvents.KeyPressed(scene, files);

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("RubIDE");
        primaryStage.show();
        
    }
}