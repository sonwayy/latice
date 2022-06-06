package latice.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import latice.application.LaticeApplicationWindow;

public class GameFinishedScreenController implements Initializable{
	@FXML
	private Button replayBtn;
	@FXML
	private Button quitBtn;
	@FXML
	private Button testBtn;
	@FXML
	private Label nameWinner;
	
	public static Label staticNameWinner;
	public static Label staticNameLooser = new Label();
	
	// Event Listener on Button[#replayBtn].onMouseClicked
	@FXML
	public void replayBtnClicked(MouseEvent event) {
		MainScreenController MSC = new MainScreenController();
		MSC.player1 = MSC.instanciatePlayer(staticNameWinner.getText());
		MSC.player2 = MSC.instanciatePlayer(staticNameLooser.getText());
		MSC.startGameInstruction(true);
		System.out.println("replayBtnClicked");
	}
	// Event Listener on Button[#quitBtn].onMouseClicked
	@FXML
	public void quitBtnClicked(MouseEvent event) {
		System.out.println("quitBtnClicked");
		Platform.exit();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		staticNameWinner = nameWinner;
		
	}

}