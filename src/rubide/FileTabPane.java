package rubide;

import java.io.File;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;

public class FileTabPane extends TabPane {
	
	static FileChooser CHOOSER = new FileChooser();
	
	public FileTabPane() {
		super();
		
		setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {
                Dragboard db = e.getDragboard();
                if (db.hasFiles())
                    e.acceptTransferModes(TransferMode.COPY);
                else
                    e.consume();
            }
        });
		
		setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {
                Dragboard db = e.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    for (File f : db.getFiles())
                        open(f);
                }
                e.setDropCompleted(success);
                e.consume();
            }
        });
	}
	
	public void open(File f) {
		FileTab t = new FileTab(f);
		getTabs().add(t);
	}
	
	public void openFiles() {
		List<File> files = CHOOSER.showOpenMultipleDialog(getScene().getWindow());
		 
		for (File f : files)
			open(f);
		 
		getSelectionModel().selectLast();
	}
	
	public void save() {
		FileTab ft = (FileTab) getSelectionModel().selectedItemProperty().get();
		ft.save();
	}
}
