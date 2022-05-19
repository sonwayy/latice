package latice.model;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Rack {
	private static ArrayList<Tile> listRackTile = new ArrayList<Tile>();
	private static ArrayList<Image> rackTileImage = new ArrayList<Image>();
	
	public Rack(Deck deck) {
		
		Image image = new Image("laticePlateau.png");
		ImageView imageView = new ImageView(image);
		Tile tile;
		
		System.out.println("Il y a dans le rack : " + listRackTile.size() + " valeurs");
		
		for (int i = 0; i < 5; i++) {
			int index = (int)(Math.random()*((deck.getListTile()).size()-0+1)+0); //(int)(Math.random()*(max-min+1)+min);
			
			tile = (deck.getListTile()).get(index);
			
			listRackTile.add(tile);
			
			// root.setCenter(imageView);
			
			deck.getListTile().remove(index);
			
		}
		
		System.out.println("Il y a dans le rack : " + listRackTile.size() + " valeurs");
	}

	public static ArrayList<Tile> getListRackTile() {
		return listRackTile;
	}

	public void setListRackTile(ArrayList<Tile> listRackTile) {
		this.listRackTile = listRackTile;
	}



	public HBox createTileImage() {
		Image image;
		ImageView imageView;
		Tile tile;
		int index;
		
		HBox rack = new HBox();
		
		for (int i = 0; i < 5; i++) {
			index = i;
			
			tile = (this.getListRackTile()).get(index);
			
			image = new Image(tile.getShape().getStringShape() + "_" + tile.getColor().getStringColor()+ ".png");
			rackTileImage.add(image);
			imageView = new ImageView(image);
			imageView.setFitHeight(80);
			imageView.setFitWidth(80);
			rack.getChildren().add(imageView);
		}
		
		rack.setSpacing(10);
        rack.setPadding(new Insets(15,20, 10,10));
		
		rack.setAlignment(Pos.CENTER);
		
		return rack;
		
	}
	
	public static ArrayList<Image> getRackTileImage() {
		return rackTileImage;
	}
	
	
	
	
	
	
	// TODO add method(s) javafx

}

