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

public class AddEditUserController {
	static String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
	static String user = "KKOLCAN";
	static String pass = "kkolcan";
	static boolean isEdit = false; 
	static String addQuery = "INSERT INTO USERS(USER_NAME, USER_PASSWORD, ACCESS_LEVEL) VALUES(?,?,?)";
	static String editQuery = "UPDATE USERS SET USER_NAME = ?, USER_PASSWORD = ?, ACCESS_LEVEL = ? WHERE ID_USER = ?";
	static int userId;
    @FXML
    private TextField userNameTF;

    @FXML
    private TextField UserPasswordTF;

    @FXML
    private TextField accessLevelTF;

    @FXML
    void cancel(ActionEvent event) {
    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	windowStage.close();
    }
    
    @FXML
    void confirm(ActionEvent event) {
    	if(isEdit) {
    		try (Connection connection = DriverManager.getConnection(url, user, pass);
    				PreparedStatement pst = connection.prepareStatement(editQuery);) {
    			pst.setString(1, userNameTF.getText());
    			pst.setString(2, UserPasswordTF.getText());
    			pst.setInt(3, Integer.parseInt(accessLevelTF.getText()));
    			pst.setInt(4, userId);
        		
    			ResultSet rs = pst.executeQuery();
    			rs.close();
    	    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	    	windowStage.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else {
    		try (Connection connection = DriverManager.getConnection(url, user, pass);
    				PreparedStatement pst = connection.prepareStatement(addQuery);) {
    			pst.setString(1, userNameTF.getText());
    			pst.setString(2, UserPasswordTF.getText());
    			pst.setInt(3, Integer.parseInt(accessLevelTF.getText()));
        		
    			ResultSet rs = pst.executeQuery();
    			rs.close();
    	    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	    	windowStage.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    }
    void setEdit(User user) {
    	userNameTF.setText(user.getUserName());
    	UserPasswordTF.setText(user.getUserPassword());
    	accessLevelTF.setText(String.valueOf(user.getAccessLevel()));
    	isEdit = true;
    	userId = user.getIdUser();
    	
    }
}
