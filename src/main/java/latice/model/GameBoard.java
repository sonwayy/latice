package latice.model;

public class GameBoard {
	private Integer DIMENSION = 9;
	private final String SUN = " SU ";
	private final String MOON = " MO ";
	private String[][] gridBoard;
	
	public GameBoard() {
		this.gridBoard = new String[DIMENSION][DIMENSION];
		
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				System.out.print("|");
				if (i == 4 && j == 4) { //Affiche la lune au centre du plateau
					
					this.gridBoard[i][j] = MOON;
					
					
				}else if (i == j && (i <= 2 || i >= 6)) { //Affiche la diagonale ('\') de soleil
					
					this.gridBoard[i][j] = SUN;
					
				}else if (i == DIMENSION-1-j && (i <= 2 || i >= 6)) { //Affiche la diagonale ('/') de soleil
					
					this.gridBoard[i][j] = SUN;
					
				}else if ( ((i == 0 || i == 8)&& j == 4) || (i== 4 && (j == 0 || j == 8)) ) {//Affiche les soleils au mileu de chaque coté ('+')
					
					this.gridBoard[i][j] = SUN;
					
				}else {
					this.gridBoard[i][j] = "    ";
				}
				
				if (j == 8) {
					System.out.println("|");
				}
			} 
		}
	}
	
	public void displayGameBoard() {
		
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				System.out.print("|");
				if (i == 4 && j == 4) { //Affiche la lune au centre du plateau
					
					System.out.print(this.gridBoard[i][j]);
					
				}else if (i == j && (i <= 2 || i >= 6)) { //Affiche la diagonale ('\') de soleil
					
					System.out.print(this.gridBoard[i][j]);
					
				}else if (i == DIMENSION-1-j && (i <= 2 || i >= 6)) { //Affiche la diagonale ('/') de soleil
					
					System.out.print(this.gridBoard[i][j]);
					
				}else if ( ((i == 0 || i == 8)&& j == 4) || (i== 4 && (j == 0 || j == 8)) ) {//Affiche les soleils au mileu de chaque coté ('+')
					
					System.out.print(this.gridBoard[i][j]);		
					
				}else {
					System.out.print(this.gridBoard[i][j]);
				}
				
				if (j == 8) {
					System.out.println("|");
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
	
	
	
}
