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
	private IntegerProperty userId;
	
	Task(int idTask,String subject,String remarks,Date registerDate,Date dueDate, int status,int registerUser, int userId){
		this.idTask = new SimpleIntegerProperty(idTask);
		this.subject = new SimpleStringProperty(subject);
		this.remarks = new SimpleStringProperty(remarks);
		this.registerDate = new SimpleObjectProperty<Date>(registerDate);
		this.dueDate = new SimpleObjectProperty<Date>(dueDate);
		this.status = new SimpleIntegerProperty(status);
		this.registerUser = new SimpleIntegerProperty(registerUser);
		this.userId = new SimpleIntegerProperty(userId);
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
	
	public int getUserId() {
		return this.userId.get();
	}
	public IntegerProperty userIdProperty(){
		return userId;
	}
}
