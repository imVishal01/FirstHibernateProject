package com.vishal.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT")
public class Student_Info {
	@Id @GeneratedValue
	@Column(name = "roll_number" )
	private int rollNumber;
	public int getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	@Column(name = "name")
	private String Name;
	@Column(name ="gender")
	private String Gender;
	
	Student_Info(){
		
	}
	
	Student_Info(String name,String gender){
		this.Name = name;
		this.Gender = gender;
	}
	
	
}
