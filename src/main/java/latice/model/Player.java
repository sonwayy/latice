package latice.model;

import java.util.Scanner;

public class Player {
	private final String name;
	private Score score;
	private Rack rack;
	private Deck deck;
	
	public Player(String name, Score score, Deck deck, Rack rack) {
		//Demande le nom du joueur
		Scanner enterPlayerName = new Scanner(System.in);
		System.out.println("Veuilez entrer votre nom " + name + " :");
		String namePlayer = enterPlayerName.next();
		
		this.name = namePlayer;
		this.score = score;
		this.deck = deck;
		this.rack = rack;
	}

	public String getName() {
		return this.name;
	}
	
	public Integer getScore() {
		return this.score.getScore();
	}
	
	public void Play(Scanner play, GameBoard board) {
		System.out.println("c'est à votre tour de jouer " + this.name +"!");
		if (this.getScore() ==  0) {
		
			System.out.println("Vous avez " + this.getScore() + " point");

		}else {
			System.out.println("Vous avez " + this.getScore() + " points");

		}
		
		System.out.print("Quel tuile voulez-vous jouez ? ");
		this.rack.displayRack();
		String tileToPlay = play.next();
		System.out.print("Sur quelle ligne, voulez-vous placer la tuile ?");
		int row = Integer.parseInt(play.next());
		System.out.print("Sur quelle colonne, voulez-vous placer la tuile ?");
		int column = Integer.parseInt(play.next());
		board.setGridBoard(" "+tileToPlay+" ", row, column);
		this.rack.removeTile(tileToPlay);
		
		board.displayGameBoard();
		
		this.rack.updateRack();
		
		
	}
	
	
}
