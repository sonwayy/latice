package latice.model;

import java.util.Scanner;

import latice.model.console.Deck;
import latice.model.console.GameBoard;
import latice.model.console.Rack;
import latice.model.console.Score;

public class Player {
	private final String name;
	private Score score;
	private Rack rack;
	private Deck deck;
	
	public Player(String name, Score score, Deck deck, Rack rack) {
		this.name = name;
		this.score = score;
		this.deck = deck;
		this.rack = rack;
	}
	
	public Player(String name, Deck deck) {
		this(name, new Score(), deck, new Rack(deck));
	}
	
	/*public Player(Score score, Deck deck, Rack rack) {
		this(namePlayer,score,deck,rack);
		//Demande le nom du joueur
		Scanner enterPlayerName = new Scanner(System.in);
		System.out.println("Veuilez entrer votre nom :");
		String namePlayer = enterPlayerName.next();
		
		
	}*/
	
	
	

	public String getName() {
		return this.name;
	}
	
	public Rack getRack() {
		return this.rack;
	}
	
	public Integer getScore() {
		return this.score.getScore();
	}
	
	public Integer getNumberOfTilesRemaining() {
		return this.deck.getListTile().size() + this.rack.getListRackTile().size();
	}
	
	public Integer addPointsToScore(Integer value) {
		int newScore = this.score.getScore()+value;
		this.score.setScore(newScore);
		return this.score.getScore();
	}
	
	public Integer removePointsFromScore(Integer value) {
		int newScore = this.score.getScore()-value;
		this.score.setScore(newScore);
		return this.score.getScore();
	}
	
	public Tile Play(Scanner play, GameBoard board, Integer start) {
		
		if (this.getScore() <=  1) {
		
			System.out.println("Vous avez " + this.getScore() + " point");

		}else {
			System.out.println("Vous avez " + this.getScore() + " points");

		}
		
		System.out.print("Quel tuile voulez-vous jouez ? ");
		this.rack.displayRack();
		Integer idTileToPlay = Integer.parseInt(play.next())-1;
		Tile tileToPlay = this.rack.getListRackTile().get(idTileToPlay);
		System.out.print("Sur quelle ligne, voulez-vous placer la tuile ?");
		int row = Integer.parseInt(play.next())-1;
		System.out.print("Sur quelle colonne, voulez-vous placer la tuile ?");
		int column = Integer.parseInt(play.next())-1;
		
		tileToPlay.setPosition(new Position(row, column));
		return tileToPlay;
		
		//.setGridBoard(" "+tileToPlay.getShapeConsole()+tileToPlay.getColorConsole()+" ", row, column);
		//this.rack.removeTile(tileToPlay);
		
		//board.displayGameBoard();
		
		//this.rack.updateRack();
		
		
	}
	
	
}
