package com.askert.app.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Repository;

/**
 * @author jayant
 *
 */
@Entity
@Repository
public class UserDevice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	
	@Column(name = "USER_ID", nullable = false)
	private String UserId;

	
	@Column(name = "USER_DEVICE_NAME", nullable = false)
	private String UserDeviceName;

	
	@Column(name = "USER_DEVICE_TOKEN", nullable = false)
	private String UserDeviceToken;
	
	@Column(name = "MO_ID", nullable = false)
	private String managementObject;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return UserId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		UserId = userId;
	}

	/**
	 * @return the userDeviceName
	 */
	public String getUserDeviceName() {
		return UserDeviceName;
	}

	/**
	 * @param userDeviceName the userDeviceName to set
	 */
	public void setUserDeviceName(String userDeviceName) {
		UserDeviceName = userDeviceName;
	}

	/**
	 * @return the userDeviceToken
	 */
	public String getUserDeviceToken() {
		return UserDeviceToken;
	}

	/**
	 * @param userDeviceToken the userDeviceToken to set
	 */
	public void setUserDeviceToken(String userDeviceToken) {
		UserDeviceToken = userDeviceToken;
	}

	/**
	 * @return the managementObject
	 */
	public String getManagementObject() {
		return managementObject;
	}

	/**
	 * @param managementObject the managementObject to set
	 */
	public void setManagementObject(String managementObject) {
		this.managementObject = managementObject;
	}
}
