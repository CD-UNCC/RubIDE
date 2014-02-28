package rubide;

import java.io.File;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;

public class FileTabPane extends TabPane {
	
	public static FileChooser CHOOSER = new FileChooser();
	
	private Tab addTab;
	private boolean addingTab = false;
	
	public FileTabPane() {
		super();
		
		setSide(Side.LEFT);
		
		getTabs().add(new FileTab());
		
		addTab = new Tab();
		addTab.closableProperty().set(false);
		addTab.setText("+");
		// addTab.setTooltip();
		
		getTabs().add(addTab);
		
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
			public void changed(ObservableValue<? extends Tab> ov, Tab oldTab, Tab newTab) {
				if (newTab == addTab) {
					getTabs().add(getTabs().size() - 1, new FileTab());
					getSelectionModel().select(getTabs().size() - 2);
				}
			}
		});
	}
	
	public void open(File f) {				
		FileTab t = new FileTab(f);
		getTabs().add(getTabs().size() - 1, t);
	}
	
	public void openFiles() {
		openFiles(CHOOSER.showOpenMultipleDialog(getScene().getWindow()));
	}
	
	public void openFiles(List<File> files) {
		if (files != null) {
			for (File f : files)
				open(f);
			
			FileTab selected = (FileTab) getSelectionModel().getSelectedItem();
			if (selected.isEmpty())
				close(selected);
			 
			getSelectionModel().select(getTabs().size() - 2);
		}
	}
	
	public void close(Tab t) {
		EventHandler<Event> e = t.getOnClosed();
		if (e != null)
			e.handle(null);
		getTabs().remove(t);
		
		if (getTabs().size() == 1)
			getTabs().add(new FileTab());
	}
	
	public void save() {
		FileTab ft = (FileTab) getSelectionModel().selectedItemProperty().get();
		ft.save();
	}
}
