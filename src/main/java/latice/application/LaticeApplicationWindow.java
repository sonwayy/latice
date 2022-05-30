package latice.application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import latice.controller.MainScreenController;
import latice.controller.PlayerNameInputController;
import latice.model.Color;
import latice.model.Deck;
import latice.model.Rack;
import latice.model.Shape;
import latice.model.Tile;

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
	
	Tile blueBird = new Tile(Color.NAVYBLUE, Shape.BIRD);
	Tile greenLeaf = new Tile(Color.GREEN, Shape.FEATHER);
	Tile redFlower = new Tile(Color.RED, Shape.FLOWER);
	
	ArrayList<Tile> listRackTile;
	ArrayList<Image> listTileImage;
	ArrayList<Tile> listOfTile = new ArrayList<Tile>();
	Map<Rectangle, Tile> assocRectangleTile = new HashMap<Rectangle, Tile>();
	static StackPane rootLayout;
	private Label namePlayer1 = new Label();
	private Label namePlayer2 = new Label();

	public static int indexTileClicked;	
	
	//root layout
	BorderPane root = new BorderPane();
	
	//StackPane for background image + BorderPane root onto it
	StackPane stackPane = new StackPane();
	static Stage primaryStageCopy;
	
	
	
	

	public static void main(String[] args) {
		Application.launch(args);

	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent loader = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
		Scene menu = new Scene(loader, 1280, 720);
		MainScreenController MSC = new MainScreenController();

		
		
		//Title
		Text title = new Text("Latice");
		title.setFont(new Font(30));
		root.setTop(title);
		root.setAlignment(title, Pos.CENTER);
		
		//Image
		Pane pane = new Pane();
		BackgroundImage myBG= new BackgroundImage(image,
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT);
		stackPane.setBackground(new Background(myBG));
		
		
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
		
		root.setCenter(pane);
		System.out.println(r);
		
		
		
		
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
						// TODO on mouse clicked on confirm
						
					}
					
				});
		
		//With Image
		Rack rack2 = new Rack(deck);
		HBox rackImage = rack2.createTileImage();
		rackImage.getChildren().add(confirmButton);
		rackImage.setMargin(rackImage.getChildren().get(4), new Insets(0,150,0,0));
		root.setBottom(rackImage);
		
		//Adding lists to Arraylists
		listRackTile = rack2.getListRackTile();
		System.out.println(listRackTile);
		listTileImage = rack2.getRackTileImage();
		System.out.println("listTileImge : " + listTileImage);
		
		
		//------------------------------------------------------------------------
		
		
		//Setting OnDragDetected on tiles
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
		}

		System.out.println((indexTileClicked));
		ImagePattern imagePattern = new ImagePattern(listTileImage.get(getIndexTileClicked()));
		
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
						
						arg0.consume();
					}
					
				});
	        }
		}
		
		root.setLeft(namePlayer1);
		
		//--------------------------------------------------------------------------------------
		setPrimaryStage(primaryStage);
		setRootLayout(stackPane);
		stackPane.getChildren().add(root);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Latice");
		primaryStage.setScene(menu);
		primaryStage.show();
		
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
	
	//player names setters
	public void setNamePlayer1(String namePlayer1) {
		this.namePlayer1.setText(namePlayer1);
	}

	public void setNamePlayer2(String namePlayer2) {
		this.namePlayer2.setText(namePlayer2);
	}
	
	public void playerNamesEntered() {
		System.out.println("entered playNamesEntered()" + namePlayer1 + " VS " + namePlayer2);
		HBox scoreHbox = new HBox();
		scoreHbox.getChildren().add(namePlayer1);
		scoreHbox.getChildren().add(namePlayer2);
		Text working = new Text("Working");
		root.setLeft(working);
		primaryStageCopy.show();
	}

}
