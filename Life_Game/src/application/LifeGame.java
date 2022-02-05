package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LifeGame extends Application{
		@Override
		public void start(Stage stage) throws Exception {
			Parent root=FXMLLoader.load(getClass().getResource("LifeGame2.fxml"));
			Scene scene=new Scene(root);
			stage.setTitle("The Life Game");
			stage.setScene(scene);
			stage.show();
		}
	
	public static void main(String[] args) {
		launch(args);
	}

}

