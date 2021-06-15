package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
/**
 * 
 * @author krystian kolcan
 * klasa s³u¿¹ca do ³adowania zestawu widok kontroller
 */
public class ViewLoader<T,U> {

	private T layout = null;
	private U controller = null;
	public ViewLoader(String fxmlFilePath) {
		try {
			FXMLLoader loader = new FXMLLoader (getClass().getResource(fxmlFilePath));
			layout = loader.load();
			controller = loader.getController();
		}catch (IOException ex) {
			System.out.println("ViewLoader exception: " + ex.getMessage());
		}
	}
	public T getLayout(){
		return layout;
	}
	public U getController() {
		return controller;
	}
}
