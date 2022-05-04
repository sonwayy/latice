package latice.application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import latice.model.Color;
import latice.model.Deck;
import latice.model.Rack;
import latice.model.Shape;
import latice.model.Tile;

public class LaticeApplicationWindow extends Application{
	
	javafx.scene.paint.Color realColor = new javafx.scene.paint.Color(0, 0, 0, 0);
	
	Image image = new Image("laticePlateau.png");
	ImageView imageView = new ImageView(image);
	
	Tile blueBird = new Tile(Color.NAVYBLUE, Shape.BIRD);
	Tile greenLeaf = new Tile(Color.GREEN, Shape.FEATHER);
	Tile redFlower = new Tile(Color.RED, Shape.FLOWER);
	ArrayList<Image> listTileImage = Rack.getRackTileImage();	
	
	
	
	
	
	

	public static void main(String[] args) {
		Application.launch(args);

	}
	
	
	
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		Text title = new Text("Latice");
		title.setFont(new Font(30));
		root.setTop(title);
		root.setAlignment(title, Pos.CENTER);
		
		//Image

        //rectangle.setFill(realColor.TRANSPARENT);
		
		Pane pane = new Pane();
		BackgroundImage myBG= new BackgroundImage(image,
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT);
		pane.setBackground(new Background(myBG));

		
		Rectangle r[] = new Rectangle[81];
		int counter = 0;
		for (int i=1; i<10 ; i++) {
			for (int j=1; j < 10 ; j++) {
				
				r[counter] = new Rectangle(i*52+358,j*52+3,50,50);
				r[counter].setFill(realColor.TRANSPARENT);
				r[counter].setStroke(realColor.BLACK);

				pane.getChildren().add(r[counter]);
				System.out.println(r[counter]);
				counter++;
			}
		}
		
		root.setCenter(pane);
		System.out.println(r);
		
		
		
		
		//--------------------------------------------------------------------------------------
		//Rack
		HBox rackBox = new HBox();
		
		rackBox.setSpacing(10);
        rackBox.setPadding(new Insets(15,20, 10,10));
		
		
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		
		System.out.println("Hello Latice !");
		
		
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
		HBox rackImage = rack2.createImageTileOfRack(); // TODO Create the deck
	

		root.setBottom(rackImage);
		
		ArrayList<Tile> listRackTile = Rack.getListRackTile();	
		
		for (int i=0; i<5; i++) {
			int a = i;
			rackImage.getChildren().get(a).setOnDragDetected(new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent arg0) {
					Dragboard dragboard = rackImage.getChildren().get(a).startDragAndDrop(TransferMode.ANY);
					ClipboardContent content = new ClipboardContent();
					content.putString("Hello !");
					dragboard.setContent(content);
					arg0.consume();
				}
			});
		}
		ImagePattern imagePattern = new ImagePattern(listTileImage.get(0));
		
		for(int i=1; i<81; i++) {
	        int a = i;
	        System.out.println(a);
			r[a].setOnDragEntered(new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent arg0) {
					if (arg0.getDragboard().hasString()){
						Dragboard dragboard = arg0.getDragboard();
						
						System.out.println("got files :" + dragboard.getFiles());
						r[a].setFill(imagePattern);
					}
					arg0.consume();
				}
			});
			
			r[a].setOnDragExited(new EventHandler<DragEvent>() {
	
				@Override
				public void handle(DragEvent arg0) {
					if (arg0.isDropCompleted() == false) {
						r[a].setFill(realColor.TRANSPARENT);
					}
					arg0.consume();
				}
				
			});
			
			r[a].setOnDragOver(new EventHandler <DragEvent>() {
				@Override
			    public void handle(DragEvent arg0) {
			        arg0.acceptTransferModes(TransferMode.ANY);
			        arg0.consume();
			    }
			});
			
			r[a].setOnDragDropped(new EventHandler<DragEvent>() {
				@Override
				public void handle(DragEvent arg0) {
					System.out.println("entered");
					Dragboard dragboard = arg0.getDragboard();
					
					System.out.println("got files :" + dragboard.getFiles());
					r[a].setFill(imagePattern);
					arg0.setDropCompleted(true);
					
					arg0.consume();
				}
				
			});
        }
		
		
	
		
		//--------------------------------------------------------------------------------------
		
		Scene scene = new Scene(root, 1280, 720);
		
		//primaryStage.setResizable(false);
		primaryStage.setTitle("Latice");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
