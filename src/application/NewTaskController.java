package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewTaskController {
	static String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
	static String user = "KKOLCAN";
	static String pass = "kkolcan";
	static String insertQuery = "INSERT INTO TASKS(SUBJECT,REMARKS, REGISTER_DATE, DUE_DATE,STATUS, REGISTER_USER, USER_ID  ) VALUES(?,'',?,?,1,?,?)";
	
	private User loggedUser;
    public void setUser(User user) {
    	this.loggedUser = user;
    }
    @FXML
    private TextField subjectTF;

    @FXML
    private DatePicker dueDateDP;

    @FXML
    private ComboBox<User> userCB;

    @FXML
    void cancel(ActionEvent event) {
    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	windowStage.close();
    }

    @FXML
    void confirm(ActionEvent event) {
    	try (Connection connection = DriverManager.getConnection(url, user, pass);
				PreparedStatement statementInsert = connection.prepareStatement(insertQuery);) {
    		Date today = new Date(System.currentTimeMillis());
    		Date due = Date.valueOf(dueDateDP.getValue());
    		statementInsert.setString(1, subjectTF.getText());
    		statementInsert.setDate(2, today);
    		statementInsert.setDate(3, due);
    		statementInsert.setInt(4, loggedUser.getIdUser());
    		statementInsert.setInt(5, userCB.getValue().getIdUser());
    		
			ResultSet rs = statementInsert.executeQuery();
			rs.close();
	    	Stage windowStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	windowStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void setEmployees(ObservableList<User> user )
	{
    	userCB.getItems().clear();
    	userCB.setItems(user);
		if (!userCB.getItems().isEmpty()) {
			userCB.getSelectionModel().select(0);
		}
	}
}
