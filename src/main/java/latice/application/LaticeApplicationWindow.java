package latice.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import latice.controller.MainScreenController;
import latice.model.Color;
import latice.model.Constant;
import latice.model.Player;
import latice.model.Position;
import latice.model.Rules;
import latice.model.Shape;
import latice.model.Tile;
import latice.model.console.Deck;
import latice.model.console.GameBoard;
import latice.model.console.Rack;
import latice.model.window.PlayerFX;

public class LaticeApplicationWindow extends Application {
	
	
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
		
		parentStackPane = MSC.getParentStackPane();
		
		setPrimaryStage(primaryStageCopy);
		
		setRootLayout(root);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Latice");
		primaryStage.setScene(menu);
		primaryStage.show();
		
	}
	
	public void startGame(Stage stage, StackPane parentStackPaneStock, Player player1, Player player2, Object menuBorderPane) {
		parentStackPane = parentStackPaneStock;
		//StackPane root = getRootLayout();
		//root.translateYProperty().set(stage.getHeight());
		System.out.println(parentStackPane);
		System.out.println(parentStackPaneStock);
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
        
        
        
		
		//--------------------------------------------------------------------------------------
		//Title
		VBox topVbox = new VBox();
		Text title = new Text("Latice");
		title.setFont(new Font(30));
		topVbox.getChildren().add(title);
		topVbox.setAlignment(Pos.CENTER);
		moonErrorLabel.setFont(new Font(20));
		moonErrorLabel.setTextFill(Constant.realColor.RED);
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
		Rectangle[][] rect = new Rectangle[9][9];
		int counterI = 0;
		int counterJ = 0;
		for (int i=1; i<=Constant.NUMBER_OF_BOX_ON_ONE_LINE ; i++) {
			for (int j=1; j <= Constant.NUMBER_OF_BOX_ON_ONE_LINE ; j++) {
				
				rect[counterI][counterJ] = new Rectangle(i*Constant.BOX_WIDTH+Constant.X_CENTER,j*Constant.BOX_WIDTH+Constant.Y_CENTER,Constant.RECTANGLE_WIDTH,Constant.RECTANGLE_HEIGHT);
				rect[counterI][counterJ].setFill(Constant.realColor.TRANSPARENT);
				pane.getChildren().add(rect[counterI][counterJ]);
				System.out.println(rect[counterI][counterJ]);
				System.out.println(counterJ);
				counterJ++;
			}
			System.out.println(counterI);
			counterJ = 0;
			counterI++;
		}
		
		borderPane.setCenter(pane);
		System.out.println(rect);
		//--------------------------------------------------------------------------------------
		
		//GameBoard
		GameBoard board = new GameBoard();
		
		//Referee
		Rules referee = new Rules();
		
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
		
		
		//Player
		Player player = player1;
			
		
		//Confirm Button
		Image checkMark = new Image("checkMark.png");
		ImageView checkMarkView = new ImageView(checkMark);
		Button confirmButton = new Button("Confirm", checkMarkView);
		confirmButton.setPrefWidth(Constant.ACTION_BUTTONS_WIDTH);
		confirmButton.setPrefHeight(Constant.ACTION_BUTTONS_HEIGHT);
		
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
		changeButton.setPrefWidth(Constant.ACTION_BUTTONS_WIDTH);
		changeButton.setPrefHeight(Constant.ACTION_BUTTONS_HEIGHT);
		
		//Buy another action Button
		Image buyActionImage = new Image("buyAction.png");
		ImageView buyActionView = new ImageView(buyActionImage);
		Button buyActionButton = new Button("Buy Action", buyActionView);
		buyActionButton.setPrefWidth(Constant.ACTION_BUTTONS_WIDTH);
		buyActionButton.setPrefHeight(Constant.ACTION_BUTTONS_HEIGHT);
		
		changeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				
				System.out.println("Changing Rack");
				rack2.changeRack();

				rackImage.getChildren().clear();
				
				rackImage.getChildren().addAll(rack2.createTileImage(), confirmButton, changeButton, buyActionButton);
				
				//Setting drag n drop on tiles
				setDragnDropOnRack(rackImage);
			}
			
		});
		
		buyActionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				//TODO verify score and give another play()
                /*if (player.getScore()>=3) {
                    //Donner une action supplémentaire et enlever 3 points au joueur
                    player.Play(play, board, 0);
                    player.diffScore(3);
                }else {
                    System.out.println("Il vous faut 3 points pour acheter une nouvelle action !");
                }*/
			}
			
		});
		rackImage.getChildren().addAll(confirmButton, changeButton, buyActionButton);
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
		for(int i=0; i<Constant.NUMBER_OF_BOX_ON_ONE_LINE; i++) {
			for(int j=0; j<Constant.NUMBER_OF_BOX_ON_ONE_LINE; j++) {
		        int a = i;
		        int b = j;
		        
				rect[a][b].setOnDragEntered(new EventHandler<DragEvent>() {
	
					@Override
					public void handle(DragEvent arg0) {
						if (arg0.getDragboard().hasString()){
							Dragboard dragboard = arg0.getDragboard();
							
							rect[a][b].setFill(new ImagePattern(listTileImage.get(getIndexTileClicked())));
						}
						arg0.consume();
					}
				});
				
				rect[a][b].setOnDragExited(new EventHandler<DragEvent>() {
		
					@Override
					public void handle(DragEvent arg0) {
						if (arg0.isDropCompleted() == false) {
							rect[a][b].setFill(Constant.realColor.TRANSPARENT);
						}
						arg0.consume();
					}
					
				});
				
				rect[a][b].setOnDragOver(new EventHandler <DragEvent>() {
					@Override
				    public void handle(DragEvent arg0) {
				        arg0.acceptTransferModes(TransferMode.ANY);
				        arg0.consume();
				    }
				});
				
				rect[a][b].setOnDragDropped(new EventHandler<DragEvent>() {
					@Override
					public void handle(DragEvent arg0) {
						System.out.println("entered");
						Dragboard dragboard = arg0.getDragboard();
						System.out.println("OK2");
						rect[a][b].setFill(new ImagePattern(listTileImage.get(getIndexTileClicked())));
						
						
						arg0.setDropCompleted(true);
						System.out.println("OK");
						//assocRectangleTile.put(rect[a][b], listRackTile.get(getIndexTileClicked()));
						//System.out.println(assocRectangleTile.toString());
						moonErrorLabel.setText("");
						if (validateBtnClickedCount == 0){
							if (Constant.START) {
								if (rect[a][b] == rect[4][4]) {
									System.out.println("MOON valid placement");
									//assocRectangleTile.put(rect[a][b], listRackTile.get(getIndexTileClicked()));
									
									board.setGridBoardTile(listRackTile.get(getIndexTileClicked()), a, b);
									
									Constant.START = false;
								}else {
									moonErrorLabel.setText("Error ! Please place the first tile on the moon");
									//removing all tiles from gameboard
									rect[a][b].setFill(Constant.realColor.TRANSPARENT);
									
									System.out.println("Please place first Tile on MOON");
									
								}
								
							}else {
								System.out.println("OK3");
								System.out.println("Règle effectué");
								listRackTile.get(getIndexTileClicked()).setPosition(new Position(a,b));
								int nbr = referee.neighborRule(board , listRackTile.get(getIndexTileClicked()));
								
								if (nbr == 0) {
									System.out.println("l'emplacement où est posé la tuile n'a pas de voisin ou il n'y a pas de correspondance avec les voisins !");
									rect[a][b].setFill(Constant.realColor.TRANSPARENT);
									
								}else {
									if (nbr == 2) {
										System.out.println("Vous avez gagné 1 point");
									}else if (nbr == 3) {
										System.out.println("Vous avez gagné 2 points");
									}else if (nbr == 4) {
										System.out.println("Vous avez gagné 4 points");
									}
									//assocRectangleTile.put(rect[a][b], listRackTile.get(getIndexTileClicked()));
									board.setGridBoardTile(listRackTile.get(getIndexTileClicked()), a, b);
									System.out.println("tuile posé!");
									
									
								}
								
								
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
		
		
		
		
		
		

		
		
		HBox players = PlayerFX.displayPlayers(parentStackPane,player1, player2);
		
		//--------------------------------------------------------------------------------------
		
		root.getChildren().addAll(players, borderPane);
		
        
        //Règles
	}


	



	private void setDragnDropOnRack(HBox rackImage) {
		//Setting drag n drop on tiles
		for (int i=0; i<5; i++) {
			int a = i;
			System.out.println(a);
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

}