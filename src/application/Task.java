package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
	private IntegerProperty idTask;
	private StringProperty subject;
	private StringProperty remarks;
	private ObjectProperty<Date> registerDate;
	private ObjectProperty<Date> dueDate;
	private IntegerProperty status;
	private IntegerProperty registerUser;
	private StringProperty registerUserName;
	private IntegerProperty userId;
	private StringProperty userName;
	private StringProperty vStatus;
	
	Task(int idTask, String subject, String remarks, Date registerDate, Date dueDate, int status, int registerUser, String registerUserName, int userId, String userName){
		this.idTask = new SimpleIntegerProperty(idTask);
		this.subject = new SimpleStringProperty(subject);
		this.remarks = new SimpleStringProperty(remarks);
		this.registerDate = new SimpleObjectProperty<Date>(registerDate);
		this.dueDate = new SimpleObjectProperty<Date>(dueDate);
		this.status = new SimpleIntegerProperty(status);
		this.registerUser = new SimpleIntegerProperty(registerUser);
		this.registerUserName = new SimpleStringProperty(registerUserName);
		this.userId = new SimpleIntegerProperty(userId);
		this.userName = new SimpleStringProperty(userName);
		if (status == 1)
			vStatus = new SimpleStringProperty("Nowe");
		else if (status == 2)
			vStatus = new SimpleStringProperty("Zrobione");
	}

	public int getIdTask() {
		return this.idTask.get();
	}
	public IntegerProperty idTaskProperty(){
		return idTask;
	}
	
	public String getSubject() {
		return this.subject.get();
	}
	public StringProperty subjectProperty(){
		return subject;
	}
	
	public String getRemarks() {
		return this.remarks.get();
	}
	public StringProperty remarksProperty(){
		return remarks;
	}
	
	public Date getRegisterDate() {
		return this.registerDate.get();
	}
	public ObjectProperty<Date> registerDateProperty(){
		return registerDate;
	}
	
	public Date getDueDate() {
		return this.dueDate.get();
	}
	public ObjectProperty<Date> dueDateProperty(){
		return dueDate;
	}
	
	public int getStatus() {
		return this.status.get();
	}
	public IntegerProperty statusProperty(){
		return status;
	}
	
	public int getRegisterUser() {
		return this.registerUser.get();
	}
	public IntegerProperty registerUserProperty(){
		return registerUser;
	}
	
	public String getRegisterUserName() {
		return this.registerUserName.get();
	}
	public StringProperty registerUserNameProperty(){
		return registerUserName;
	}
	
	public int getUserId() {
		return this.userId.get();
	}
	public IntegerProperty userIdProperty(){
		return userId;
	}
	
	public String getUserName() {
		return this.userName.get();
	}
	public StringProperty userNameProperty(){
		return userName;
	}
	
	public String getVStatus() {
		return this.vStatus.get();
	}
	public StringProperty vStatusProperty(){
		return vStatus;
	}
}
