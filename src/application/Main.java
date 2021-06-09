package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.GridPane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			ViewLoader<GridPane, WelcomeController> viewLoader = new ViewLoader<>("WelcomeView.fxml");
			Scene scene = new Scene(viewLoader.getLayout());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
