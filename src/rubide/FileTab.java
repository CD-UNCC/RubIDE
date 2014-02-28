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
		TextArea ta = (TextArea) this.getContent();
		if (heldFile.isNewFile()) {
			File f = FileTabPane.CHOOSER.showSaveDialog(getTabPane().getScene().getWindow());
			if (f != null) {
				heldFile = new RubideFile(f);
				setText(heldFile.getFileName());
			}
			else
				return;
		}
		
		heldFile.save(ta.getText());
	}
	
	public RubideFile getFile() { return heldFile; }
}
