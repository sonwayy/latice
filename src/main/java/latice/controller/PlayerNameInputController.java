package latice.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import latice.application.LaticeApplicationWindow;

public class PlayerNameInputController {
	@FXML
	private TextField nomJoueur1;
	@FXML
	private TextField nomJoueur2;
	@FXML
	public Button btnValid;
	public static boolean btnClicked = false;
	MainScreenController mainScreenController = new MainScreenController();
	LaticeApplicationWindow laticeApplicationWindow = new LaticeApplicationWindow();
	
	@FXML
	public void validBtnClicked(MouseEvent event) {
		System.out.println("valid Button Clicked");
		Stage nameInputStage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		//setting player names
		String name1 = nomJoueur1.getText();
		String name2 = nomJoueur2.getText();
		laticeApplicationWindow.setNamePlayer1(name1);
		laticeApplicationWindow.setNamePlayer2(name2);
		btnClicked = true;
		nameInputStage.close();
		laticeApplicationWindow.playerNamesEntered();
		mainScreenController.startGameInstruction();
	}

}
