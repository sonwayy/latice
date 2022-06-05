package latice.model.window;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import latice.model.Player;

public class PlayerFX {
	private Text name;
	private Text score;
	private Text nbrOfTiles;
	private VBox infoPlayer;
	
	public VBox displayPlayers(StackPane root, Player player) {
		//###################### display name, score and deck of each player ######################//
		//HBox players = new HBox();
		
		//ArrayList<Player> allPlayers = new ArrayList<>();
		//allPlayers.add(player1);
		//allPlayers.add(player2);
		
		//for (Player namePlayer : allPlayers ) {
			this.infoPlayer = new VBox();
			
			this.name = new Text();
			this.name.setFont(Font.font(player.getName(), FontWeight.BOLD, 20));
			this.name.setText(player.getName());
			
			this.score = new Text();
			this.score.setText("Score : " + player.getScore());
			
			this.nbrOfTiles = new Text();
			this.nbrOfTiles.setText("Tuiles restantes : " + player.getNumberOfTilesRemaining());
			
			this.infoPlayer.getChildren().addAll(name, score, nbrOfTiles);
			this.infoPlayer.setSpacing(5);
			
			//players.getChildren().add(infoPlayer);
			//players.setMargin(infoPlayer, new Insets(50,0,0,55));
		//}
		
		//players.setSpacing(850);
		return this.infoPlayer;
	}
	
	public void setFillName(javafx.scene.paint.Color color) {
		name.setFill(color);
	}
	
	public void setAddScore(Player player, Integer score) {
		player.addScore(score);
		this.score.setText("Score : " + player.getScore());
	}
	
	public void setDiffScore(Player player, Integer score) {
		player.diffScore(score);
		this.score.setText("Score : " + player.getScore());
	}
	
	public void setTilesRemaining(Player player) {
		this.nbrOfTiles.setText("Tuiles restantes : " + player.getNumberOfTilesRemaining());
	}
	
	
	
}
