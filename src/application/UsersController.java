package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UsersController {

	ObservableList<User> users = FXCollections.observableArrayList();

	static String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
	static String user = "KKOLCAN";
	static String pass = "kkolcan";
	
	private User loggedUser;
	static String usersQuery = "SELECT * FROM USERS";
	static String deleteUserQuery = "DELETE FROM USERS WHERE ID_USER = ?";
	
	public void setUser(User user) {
		this.loggedUser = user;
	}

	@FXML
	private TableView<User> usersTable;

	@FXML
	private TableColumn<User, String> userColumn;

	@FXML
	private TableColumn<User, String> passwordColumn;

	@FXML
	private TableColumn<User, String> accessLevelColumn;

	@FXML
	private void initialize() {
		usersTable.setTableMenuButtonVisible(true);
		userColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
		passwordColumn.setCellValueFactory(cellData -> cellData.getValue().userPasswordProperty());
		accessLevelColumn.setCellValueFactory(cellData -> cellData.getValue().vAccessLevelProperty());
	}

	@FXML
	void Exit(ActionEvent event) {
		ViewLoader<GridPane, MainController> viewLoader = new ViewLoader<>("ExitView.fxml");
		Stage popUpWindow = new Stage();
		Scene scene = new Scene(viewLoader.getLayout());
		Stage windowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		popUpWindow.setScene(scene);
		popUpWindow.initOwner(windowStage);
		popUpWindow.initModality(Modality.APPLICATION_MODAL);
		popUpWindow.showAndWait();
		LoadExternally();
	}

	@FXML
	void goTasks(ActionEvent event) {
		ViewLoader<GridPane, MainController> viewLoader = new ViewLoader<>("MainView.fxml");
		MainController controller = viewLoader.getController();
		controller.setUser(loggedUser);
		controller.LoadExternally();
		controller.setUsers(users);
		Scene scene = new Scene(viewLoader.getLayout());
		Stage windowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		windowStage.setScene(scene);
	}

	@FXML
	void deleteUser(ActionEvent event) {
		
		try (Connection connection = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = connection.prepareStatement(deleteUserQuery);) {
			
			pst.setInt(1, usersTable.getSelectionModel().getSelectedItem().getIdUser());
			ResultSet rs = pst.executeQuery();
			rs.close();
			usersTable.getItems().remove(usersTable.getSelectionModel().getSelectedIndex());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void editUser(ActionEvent event) {
		ViewLoader<GridPane, AddEditUserController> viewLoader = new ViewLoader<>("AddEditUserView.fxml");
		Stage popUpWindow = new Stage();
		Scene scene = new Scene(viewLoader.getLayout());
		Stage windowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		popUpWindow.setScene(scene);
		popUpWindow.initOwner(windowStage);
		popUpWindow.initModality(Modality.APPLICATION_MODAL);
		AddEditUserController controller = viewLoader.getController();
		controller.setEdit(usersTable.getSelectionModel().getSelectedItem());
		popUpWindow.showAndWait();
		LoadExternally();
	}

	@FXML
	void newUser(ActionEvent event) {
		ViewLoader<GridPane, AddEditUserController> viewLoader = new ViewLoader<>("AddEditUserView.fxml");
		Stage popUpWindow = new Stage();
		Scene scene = new Scene(viewLoader.getLayout());
		Stage windowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		popUpWindow.setScene(scene);
		popUpWindow.initOwner(windowStage);
		popUpWindow.initModality(Modality.APPLICATION_MODAL);
		popUpWindow.showAndWait();
		LoadExternally();
	}

	void LoadExternally() {
		users.removeAll(users);
		try (Connection connection = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = connection.prepareStatement(usersQuery);) {
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			rs.close();
			usersTable.setItems(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
