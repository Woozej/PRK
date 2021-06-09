package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    void contionueButtonClicked(ActionEvent event) {
    	ViewLoader<GridPane, MainController> viewLoader = new ViewLoader<>("MainView.fxml");
    	Scene scene = new Scene(viewLoader.getLayout());
    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	windowStage.setScene(scene);
    	
    }
    
    @FXML
    void exitButtonClicked(ActionEvent event) {
    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	windowStage.close();
    }

}