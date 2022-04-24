package latice.model;

import java.util.ArrayList;

public class Rack {
	private static ArrayList<Tile> listRackTile = new ArrayList<Tile>();
	
	public Rack(Deck deck) {
		System.out.println("Il y a dans le rack : " + listRackTile.size() + " valeurs");
		
		for (int i = 0; i < 5; i++) {
			int index = (int)(Math.random()*((deck.getListTile()).size()-0+1)+0); //(int)(Math.random()*(max-min+1)+min);
			listRackTile.add((deck.getListTile()).get(index));
			System.out.println("l'indice de la tuile ajouté au rack est : " + index + 
					" qui est la tuile : couleur = " + (deck.getListTile()).get(index).getColor() + 
					"  forme = " + (deck.getListTile()).get(index).getShape());
			deck.getListTile().remove(index);
			
		}
		
		System.out.println("Il y a dans le rack : " + listRackTile.size() + " valeurs");
	}

	public static ArrayList<Tile> getListRackTile() {
		return listRackTile;
	}

	public void setListRackTile(ArrayList<Tile> listRackTile) {
		this.listRackTile = listRackTile;
	}
	
	
	
	// TODO add method(s) javafx

}

