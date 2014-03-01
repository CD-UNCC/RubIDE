package rubide;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SceneEvents {
	public static void KeyPressed(Scene s, final FileTabPane files) {
		s.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if (e.isControlDown() && e.getCode() == KeyCode.S)
					files.save();
			}
		});
	}
}
