package rubide;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ButtonLayout extends VBox {
	
	final FileTabPane files;
	
	public ButtonLayout(FileTabPane filePane) {
		this.files = filePane;
		
        Button save = new Button("Save");
        Button open = new Button("Open");
        
        addButtons(save, open);
		
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
	}
	
	public void addButtons(Button... btns) {
		this.getChildren().addAll(btns);
	}
}
