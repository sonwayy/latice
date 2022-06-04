package latice.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class GameFinishedScreenController {
	@FXML
	private Button replayBtn;
	@FXML
	private Button quitBtn;
	@FXML
	public static Label nameWinner;

	// Event Listener on Button[#replayBtn].onMouseClicked
	@FXML
	public void replayBtnClicked(MouseEvent event) {
		System.out.println("replayBtnClicked");
	}
	// Event Listener on Button[#quitBtn].onMouseClicked
	@FXML
	public void quitBtnClicked(MouseEvent event) {
		System.out.println("quitBtnClicked");
	}
}
