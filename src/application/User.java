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
	private StringProperty vAccessLevel;
	
	User(int idUser, String userName,String userPassword,int accessLevel){
		this.idUser = new SimpleIntegerProperty(idUser);
		this.userName = new SimpleStringProperty(userName);
		this.userPassword = new SimpleStringProperty(userPassword);
		this.accessLevel = new SimpleIntegerProperty(accessLevel);
		if(accessLevel == 1)
			this.vAccessLevel = new SimpleStringProperty("Pracownik");
		else if(accessLevel == 2)
			this.vAccessLevel = new SimpleStringProperty("Prze³o¿ony");
		else if(accessLevel == 3)
			this.vAccessLevel = new SimpleStringProperty("Administrator");

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

	public String getVAccessLevel() {
		return this.vAccessLevel.get();
	}
	public StringProperty vAccessLevelProperty(){
		return vAccessLevel;
	}
	
}
