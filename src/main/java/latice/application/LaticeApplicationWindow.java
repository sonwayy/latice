package latice.application;

import java.io.IOException;
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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import latice.controller.GameFinishedScreenController;
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
	ArrayList<Image> listRackImage;
	ArrayList<Tile> listOfTile = new ArrayList<Tile>();
	static StackPane rootLayout;

	public static int indexTileClicked;
	
	
	//settings players
	//public Player player1;
	//public Player player2;
	public Player player;
	
	// informations of the player
	public PlayerFX playerFX;
	
	//settings Button
	Button confirmButton;
	Button changeButton;
	Button buyActionButton;
	
	//setting the referee to check rules, the GameBoard where the tile are placed and the Rectangle to put the image tile in the plateau
	Rectangle[][] rect;
	GameBoard board;
	Rules referee;
	
	
	
	//setting to know if the tile is well dropped with the check of rules or not
	//To start, it's false
	Boolean tileDropped = false;
	
	Paint tileOnRect;
	
	//tile is free when it the first tile put but tile is payable after
	Boolean freeOrPayableTile = true;
	
	//borderPane layout
	public static BorderPane borderPane = new BorderPane();
	
	//StackPane for background image + BorderPane onto it
	StackPane root = new StackPane();
	
	
	Stage primaryStageCopy;
	StackPane parentStackPane = new StackPane();
	Label ErrorLabel = new Label();
	
	HBox rackImage;
	static Label nameWinner = new Label();
	
	int confirmBtnClickedCount;
	

	public static void main(String[] args) {
		Application.launch(args);

	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent loader = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
		Scene menu = new Scene(loader, 1280, 720);
		
		MainScreenController MSC = new MainScreenController();
		
		parentStackPane = MSC.getParentStackPane();
		primaryStageCopy = primaryStage;
		
		setRootLayout(root);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Latice");
		primaryStage.setScene(menu);
		primaryStage.show();
		
	}
	
	public void startGame(Stage stage, StackPane parentStackPaneStock, Player player1, Player player2, Object menuBorderPane) {
		parentStackPane = parentStackPaneStock;
		StackPane root = getRootLayout();
		root.translateYProperty().set(stage.getHeight());
		System.out.println(parentStackPane);
		System.out.println(parentStackPaneStock);
		if (parentStackPane.getChildren().contains(root)) {
			parentStackPane.getChildren().remove(root);
		}
		parentStackPane.getChildren().add(root);
		
		
		//settings for the animation
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
		ErrorLabel.setFont(new Font(20));
		ErrorLabel.setTextFill(Constant.realColor.RED);
		topVbox.getChildren().add(ErrorLabel);
		borderPane.setTop(topVbox);
		
		//Image
		Pane pane = new Pane();
		BackgroundImage myBG= new BackgroundImage(image,
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBG));
		
		//--------------------------------------------------------------------------------------
		//Creating rectangle for tiles placement
		rect = new Rectangle[Constant.DIMENSION][Constant.DIMENSION];
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
		board = new GameBoard();
		
		//Referee
		referee = new Rules();
		
		
		
		//--------------------------------------------------------------------------------------
		
		//display name, score and deck of each player
		//----------- settings the attributes of the players -----------//
		PlayerFX player1FX = new PlayerFX();
		PlayerFX player2FX = new PlayerFX(); 
		
		//----------- display attribute of the player in a VBox -----------//
		VBox infoPlayer1 = player1FX.displayPlayers(parentStackPane,player1);
		VBox infoPlayer2 = player2FX.displayPlayers(parentStackPane,player2);
		
		//----------- group all players in a HBox to display -----------//
		HBox infoPlayers = new HBox();
		infoPlayers.getChildren().addAll(infoPlayer1, infoPlayer2);
		HBox.setMargin(infoPlayer1, new Insets(50,0,0,55));
		HBox.setMargin(infoPlayer2, new Insets(50,0,0,55));
		infoPlayers.setSpacing(850);
		
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
		player = player1;
		playerFX = player1FX;
		playerFX.setFillName(Constant.realColor.RED);
		
		
		//Confirm Button
		Image checkMark = new Image("checkMark.png");
		ImageView checkMarkView = new ImageView(checkMark);
		confirmButton = new Button("Confirm", checkMarkView);
		confirmButton.setPrefWidth(Constant.ACTION_BUTTONS_WIDTH);
		confirmButton.setPrefHeight(Constant.ACTION_BUTTONS_HEIGHT);
		

		//RackChange Button
		Image changeIconImage = new Image("changeIcon.png");
		ImageView changeIconView = new ImageView(changeIconImage);
		changeButton = new Button("Change Rack", changeIconView);
		changeButton.setPrefWidth(Constant.ACTION_BUTTONS_WIDTH);
		changeButton.setPrefHeight(Constant.ACTION_BUTTONS_HEIGHT);
		
		//Buy another action Button
		Image buyActionImage = new Image("buyAction.png");
		ImageView buyActionView = new ImageView(buyActionImage);
		buyActionButton = new Button("Buy Action", buyActionView);
		buyActionButton.setPrefWidth(Constant.ACTION_BUTTONS_WIDTH);
		buyActionButton.setPrefHeight(Constant.ACTION_BUTTONS_HEIGHT);
		
		confirmButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				
				System.out.println("confirmed placement");
				
				try {
					switchToGameFinishedScreen();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				confirmBtnClickedCount++;
				player.getRack().displayRack();
				player.getRack().updateRack();
				
				//HBox newRackImage = rack2.createTileImage();
				//rackImage.getChildren().clear();
				//listRackTile = rack2.getListRackTile();
				//System.out.println(listRackTile);
				player.getRack().displayRack();
				

				
				//Setting drag n drop on tiles
				
				
				
				//setDragnDropOnRectangles(rect, board, referee, player);
				
				
				
				if (confirmBtnClickedCount%2 == 0) {
					playerFX.setFillName(Constant.realColor.BLACK);
					player = player1;
					playerFX = player1FX;
					playerFX.setFillName(Constant.realColor.RED);
				}else {
					playerFX.setFillName(Constant.realColor.BLACK);
					player = player2;
					playerFX = player2FX;
					playerFX.setFillName(Constant.realColor.RED);
				}
				
				freeOrPayableTile = true;
				
				rackImage = player.getRack().createTileImage();
				setDragnDropOnRack(rackImage, player);
				setDragnDropOnRectangles(rect, board, referee, player);
				rackImage.getChildren().addAll(confirmButton, changeButton, buyActionButton);
				borderPane.setBottom(rackImage);
				
				
			}

			private void switchToGameFinishedScreen() throws IOException {
				System.out.println("confirmBtnClickedCount : " + confirmBtnClickedCount);
				if (confirmBtnClickedCount>=20) {
					Parent loader = FXMLLoader.load(getClass().getResource("../view/GameFinishedScreen.fxml"));
					Scene gameFinishedScreenScene = new Scene(loader, 1280, 720);
					if (player1.getNumberOfTilesRemaining() < player2.getNumberOfTilesRemaining()) {
						// if player 1 has less tiles then he wins
						GameFinishedScreenController.staticNameWinner.setText(player1.getName());
					}else {
						//if player 2 has less tiles then he wins
						GameFinishedScreenController.staticNameWinner.setText(player2.getName());
					}
					stage.setScene(gameFinishedScreenScene);
					
					//----------------------------------------
					
				
				}
			}

			
			
		});
		
		
		//With Image
		//Rack player.getRack() = new Rack(deck);
		rackImage = player.getRack().createTileImage();
		
		//Adding lists to Arraylists
		listRackTile = player.getRack().getListRackTile();
		System.out.println(listRackTile);
		listRackImage = player.getRack().getRackTileImage();
		System.out.println("listTileImge : " + listRackImage);
		//HBox rackTile = rack2.createTileImage();
		
		
		
		changeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				
				
				//player.getRack().changeRack();
				
				//HBox newRackImage = rack2.createTileImage();
				//rackImage.getChildren().clear();
				//listRackTile = rack2.getListRackTile();
				//System.out.println(listRackTile);
				
				
				////// for the actual player //////
				if (player.getScore() > 1) {
					System.out.println("Changing Rack");
					confirmBtnClickedCount++;
					player.getRack().changeRack();
					player.getRack().updateRack();
					
					////// changing player //////
					if (confirmBtnClickedCount%2 == 0) {
						playerFX.setFillName(Constant.realColor.BLACK);
						player = player1;
						playerFX = player1FX;
						playerFX.setFillName(Constant.realColor.RED);
						
					}else {
						playerFX.setFillName(Constant.realColor.BLACK);
						player = player2;
						playerFX = player2FX;
						playerFX.setFillName(Constant.realColor.RED);
					}
						
						
					////// for the next player //////
					freeOrPayableTile = true;
					rackImage = player.getRack().createTileImage();
					
					//Setting drag n drop on tiles
					setDragnDropOnRack(rackImage, player);

					rackImage.getChildren().addAll(confirmButton, changeButton, buyActionButton);
					setDragnDropOnRectangles(rect, board, referee, player);
					borderPane.setBottom(rackImage);
						
				}else {
					System.out.println("Not enough points to change the rack");
				}
			
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
		
		//HBox rackTile = rack2.createTileImage();
		
		rackImage.getChildren().addAll(confirmButton, changeButton, buyActionButton);
		setDragnDropOnRack(rackImage, player);
		System.out.println();
		borderPane.setBottom(rackImage);
		
		
		
		
		
		
		//------------------------------------------------------------------------
		
		//Setting drag n drop on tiles
		

		System.out.println((indexTileClicked));
		ImagePattern imagePattern = new ImagePattern(listRackImage.get(getIndexTileClicked()));
		
		//------------------------------------------------------------------------
		//###################### creating all rectangles and DragnDrop ######################//
		//RectangleFX rectFX = new RectangleFX();
		//rectFX.createRectangle(root, pane);
		//rectFX.dragnDropOnAllRectangles(player1, indexTileClicked, validateBtnClickedCount);
		//rectFX.dragnDropOnAllRectangles(player2, indexTileClicked, validateBtnClickedCount);
		//------------------------------------------------------------------------
		
		//Setting drag & drop on rectangles
		setDragnDropOnRectangles(rect, board, referee, player);
		
	
		
		
		//--------------------------------------------------------------------------------------
		
		root.getChildren().addAll(infoPlayers, borderPane);
		
        
	}



	public void setDragnDropOnRectangles(Rectangle[][] rect, GameBoard board, Rules referee, Player player) {
		for(int i=0; i<Constant.NUMBER_OF_BOX_ON_ONE_LINE; i++) {
			for(int j=0; j<Constant.NUMBER_OF_BOX_ON_ONE_LINE; j++) {
		        int a = i;
		        int b = j;
		        
		        
		        
				rect[a][b].setOnDragEntered(new EventHandler<DragEvent>() {
	
					@Override
					public void handle(DragEvent arg0) {
						
						
						tileOnRect = rect[a][b].getFill();
						if (arg0.getDragboard().hasString()){
							Dragboard dragboard = arg0.getDragboard();
							
							//not put the virtual image drag on the case because an image is already into it
							player.getRack().getListRackTile().get(indexTileClicked).setPosition(new Position(a,b));
							if ( referee.checkPositionRule(board, player.getRack().getListRackTile().get(indexTileClicked))  == true ) {
							rect[a][b].setFill(new ImagePattern(player.getRack().getRackTileImage().get(indexTileClicked)));
							}
						}
						arg0.consume();
					}
				});
				
				rect[a][b].setOnDragExited(new EventHandler<DragEvent>() {
		
					@Override
					public void handle(DragEvent arg0) {
						if (arg0.isDropCompleted() == false) {
							
							rect[a][b].setFill(tileOnRect);
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
						
						
						rect[a][b].setFill(new ImagePattern(player.getRack().getRackTileImage().get(indexTileClicked)));
						
						
						arg0.setDropCompleted(true);
						System.out.println("OK");
						ErrorLabel.setText("");
						
						if (referee.checkScoreToPlay(player, freeOrPayableTile) == false) {
							ErrorLabel.setText("Error ! You haven't enough points to play another tile");
							rect[a][b].setFill(tileOnRect);
							
						}else {
							
							if (Constant.START) {
								if (rect[a][b] == rect[4][4]) {
									System.out.println("MOON valid placement");
									
									board.setGridBoardTile(player.getRack().getListRackTile().get(indexTileClicked), a, b);
									tileDropped = true;
									freeOrPayableTile = false;
									Constant.START = false;
									
								}else {
									ErrorLabel.setText("Error ! Please place the first tile on the moon");
									//removing all tiles from gameboard
									rect[a][b].setFill(Constant.realColor.TRANSPARENT);
									
									System.out.println("Please place first Tile on MOON");
									
								}
								
							}else {
								System.out.println("OK3");
								System.out.println("Règle effectué");
								player.getRack().getListRackTile().get(indexTileClicked).setPosition(new Position(a,b));
								
								
								
								
								if ( referee.checkPositionRule(board, player.getRack().getListRackTile().get(indexTileClicked))  == false ) {
									ErrorLabel.setText("Error ! The tile can't be placed here because there is already a tile placed");
									rect[a][b].setFill(tileOnRect);
									
								}else {
									int nbr = referee.neighborRule(board , player.getRack().getListRackTile().get(indexTileClicked));
									
									if (nbr == 0) {
										ErrorLabel.setText("Error ! The tile isn't place next to another tile or there is no correspondance with the neighbor tiles !!");
										rect[a][b].setFill(Constant.realColor.TRANSPARENT);
										
									}else {
										
										if (freeOrPayableTile == false) {
											playerFX.setDiffScore(player, 2);
										}
										
										if (nbr == 2) {
											System.out.println("Vous avez gagné 1 point");
											playerFX.setAddScore(player, 1);
											
										}else if (nbr == 3) {
											System.out.println("Vous avez gagné 2 points");
											playerFX.setAddScore(player, 2);
											
										}else if (nbr == 4) {
											System.out.println("Vous avez gagné 4 points");
											playerFX.setAddScore(player, 4);
										}
										
										board.setGridBoardTile(player.getRack().getListRackTile().get(getIndexTileClicked()), a, b);
										tileDropped = true;
										freeOrPayableTile = false;
										System.out.println("tuile posé!");
									}
								}
							}
								
						}
							
							
							
							
							
				        
						
						
						arg0.consume();
					}
					
				});
				
			
	        }
		}
	}


	



	private void setDragnDropOnRack(HBox rackBox, Player player) {
		//Setting drag n drop on tiles
		for (int i=0; i< player.getRack().getRackTileImage().size(); i++) {
			int a = i;
			System.out.println(a);
			//HBox t = rackBox.getChildren().indexOf(rack);
			rackBox.getChildren().get(a).setOnDragDetected(new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent arg0) {
					Dragboard dragboard = rackBox.getChildren().get(a).startDragAndDrop(TransferMode.ANY);
					ClipboardContent content = new ClipboardContent();
					dragboard.setDragView(player.getRack().getRackTileImage().get(a));
					content.putString("Hello !");
					setIndexTileClicked(a);
					dragboard.setContent(content);
					arg0.consume();
				}
				
			});
			
			rackBox.getChildren().get(a).setOnDragDone(new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent arg0) {
					if (tileDropped) {
						player.getRack().getListRackTile().remove(a);
						rackImage = player.getRack().createTileImage();
						
						//Setting drag n drop on tiles
						setDragnDropOnRack(rackImage, player);
	
						rackImage.getChildren().addAll(confirmButton, changeButton, buyActionButton);
						//setDragnDropOnRectangles(rect, board, referee, player);
						borderPane.setBottom(rackImage);
						tileDropped = false;
					}
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

	public static String getNameWinner() {
		return nameWinner.getText();
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