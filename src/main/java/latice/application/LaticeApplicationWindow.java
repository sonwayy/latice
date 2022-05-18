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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import latice.controller.MainScreenController;
import latice.model.Color;
import latice.model.Deck;
import latice.model.Rack;
import latice.model.Shape;
import latice.model.Tile;

public class LaticeApplicationWindow extends Application {
	
	javafx.scene.paint.Color realColor = new javafx.scene.paint.Color(0, 0, 0, 0);
	
	Image image = new Image("laticePlateau.png");
	ImageView imageView = new ImageView(image);
	
	Tile blueBird = new Tile(Color.NAVYBLUE, Shape.BIRD);
	Tile greenLeaf = new Tile(Color.GREEN, Shape.FEATHER);
	Tile redFlower = new Tile(Color.RED, Shape.FLOWER);
	
	ArrayList<Tile> listRackTile = Rack.getListRackTile();	
	ArrayList<Image> listTileImage = Rack.getRackTileImage();
	ArrayList<Tile> listOfTile = new ArrayList<Tile>();
	Map<Rectangle, Tile> assocRectangleTile = new HashMap<Rectangle, Tile>();
	static BorderPane rootLayout;

	
	public static int indexTileClicked;	
	
	
	
	

	public static void main(String[] args) {
		Application.launch(args);

	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent loader = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
		Scene menu = new Scene(loader, 1280, 720);
		MainScreenController MSC = new MainScreenController();
		//root layout
		BorderPane root = new BorderPane();
		
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
		pane.setBackground(new Background(myBG));

		
		//Creating rectangle for tiles placement
		Rectangle[][] r = new Rectangle[9][9];
		int counterI = 0;
		int counterJ = 0;
		for (int i=1; i<10 ; i++) {
			for (int j=1; j < 10 ; j++) {
				
				r[counterI][counterJ] = new Rectangle(i*52+307,j*52+3,50,50);
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
		
		
		//With Image
		Rack rack2 = new Rack(deck);
		HBox rackImage = rack2.createImageTileOfRack();

		root.setBottom(rackImage);
		
		
		//Confirm Button
		Image checkMark = new Image("checkMark.png");
		ImageView checkMarkView = new ImageView(checkMark);
		Button confirmButton = new Button("Confirm", checkMarkView);
		root.setAlignment(confirmButton, Pos.BOTTOM_LEFT);
		confirmButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO on mouse clicked on confirm
				
			}
			
		});
		
		
		root.setRight(confirmButton);
		
		
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
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
		        int a = i;
		        int b = j;
		        
				r[a][b].setOnDragEntered(new EventHandler<DragEvent>() {
	
					@Override
					public void handle(DragEvent arg0) {
						if (arg0.getDragboard().hasString()){
							Dragboard dragboard = arg0.getDragboard();
							
							System.out.println("got files :" + dragboard.getFiles());
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
						
						System.out.println("got files :" + dragboard.getFiles());
						r[a][b].setFill(new ImagePattern(listTileImage.get(getIndexTileClicked())));
						arg0.setDropCompleted(true);
						assocRectangleTile.put(r[a][b], listRackTile.get(getIndexTileClicked()));
						System.out.println(assocRectangleTile.toString());
						
						arg0.consume();
					}
					
				});
	        }
		}
		
		
	
		
		//--------------------------------------------------------------------------------------
		setRootLayout(root);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Latice");
		primaryStage.setScene(menu);
		primaryStage.show();
		
	}
	public static void setRootLayout(BorderPane root) {
		rootLayout = root;
	}
	public static BorderPane getRootLayout() {
		return rootLayout;
	}



	//getter to get the index of the mouse clicked tile
	public static int getIndexTileClicked() {
		return indexTileClicked;
	}





	//Setter to set the mouse clicked tile
	public static void setIndexTileClicked(int indexTileClicked) {
		LaticeApplicationWindow.indexTileClicked = indexTileClicked;
	}
	
	

}
