package rubide;

import java.io.File;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

public class FileTab extends Tab {
	private RubideFile heldFile;
	
	public FileTab(File f) {
		heldFile = new RubideFile(f);
		setText(heldFile.getFileName());
		
		TextArea ta = new TextArea();
		ta.setText(heldFile.read());
		
		setContent(ta);
	}
	
	public void save() {
		TextArea ta = (TextArea) this.getContent();
		heldFile.save(ta.getText());
	}
}
