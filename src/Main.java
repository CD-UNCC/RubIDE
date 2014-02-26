import rubide.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcBuilder;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
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
        
        
        final FileController txt = new FileController();
        txt.setWrapText(false);
        
        layout.getChildren().addAll(txt, buttons);
      
        Scene scene = new Scene(layout, 551, 400);
        
        save.setOnAction(new EventHandler<ActionEvent>() {
         @Override
            public void handle(ActionEvent e) {
               txt.saveFile();
            }
        });
        
        open.setOnAction(new EventHandler<ActionEvent>() {
         @Override
            public void handle(ActionEvent e) {
               txt.openFiles();
            }
        });
        
        // Dropping over surface
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}