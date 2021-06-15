package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WelcomeController {
	static String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
	static String user = "KKOLCAN";
	static String pass = "kkolcan";
	static String level = "SELECT * FROM USERS WHERE USER_NAME = ? AND USER_PASSWORD = ?";

	@FXML
	private TextField login;
	
    @FXML
    private PasswordField password;

	@FXML
	void contionueButtonClicked(ActionEvent event) {
		try (Connection connection = DriverManager.getConnection(url, user, pass);
				PreparedStatement statementLogin = connection.prepareStatement(level);) {
			statementLogin.setString(1, login.getText());
			statementLogin.setString(2, password.getText());
			ResultSet rs = statementLogin.executeQuery();
			
			if (rs.next()) {
				User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
				rs.close();
				ViewLoader<GridPane, MainController> viewLoader = new ViewLoader<>("MainView.fxml");
				MainController controller = viewLoader.getController();
				controller.setUser(user);
				controller.loadUsers();
				controller.LoadExternally();
				Scene scene = new Scene(viewLoader.getLayout());
				Stage windowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				windowStage.setScene(scene);

			} else {
				rs.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void exitButtonClicked(ActionEvent event) {
		Stage windowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		windowStage.close();
	}

}