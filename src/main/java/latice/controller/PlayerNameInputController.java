package latice.controller;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import latice.application.LaticeApplicationWindow;
import latice.model.Color;
import latice.model.Constant;
import latice.model.Player;
import latice.model.Shape;
import latice.model.Tile;
import latice.model.console.Deck;
import latice.model.console.Rack;
import latice.model.console.Score;
import latice.model.window.PlayerFX;

public class PlayerNameInputController {
	@FXML
	public TextField nomJoueur1;
	@FXML
	public TextField nomJoueur2;
	@FXML
	public Button btnValid;
	@FXML
	public Label label1;
	@FXML
	public Label label2;
	
	public static boolean btnClicked = false;
	MainScreenController mainScreenController = new MainScreenController();
	LaticeApplicationWindow laticeApplicationWindow = new LaticeApplicationWindow();
	
	public static String namePlayer1;
	public static String namePlayer2;
	
	
	@FXML
	public void validBtnClicked(MouseEvent event) {
		System.out.println("valid Button Clicked");
		
		//Verifying if player's names are correct
		if(nomJoueur1.getText().length() < 3 || nomJoueur1.getText().length() > 16) {
			label1.setVisible(true);
		}
		if(nomJoueur2.getText().length() < 3 || nomJoueur2.getText().length() > 16) {
			label2.setVisible(true);
		}
		if ((nomJoueur1.getText().length() >= 3 && nomJoueur1.getText().length() <= 16 ) && (nomJoueur2.getText().length() >= 3 && nomJoueur2.getText().length() <= 16)) {
			
			Stage nameInputStage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
			//setting player names
			String name1 = nomJoueur1.getText();
			String name2 = nomJoueur2.getText();
			System.out.println("nom des joueurs :");
			System.out.println(nomJoueur1.getText());
			System.out.println(nomJoueur2.getText());
			namePlayer1 = name1;
			namePlayer2 = name2;
			
			btnClicked = true;
			nameInputStage.close();
			playerNamesEntered();
			mainScreenController.player1 = mainScreenController.instanciatePlayer(name1);
			mainScreenController.player2 = mainScreenController.instanciatePlayer(name2);
			
			//Starting game
			mainScreenController.startGameInstruction(false);
		}
	}

	public String getNomJoueur1() {
		return nomJoueur1.getText();
	}

	public void setNomJoueur1(TextField nomJoueur1) {
		this.nomJoueur1 = nomJoueur1;
	}

	public String getNomJoueur2() {
		return nomJoueur2.getText();
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