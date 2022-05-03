package latice.application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import latice.model.Color;
import latice.model.Deck;
import latice.model.Rack;
import latice.model.Shape;
import latice.model.Tile;

public class LaticeApplicationWindow extends Application{
	
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
		root.setCenter(imageView);
		
		//--------------------------------------------------------------------------------------
		//Deck
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		
		Deck deck = new Deck(listOfTile);
		
		
		//--------------------------------------------------------------------------------------
		//Rack
			//Without Image
		HBox rack = new HBox();
		
		rack.setSpacing(10);
        rack.setPadding(new Insets(15,20, 10,10));
		
		Text rackTile1 = new Text();
		rackTile1.setText(blueBird.getShape().toString() + blueBird.getColor());
		Text rackTile2 = new Text();
		rackTile2.setText(greenLeaf.getShape().toString() + greenLeaf.getColor());
		Text rackTile3 = new Text();
		rackTile3.setText(redFlower.getShape().toString() + redFlower.getColor());
		
		rack.getChildren().addAll(rackTile1, rackTile2, rackTile3);
		rack.setAlignment(Pos.CENTER);
		root.setBottom(rack);
		
			//With Image
		Rack rack2 = new Rack(deck);
		HBox rackImage = rack2.createImageTileOfRack(); // TODO Create the deck
		

		root.setBottom(rackImage);
		
		//--------------------------------------------------------------------------------------
		
		Scene scene = new Scene(root, 1280, 720);
		
		primaryStage.setTitle("Latice");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
