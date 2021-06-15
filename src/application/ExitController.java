package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
/**
 * 
 * @author Krystian Kolcan
 * Klasa kontrolera ob�uguj�ca okienko wyj�cia z programu
 */
public class ExitController {
    /**
     * 
     * @param event
     * funkcja obs�uguj�ca naci�ni�cie przycisku "zatwierdz"
     * s�u�y do zamykania wszystkich okien
     */
    @FXML
    void ExitProgram(ActionEvent event) {
    		Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	    Stage parentStage = (Stage)windowStage.getOwner();
    	    parentStage.close();
    }
    /**
     * 
     * @param event
     * funkcja obs�uguj�ca naci�ni�cie przycisku "cofnij"
     * s�u�y do zamykania okna modalnego
     */
    @FXML
    void Return(ActionEvent event) {
    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	windowStage.close();
    }

}
