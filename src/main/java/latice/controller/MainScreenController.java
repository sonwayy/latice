package latice.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import latice.application.LaticeApplicationWindow;

public class MainScreenController extends LaticeApplicationWindow{
	@FXML
	private Rectangle playButton;
	@FXML
	private Rectangle rulesButton;
	@FXML
	private Rectangle exitButton;

	// Event Listener on Rectangle[#playButton].onMouseClicked
	@FXML
	public void playButtonClicked(MouseEvent event) {
		System.out.println("playButtonClicked");
		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		StackPane root = getRootLayout();
		Scene scene = new Scene(root, 1280, 720);
		stage.setScene(scene);
	}
	// Event Listener on Rectangle[#rulesButton].onMouseClicked
	@FXML
	public void rulesButtonClicked(MouseEvent event) {
		System.out.println("rulesButtonClicked");
	}
	// Event Listener on Rectangle[#exitButton].onMouseClicked
	@FXML
	public void exitButtonClicked(MouseEvent event) {
		System.out.println("exitButtonClicked");
		Platform.exit();
	}
}
