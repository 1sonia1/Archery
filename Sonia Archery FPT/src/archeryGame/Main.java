// Jan. 21, 2020
// Sonia Tinaz
// archeryGame, Main
// Starts up the game, displaying elements in Archery.fxml file, and running code in ArcheryController 

package archeryGame;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// title of game will be displayed as 'Archery Game' 
			primaryStage.setTitle("Archery Game");
			// displays content stored in Archery.fxml (i.e. the pane)
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Archery.fxml"));
			Pane root = (Pane) loader.load();
			Scene scene = new Scene(root, 1000, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			ArcheryController controller = loader.getController();
			primaryStage.setScene(scene);
			// prevents user from being able to resize the game screen
			primaryStage.setResizable(false);
			controller.getScene(primaryStage);
			controller.gameLoop();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
