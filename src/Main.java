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
        
        Button save = new Button("Save");
        
        
        final FileController txt = new FileController();
        txt.setWrapText(false);
        txt.setText("Yo\ntest");
        
        layout.getChildren().addAll(txt, save);
      
        Scene scene = new Scene(layout, 551, 400);
        
        scene.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });
        
        save.setOnAction(new EventHandler<ActionEvent>() {
         @Override
            public void handle(ActionEvent e) {
               txt.saveFile();
            }
        });
        
        // Dropping over surface
        scene.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    String fileName = null;
                    for (File file:db.getFiles()) {
                        RubideFile f = new RubideFile(file);
                        txt.setFile(f);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}