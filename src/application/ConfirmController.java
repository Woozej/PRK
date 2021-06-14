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

public class ConfirmController {

	static String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
	static String user = "KKOLCAN";
	static String pass = "kkolcan";
	static String updateQuery = "UPDATE TASKS SET REMARKS = ?, STATUS = 2 WHERE ID_TASK = ?";
	static int id_task;
    @FXML
    private TextField remarksTF;

    @FXML
    void cancel(ActionEvent event) {
    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	windowStage.close();
    }
    
    void loadId(int id){
    	id_task = id;
    }
    
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
