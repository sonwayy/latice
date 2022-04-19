package latice.model;

import java.util.ArrayList;

public class Rack {
	private ArrayList<Tile> listRackTile = new ArrayList<Tile>();
	
	public Rack(ArrayList<Tile> deck) {
		for (int i = 0; i < 5; i++) {
			int index = (int)(Math.random()*(deck.size()-0+1)+0);
			listRackTile.add(deck.get(index));
			System.out.println("l'indice de la tuile ajouté au rack est : " + index + " qui est la tuile " + deck.get(index));
			
		}
		
		
	}
}


//(int)(Math.random()*(max-min+1)+min);  