package latice.model.console;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import latice.model.Tile;

public class Rack {
	private ArrayList<Tile> listRackTile = new ArrayList<Tile>();
	private ArrayList<Image> rackTileImage = new ArrayList<Image>();
	private Deck deck;
	
	public Rack(Deck deck) {
		this.deck = deck;
		
		Tile tile;
		
		//System.out.println("Il y a dans le rack : " + listRackTile.size() + " valeurs");
		
		for (int i = 0; i < 5; i++) {
			int index = (int)(Math.random()*(((this.deck.getListTile()).size()-1)-0+1)+0); //(int)(Math.random()*(max-min+1)+min);
			
			tile = (this.deck.getListTile()).get(index);
			this.listRackTile.add(tile);
			this.deck.getListTile().remove(index);
	
		}
		
		//System.out.println("Il y a dans le rack : " + this.listRackTile.size() + " valeurs");
	}

	public ArrayList<Tile> getListRackTile() {
		return this.listRackTile;
	}

	public void setListRackTile(ArrayList<Tile> listRackTile) {
		this.listRackTile = listRackTile;
	}
	
	
	public void updateRack() {
		
		Tile tile;
		//System.out.println("nomdre de tuile dans le rack : " + this.listRackTile.size());
		for (int i = this.listRackTile.size(); i < 5 ; i++) {
			int index = (int)(Math.random()*( ((this.deck.getListTile()).size()-1)-0+1)+0); //(int)(Math.random()*(max-min+1)+min);
			
			tile = (this.deck.getListTile()).get(index);
			this.listRackTile.add(tile);
			this.deck.getListTile().remove(index);
			
		}
		
	}
	
	public void removeTile(Tile tileToDelete) {
		int count = 0;
		int index = -1;
		System.out.println("taille : " + this.listRackTile.size());
		String strTileToDelete = tileToDelete.getShapeConsole()+tileToDelete.getColorConsole();
		for (Tile tile : this.listRackTile) {
			System.out.println(count++);
			if (strTileToDelete.equals(tile.getShapeConsole()+tile.getColorConsole())) {
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
	
	public void changeRack() {
		
		Tile tile;
		int listRackTileSize = this.listRackTile.size();
		
		for (int i = 0; i < listRackTileSize ; i++) {
			
			tile = this.listRackTile.get(0);
			this.deck.getListTile().add(tile);
			this.listRackTile.remove(0);
		}
		
		for (int i = 0; i < listRackTileSize ; i++) {
			int index = (int)(Math.random()*( ((this.deck.getListTile()).size()-1)-0+1)+0); //(int)(Math.random()*(max-min+1)+min);
			
			tile = (this.deck.getListTile()).get(index);
			this.listRackTile.add(tile);
			this.deck.getListTile().remove(index);
			
		}
		
	}
	
	public void displayRack() {
		boolean success = false;
		Integer tile_id = 1;
		System.out.print("rack : ");
		for (Tile tile : this.listRackTile) {
			if (success) {
				System.out.print(", " + tile_id + "." + tile.getShapeConsole() + tile.getColorConsole());
			}else {
				System.out.print(tile_id + "." + tile.getShapeConsole() + tile.getColorConsole());
				success = true;
			}
			tile_id = tile_id + 1;
		}
		System.out.println();
	}



	public HBox createTileImage() {
		Image image;
		ImageView imageView;
		Tile tile;
		int index;
		
		HBox rack = new HBox();
		rackTileImage.clear();
		
		for (int i = 0; i < this.getListRackTile().size(); i++) {
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
		return this.rackTileImage;
	}
	
	
	
	
	
	
	// TODO add method(s) javafx

}

