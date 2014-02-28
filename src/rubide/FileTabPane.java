package rubide;

import java.io.File;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;

public class FileTabPane extends TabPane {
	
	public static FileChooser CHOOSER = new FileChooser();
	
	public FileTabPane() {
		super();
		
		getTabs().add(new FileTab());
		
		// Set Events
		
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
                    openFiles(db.getFiles());
                }
                e.setDropCompleted(success);
                e.consume();
            }
        });
		
		getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			public void changed(ObservableValue<? extends Tab> ov, Tab newTab, Tab oldTab) {
				if (getTabs().size() == 0)
					getTabs().add(new FileTab());
				if (getTabs().size() > 1) {
					FileTab firstTab = (FileTab) getTabs().get(0);
					if (firstTab.getFile().isNewFile()) 
						if (((TextArea) firstTab.getContent()).getText().length() == 0)
							close(firstTab);						
				}
			}
		});
	}
	
	public void tabClosed() {
		if (getTabs().size() == 0) {
			getTabs().add(new FileTab());
		}
	}
	
	public void open(File f) {
		FileTab t = new FileTab(f);
		getTabs().add(t);
	}
	
	public void openFiles() {
		openFiles(CHOOSER.showOpenMultipleDialog(getScene().getWindow()));
	}
	
	public void openFiles(List<File> files) {		 
		for (File f : files)
			open(f);
		 
		getSelectionModel().selectLast();
	}
	
	public void close(Tab t) {
		EventHandler<Event> e = t.getOnClosed();
		if (e != null)
			e.handle(null);
		System.out.println(getTabs().remove(t));
	}
	
	public void save() {
		FileTab ft = (FileTab) getSelectionModel().selectedItemProperty().get();
		ft.save();
	}
}
