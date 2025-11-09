package com.data.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity class representing a user in the bidding application.
 * Maps to the 'user' table in the database.
 */
@NamedQueries({
	@NamedQuery(name = UserQueries.FIND_USER_BY_EMAIL_ID, query = UserQueries.FIND_USER_BY_EMAIL_ID_QUERY)
})
@Entity
@Table(name = "user")
public class DataProvider {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ids")
	@SequenceGenerator(name = "ids", sequenceName = "ids", allocationSize = 1)
	@Column(name = "u_id")
	private Integer id;

	@Column(name = "uname")
	private String uname;

	@Column(name = "fname")
	private String fname;

	@Column(name = "lname")
	private String lname;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "lastlogintime")
	private Timestamp lastlogintime;

	@Column(name = "failedLoginCount")
	private Integer failedLoginCount;

	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Timestamp lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public Integer getFailedLoginCount() {
		return failedLoginCount;
	}

	public void setFailedLoginCount(Integer failedLoginCount) {
		this.failedLoginCount = failedLoginCount;
	}
}
