package latice.model.window;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import latice.model.Player;

public class PlayerFX {

	
	public static HBox displayPlayers(StackPane root, Player player1, Player player2) {
		//###################### display name, score and deck of each player ######################//
		HBox players = new HBox();
		
		ArrayList<Player> allPlayers = new ArrayList<>();
		allPlayers.add(player1);
		allPlayers.add(player2);
		
		for (Player nameplayer : allPlayers ) {
			VBox player = new VBox();
			
			Text name = new Text();
			name.setFont(Font.font(nameplayer.getName(), FontWeight.BOLD, 20));
			name.setText(nameplayer.getName());
			
			Text score = new Text();
			score.setText("Score : ");
			
			Text nbrOfTiles = new Text();
			nbrOfTiles.setText("Tuiles restantes : ");
			
			player.getChildren().addAll(name, score, nbrOfTiles);
			player.setSpacing(5);
			
			players.getChildren().add(player);
			players.setMargin(player, new Insets(50,0,0,55));
		}
		System.out.println("largeur : " + root.getMaxWidth());
		players.setSpacing(850);
		return players;
	}
	
}
