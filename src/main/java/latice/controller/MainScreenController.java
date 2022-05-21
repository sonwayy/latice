package latice.controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import latice.application.LaticeApplicationWindow;

public class MainScreenController extends LaticeApplicationWindow{
	@FXML
	private Rectangle playButton;
	@FXML
	private Rectangle rulesButton;
	@FXML
	private Rectangle exitButton;
	@FXML
	private StackPane parentStackPane;
	@FXML
	private BorderPane menuBorderPane;

	// Event Listener on Rectangle[#playButton].onMouseClicked
	@FXML
	public void playButtonClicked(MouseEvent event) {
		System.out.println("playButtonClicked");
		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		StackPane root = getRootLayout();
		root.translateYProperty().set(stage.getHeight());
		parentStackPane.getChildren().add(root);
		
		//parameters of the animation
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        
        //when the animation is finished we're removing the main screen
        timeline.setOnFinished(t -> {
            parentStackPane.getChildren().remove(menuBorderPane);
        });
        timeline.play();
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
