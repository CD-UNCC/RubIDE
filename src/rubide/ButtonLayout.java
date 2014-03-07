package rubide;
//import java.io.Console;
import java.io.IOException;
import org.jruby.embed.ScriptingContainer;

//import org.junit.Test;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ButtonLayout extends VBox {
	
	final FileTabPane files;
	private Runtime rt = Runtime.getRuntime();
	public ButtonLayout(FileTabPane filePane) {
		this.files = filePane;
		
        Button save = new Button("Save");
        Button open = new Button("Open");
        Button run = new Button("Run");
        
        addButtons(save, open, run);
        
        run.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {
        		
        		try {
        			//new ScriptingContainer().runScriptlet(files.selectedTab().getCode());
        			if(OpSys.isWindows())
        				rt.exec("cmd.exe /c start cmd.exe /k ruby "+files.selectedTab().getFile().getAbsolutePath());
				} catch (IOException e1) {
					// TODO OS verification and extension management
					e1.printStackTrace();
				}
            }
        });
		
        save.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {
        		files.save();
            }
        });
        
        open.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {
        		files.openFiles();
            }
        });
	}
	
	public void addButtons(Button... btns) {
		this.getChildren().addAll(btns);
	}
}
