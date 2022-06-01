package latice.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
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
		
		
		btnClicked = true;
		nameInputStage.close();
		playerNamesEntered();
		laticeApplicationWindow.player1 = mainScreenController.instanciatePlayer(name1);
		laticeApplicationWindow.player2 = mainScreenController.instanciatePlayer(name2);
		mainScreenController.startGameInstruction();
	}

	public TextField getNomJoueur1() {
		return nomJoueur1;
	}

	public void setNomJoueur1(TextField nomJoueur1) {
		this.nomJoueur1 = nomJoueur1;
	}

	public TextField getNomJoueur2() {
		return nomJoueur2;
	}

	public void setNomJoueur2(TextField nomJoueur2) {
		this.nomJoueur2 = nomJoueur2;
	}
	
	public void playerNamesEntered() {
		System.out.println("entered playNamesEntered()" + nomJoueur1.getText() + " VS " + nomJoueur2.getText());
		
		MainScreenController MSC = new MainScreenController();
		StackPane parentStackPane = MSC.getParentStackPane();
		MSC.setParentStackPane(parentStackPane);

	}
	
}
