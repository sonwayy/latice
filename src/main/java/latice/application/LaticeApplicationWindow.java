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
		for (int i=1; i<10 ; i++) {
			for (int j=1; j < 10 ; j++) {
				r[i] = new Rectangle(i*52+336,j*52+15,50,50);
				r[i].setFill(realColor.TRANSPARENT);
				r[i].setStroke(realColor.BLACK);

				pane.getChildren().add(r[i]);
				System.out.println(r[i]);
			}
		}
		
		root.setCenter(pane);
		
		
		
		
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
		
		ArrayList<Tile> listRackTile = Rack.getListRackTile();
		
		
		Text rackTile1 = new Text();
		rackTile1.setText(listRackTile.get(0).getShape().toString() + listRackTile.get(0).getColor().toString());
		Text rackTile2 = new Text();
		rackTile2.setText(listRackTile.get(1).getShape().toString() + listRackTile.get(1).getColor().toString());
		Text rackTile3 = new Text();
		rackTile3.setText(listRackTile.get(2).getShape().toString() + listRackTile.get(2).getColor().toString());
		Text rackTile4 = new Text();
		rackTile4.setText(listRackTile.get(3).getShape().toString() + listRackTile.get(3).getColor().toString());
		Text rackTile5 = new Text();
		rackTile5.setText(listRackTile.get(4).getShape().toString() + listRackTile.get(4).getColor().toString());
        
        
		rackTile1.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Dragboard dragboard = rackTile1.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString("Hello !");
				dragboard.setContent(content);
				arg0.consume();
			}
			
		});
		
		ImagePattern imagePattern = new ImagePattern(image);
		r[1].setOnDragEntered(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent arg0) {
				if (arg0.getDragboard().hasString()){
					r[1].setFill(imagePattern);
				}
				arg0.consume();
			}
		});
		
		r[1].setOnDragExited(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent arg0) {
				r[1].setFill(realColor.BLUE);
				arg0.consume();
			}
			
		});
		
		r[1].setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent arg0) {
				System.out.println("entered");
				Dragboard dragboard = arg0.getDragboard();
				boolean success = false;
				r[1].setFill(imagePattern);
				System.out.println("got files :" + dragboard.getFiles());
				if (dragboard.hasString()){
					success = true;
					r[1].setFill(imagePattern);
				}
				arg0.setDropCompleted(success);
				
				arg0.consume();
			}
			
		});
		
		rackBox.getChildren().addAll(rackTile1, rackTile2, rackTile3, rackTile4, rackTile5);
		rackBox.setAlignment(Pos.CENTER);
		root.setBottom(rackBox);
		root.setPadding(new Insets(20,20,20,20));
		
		
		
			//With Image
		Rack rack2 = new Rack(deck);
		HBox rackImage = rack2.createImageTileOfRack(); // TODO Create the deck
		

		root.setBottom(rackImage);
		
		//--------------------------------------------------------------------------------------
		
		Scene scene = new Scene(root, 1280, 720);
		
		//primaryStage.setResizable(false);
		primaryStage.setTitle("Latice");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
