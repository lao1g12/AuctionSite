package com.fdmgroup.AuctionSite;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AUCTION_PERSONINFO")

public class Person {
	
	@Id
	@SequenceGenerator(name="personid_sequence", sequenceName="personid_sequence", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="personid_sequence")
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private String address;
	private String email;
	private String interests;
	private String accountDetails;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar startDate = Calendar.getInstance();
	







	public Person() {	}
	
	
	
	

	public Person(String firstName, String lastName, String gender, String address, String email, String interests) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.interests = interests;
	}
	
	
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public String getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(String accountDetails) {
		this.accountDetails = accountDetails;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	
	public Date getStartDateFormat() {

		Date date = this.startDate.getTime();

		return date;

	}



}
