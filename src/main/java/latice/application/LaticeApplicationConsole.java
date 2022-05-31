package latice.application;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import latice.model.Color;
import latice.model.Deck;
import latice.model.GameBoard;
import latice.model.Player;
import latice.model.Rack;
import latice.model.Rules;
import latice.model.Score;
import latice.model.Shape;
import latice.model.Tile;

public class LaticeApplicationConsole {
	public static void main(String[] args) {
		
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		
		//System.out.println("Hello Latice !");
		
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
		//System.out.println("Notre Deck :");
		//Deck deck1 = new Deck(listOfTile);
		//Deck deck2 = new Deck(listOfTile);
		//deck1.displayListTile();
		System.out.println("-----------------");
		//Rack rack1 = new Rack(deck1);
		//Rack rack2 = new Rack(deck2);
		//Score scorePlayer1 = new Score();
		//Score scorePlayer2 = new Score();
		//Player player1 = new Player("player1", scorePlayer1);
		//Player player2 = new Player("player2", scorePlayer2);
		
		System.out.println("-----------------");
		//GameBoard board = new GameBoard(); 
		//board.displayGameBoard();
		System.out.println("-----------------");
		
		//System.out.println(player1.getName() + " a " + scorePlayer1.getScore() +" points");
		//System.out.println(player2.getName() + " a " + scorePlayer2.getScore() +" points");
		//rack1.displayRack();

		
		System.out.println("Hello Latice !");
		System.out.println("-----------------");
		
		Deck deck1 = new Deck(listOfTile);
		Deck deck2 = new Deck(listOfTile);
		Rack rack1 = new Rack(deck1);
		Rack rack2 = new Rack(deck2);
		
		Score scorePlayer1 = new Score();
		Score scorePlayer2 = new Score();
		Player player1 = new Player("player1", scorePlayer1, deck1, rack1);
		Player player2 = new Player("player2", scorePlayer2, deck2, rack2);
		
		System.out.println("-----------------");
		GameBoard board = new GameBoard(); 
		board.displayGameBoard();
		
		System.out.println(Objects.equals(board.getGridBoard()[1][0], Tile.class));
		Scanner play = new Scanner(System.in);
		Player player;
		Boolean round;
		Tile tile = null;
		Boolean start = true;
		Boolean freeTile;
		Rules arbitre = new Rules();
		
		for(int i = 0; i < 20; i++) {
			round = true;
			freeTile = true;
			
			if (i%2 == 0) {
				player = player1;
			}else {
				player = player2;
			}
			
			while (round) {
				System.out.println("c'est à votre tour de jouer " + player.getName() +"!");
				System.out.println("Vous avez " + player.getScore() +" points, que voulez-vous faire ?\n"
						+ "    1. Jouer une Tuile (à partir de la deuxième tuile jouée, cela coûtera 2 points)\n"
						+ "    2. Acheter une action supplémentaire\n"
						+ "    3. Changer le Rack et passer(coûte 3 points)\n"
						+ "    4. Passer\n");
				
				int choiceMenu = Integer.parseInt(play.next());
				switch(choiceMenu) {
					case 1:		if (arbitre.checkScoreToPlay(player, freeTile) == false){
									System.out.println("Il vous faut 2 points pour jouer un nouvelle tuile !!!");
								}else {
									Boolean rulesCheck = false;
									
									while (rulesCheck == false) {
										
										tile = player.Play(play,board,i);
										rulesCheck = arbitre.arbitration(player, board, tile, start);
										
									};
									
									if (i == 0) {
										start = false;
									}
									
									board.setGridBoard(" "+tile.getShapeConsole()+tile.getColorConsole()+" ", tile.getPositionRow(), tile.getPositionColumn());
									
									player.getRack().removeTile(tile);
									board.displayGameBoard();
								}
								break;
							
							
					case 2:
						
					case 3: player.getRack().changeRack();
							System.out.println("Votre rack à été changé avec succès !");
					
					case 4: System.out.println("Votre tour est terminé " + player.getName() + " !");
							round = false;
							break;
					
					default: throw new IllegalArgumentException("Veuillez choisir un nombre entre 1 et 4!");
				}
			}
			
			
			/*
			if (PlayOrPass == 2) {
				round = false;
				System.out.println("Votre tour est terminé " + player.getName() + " !");
			}
			
			while (round) {
				Boolean rulesCheck = false;
				
				System.out.println("c'est à votre tour de jouer " + player.getName() +"!");
				
				while (rulesCheck == false) {
					tile = player.Play(play,board,i);
					rulesCheck = arbitre.arbitration(player, board, tile, START);
					
				};
				
				if (i == 0) {
					START = false;
				}
				
				board.setGridBoard(" "+tile.getShapeConsole()+tile.getColorConsole()+" ", tile.getPositionRow(), tile.getPositionColumn());
				
				player.getRack().removeTile(tile);
				board.displayGameBoard();
				
			
				System.out.println(player.getName() + " ! Voulez-vous passer votre tour ou continuer à jouer ? 1.continuer ou 2.passer");
				int ContinueOrPass = Integer.parseInt(play.next());
				if (ContinueOrPass == 2) {
					round = false;
					System.out.println("Votre tour est terminé " + player.getName() + " !");
				}
			}
			
			player.getRack().updateRack();*/
			
			
		}
	}

}
