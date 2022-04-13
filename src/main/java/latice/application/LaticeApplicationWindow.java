package latice.application;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LaticeApplicationWindow extends Application{
	
	Image image = new Image("C:/Users/cemal/saebut1/latice/src/main/resources/laticePlateau.png");
	ImageView imageView = new ImageView(image);

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		Text title = new Text("Latice");
		title.setFont(new Font(20));
		root.setTop(title);
		root.setAlignment(title, Pos.CENTER);
		root.setCenter(imageView);
		
		Scene scene = new Scene(root, 1280, 720);
		

		primaryStage.setTitle("Latice");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
