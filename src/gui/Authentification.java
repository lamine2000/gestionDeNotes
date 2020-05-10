package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Authentification extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/Authentification.fxml"));
		root.getStylesheets().add(getClass().getResource("/css/styleAuthentification.css").toString());
		primaryStage.setTitle("Gestionnaire De Notes");
		primaryStage.getIcons()
				.add(new Image(getClass().getResource("resources/images/icone.png").toExternalForm(), false));
		primaryStage.setScene(new Scene(root, 800, 500));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
