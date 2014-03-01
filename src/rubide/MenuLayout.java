package rubide;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuLayout extends MenuBar {
	final FileTabPane files;
	
	
	public MenuLayout(FileTabPane fileSystem) {
		files = fileSystem;
		
		// FILE
		Menu file = new Menu("File");
			MenuItem newFile = new MenuItem("New");
			newFile.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					files.addTab(new FileTab());
				}
			});
			
			MenuItem open = new MenuItem("Open");
			open.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					files.openFiles();
				}
			});
			
			MenuItem save = new MenuItem("Save");
			save.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					files.save();
				}
			});
			
			file.getItems().addAll(newFile, open, save);
			
		this.getMenus().addAll(file);
	}
}
