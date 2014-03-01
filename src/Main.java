import rubide.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox layout = new HBox();             
        
        final FileTabPane files = new FileTabPane();
        
        ButtonLayout buttons = new ButtonLayout(files);
        
        layout.getChildren().addAll(files, buttons);
      
        Scene scene = new Scene(layout, 551, 400);       

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}