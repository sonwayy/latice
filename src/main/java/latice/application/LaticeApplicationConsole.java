package latice.application;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.scene.image.Image;
import latice.model.Color;
import latice.model.Deck;
import latice.model.Rack;
import latice.model.Shape;
import latice.model.Tile;

public class LaticeApplicationConsole {
	public static void main(String[] args) {
		
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
		deck.displayListTile();
		System.out.println("-----------------");
		Rack rack = new Rack(deck);
		System.out.println("-----------------");
		deck.displayListTile();

		
		
	}

}
