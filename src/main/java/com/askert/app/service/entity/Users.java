package com.askert.app.service.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Repository;

/**
 * @author jayant
 *
 */

@Entity
@Repository
@Table(name = "USER")
@NamedQueries({ @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
		@NamedQuery(name = "Users.validatePwd", query = "SELECT u FROM Users u WHERE u.emailId = :email and u.pwd = :pwd"),
		@NamedQuery(name = "Users.forgetPwd", query = "UPDATE Users u SET u.pwd= :pwd WHERE u.emailId= :email"),
		@NamedQuery(name = "Users.activateUser", query = "UPDATE Users u SET u.isActive= :activeInd WHERE u.emailId= :email"), })
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1385794955661915701L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "USER_ID", nullable = false)
	private String userId;

	@Column(name = "EMAIL_ID", nullable = false)
	private String emailId;
	@Column(name = "Last_Login_Date")
	private Date lastLoginDate;

	@Column(name = "Dob")
	private Date dob;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "INITIALS", nullable = true)
	private String initials;

	@Transient
	private String displayName;

	@Column(name = "SUPERVISOR_ID", nullable = true)
	private String supervisorId;

	@Column(name = "MO_ID", nullable = false)
	private String managementObject;

	@Transient
	private ArrayList<Users> assistants;

	// @Type(type="yes_no")
	@Column(name = "ACTIVE_IND")
	private Boolean isActive;
	@Column(name = "pwd")
	private String pwd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users() {
		super();
	}

	public Users(Long userId, String firstName, String lastName) {
		this.id = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Users(Long userId, String firstName, String lastName, String managedObjectId) {
		this.id = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.managementObject = managedObjectId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getManagementObject() {
		return managementObject;
	}

	public void setManagementObject(String managementObject) {
		this.managementObject = managementObject;
	}

	public ArrayList<Users> getAssistants() {
		return assistants;
	}

	public void setAssistants(ArrayList<Users> assistants) {
		this.assistants = assistants;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * @OneToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "USER_ROLE_ID") private UserRole userRole;
	 */
}
