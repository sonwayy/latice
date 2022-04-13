package latice.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import latice.model.Color;
import latice.model.Shape;
import latice.model.Tile;

public class LaticeApplicationConsole {
	public static void main(String[] args) {
		
		Tile blueBird = new Tile(Color.BLUE, Shape.BIRD);
		Tile greenLeaf = new Tile(Color.GREEN, Shape.LEAF);
		Tile redFlower = new Tile(Color.RED, Shape.FLOWER);
		ArrayList<Tile> rackPlayer1 = new ArrayList<Tile>();
		ArrayList<Tile> rackPlayer2 = new ArrayList<Tile>();
		int tilesNumber = 3;
		
		ArrayList<Tile> tiles =new ArrayList<Tile>(Arrays.asList(blueBird, greenLeaf, redFlower));
		
		System.out.println("Hello Latice ! " + blueBird.getShape() + blueBird.getColor());
		System.out.println("Hello Latice ! " + greenLeaf.getShape() + greenLeaf.getColor());
		System.out.println("Hello Latice ! " + redFlower.getShape() + redFlower.getColor());
		System.out.println("Hello Latice ! " + tiles.get(0).getShape() + tiles.get(0).getColor());
		
		//------------------------------Tiles attribution-----------------------------------
		Random random = new Random();
		for(int i=0; i<=tilesNumber; i++) {
			if(! tiles.isEmpty()) {
				int randomNumber = random.nextInt(tiles.size());
				if(i%2 == 0) {
					rackPlayer1.add(tiles.get(randomNumber));
					System.out.println(rackPlayer1.get(0).getColor().toString() + rackPlayer1.get(0).getShape().toString());
				} else {
					rackPlayer2.add(tiles.get(randomNumber));
					System.out.println(rackPlayer2.get(0).getColor().toString() + rackPlayer2.get(0).getShape().toString());
				}
				tiles.remove(randomNumber);
				System.out.println(randomNumber);
				System.out.println(rackPlayer1);
				System.out.println(rackPlayer2);
			}
			
		}
		
		
	}

}
