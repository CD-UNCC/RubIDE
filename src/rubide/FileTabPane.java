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
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;

public class FileTabPane extends TabPane {
	
	public static FileChooser CHOOSER = new FileChooser();
	
	private Tab addTab;
	
	public FileTabPane() {
		super();
		
		setSide(Side.LEFT);
		
		addTab(0, new FileTab());
		
		addTab = new Tab();
		addTab.closableProperty().set(false);
		addTab.setText("+");
		// addTab.setTooltip();
		
		addTab(1, addTab);
		
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
					addTab(new FileTab());
					selectLastTab();
				}
			}
		});
	}
	
	public void open(File f) {				
		FileTab t = new FileTab(f);
		addTab(t);
	}
	
	public void openFiles(List<File> files) {
		if (files != null) {
			for (File f : files)
				open(f);
			
			if (selectedTab().isEmpty())
				close(selectedTab());
			 
			selectLastTab();
		}
	}
	
	public void openFiles() {
		openFiles(CHOOSER.showOpenMultipleDialog(getScene().getWindow()));
	}
	
	public void close(Tab t) {
		EventHandler<Event> e = t.getOnClosed();
		if (e != null)
			e.handle(null);
		getTabs().remove(t);
		
		if (tabCount() == 1)
			addTab(new FileTab());
	}
	
	public void save() {
		selectedTab().save();
	}
	
	public void addTab(Tab t) { getTabs().add(tabCount() - 1, t); }
	public void addTab(int index, Tab t) { getTabs().add(index, t); }	
	public int tabCount() { return getTabs().size(); }
	
	public void selectLastTab() { getSelectionModel().select(tabCount() - 2); }
	
	public FileTab selectedTab() { return (FileTab) getSelectionModel().getSelectedItem(); }
}
