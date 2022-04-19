package latice.application;

import java.util.ArrayList;

import latice.model.Color;
import latice.model.Deck;
import latice.model.Shape;
import latice.model.Tile;

public class LaticeApplicationConsole {
	public static void main(String[] args) {
		
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		
		System.out.println("Hello Latice !");
		
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println("tuile : couleur = " + tile.getColor() + "  forme = " + tile.getShape());
				listOfTile.add(tile);
				
			}
		}
		
		
		System.out.println("Notre Deck :");
		Deck deck = new Deck(listOfTile);
		deck.getListTile();
		System.out.println("-----------------");


		
		
	}

}
