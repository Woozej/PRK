package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {

	ObservableList<Task> tasks = FXCollections.observableArrayList();

	static String url = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
	static String user = "KKOLCAN";
	static String pass = "kkolcan";
	static String taskUserQuery = "SELECT * FROM TASKS WHERE USER_ID = ? AND STATUS = 1";
	static String taskUsersQuery = "SELECT * FROM TASKS WHERE REGISTER_USER = ?";

	private User loggedUser;

	public void setUser(User user) {
		this.loggedUser = user;
	}

	@FXML
	private Button usersTaskButton;

	@FXML
	private Button newTaskButton;

	@FXML
	private Button usersButton;

	@FXML
	private TableColumn<Task, Integer> userColumn;

	@FXML
	private TableView<Task> tasksTable;

	@FXML
	private TableColumn<Task, String> descriptionColumn;

	@FXML
	private TableColumn<Task, String> remarksColumn;

	@FXML
	private TableColumn<Task, Date> registerDateColumn;

	@FXML
	private TableColumn<Task, Date> dueDateColumn;

	@FXML
	private TableColumn<Task, Integer> registerUserColumn;

	@FXML
	private TableColumn<Task, Integer> statusColumn;

	@FXML
	void Confirm(ActionEvent event) {
		ViewLoader<GridPane, ConfirmController> viewLoader = new ViewLoader<>("ConfirmView.fxml");
		Stage popUpWindow = new Stage();
		Scene scene = new Scene(viewLoader.getLayout());
		Stage windowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		popUpWindow.setScene(scene);
		popUpWindow.initOwner(windowStage);
		popUpWindow.initModality(Modality.APPLICATION_MODAL);
		ConfirmController controller = viewLoader.getController();
		controller.loadId(tasksTable.getSelectionModel().getSelectedItem().getIdTask());
		popUpWindow.showAndWait();
		LoadData();
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
		LoadData();
	}

	@FXML
	void NewTask(ActionEvent event) {

		ViewLoader<GridPane, NewTaskController> viewLoader = new ViewLoader<>("NewTaskView.fxml");
		Stage popUpWindow = new Stage();
		Scene scene = new Scene(viewLoader.getLayout());
		Stage windowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		popUpWindow.setScene(scene);
		popUpWindow.initOwner(windowStage);
		popUpWindow.initModality(Modality.APPLICATION_MODAL);
		NewTaskController controller = viewLoader.getController();
		controller.setUser(loggedUser);
		popUpWindow.showAndWait();
		LoadData();
	}

	@FXML
	void Users(ActionEvent event) {
		ViewLoader<GridPane, UsersController> viewLoader = new ViewLoader<>("UsersView.fxml");
		Scene scene = new Scene(viewLoader.getLayout());
		Stage windowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		UsersController controller = viewLoader.getController();
		controller.setUser(loggedUser);
		controller.LoadExternally();
		windowStage.setScene(scene);
	}

	@FXML
	void UsersTasks(ActionEvent event) {
		tasks.removeAll(tasks);
		String query = taskUsersQuery;

		try (Connection connection = DriverManager.getConnection(url, user, pass);
				PreparedStatement statementLogin = connection.prepareStatement(query);) {
			statementLogin.setInt(1, loggedUser.getIdUser());
			ResultSet rs = statementLogin.executeQuery();

			while (rs.next()) {
				tasks.add(new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			}
			rs.close();
			tasksTable.setItems(tasks);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void initialize() {

		tasksTable.setTableMenuButtonVisible(true);
		userColumn.setCellValueFactory(cellData -> cellData.getValue().userIdProperty().asObject());
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
		remarksColumn.setCellValueFactory(cellData -> cellData.getValue().remarksProperty());
		registerDateColumn.setCellValueFactory(cellData -> cellData.getValue().registerDateProperty());
		dueDateColumn.setCellValueFactory(cellData -> cellData.getValue().dueDateProperty());
		registerUserColumn.setCellValueFactory(cellData -> cellData.getValue().registerUserProperty().asObject());
		statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty().asObject());

	}

	@FXML
	void Load(ActionEvent event) {
		tasks.removeAll(tasks);
		String query = taskUserQuery;
		try (Connection connection = DriverManager.getConnection(url, user, pass);
				PreparedStatement statementLogin = connection.prepareStatement(query);) {
			statementLogin.setInt(1, loggedUser.getIdUser());
			ResultSet rs = statementLogin.executeQuery();

			while (rs.next()) {
				tasks.add(new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			}
			rs.close();
			tasksTable.setItems(tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void LoadData() {
		tasks.removeAll(tasks);
		String query = taskUserQuery;
		try (Connection connection = DriverManager.getConnection(url, user, pass);
				PreparedStatement statementLogin = connection.prepareStatement(query);) {
			statementLogin.setInt(1, loggedUser.getIdUser());
			ResultSet rs = statementLogin.executeQuery();

			while (rs.next()) {
				tasks.add(new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			}
			rs.close();
			tasksTable.setItems(tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void LoadExternally() {
		String query = taskUserQuery;
		if (loggedUser.getAccessLevel() < 2) {
			usersTaskButton.setDisable(true);
			newTaskButton.setDisable(true);
		}
		if (loggedUser.getAccessLevel() < 3)
			usersButton.setDisable(true);

		try (Connection connection = DriverManager.getConnection(url, user, pass);
				PreparedStatement statementLogin = connection.prepareStatement(query);) {
			statementLogin.setInt(1, loggedUser.getIdUser());
			ResultSet rs = statementLogin.executeQuery();

			while (rs.next()) {
				tasks.add(new Task(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			}
			rs.close();
			tasksTable.setItems(tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
