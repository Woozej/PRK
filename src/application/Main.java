package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.GridPane;


public class Main extends Application {
	static String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
	static String user = "KKOLCAN";
	static String password = "kkolcan";

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
		checkOracleDriver();
		launch(args);
	}
	
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
