package latice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import latice.model.Color;
import latice.model.Player;
import latice.model.Position;
import latice.model.Rules;
import latice.model.Shape;
import latice.model.Tile;
import latice.model.console.Deck;
import latice.model.console.GameBoard;
import latice.model.console.Rack;
import latice.model.console.Score;

class LaticeTest {
	
	//VEUILLEZ SAISIR Marc COMME NOM DU JOUEUR DANS LA CONSOLE
	

	@Test
	void should_return_tile_with_position_shape_color() {
		//Arrange
		Tile tile = new Tile(Color.GREEN, Shape.BIRD);
		final int COLUMN = 10;
		final int ROW = 10;
		
		
		//Act
		Color returnedColor = tile.getColor();
		Shape returnedShape = tile.getShape();
		tile.setPosition(new Position(ROW, COLUMN));
		Integer returnedRowPosition = tile.getPositionRow();
		Integer returnedColumnPosition = tile.getPositionColumn();
		
		
		//Assert
		assertEquals(Color.GREEN, returnedColor);
		assertEquals(Shape.BIRD, returnedShape);
		assertEquals(ROW, returnedRowPosition);
		assertEquals(COLUMN, returnedColumnPosition);
		
	}
	
	@Test
	void should_return_good_score() {
		//Arrange
		Score score = new Score();
		final int POINTS = 0;
		
		//Act
		score.setScore(POINTS);
		
		//Assert
		assertEquals(POINTS, score.getScore());
	}
	
	@Test
	void deck_test(){
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		
		//Act
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		
		//Assert
		assertEquals(listOfTile, deck.getListTile());
			
		
	}
	
	@Test
	void should_change_rack() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Deck deck2;
		Rack rack;
		Rack rack2;
		
		//Act
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		deck2 = new Deck(listOfTile);
		
		rack = new Rack(deck);
		rack2 = new Rack(deck2);
		
		//Assert
		assertEquals(5, rack.getListRackTile().size());
		
		//changeRack Test
		rack2.changeRack();
		
