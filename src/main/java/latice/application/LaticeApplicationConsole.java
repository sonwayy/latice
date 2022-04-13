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
		
		ArrayList<Tile> tiles =new ArrayList<Tile>(Arrays.asList(redFlower, greenLeaf, redFlower));
		
		System.out.println("Hello Latice ! " + blueBird.getShape() + blueBird.getColor());
		System.out.println("Hello Latice ! " + greenLeaf.getShape() + greenLeaf.getColor());
		System.out.println("Hello Latice ! " + redFlower.getShape() + redFlower.getColor());
		System.out.println("Hello Latice ! " + tiles.get(0).getShape() + tiles.get(0).getColor());
		
		//------------------------------Attribution des tuiles-----------------------------------
		Random random = new Random();
		for(int i=0; i<2; i++) {
			int randomNumber = random.nextInt(3);
			Tile[] rackPlayer1 = {tiles.get(randomNumber), tiles.get(randomNumber)};;
		}
		for(int i=0; i<2; i++) {
			int randomNumber = random.nextInt(3);
			Tile[] rackPlayer2 = {tiles.get(randomNumber), tiles.get(randomNumber)};
		}
		
	}

}
