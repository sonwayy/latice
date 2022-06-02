package latice.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import latice.controller.MainScreenController;
import latice.controller.PlayerNameInputController;
import latice.model.Color;
import latice.model.Player;
import latice.model.Shape;
import latice.model.Tile;
import latice.model.console.Deck;
import latice.model.console.Rack;
import latice.model.console.Score;
import latice.model.window.PlayerFX;
import latice.model.window.RectangleFX;

public class LaticeApplicationWindow extends Application {
	
	private static final int NUMBER_OF_BOX_ON_ONE_LINE = 9;

	private static final int RECTANGLE_HEIGHT = 50;

	private static final int RECTANGLE_WIDTH = 50;

	private static final int Y_CENTER = 37;

	private static final int X_CENTER = 355;

	private static final int BOX_WIDTH = 52;

	javafx.scene.paint.Color realColor = new javafx.scene.paint.Color(0, 0, 0, 0);
	
	Image image = new Image("backgroundLatice.png");
	ImageView imageView = new ImageView(image);
	
	
	ArrayList<Tile> listRackTile;
	ArrayList<Image> listTileImage;
	ArrayList<Tile> listOfTile = new ArrayList<Tile>();
	Map<Rectangle, Tile> assocRectangleTile = new HashMap<Rectangle, Tile>();
	static StackPane rootLayout;
	private Label namePlayer1 = new Label("Anonyme");
	private Label namePlayer2 = new Label("Anonyme"); 

	public static int indexTileClicked;	
	
	
	//settings players
	//public Player player1;
	//public Player player2;
	
	//borderPane layout
	public static BorderPane borderPane = new BorderPane();
	
	//StackPane for background image + BorderPane onto it
	StackPane root = new StackPane();
	
	static Stage primaryStageCopy;
	StackPane parentStackPane = new StackPane();
	Label moonErrorLabel = new Label();
	
	int validateBtnClickedCount;
	

	public static void main(String[] args) {
		Application.launch(args);

	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent loader = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
		Scene menu = new Scene(loader, 1280, 720);
		MainScreenController MSC = new MainScreenController();
		
		//--------------------------------------------------------------------------------------
		//Title
		VBox topVbox = new VBox();
		Text title = new Text("Latice");
		title.setFont(new Font(30));
		topVbox.getChildren().add(title);
		topVbox.setAlignment(Pos.CENTER);
		moonErrorLabel.setFont(new Font(20));
		moonErrorLabel.setTextFill(realColor.RED);
		topVbox.getChildren().add(moonErrorLabel);
		borderPane.setTop(topVbox);
		
		//Image
		Pane pane = new Pane();
		BackgroundImage myBG= new BackgroundImage(image,
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBG));
		
		//--------------------------------------------------------------------------------------
		//Creating rectangle for tiles placement
		Rectangle[][] r = new Rectangle[9][9];
		int counterI = 0;
		int counterJ = 0;
		for (int i=1; i<=NUMBER_OF_BOX_ON_ONE_LINE ; i++) {
			for (int j=1; j <= NUMBER_OF_BOX_ON_ONE_LINE ; j++) {
				
				r[counterI][counterJ] = new Rectangle(i*BOX_WIDTH+X_CENTER,j*BOX_WIDTH+Y_CENTER,RECTANGLE_WIDTH,RECTANGLE_HEIGHT);
				r[counterI][counterJ].setFill(realColor.TRANSPARENT);
				pane.getChildren().add(r[counterI][counterJ]);
				System.out.println(r[counterI][counterJ]);
				System.out.println(counterJ);
				counterJ++;
			}
			System.out.println(counterI);
			counterJ = 0;
			counterI++;
		}
		
		borderPane.setCenter(pane);
		System.out.println(r);
		//--------------------------------------------------------------------------------------
		
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
		Deck deck1 = new Deck(listOfTile);
		Deck deck2 = new Deck(listOfTile);
		
		//setting player names
		StringProperty name1 = new SimpleStringProperty();
		StringProperty name2 = new SimpleStringProperty();
		//name1.bind(PlayerNameInputController.namePlayer1);
		//name2.bind(PlayerNameInputController.namePlayer2);

		//Player player1 = new Player(namePlayer1.getText(), new Score(), deck1, new Rack(deck1));
		//Player player2 = new Player(namePlayer2.getText(), new Score(), deck2, new Rack(deck2));
		//Player player1 = MSC.instanciatePlayer(PlayerNameInputController.getNomJoueur1());
		//Player player2 = MSC.instanciatePlayer(PlayerNameInputController.getNomJoueur2());
		
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
						
						
						System.out.println("confirmed placement");
						
