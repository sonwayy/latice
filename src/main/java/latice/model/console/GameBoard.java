package latice.model.console;

import latice.model.Position;
import latice.model.Tile;

public class GameBoard {
	private Integer DIMENSION = 9;
	public static final String SUN = " SU ";
	public static final String MOON = " MO ";
	public static final String BLUE = "    ";
	
	private String[][] gridBoard;
	private Tile[][] gridBoardTile = new Tile[DIMENSION][DIMENSION];;
	
	public GameBoard() {
		this.gridBoard = new String[DIMENSION][DIMENSION];
		
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				
				if (i == 4 && j == 4) { //Affiche la lune au centre du plateau
					
					this.gridBoard[i][j] = MOON;
					
					
				}else if (i == j && (i <= 2 || i >= 6)) { //Affiche la diagonale ('\') de soleil
					
					this.gridBoard[i][j] = SUN;
					
				}else if (i == DIMENSION-1-j && (i <= 2 || i >= 6)) { //Affiche la diagonale ('/') de soleil
					
					this.gridBoard[i][j] = SUN;
					
				}else if ( ((i == 0 || i == 8)&& j == 4) || (i== 4 && (j == 0 || j == 8)) ) {//Affiche les soleils au mileu de chaque coté ('+')
					
					this.gridBoard[i][j] = SUN;
					
				}else {
					this.gridBoard[i][j] = BLUE;
				}

			} 
		}
	}
	
	public void displayGameBoard() {
		System.out.println("     1.   2.   3.   4.   5.   6.   7.   8.   9.");
		System.out.println("    ____ ____ ____ ____ ____ ____ ____ ____ ____ ");
		for (int i = 0; i < DIMENSION; i++) {
			System.out.println("   |    |    |    |    |    |    |    |    |    |");
			System.out.print(i+1 + ". ");
			for (int j = 0; j < DIMENSION; j++) {
				System.out.print("|");
				if (i == 4 && j == 4) { //display moon in the center of the board
					if (this.gridBoardTile[i][j] == null) {
						System.out.print(this.gridBoard[i][j]);
					}else {
						System.out.print(" " + this.gridBoardTile[i][j].getShapeConsole() + this.gridBoardTile[i][j].getColorConsole() + " ");
					}
					
				}else if (i == j && (i <= 2 || i >= 6)) { //display the diagonal ('\') of the sun
					if (this.gridBoardTile[i][j] == null) {
						System.out.print(this.gridBoard[i][j]);
					}else {
						System.out.print(" " + this.gridBoardTile[i][j].getShapeConsole() + this.gridBoardTile[i][j].getColorConsole() + " ");
					}
					
				}else if (i == DIMENSION-1-j && (i <= 2 || i >= 6)) { //display the diagonal ('/') of the sun
					if (this.gridBoardTile[i][j] == null) {
						System.out.print(this.gridBoard[i][j]);
					}else {
						System.out.print(" " + this.gridBoardTile[i][j].getShapeConsole() + this.gridBoardTile[i][j].getColorConsole() + " ");
					}
					
				}else if ( ((i == 0 || i == 8)&& j == 4) || (i== 4 && (j == 0 || j == 8)) ) {//display the suns at the middle of each side ('+')
					if (this.gridBoardTile[i][j] == null) {
						System.out.print(this.gridBoard[i][j]);
					}else {
						System.out.print(" " + this.gridBoardTile[i][j].getShapeConsole() + this.gridBoardTile[i][j].getColorConsole() + " ");
					}	
					
				}else {
					if (this.gridBoardTile[i][j] == null) {
						System.out.print(this.gridBoard[i][j]);
					}else {
						System.out.print(" " + this.gridBoardTile[i][j].getShapeConsole() + this.gridBoardTile[i][j].getColorConsole() + " ");
					}
				}
				
				if (j == 8) {
					System.out.println("|");
					System.out.println("   |____|____|____|____|____|____|____|____|____|");
				}
				
				
			} 
		}
	}

	public String[][] getGridBoard() {
		return gridBoard;
	}
	
	public void setGridBoard(String value, Integer x, Integer y) {
		this.gridBoard[x][y] = value;
	}
	
	
	public Tile[][] getGridBoardTile() {
		return gridBoardTile;
	}
	
	public void setGridBoardTile(Tile tile, Integer x, Integer y) {
		tile.setPosition(new Position(x,y));
		this.gridBoardTile[x][y] = tile;
	}
	
	
	
}
