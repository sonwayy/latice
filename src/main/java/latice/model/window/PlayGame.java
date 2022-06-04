package latice.model.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import latice.model.Color;
import latice.model.Player;
import latice.model.Shape;
import latice.model.Tile;
import latice.model.console.Deck;
import latice.model.console.Rack;

public class PlayGame {
	
	Image image = new Image("backgroundLatice.png");
	ImageView imageView = new ImageView(image);
	
	Tile blueBird = new Tile(Color.NAVYBLUE, Shape.BIRD);
	Tile greenLeaf = new Tile(Color.GREEN, Shape.FEATHER);
	Tile redFlower = new Tile(Color.RED, Shape.FLOWER);
	
	ArrayList<Tile> listRackTile;
	ArrayList<Image> listTileImage;
	ArrayList<Tile> listOfTile = new ArrayList<Tile>();
	Map<Rectangle, Tile> assocRectangleTile = new HashMap<Rectangle, Tile>();
	static StackPane rootLayout;
	private Label namePlayer1 = new Label("Anonyme");
	private Label namePlayer2 = new Label("Anonyme"); 

	public static int indexTileClicked;	
	
	
	//settings players
	public Player player1;
	public Player player2;
	
	//root layout
	BorderPane borderPane = new BorderPane();
	
	//StackPane for background image + BorderPane root onto it
	StackPane root = new StackPane();
	
	static Stage primaryStageCopy;
	
	
	int confirmBtnClickedCount;
	
	public Stage playGame(Stage StageToGame) {
		//--------------------------------------------------------------------------------------
		//Title
		Text title = new Text("Latice");
		title.setFont(new Font(30));
		borderPane.setTop(title);
		borderPane.setAlignment(title, Pos.CENTER);
		//--------------------------------------------------------------------------------------
		//Image
		Pane pane = new Pane();
		BackgroundImage myBG= new BackgroundImage(image,
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBG));
		
		//--------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------
		//###################### creating all rectangles and DragnDrop ######################//
		RectangleFX rectFX = new RectangleFX();
		rectFX.createRectangle(borderPane, pane);
		rectFX.dragnDropOnAllRectangles(player1, indexTileClicked, confirmBtnClickedCount);
		rectFX.dragnDropOnAllRectangles(player2, indexTileClicked, confirmBtnClickedCount);
		//--------------------------------------------------------------------------------------
		
		borderPane.setCenter(pane);
		//--------------------------------------------------------------------------------------
		
		
		//--------------------------------------------------------------------------------------
		//Rack
		HBox rackBox = new HBox();
		
		rackBox.setSpacing(10);
        rackBox.setPadding(new Insets(15,20, 10,10));

		
		//Creating tiles
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				
				listOfTile.add(tile);
				
			}
		}
		
		System.out.println("-----------------");
		System.out.println("Notre Deck :");
		Deck deck = new Deck(listOfTile);
		System.out.println("-----------------");
		Rack rack = new Rack(deck);
		System.out.println("-----------------");
		//deck.displayListTile();
		
		
		
		//Confirm Button
		Image checkMark = new Image("checkMark.png");
		ImageView checkMarkView = new ImageView(checkMark);
		Button confirmButton = new Button("Confirm", checkMarkView);
		
		confirmButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				confirmBtnClickedCount++;
				System.out.println("confirmed placement");
				
			}
			
		});
		
		//With Image
		Rack rack2 = new Rack(deck);
		HBox rackImage = rack2.createTileImage();
		rackImage.getChildren().add(confirmButton);
		rackImage.setMargin(rackImage.getChildren().get(4), new Insets(0,150,0,0));
		borderPane.setBottom(rackImage);
		
		//Adding lists to Arraylists
		listRackTile = rack2.getListRackTile();
		System.out.println(listRackTile);
		listTileImage = rack2.getRackTileImage();
		System.out.println("listTileImge : " + listTileImage);
		
		
		//------------------------------------------------------------------------
		
		
		//Setting OnDragDetected on tiles
		setDragnDropOnRack(rackImage);

		System.out.println((indexTileClicked));
		ImagePattern imagePattern = new ImagePattern(listTileImage.get(indexTileClicked));
		
		
		
		//rules / referee implementaion
		
		this.transition(namePlayer1, namePlayer2);
		//root.setLeft(namePlayer1);
		
		//###################### display name, score and deck of each player ######################//
		HBox players = new HBox();
		
		ArrayList<Player> allPlayers = new ArrayList<>();
		allPlayers.add(player1);
		allPlayers.add(player2);
		
		for (Player nameplayer : allPlayers ) {
			VBox player = new VBox();
			
			Text name = new Text();
			name.setFont(Font.font("Anonyme", FontWeight.BOLD, 20));
			name.setText("Anonyme");
			
			Text score = new Text();
			score.setText("Score : " + nameplayer.getScore());
			
			Text nbrOfTiles = new Text();
			nbrOfTiles.setText("Tuiles restantes : " + nameplayer.getNumberOfTilesRemaining());
			
			player.getChildren().addAll(name, score, nbrOfTiles);
			player.setSpacing(5);
			
			players.getChildren().add(player);
			players.setMargin(player, new Insets(50,0,0,55));
		}
		System.out.println("largeur : " + borderPane.getMaxWidth());
		players.setSpacing(850);
		
		//--------------------------------------------------------------------------------------
		setPrimaryStage(StageToGame);
		setRootLayout(root);
		root.getChildren().addAll(players, borderPane);
		
		return StageToGame;
		
	}
	
	public void transition(Label player1, Label player2) {
		
		borderPane.setLeft(player1);
		borderPane.setRight(player2);
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStageCopy = primaryStage;
	}
	
	public static void setRootLayout(StackPane root) {
		rootLayout = root;
	}
	
	private void setDragnDropOnRack(HBox rackImage) {
		for (int i=0; i<5; i++) {
			int index = i;
			rackImage.getChildren().get(index).setOnDragDetected(new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent arg0) {
					Dragboard dragboard = rackImage.getChildren().get(index).startDragAndDrop(TransferMode.ANY);
					ClipboardContent content = new ClipboardContent();
					dragboard.setDragView(listTileImage.get(index));
					content.putString("Hello !");
					indexTileClicked = index;
					//setIndexTileClicked(index);
					dragboard.setContent(content);
					arg0.consume();
					
				}
				
			});
			
			rackImage.getChildren().get(index).setOnDragDone(new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent arg0) {
					
				}
				
			});
		}
	}


}
