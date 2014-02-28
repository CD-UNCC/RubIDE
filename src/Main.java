import rubide.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox layout = new HBox();
        
        VBox buttons = new VBox();
        
        
        Button save = new Button("Save");
        Button open = new Button("Open");
        
        buttons.getChildren().addAll(save, open);
        
        final FileTabPane files = new FileTabPane();
        
        layout.getChildren().addAll(files, buttons);
      
        Scene scene = new Scene(layout, 551, 400);
        
        save.setOnAction(new EventHandler<ActionEvent>() {
         @Override
            public void handle(ActionEvent e) {
        	 	files.save();
            }
        });
        
        open.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {
               files.openFiles();
            }        	
        });
        
        // Dropping over surface
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}