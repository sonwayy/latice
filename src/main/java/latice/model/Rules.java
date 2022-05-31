package latice.model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Rules {
	//private static boolean START = true;
	
	public Rules() {
		// TODO Auto-generated constructor stub
	}
	
	public Boolean moonRule(GameBoard board, Tile tile) {
		if (GameBoard.MOON.equals(board.getGridBoard()[tile.getPositionRow()][tile.getPositionColumn()])) {
			System.out.println("La première tuile se trouve bien sur la lune !");
			return true;
		}else {
			System.out.println("La première tuile doit être placé sur la lune, recommencez !");
			return false;
		}
	}
	
	
	public Integer neighborRule(GameBoard board, Tile tile) {

		Integer nbrNeighbor = 0;
		String checkNeighbor = null;
		Boolean checkCase = false;
		
		for(int i = 0; i < 2 ; i++) {
			for(int j = -1; j < 2 ; j=j+2) {
				if (i == 0) {
					if (tile.getPositionColumn()+j >= 0 && tile.getPositionColumn()+j <= 9) {
						checkNeighbor = board.getGridBoard()[tile.getPositionRow()][tile.getPositionColumn()+j];
						checkCase = true;
					}
				}else {
					if (tile.getPositionRow()+j >= 0 && tile.getPositionRow()+j <= 9) {
						checkNeighbor = board.getGridBoard()[tile.getPositionRow()+j][tile.getPositionColumn()];
						checkCase = true;
					}
				}
				
				if (checkCase) {
					if (!(GameBoard.SUN.equals(checkNeighbor)) || !(GameBoard.BLUE.equals(checkNeighbor))) {
						
						System.out.println("Il y a une tuile");
						
						if ( tile.getShapeConsole().equals(checkNeighbor.substring(1, 2)) || tile.getColorConsole().equals(checkNeighbor.substring(2, 3)) ) {
							System.out.println("Il y a correspondance avec la tuile !");
							nbrNeighbor = nbrNeighbor + 1;
						}else {
							System.out.println("Il n'y a pas correspondance avec la tuile !");
							
						}
					}
				}
			}
		}
	
		
		return nbrNeighbor;
	}
	
	public Boolean sunRule(GameBoard board, Tile tile) {
		
		Boolean sun;
		
		if (GameBoard.SUN.equals(board.getGridBoard()[tile.getPositionRow()][tile.getPositionColumn()])) {
			sun = true;
		}else {
			sun = false;
		}
		
		return sun;
	}
	
	public Boolean checkScoreToPlay(Player player, Boolean free) {
		if (player.getScore() < 2 && free == false) {
			return false;	
		}else {
			return true;
		}
	}
	
	public Boolean arbitration(Player player, GameBoard board, Tile tile, Boolean start) {
		
		if (start == true){
			return this.moonRule(board, tile);
		}else {
			System.out.println("-----------------------------");
			if (this.sunRule(board, tile)){
				player.addScore(2);
			}
			int nbr = this.neighborRule(board, tile);
			if (nbr == 0) {
				System.out.println("l'emplacement où est posé la tuile n'a pas de voisin ou il n'y a pas de correspondance avec les voisins !");
				return false;
				
			}else {
				if (nbr == 2) {
					System.out.println("Vous avez gagné 1 point");
					player.addScore(1);
				}else if (nbr == 3) {
					System.out.println("Vous avez gagné 2 points");
					player.addScore(2);
				}else if (nbr == 4) {
					System.out.println("Vous avez gagné 4 points");
					player.addScore(4);
				}
				return true;
				
			}
			
			
		}
		
	}
	
}
