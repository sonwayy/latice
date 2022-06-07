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
	
	//lists for tiles
	ArrayList<Tile> listRackTile;
	ArrayList<Image> listOfTilesInRack;
	//ArrayList<Tile> listOfTile = new ArrayList<Tile>();
	static StackPane rootLayout;

	public static int indexTileClicked;
	
	//settings players
	public Player player;
	
	// informations of the player
	public PlayerFX playerFX;
	
	//settings Button
	Button confirmButton;
	Button changeButton;
	Button buyActionButton;
	
	//setting the referee to check rules, the GameBoard where the tile are placed and the Rectangle to put the image tile in the game board
	Rectangle[][] rect;
	GameBoard board;
	Rules referee;
	
	//setting to know if the tile is well dropped with the check of rules or not
	//To start, it's false
	Boolean tileDropped = false;
	
	Paint tileOnRect;
	
	//tile is free when it's the first tile put but tile is payable after
	Boolean isFreePlacement = true;
	
	public static BorderPane borderPane = new BorderPane();
	
	//StackPane for background image + BorderPane onto it
	StackPane root = new StackPane();
	
	
	Stage primaryStageCopy;
	StackPane parentStackPane = new StackPane();
	Label gameInfoLabel = new Label();
	
	HBox rackTileImage;
	static Label nameWinner = new Label();
	
	int confirmBtnClickedCount;
	
	Parent loader;
	static Scene menu;

	public static void main(String[] args) {
		Application.launch(args);

	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception{
		//setting main menu screen
		loader = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
		menu = new Scene(loader, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
		
		MainScreenController MSC = new MainScreenController();
		
		parentStackPane = MSC.getParentStackPane();
		primaryStageCopy = primaryStage;
		
		setRootLayout(root);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Latice");
		primaryStage.setScene(menu);
		primaryStage.show();
		
	}
	
	public void startGame(Stage stage, StackPane parentStackPaneStock, Player player1, Player player2, Object menuBorderPane, Boolean isRestart) {
		//changing layouts to start the game
		parentStackPane = parentStackPaneStock;
		StackPane root = getRootLayout();
		System.out.println(parentStackPane);
		System.out.println(parentStackPaneStock);
		if (isRestart) {
			//if we're restarting the game then do some settings
			System.out.println("Voici le menu : " + menu);
			Constant.START = true;
			stage.setScene(menu);
			
			parentStackPane.getChildren().remove(root);
			parentStackPane.getChildren().add(root);
            parentStackPane.getChildren().remove(menuBorderPane);
		}else {
			
			//else if it's the first time we're launching it
			root.translateYProperty().set(stage.getHeight());
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
		}
        
        
        
		
		//--------------------------------------------------------------------------------------
		//Title
		VBox topVbox = new VBox();
		Text title = new Text("Latice");
		title.setFont(new Font(30));
		topVbox.getChildren().add(title);
		topVbox.setAlignment(Pos.CENTER);
		
		//error label for displaying errors
		gameInfoLabel.setFont(new Font(20));
		gameInfoLabel.setTextFill(Constant.realColor.RED);
		topVbox.getChildren().add(gameInfoLabel);
		borderPane.setTop(topVbox);
		
		//background image
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
		/*for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				
				listOfTile.add(tile);
				
			}
		}*/
		
		/*System.out.println("-----------------");
		System.out.println("Notre Deck :");
		Deck deck = new Deck(listOfTile);
		System.out.println("-----------------");
		Rack rack = new Rack(deck);
		System.out.println("-----------------");*/
		
		
		//Player
		player = player1;
		playerFX = player1FX;
		playerFX.setFillName(Constant.realColor.RED);
		
		
		//Confirm Button
		Image checkMark = new Image("checkMark.png");
		ImageView checkMarkView = new ImageView(checkMark);
		confirmButton = new Button("End Turn", checkMarkView);
		confirmButton.setPrefWidth(Constant.ACTION_BUTTONS_WIDTH);
		confirmButton.setPrefHeight(Constant.ACTION_BUTTONS_HEIGHT);
		

		//RackChange Button
		Image changeIconImage = new Image("changeIcon.png");
		ImageView changeIconView = new ImageView(changeIconImage);
		changeButton = new Button("Change Rack (2 points)", changeIconView);
		changeButton.setPrefWidth(Constant.ACTION_BUTTONS_WIDTH);
		changeButton.setPrefHeight(Constant.ACTION_BUTTONS_HEIGHT);
		
		//Buy another action Button
		/*Image buyActionImage = new Image("buyAction.png");
		ImageView buyActionView = new ImageView(buyActionImage);
		buyActionButton = new Button("Buy Action (2 points)", buyActionView);
		buyActionButton.setPrefWidth(Constant.ACTION_BUTTONS_WIDTH);
		buyActionButton.setPrefHeight(Constant.ACTION_BUTTONS_HEIGHT);*/
		
		confirmButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			//confirming the end of the round and switching round

			@Override
			public void handle(MouseEvent arg0) {
				
				
				System.out.println("confirmed placement");
				
				try {
					switchToGameFinishedScreen();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				confirmBtnClickedCount++;
				
				////// for the actual player //////
				player.getRack().updateRack();
				
				////// changing player //////
				//making names colored in red to make the players know which player's round is
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
				isFreePlacement = true;
				
				rackTileImage = player.getRack().createTileImage();
				//Setting drag n drop on tiles
				setDragnDropOnRack(rackTileImage, player);
				setDragnDropOnRectangles(rect, board, referee, player);
				rackTileImage.getChildren().addAll(confirmButton, changeButton/*, buyActionButton*/);
				borderPane.setBottom(rackTileImage);
				
				
			}

			public void switchToGameFinishedScreen() throws IOException {
				
				//switching to game finished screen if the game finishes
				System.out.println("confirmBtnClickedCount : " + confirmBtnClickedCount);
				if (confirmBtnClickedCount>=20) {
					Parent loader = FXMLLoader.load(getClass().getResource("../view/GameFinishedScreen.fxml"));
					Scene gameFinishedScreenScene = new Scene(loader, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
					if (player1.getNumberOfTilesRemaining() < player2.getNumberOfTilesRemaining()) {
						// if player 1 has less tiles then he wins
						GameFinishedScreenController.staticNameWinner.setText(player1.getName());
						GameFinishedScreenController.staticNameLooser.setText(player2.getName());
					}else {
						//if player 2 has less tiles then he wins
						GameFinishedScreenController.staticNameWinner.setText(player2.getName());
						GameFinishedScreenController.staticNameLooser.setText(player1.getName());
					}
					stage.setScene(gameFinishedScreenScene);
					
					//----------------------------------------
					
				
				}
			}

			
			
		});
		
		//Rack with Image
		rackTileImage = player.getRack().createTileImage();
		
		//Adding lists to Arraylists
		listRackTile = player.getRack().getListRackTile();
		System.out.println(listRackTile);
		listOfTilesInRack = player.getRack().getRackTileImage();
		System.out.println("listTileImge : " + listOfTilesInRack);		
		
		changeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {

				////// for the actual player //////
				if (player.getScore() >= 2 || isFreePlacement) {
					if (!isFreePlacement) {
						playerFX.setRemovePointsFromScore(player, 2);
					}
					
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
					isFreePlacement = true;
					rackTileImage = player.getRack().createTileImage();
					
					//Setting drag n drop on tiles
					setDragnDropOnRack(rackTileImage, player);

					rackTileImage.getChildren().addAll(confirmButton, changeButton/*, buyActionButton*/);
					setDragnDropOnRectangles(rect, board, referee, player);
					borderPane.setBottom(rackTileImage);
					
						
				}else {
					System.out.println("Not enough points to change the rack");
					gameInfoLabel.setText("Error ! " + player.getName() + ", you haven't enough points to change your rack");
				}
			
			}
			
		});
		
		/*buyActionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				gameInfoLabel.setText(player.getName() + ", you can now play another time !");
			}
			
		});*/
				
		rackTileImage.getChildren().addAll(confirmButton, changeButton/*, buyActionButton*/);
		setDragnDropOnRack(rackTileImage, player);
		System.out.println();
		borderPane.setBottom(rackTileImage);
		
		//------------------------------------------------------------------------
		
		//Setting drag n drop on tiles
		

		System.out.println((indexTileClicked));
		ImagePattern imagePattern = new ImagePattern(listOfTilesInRack.get(getIndexTileClicked()));
		
		//------------------------------------------------------------------------

		
		//Setting drag & drop on rectangles
		setDragnDropOnRectangles(rect, board, referee, player);
		
	
		
		
		//------if there is already childrens, remove them all to start or restart game--------------------------------
		root.getChildren().clear();
		//adding new childrens
		root.getChildren().addAll(infoPlayers, borderPane);
		
        
	}



	public void setDragnDropOnRectangles(Rectangle[][] rect, GameBoard board, Rules referee, Player player) {
		for(int i=0; i<Constant.NUMBER_OF_BOX_ON_ONE_LINE; i++) {
			for(int j=0; j<Constant.NUMBER_OF_BOX_ON_ONE_LINE; j++) {
		        int a = i;
		        int b = j;
		        
		        
		        
				rect[a][b].setOnDragEntered(new EventHandler<DragEvent>() {
					
					//if we are entering a rectangle
					@Override
					public void handle(DragEvent arg0) {
						
						
						tileOnRect = rect[a][b].getFill();
						//not putting the virtual image drag on the case because an image is already into it
						player.getRack().getListRackTile().get(indexTileClicked).setPosition(new Position(a,b));
						if ( referee.checkPositionRule(board, player.getRack().getListRackTile().get(indexTileClicked))  == true ) {
						rect[a][b].setFill(new ImagePattern(player.getRack().getRackTileImage().get(indexTileClicked)));
						}
						arg0.consume();
					}
				});
				
				rect[a][b].setOnDragExited(new EventHandler<DragEvent>() {
					//if we are leaving a rectangle
					@Override
					public void handle(DragEvent arg0) {
						if (arg0.isDropCompleted() == false) {
							
							rect[a][b].setFill(tileOnRect);
						}
						arg0.consume();
					}
					
				});
				
				rect[a][b].setOnDragOver(new EventHandler <DragEvent>() {
					
					//If we are traveling over a rectangle
					@Override
				    public void handle(DragEvent arg0) {
						
				        arg0.acceptTransferModes(TransferMode.ANY);
				        arg0.consume();
				    }
				});
				
				rect[a][b].setOnDragDropped(new EventHandler<DragEvent>() {
					
					//If we are dropping a tile in a rectangle
					@Override
					public void handle(DragEvent arg0) {
						
						rect[a][b].setFill(new ImagePattern(player.getRack().getRackTileImage().get(indexTileClicked)));
						
						arg0.setDropCompleted(true);
						gameInfoLabel.setText("");
						
						//testing if player has enough points
						if (referee.checkScoreToPlay(player, isFreePlacement) == false) {
							gameInfoLabel.setText("Error ! " + player.getName() + ", you haven't enough points to play another tile");
							rect[a][b].setFill(tileOnRect);
							
						}else {
							
							//testing if it's the free round
							if (Constant.START) {
								if (rect[a][b] == rect[Constant.MOON_BOX_X][Constant.MOON_BOX_Y]) {
									//if its's the center box (moon box)
									System.out.println("MOON valid placement");
									
									board.setGridBoardTile(player.getRack().getListRackTile().get(indexTileClicked), a, b);
									tileDropped = true;
									isFreePlacement = false;
									Constant.START = false;
									
								}else {
									gameInfoLabel.setText("Error ! " + player.getName() + ", please place the first tile on the moon");
									//removing all tiles from gameboard
									rect[a][b].setFill(Constant.realColor.TRANSPARENT);
									
									System.out.println("Please place first Tile on MOON");
									
								}
								
							}else {
								//if it's not the first round of the game
								//putting the position of the gameboard's case on the tile from the rack
								player.getRack().getListRackTile().get(indexTileClicked).setPosition(new Position(a,b));
								
								//verify if a tile is already placed
								if ( referee.checkPositionRule(board, player.getRack().getListRackTile().get(indexTileClicked))  == false ) {
									gameInfoLabel.setText("Error ! The tile can't be placed here because there is already a tile placed");
									rect[a][b].setFill(tileOnRect);
									
								}else {
									//if there is not already a tile
									int nbr = referee.neighborRule(board , player.getRack().getListRackTile().get(indexTileClicked));
									
									if (nbr == 0) {
										gameInfoLabel.setText("Error! The tile isn't placed next to another tile or there is no correspondance with the neighbor tiles!");
										rect[a][b].setFill(Constant.realColor.TRANSPARENT);
										
									}else {
										
										if (isFreePlacement == false) {
											playerFX.setRemovePointsFromScore(player, 2);
										}
										
										if (nbr == 2) {
											System.out.println("Vous avez gagné 1 point");
											gameInfoLabel.setText(player.getName() + ", you won 1 point");
											playerFX.setAddPointsToScore(player, 1);
											
										}else if (nbr == 3) {
											System.out.println("Vous avez gagné 2 points");
											gameInfoLabel.setText(player.getName() + ", you won 2 points");
											playerFX.setAddPointsToScore(player, 2);
											
										}else if (nbr == 4) {
											System.out.println("Vous avez gagné 4 points");
											gameInfoLabel.setText(player.getName() + ", you won 4 points");
											playerFX.setAddPointsToScore(player, 4);
										}
										
										board.setGridBoardTile(player.getRack().getListRackTile().get(getIndexTileClicked()), a, b);
										tileDropped = true;
										isFreePlacement = false;
										System.out.println("tuile posé!");
										
										//Sun rule
										if (referee.sunRule(board, player.getRack().getListRackTile().get(indexTileClicked))) {
											System.out.println("Vous avez gagné 2 points en mettant votre tuile sur un soleil");
											gameInfoLabel.setText("Wow! " + player.getName() + ", you won 2 points by placing a tile on a sun !");
											playerFX.setAddPointsToScore(player, 2);
											
										}
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
	//-------------------------------------------------------------------------------

	private void setDragnDropOnRack(HBox rackBox, Player player) {
		//Setting drag n drop on tiles
		for (int i=0; i< player.getRack().getRackTileImage().size(); i++) {
			int a = i;
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
						rackTileImage = player.getRack().createTileImage();
						
						//Setting drag n drop on tiles
						setDragnDropOnRack(rackTileImage, player);
	
						rackTileImage.getChildren().addAll(confirmButton, changeButton/*, buyActionButton*/);
						borderPane.setBottom(rackTileImage);
						tileDropped = false;
						playerFX.setTilesRemaining(player);
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