package rubide;

import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.control.TextArea;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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
                        RubideFile f = new RubideFile(file);
                        setFile(f);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
   }
   
   public void setFile(RubideFile f) { 
      currentFile = f; 
      setText(f.read());
   }
   public void saveFile() { currentFile.save(getText()); }
}