						validateBtnClickedCount++;
						
					}
					
				});
		
		
		//With Image
		Rack rack2 = new Rack(deck);
		HBox rackImage = rack2.createTileImage();
		
		
		//RackChange Button
		Image changeIconImage = new Image("changeIcon.png");
		ImageView changeIconView = new ImageView(changeIconImage);
		Button changeButton = new Button("Change Rack", changeIconView);
		
		
		changeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				
				System.out.println("Changing Rack");
				rack2.changeRack();

				rackImage.getChildren().clear();
				
				rackImage.getChildren().addAll(rack2.createTileImage(), confirmButton, changeButton);
				
				//Setting drag n drop on tiles
				setDragnDropOnRack(rackImage);
			}
			
		});
		rackImage.getChildren().addAll(confirmButton, changeButton);
		borderPane.setBottom(rackImage);
		
		//Adding lists to Arraylists
		listRackTile = rack2.getListRackTile();
		System.out.println(listRackTile);
		listTileImage = rack2.getRackTileImage();
		System.out.println("listTileImge : " + listTileImage);
		
		
		//------------------------------------------------------------------------
		
		//Setting drag n drop on tiles
		setDragnDropOnRack(rackImage);

		System.out.println((indexTileClicked));
		ImagePattern imagePattern = new ImagePattern(listTileImage.get(getIndexTileClicked()));
		
		//------------------------------------------------------------------------
		//###################### creating all rectangles and DragnDrop ######################//
		//ectangleFX rectFX = new RectangleFX();
		//rectFX.createRectangle(root, pane);
		//rectFX.dragnDropOnAllRectangles(player1, indexTileClicked, validateBtnClickedCount);
		//rectFX.dragnDropOnAllRectangles(player2, indexTileClicked, validateBtnClickedCount);
		//------------------------------------------------------------------------
		
		//Setting drag & drop on rectangles
		for(int i=0; i<NUMBER_OF_BOX_ON_ONE_LINE; i++) {
			for(int j=0; j<NUMBER_OF_BOX_ON_ONE_LINE; j++) {
		        int a = i;
		        int b = j;
		        
				r[a][b].setOnDragEntered(new EventHandler<DragEvent>() {
	
					@Override
					public void handle(DragEvent arg0) {
						if (arg0.getDragboard().hasString()){
							Dragboard dragboard = arg0.getDragboard();
							
							r[a][b].setFill(new ImagePattern(listTileImage.get(getIndexTileClicked())));
						}
						arg0.consume();
					}
				});
				
				r[a][b].setOnDragExited(new EventHandler<DragEvent>() {
		
					@Override
					public void handle(DragEvent arg0) {
						if (arg0.isDropCompleted() == false) {
							r[a][b].setFill(realColor.TRANSPARENT);
						}
						arg0.consume();
					}
					
				});
				
				r[a][b].setOnDragOver(new EventHandler <DragEvent>() {
					@Override
				    public void handle(DragEvent arg0) {
				        arg0.acceptTransferModes(TransferMode.ANY);
				        arg0.consume();
				    }
				});
				
				r[a][b].setOnDragDropped(new EventHandler<DragEvent>() {
					@Override
					public void handle(DragEvent arg0) {
						System.out.println("entered");
						Dragboard dragboard = arg0.getDragboard();
						
						r[a][b].setFill(new ImagePattern(listTileImage.get(getIndexTileClicked())));
						arg0.setDropCompleted(true);
						assocRectangleTile.put(r[a][b], listRackTile.get(getIndexTileClicked()));
						System.out.println(assocRectangleTile.toString());
						moonErrorLabel.setText("");
						if (validateBtnClickedCount == 0){
							if (r[a][b] == r[4][4]) {
								System.out.println("MOON valid placement");	
							}else {
								moonErrorLabel.setText("Error ! Please place the first tile on the moon");
								//removing all tiles from gameboard
								for(int i=0; i<NUMBER_OF_BOX_ON_ONE_LINE; i++) {
									for(int j=0; j<NUMBER_OF_BOX_ON_ONE_LINE; j++) {
										r[i][j].setFill(realColor.TRANSPARENT);
									}
								}
								System.out.println("Please place first Tile on MOON");
								
							}
				        }
						
						
						arg0.consume();
					}
					
				});
				
			
	        }
		}
		
	
		//rules / referee implementaion
		
		//this.transition(namePlayer1, namePlayer2);
		//root.setLeft(namePlayer1);
		
		
		
		
		
		

		parentStackPane = MSC.getParentStackPane();
		
		
		
		//--------------------------------------------------------------------------------------
		setPrimaryStage(primaryStage);
		
		setRootLayout(root);
		//root.getChildren().add(borderPane);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Latice");
		primaryStage.setScene(menu);
		primaryStage.show();
		
	}



	



	private void setDragnDropOnRack(HBox rackImage) {
		//Setting drag n drop on tiles
		for (int i=0; i<5; i++) {
			int a = i;
			rackImage.getChildren().get(a).setOnDragDetected(new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent arg0) {
					Dragboard dragboard = rackImage.getChildren().get(a).startDragAndDrop(TransferMode.ANY);
					ClipboardContent content = new ClipboardContent();
					dragboard.setDragView(listTileImage.get(a));
					content.putString("Hello !");
					setIndexTileClicked(a);
					dragboard.setContent(content);
					arg0.consume();
				}
				
			});
			
			rackImage.getChildren().get(a).setOnDragDone(new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent arg0) {
					
				}
				
			});
		}
	}
	public static void setRootLayout(StackPane root) {
		rootLayout = root;
	}
	public static StackPane getRootLayout() {
		return rootLayout;
	}



	//getter to get the index of the mouse clicked tile
	public static int getIndexTileClicked() {
		return indexTileClicked;
	}
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStageCopy = primaryStage;
	}


	//Setter to set the mouse clicked tile
	public static void setIndexTileClicked(int indexTileClicked) {
		LaticeApplicationWindow.indexTileClicked = indexTileClicked;
	}
	
	/*//player names setters
	public void setNamePlayer1(String namePlayer1) {
		this.namePlayer1.setText(namePlayer1);
	}

	public void setNamePlayer2(String namePlayer2) {
		this.namePlayer2.setText(namePlayer2);
	}*/
	
	public void playerNamesEntered() {
		System.out.println("entered playNamesEntered()" + namePlayer1.getText() + " VS " + namePlayer2.getText());
		
		MainScreenController MSC = new MainScreenController();
		MSC.setParentStackPane(parentStackPane);
		
		primaryStageCopy.setTitle("working");
	}
	
	public void transition(Label player1, Label player2) {
		
		borderPane.setLeft(player1);
		borderPane.setRight(player2);
	}

}
