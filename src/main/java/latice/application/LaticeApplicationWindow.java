package latice.application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
	
	Image image = new Image("C:/Users/cemal/saebut1/latice/src/main/resources/laticePlateau.png");
	ImageView imageView = new ImageView(image);
	
	
	
	Tile blueBird = new Tile(Color.BLUE, Shape.BIRD);
	Tile greenLeaf = new Tile(Color.GREEN, Shape.LEAF);
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
		Rectangle rectangle = new Rectangle();

        rectangle.setWidth(50);
        rectangle.setHeight(50);
        //rectangle.setFill(realColor.TRANSPARENT);
		
		Pane pane = new Pane();
		BackgroundImage myBG= new BackgroundImage(image,
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT);
		pane.setBackground(new Background(myBG));
		pane.getChildren().add(rectangle);
		
		for (int i=0; i<450 ;i += 50) {
			for (int j=0; j < 450 ; j += 50) {
				Rectangle r = new Rectangle(i,j,50,50);
				r.setFill(realColor.TRANSPARENT);
				r.setStroke(realColor.BLACK);

				pane.getChildren().add(r);
			}
		}
		pane.setLayoutX(100);
		pane.setLayoutY(50);
		root.setCenter(pane);
		
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
        
        
		rackBox.getChildren().addAll(rackTile1, rackTile2, rackTile3, rackTile4, rackTile5);
		rackBox.setAlignment(Pos.CENTER);
		root.setBottom(rackBox);
		root.setPadding(new Insets(20,20,20,20));
		
		
		
		Scene scene = new Scene(root, 1280, 720);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Latice");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
