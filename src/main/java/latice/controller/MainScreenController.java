package latice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import latice.application.LaticeApplicationWindow;
import latice.model.Color;
import latice.model.Player;
import latice.model.Shape;
import latice.model.Tile;
import latice.model.console.Deck;
import latice.model.console.Rack;
import latice.model.console.Score;
import latice.model.window.PlayerFX;

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
	public static Stage mainStage;
	private static StackPane parentStackPaneStock;
	
	
	public Player player1;
	public Player player2;
	public HBox players;

	// Event Listener on Rectangle[#playButton].onMouseClicked
	@FXML
	public void playButtonClicked(MouseEvent event) throws IOException {
		System.out.println(parentStackPane);
		parentStackPaneStock = parentStackPane;
		System.out.println("playButtonClicked");
		mainStage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		System.out.println(mainStage);
		if (!PlayerNameInputController.btnClicked) {
			playerNamesInput(event);
			
		}
	}
	
	// Event Listener on Rectangle[#rulesButton].onMouseClicked
	@FXML
	public void rulesButtonClicked(MouseEvent event) {
		System.out.println("rulesButtonClicked");
		Stage primaryStage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		Label secondLabel = new Label("Règles");
		//TODO règles à saisir
		StackPane secondaryLayout = new StackPane();
		secondaryLayout.getChildren().add(secondLabel);
		
		Scene secondScene = new Scene(secondaryLayout, 230, 100);
		
		// New window (Stage)
		Stage newWindow = new Stage();
		newWindow.setTitle("Règles du jeu Latice");
		newWindow.setScene(secondScene);
		
		// Specifies the modality for new window
		newWindow.initModality(Modality.WINDOW_MODAL);
		
		// Specifies the owner window
		newWindow.initOwner(primaryStage);
		
		// Set position of window
		newWindow.setX(primaryStage.getX() + 300);
		newWindow.setY(primaryStage.getY() + 175);
		
		newWindow.show();
	}
	// Event Listener on Rectangle[#exitButton].onMouseClicked
	@FXML
	public void exitButtonClicked(MouseEvent event) {
		System.out.println("exitButtonClicked");
		Platform.exit();
	}
	
	public void playerNamesInput(MouseEvent event) throws IOException {
		Parent loader = FXMLLoader.load(getClass().getResource("../view/PlayerNameInput.fxml"));
		Scene nameInputScene = new Scene(loader, 600, 300);
		Stage primaryStage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		
		// New window (Stage)
		Stage nameInputStage = new Stage();
		nameInputStage.setTitle("Names");
		nameInputStage.setScene(nameInputScene);
		
		// Specifies the modality for new window
		nameInputStage.initModality(Modality.WINDOW_MODAL);
		
		// Specifies the owner window
		nameInputStage.initOwner(primaryStage);
		
		// Set position of window
		nameInputStage.setX(primaryStage.getX() + 300);
		nameInputStage.setY(primaryStage.getY() + 175);
		
		nameInputStage.show();
	}
	
	public void startGameInstruction(Boolean isRestart) {
		
		startGame(mainStage, parentStackPaneStock, player1, player2, menuBorderPane, isRestart);
        
	}
	
	/*public void startGame(Stage stage) {
		parentStackPane = parentStackPaneStock;
		StackPane root = getRootLayout();
		root.translateYProperty().set(stage.getHeight());
		System.out.println(parentStackPane);
		System.out.println(parentStackPaneStock);
		parentStackPane.getChildren().add(root);
		
		players = PlayerFX.displayPlayers(parentStackPane,player1, player2);
		root.getChildren().addAll(players,LaticeApplicationWindow.borderPane);
		
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
        
        
        
        //Règles
	}*/

	public StackPane getParentStackPane() {
		System.out.println(parentStackPane);
		return parentStackPane;
	}

	public void setParentStackPane(StackPane parentStackPane) {
		this.parentStackPane = parentStackPane;
	}
	
	
	public Player instanciatePlayer(String namePlayer) {
		//###################### Instantiating of players ######################///
		//create the list of all tiles
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				//System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		//setting decks for the 2 players
		Deck deck = new Deck(listOfTile);
		Deck deck2 = new Deck(listOfTile);
		
		Player player = new Player(namePlayer, new Score(), deck, new Rack(deck));
		return player;
	}
	
	
}
