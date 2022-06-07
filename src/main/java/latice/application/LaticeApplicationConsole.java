package latice.application;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import latice.model.Color;
import latice.model.Player;
import latice.model.Rules;
import latice.model.Shape;
import latice.model.Tile;
import latice.model.console.Deck;
import latice.model.console.GameBoard;
import latice.model.console.Rack;
import latice.model.console.Score;

public class LaticeApplicationConsole {
	public static void main(String[] args) {
		
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				
				listOfTile.add(tile);
				
			}
		}


		
		System.out.println("Hello Latice !");
		System.out.println("-----------------");
		
		Deck deck1 = new Deck(listOfTile);
		Deck deck2 = new Deck(listOfTile);

		Player player1 = new Player("player1", deck1);
		Player player2 = new Player("player2", deck2);
		

		GameBoard board = new GameBoard();
		board.displayGameBoard();
		
		
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
			
			System.out.println("c'est à votre tour de jouer " + player.getName() +"!");
			
			while (round) {
				player.getRack().displayRack();
				System.out.println("Vous avez " + player.getScore() +" points, que voulez-vous faire ?\n"
						+ "    1. Jouer une Tuile\n"
						+ "    2. Acheter une action supplémentaire(coûte 2 points)\n"
						+ "    3. Changer le Rack et passer(coûte 3 points)\n"
						+ "    4. Passer\n");
				
				System.out.print("Choix : ");
				int choiceMenu = Integer.parseInt(play.next());
				switch(choiceMenu) {
				
					case 1:	if (freeTile) {	
								freeTile = false;
								Boolean rulesCheck = false;
								
								while (rulesCheck == false) {
									
									tile = player.Play(play,board,i);
									rulesCheck = arbitre.arbitration(player, board, tile, start);
									
								};
								
								if (i == 0) {
									start = false;
								}
								
								board.setGridBoardTile(tile, tile.getPositionRow(), tile.getPositionColumn());
								
								
								player.getRack().removeTile(tile);
								board.displayGameBoard();
								
							}else {
								System.out.println("Vous n'avez pas acheter une action pour jouer un autre tuile dans ce tour ! Veuillez en acheter une ou passer votre tour !");
							}
							
							break;
							
							
					case 2:
							if (freeTile) {
								System.out.println("Vous avez dejà une action pour jouer une tuile ! Veuillez jouer votre tuile d'abord pour ensuite acheter une nouvelle action pour jouer une autre tuile !");
							}else {
								if (player.getScore()>=2) {
									//Buy another action and remove 2 points from score
									player.Play(play, board, 0);
									player.removePointsFromScore(2);
									freeTile = true;
								}else {
									System.out.println("Il vous faut 2 points pour acheter une nouvelle action !");
								}
							}
							break;
								
							
						
					case 3: player.getRack().changeRack();
							System.out.println("Votre rack à été changé avec succès !");
					
					case 4: System.out.println("Votre tour est terminé " + player.getName() + " !");
							player.getRack().updateRack();
							round = false;
							break;
					
					default: throw new IllegalArgumentException("Veuillez choisir un nombre entre 1 et 4!");
				}
			}
			
			
			System.out.println("-------------------------------------------------------------------------------------------");
			
			if (player1.getNumberOfTilesRemaining() < player2.getNumberOfTilesRemaining()) {
				System.out.println("-------------- Félicitations" + player1.getName() + " pour votre victoire !! --------------");
				System.out.println("-------------- Nos condoléances " + player2.getName() + " pour votre victoire !! --------------");
				System.out.println("Le gagnant de la partie est " + player1.getName() + " car il a posé le plus de tuiles sur le plateau Latice !!");
				System.out.println(player2.getName() + ", vous êtes tombés sur un joueur plus fort que vous. Réentrainez-vous pour pouvoir prendre votre vengeance hehe");
			
			}else if (player2.getNumberOfTilesRemaining() < player1.getNumberOfTilesRemaining()){
				System.out.println("-------------- Félicitations" + player2.getName() + " pour votre victoire !! --------------");
				System.out.println("-------------- Nos condoléances " + player1.getName() + " pour votre victoire !! --------------");
				System.out.println("Le gagnant de la partie est " + player2.getName() + " car il a posé le plus de tuiles sur le plateau Latice !!");
				System.out.println(player1.getName() + ", vous êtes tombés sur un joueur plus fort que vous. Réentrainez-vous pour pouvoir prendre votre vengeance hehe");
			}else {
				System.out.println("Match nul !! Recommencez une partie si vous voulez vous départagez !!");
			}
			
			
		}
	}

}
