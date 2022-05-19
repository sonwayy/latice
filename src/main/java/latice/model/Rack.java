package latice.model;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Rack {
	private ArrayList<Tile> listRackTile = new ArrayList<Tile>();
	private ArrayList<Image> rackTileImage = new ArrayList<Image>();
	
	public Rack(Deck deck) {
		
		Tile tile;
		
		System.out.println("Il y a dans le rack : " + listRackTile.size() + " valeurs");
		
		for (int i = 0; i < 5; i++) {
			int index = (int)(Math.random()*(((deck.getListTile()).size()-1)-0+1)+0); //(int)(Math.random()*(max-min+1)+min);
			
			tile = (deck.getListTile()).get(index);
			this.listRackTile.add(tile);
			deck.getListTile().remove(index);
			
			
			
			
			
			
		}
		
		System.out.println("Il y a dans le rack : " + this.listRackTile.size() + " valeurs");
	}

	public ArrayList<Tile> getListRackTile() {
		return this.listRackTile;
	}

	public void setListRackTile(ArrayList<Tile> listRackTile) {
		this.listRackTile = listRackTile;
	}
	
	
	public void updateRack(Deck deck) {
		
		Tile tile;
		
		for (int i = 0; i < 5-this.listRackTile.size() ; i++) {
			int index = (int)(Math.random()*( ((deck.getListTile()).size()-1)-0+1)+0); //(int)(Math.random()*(max-min+1)+min);
			
			tile = (deck.getListTile()).get(index);
			this.listRackTile.add(tile);
			deck.getListTile().remove(index);

		}
		
	}
	
	public void removeTile(String stringTile) {
		int count = 0;
		int index = -1;
		System.out.println("taille : " + this.listRackTile.size());
		for (Tile tile : this.listRackTile) {
			System.out.println(count++);
			if (stringTile.equals(tile.getShapeConsole() + tile.getColorConsole())) {
				index = this.listRackTile.indexOf(tile);
				System.out.println(index);
				System.out.println("tuile supprimé avec succès");
			}
			
		}
		
		if (index != -1) {
			this.listRackTile.remove(index);
		}
		System.out.println("taille : " + this.listRackTile.size());

	}
	
	public void displayRack() {
		boolean success = false;
		System.out.print("rack : ");
		for (Tile tile : this.listRackTile) {
			if (success) {
				System.out.print(", " + tile.getShapeConsole() + tile.getColorConsole());
			}else {
				System.out.print(tile.getShapeConsole() + tile.getColorConsole());
				success = true;
			}
		}
		System.out.println();
	}



	public HBox createImageTileOfRack() {
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
	
	public ArrayList<Image> getRackTileImage() {
		return rackTileImage;
	}
	
	
	
	
	
	
	// TODO add method(s) javafx

}

