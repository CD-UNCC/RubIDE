package rubide;

import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.control.TextArea;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import java.util.ArrayList;

public class FileController extends TextArea {
   private RubideFile currentFile;
   
   public FileController() {
      setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });
   
      setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    String fileName = null;
                    for (File file:db.getFiles()) {
                        setFile(file);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
   }
   
   public void openFiles() {
      // TODO: add support for multiple files
      FileChooser chooser = new FileChooser();
      
      File file = chooser.showOpenDialog(getScene().getWindow());
      if (file != null)
         setFile(file);
   }
   
   public void setFile(File f) { 
      currentFile = new RubideFile(f); 
      setText(currentFile.read());
   }
   public void saveFile() { currentFile.save(getText()); }
}