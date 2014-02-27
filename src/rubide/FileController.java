package rubide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.control.TextArea;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;

public class FileController extends TextArea {
   private ArrayList<RubideFile> edittingFiles;
   private int currFile;
   
   public FileController() {
	  edittingFiles = new ArrayList<RubideFile>();
	   
	   
      setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
                
                currFile = 0;
            }
        });
   
      setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    for (File file:db.getFiles()) {
                        addFile(file);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
   }
   
   public int[] openFiles() {
      // TODO: add support for multiple files
      FileChooser chooser = new FileChooser();
      int last = -1;
      List<File> files = chooser.showOpenMultipleDialog(getScene().getWindow());
      for (File file : files)
      {
    	  if (file != null)
    		  addFile(file);
    	  last++;
      }
      
      if (last > -1)
    	  setFile(last);
      
      return new int[] { files.size(), edittingFiles.size() - files.size() };
   }
   
   public void addFile(File f) { 
      edittingFiles.add(new RubideFile(f)); 
   }
   
   public RubideFile getFile(int index) { return index >= 0 && index < edittingFiles.size() ? edittingFiles.get(index) : null; }
   
   public RubideFile getCurrentFile() { return edittingFiles.size() >= 0 ?  edittingFiles.get(currFile) : null; }
   
   public void saveFile() { getCurrentFile().save(getText()); }
   
   public void setFile(int index) {
	   if (edittingFiles.size() == 0)
		   setText("");
	   else if (index >= 0 && index < edittingFiles.size())
	   {
		   currFile = index;
		   setText(getCurrentFile().read());
	   }
   }
}