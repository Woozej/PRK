package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class User {
	private IntegerProperty idUser;
	private StringProperty userName;
	private StringProperty userPassword;
	private IntegerProperty accessLevel;
	
	User(int idUser, String userName,String userPassword,int accessLevel){
		this.idUser = new SimpleIntegerProperty(idUser);
		this.userName = new SimpleStringProperty(userName);
		this.userPassword = new SimpleStringProperty(userPassword);
		this.accessLevel = new SimpleIntegerProperty(accessLevel);

	}
	
	public int getIdUser() {
		return this.idUser.get();
	}
	
	public IntegerProperty idUserProperty(){
		return idUser;
	}
	public String getUserName() {
		return this.userName.get();
	}
	
	public StringProperty userNameProperty(){
		return userName;
	}
	
	public String getUserPassword() {
		return this.userPassword.get();
	}
	public StringProperty userPasswordProperty(){
		return userPassword;
	}
	public int getAccessLevel() {
		return this.accessLevel.get();
	}
	
	public IntegerProperty accessLevelProperty(){
		return accessLevel;
	}

	
}
