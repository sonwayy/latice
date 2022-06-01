package latice.model.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import latice.model.Constant;
import latice.model.Player;
import latice.model.Tile;

public class RectangleFX {
	
	javafx.scene.paint.Color realColor = new javafx.scene.paint.Color(0, 0, 0, 0);
	Rectangle[][] rect = new Rectangle[9][9];
	ArrayList<Image> listTileImage;
	Map<Rectangle, Tile> assocRectangleTile = new HashMap<Rectangle, Tile>();
	
	public void createRectangle(BorderPane board, Pane pane) {
		//Creating rectangle for tiles placement
		
		int counterI = 0;
		int counterJ = 0;
		for (int i=1; i<= Constant.NUMBER_OF_BOX_ON_ONE_LINE ; i++) {
			for (int j=1; j <= Constant.NUMBER_OF_BOX_ON_ONE_LINE ; j++) {
				
				rect[counterI][counterJ] = new Rectangle(i*Constant.BOX_WIDTH+Constant.X_CENTER,j*Constant.BOX_WIDTH+Constant.Y_CENTER,Constant.RECTANGLE_WIDTH,Constant.RECTANGLE_HEIGHT);
				rect[counterI][counterJ].setFill(realColor.TRANSPARENT);
				pane.getChildren().add(rect[counterI][counterJ]);
				System.out.println(rect[counterI][counterJ]);
				System.out.println(counterJ);
				counterJ++;
			}
			System.out.println(counterI);
			counterJ = 0;
			counterI++;
		}
		
		board.setCenter(pane);
		System.out.println(rect);
	}
	
	
	public void dragnDropOnAllRectangles(Player player, Integer index, Integer validateBtnClicked) {

		//Setting drag & drop on rectangles
		for(int i=0; i<Constant.NUMBER_OF_BOX_ON_ONE_LINE; i++) {
			for(int j=0; j<Constant.NUMBER_OF_BOX_ON_ONE_LINE; j++) {
		        int a = i;
		        int b = j;
		        
				rect[a][b].setOnDragEntered(new EventHandler<DragEvent>() {
	
					@Override
					public void handle(DragEvent arg0) {
						if (arg0.getDragboard().hasString()){
							Dragboard dragboard = arg0.getDragboard();
							
							rect[a][b].setFill(new ImagePattern(listTileImage.get(index)));
						}
						arg0.consume();
					}
				});
				
				rect[a][b].setOnDragExited(new EventHandler<DragEvent>() {
		
					@Override
					public void handle(DragEvent arg0) {
						if (arg0.isDropCompleted() == false) {
							rect[a][b].setFill(realColor.TRANSPARENT);
						}
						arg0.consume();
					}
					
				});
				
				rect[a][b].setOnDragOver(new EventHandler <DragEvent>() {
					@Override
				    public void handle(DragEvent arg0) {
				        arg0.acceptTransferModes(TransferMode.ANY);
				        arg0.consume();
				    }
				});
				
				rect[a][b].setOnDragDropped(new EventHandler<DragEvent>() {
					@Override
					public void handle(DragEvent arg0) {
						System.out.println("entered");
						Dragboard dragboard = arg0.getDragboard();
						
						rect[a][b].setFill(new ImagePattern(listTileImage.get(index)));
						arg0.setDropCompleted(true);
						assocRectangleTile.put(rect[a][b], player.getRack().getListRackTile().get(index));
						System.out.println(assocRectangleTile.toString());
						if (validateBtnClicked == 0){
							if (rect[a][b] == rect[4][4]) {
								System.out.println("MOON valid placement");
							}else {
								System.out.println("Please place first Tile on MOON");
							}
				        }
						
						
						arg0.consume();
					}
					
				});
				
			
	        }
		}
	}
}
