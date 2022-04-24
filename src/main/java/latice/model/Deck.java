package latice.model;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Tile> deckTile = new ArrayList<Tile>();
	
	public Deck(ArrayList<Tile> deckTile) {
		this.deckTile = deckTile;
	}

	public void displayListTile() {
		for (Tile tile : deckTile) {
			System.out.println("tuile : couleur = " + tile.getColor() + "  forme = " + tile.getShape());
		}
		
	}
	
	public ArrayList<Tile> getListTile() {
		return this.deckTile;
	}
	
	

	
}
