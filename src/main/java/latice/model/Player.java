package latice.model;

import java.util.Scanner;

public class Player {
	private final String name;
	private Score score;
	
	public Player(String name, Score score) {
		//Demande le nom du joueur
		Scanner enterPlayerName = new Scanner(System.in);
		System.out.println("Veuilez entrer votre nom " + name + " :");
		String namePlayer = enterPlayerName.next();
		
		this.name = namePlayer;
		this.score = score;
	}

	public String getName() {
		return name;
	}
	
	
}