		assertThat(rack.getListRackTile())
			.isNotEqualTo(rack2.getListRackTile());
		
		
	}
	
	@Test
	void should_set_new_rack() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Deck deck2;
		Rack rack;
		Rack rack2;
		
		//Act
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		deck2 = new Deck(listOfTile);
		rack = new Rack(deck);
		rack2 = new Rack(deck2);
		rack.setListRackTile(rack2.getListRackTile());
		
		//Assert
		assertEquals(rack.getListRackTile(), rack2.getListRackTile());
	}
	
	@Test
	void should_remove_tile_from_rack() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Rack rack;
		
		//Act
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		rack = new Rack(deck);
		rack.removeTile(rack.getListRackTile().get(0));
		
		//Assert
		assertEquals(4, rack.getListRackTile().size());
		
	}
	
	@Test
	void test_getters_and_seters_of_Player_class() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Rack rack;
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		rack = new Rack(deck);
		Player player = new Player("Marc", new Score(), deck, rack);
		
		//Act
		
		
		//Assert
		assertEquals("Marc", player.getName());
		assertEquals(0, player.getScore());
		assertEquals(rack, player.getRack());
		
		player.addPointsToScore(2);
		assertEquals(2, player.getScore());
		
		player.removePointsFromScore(1);
		assertEquals(1, player.getScore());
		
	}

	
	@Test
	void should_create_tile_image() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Rack rack;
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		rack = new Rack(deck);
		Player player = new Player("Marc", new Score(), deck, rack);
		
		//Act
		
		//Assert
		//assertEquals(5, player.getRack().createTileImage().getChildren().size());
		
	}
	
	@Test
	void should_get_gridboard() {
		GameBoard gameBoard = new GameBoard();
		String[][] gridBoard = gameBoard.getGridBoard();
		assertEquals(9, gridBoard.length);
	}
	
	@Test
	void should_set_gridboard() {
		GameBoard gameBoard = new GameBoard();
		gameBoard.setGridBoard("SUN", 0, 0);
		String[][] gridBoard = gameBoard.getGridBoard();
		
		assertEquals("SUN", gridBoard[0][0]);
	}
	
	@Test
	void should_get_gridboard_tile() {
		GameBoard gameBoard = new GameBoard();
		Tile[][] tiles = gameBoard.getGridBoardTile();
		assertEquals(9, tiles.length);
	}
	
	@Test
	void should_set_gridboard_tile() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Rack rack;
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		rack = new Rack(deck);
		Player player = new Player("Marc", new Score(), deck, rack);
		
		GameBoard gameBoard = new GameBoard();
		Tile[][] tiles = gameBoard.getGridBoardTile();
		System.out.println("tiles\n\n" + tiles[0][0]);
		gameBoard.setGridBoardTile(player.getRack().getListRackTile().get(0), 1, 0);
		assertEquals(player.getRack().getListRackTile().get(0), gameBoard.getGridBoardTile()[1][0]);
	}
	
	@Test
	void should_display_list_tile() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		
		//Act
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		deck.displayListTile();
		
	}
	
	@Test
	void should_return_rackTileImage() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Rack rack;
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		rack = new Rack(deck);
		
		assertEquals(0, rack.getRackTileImage().size());
	}
	
	@Test
	void should_verfy_moon_placement() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Rack rack;
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		rack = new Rack(deck);
		rack.getListRackTile().get(0).setPosition(new Position(4, 4));
		rack.getListRackTile().get(1).setPosition(new Position(3, 4));
		GameBoard gameBoard = new GameBoard();
		Rules referee = new Rules();
		
		assertEquals(true, referee.moonRule(gameBoard, rack.getListRackTile().get(0)));
		assertEquals(false, referee.moonRule(gameBoard, rack.getListRackTile().get(1)));
		
	}
	
	@Test
	void should_verfy_sun_placement() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Rack rack;
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		rack = new Rack(deck);
		rack.getListRackTile().get(0).setPosition(new Position(0, 0));
		rack.getListRackTile().get(1).setPosition(new Position(1,0));
		GameBoard gameBoard = new GameBoard();
		Rules referee = new Rules();
		
		assertEquals(true, referee.sunRule(gameBoard, rack.getListRackTile().get(0)));
		assertEquals(false, referee.sunRule(gameBoard, rack.getListRackTile().get(1)));
		
	}
	
	@Test
	void should_test_if_we_have_enough_points_to_play_a_tile() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Rack rack;
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		rack = new Rack(deck);
		Player player = new Player("Marc", new Score(), deck, rack);
		
		GameBoard gameBoard = new GameBoard();
		Rules referee = new Rules();
		assertEquals(false, referee.checkScoreToPlay(player, false));
		assertEquals(true, referee.checkScoreToPlay(player, true));
		
		
	}
	
	@Test
	void should_test_if_a_box_is_empty() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Rack rack;
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		rack = new Rack(deck);
		Player player = new Player("Marc", new Score(), deck, rack);
		
		GameBoard gameBoard = new GameBoard();
		Rules referee = new Rules();
		player.getRack().getListRackTile().get(0).setPosition(new Position(0, 0));
		player.getRack().getListRackTile().get(1).setPosition(new Position(0, 0));
		
		assertEquals(true, referee.checkPositionRule(gameBoard, player.getRack().getListRackTile().get(0)));
		assertEquals(true, referee.checkPositionRule(gameBoard, player.getRack().getListRackTile().get(1)));
	}
	
	@Test
	void should_test_neighbor_rule() {
		//Arrange
		ArrayList<Tile> listOfTile = new ArrayList<Tile>();
		Deck deck;
		Rack rack;
		
		for (Color color : Color.values()) {
			for (Shape shape : Shape.values()) {
				Tile tile = new Tile(color, shape);
				System.out.println(color.getStringColor() + shape.getStringShape()+ ".png");
				
				listOfTile.add(tile);
				
			}
		}
		deck = new Deck(listOfTile);
		rack = new Rack(deck);
		Player player = new Player("Marc", new Score(), deck, rack);
		
		GameBoard gameBoard = new GameBoard();
		Rules referee = new Rules();
		player.getRack().getListRackTile().get(0).setPosition(new Position(0, 0));
		player.getRack().getListRackTile().get(1).setPosition(new Position(0, 1));
		
		assertEquals(0, referee.neighborRule(gameBoard, player.getRack().getListRackTile().get(1)));
		
	}


}
