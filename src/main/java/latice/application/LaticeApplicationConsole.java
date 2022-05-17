package latice.application;

import java.util.ArrayList;

import latice.model.Color;
import latice.model.Deck;
import latice.model.GameBoard;
import latice.model.Player;
import latice.model.Rack;
import latice.model.Score;
import latice.model.Shape;
import latice.model.Tile;

public class LaticeApplicationConsole {
	public static void main(String[] args) {
		
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		
		System.out.println("Hello Latice !");
		
		/*
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
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
		
		System.out.println("-----------------");
		GameBoard board = new GameBoard(); 
		board.displayGameBoard();
		System.out.println("-----------------");
		board.setGridBoard(" NV ", 4, 4);
		board.displayGameBoard();
		System.out.println("-----------------");
		Score scorePlayer1 = new Score();
		Score scorePlayer2 = new Score();
		Player player1 = new Player("player1", scorePlayer1);
		Player player2 = new Player("player2", scorePlayer2);
		
		System.out.println(player1.getName() + " a " + scorePlayer1.getScore() +" points");
		System.out.println(player2.getName() + " a " + scorePlayer2.getScore() +" points");
		
		rack.displayRack();
		
		*/
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		
		System.out.println("-----------------");
		System.out.println("Notre Deck :");
		Deck deck1 = new Deck(listOfTile);
		Deck deck2 = new Deck(listOfTile);
		deck1.displayListTile();
		System.out.println("-----------------");
		Rack rack1 = new Rack(deck1);
		Rack rack2 = new Rack(deck2);
		Score scorePlayer1 = new Score();
		Score scorePlayer2 = new Score();
		Player player1 = new Player("player1", scorePlayer1);
		Player player2 = new Player("player2", scorePlayer2);
		
		System.out.println("-----------------");
		GameBoard board = new GameBoard(); 
		board.displayGameBoard();
		System.out.println("-----------------");
		
		System.out.println(player1.getName() + " a " + scorePlayer1.getScore() +" points");
		System.out.println(player2.getName() + " a " + scorePlayer2.getScore() +" points");
		rack1.displayRack();

	}

}
