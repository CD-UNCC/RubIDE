import rubide.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
        
        
        final FileController fileControl = new FileController();
        fileControl.setWrapText(false);
        
        final TabPane tabs = new TabPane();
        tabs.setSide(Side.LEFT);
        
        layout.getChildren().addAll(tabs, fileControl, buttons);
      
        Scene scene = new Scene(layout, 551, 400);
        
        save.setOnAction(new EventHandler<ActionEvent>() {
         @Override
            public void handle(ActionEvent e) {
        	 	fileControl.saveFile();
            }
        });
        
        open.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {
               int[] fileData = fileControl.openFiles();
               for (int i = fileData[1]; i < fileData[1] + fileData[0]; i++)
               {
            	   Tab newTab = new Tab();
            	   newTab.setText(fileControl.getFile(i).getFileName());
            	   tabs.getTabs().add(newTab);
               }
            }
        });
        
        tabs.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        	public void changed(ObservableValue<? extends Number> ov, Number old, Number cur) {
        		fileControl.setFile(cur.intValue());
        	}
        });
        
        // Dropping over surface
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}