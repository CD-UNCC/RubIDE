package rubide;

import java.io.File;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

public class FileTab extends Tab {
	private RubideFile heldFile;
	
	public FileTab(File f) {
		heldFile = new RubideFile(f);
		LoadTab(heldFile.getFileName(), heldFile.read());
	}
	
	public FileTab() {
		heldFile = new RubideFile();
		LoadTab("New file", "");
	}
	
	void LoadTab(String name, String text) {
		setText(name);
		
		TextArea ta = new TextArea();
		ta.setText(text);
		
		setContent(ta);
	}
	
	public void save() {
		if (heldFile.isNewFile()) {
			File f = FileTabPane.CHOOSER.showSaveDialog(getTabPane().getScene().getWindow());
			if (f != null) {
				heldFile = new RubideFile(f);
				setText(heldFile.getFileName());
			}
			else
				return;
		}
		
		heldFile.save(getContentText());
	}	
	
	public boolean isEmpty() { return heldFile.isNewFile() && getContentText().length() == 0; }
	public RubideFile getFile() { return heldFile; }
	public String getContentText() { return ((TextArea) getContent()).getText(); }
}
