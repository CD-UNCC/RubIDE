package rubide;

import javafx.scene.control.TextArea;
import java.util.ArrayList;

public class FileController extends TextArea {
   private RubideFile currentFile;
   
   public void setFile(RubideFile f) { 
      currentFile = f; 
      setText(f.read());
   }
   public void saveFile() { currentFile.save(getText()); }
}