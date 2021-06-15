package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * 
 * @author kryst
 * Klasa kontrolera pozwalaj�ca na akceptowanie zada�
 */
public class ConfirmController {
	/**
	 * Zmienne potrzebne do nawi�zywania do po��czenia oraz wcze�niej przygotowana kwerenda
	 */
	static String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
	static String user = "KKOLCAN";
	static String pass = "kkolcan";
	static String updateQuery = "UPDATE TASKS SET REMARKS = ?, STATUS = 2 WHERE ID_TASK = ?";
	static int id_task;
	/**
	 * elementy wymagane do utworzenia widoku
	 */
    @FXML
    private TextField remarksTF;
    /**
     * 
     * @param event
     * funkcja obs�uguj�ca naci�ni�cie przycisku "cofnij"
     * s�u�y do zamykania okna modalnego
     */
    @FXML
    void cancel(ActionEvent event) {
    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	windowStage.close();
    }
    /**
     * 
     * @param id
     * funkcja s�u��ca do za�adowania identyfikatora zadania
     */
    void loadId(int id){
    	id_task = id;
    }
    /**
     * 
     * @param event
     * funkcja obs�uguj�ca naci�ni�cie przycisku "zatwierdz"
     * zmieniany jest za jej pomoc� status zadania oraz uwagi do niego
     */
    @FXML
    void confirm(ActionEvent event) {
    	try (Connection connection = DriverManager.getConnection(url, user, pass);
				PreparedStatement statementUpdate = connection.prepareStatement(updateQuery);) {
    		statementUpdate.setString(1, remarksTF.getText());
    		statementUpdate.setInt(2, id_task);
    		
			ResultSet rs = statementUpdate.executeQuery();
			rs.close();
	    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	windowStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
