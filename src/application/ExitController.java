package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ExitController {

    @FXML
    void ExitProgram(ActionEvent event) {
    		Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	    Stage parentStage = (Stage)windowStage.getOwner();
    	    parentStage.close();
    }

    @FXML
    void Return(ActionEvent event) {
    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	windowStage.close();
    }

}
