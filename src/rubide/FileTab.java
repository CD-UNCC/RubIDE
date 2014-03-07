package rubide;

import java.io.File;

import codemirror.CodeEditor;
import javafx.scene.control.Tab;

public class FileTab extends Tab {
	final CodeEditor editor;
	private RubideFile heldFile;
	
	public FileTab(RubideFile f) {
		heldFile = f;
		
		setText(f.getFileName());
		
		String text = (f.isNewFile() ? "" : f.read());
		
		editor = new CodeEditor(text, f.getFileExtension());
		
		setContent(editor);
	}
	
	public FileTab(File f) {
		this(new RubideFile(f));
	}
	
	public FileTab() {
		this(new RubideFile());
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
		
		heldFile.save(getCode());
		editor.setCode(heldFile.read(), heldFile.getFileExtension());
	}	
	public boolean isEmpty() { return heldFile.isNewFile() && getCode().length() == 0; }
	public RubideFile getFile() { return heldFile; }
	public String getCode() { return editor.getCodeAndSnapshot(); }
}
