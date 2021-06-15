package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.GridPane;

/**
 * 
 * @author krystian Kolcan
 * G��wna klasa programu
 */
public class Main extends Application {

	/**
	 * funkcja s�u��ca do w��czenia pierwszego okna programu
	 */
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
	
	/**
	 * 
	 * @param args
	 * g��wna cz�� programu
	 */
	public static void main(String[] args) {
		checkOracleDriver();
		launch(args);
	}
	/**
	 * funkcja s�u��ca do sprawdzenia dzia�ania sterownika
	 */
	public static void checkOracleDriver() {
		String driverName = "oracle.jdbc.driver.OracleDriver";
		try {
			Class<?> c = Class.forName(driverName);
			System.out.println("Pakiet     : " + c.getPackage());
			System.out.println("Nazwa Klasy: " + c.getName());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}
}
