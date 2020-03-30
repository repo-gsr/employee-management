package com.employeemngt.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserRole")
public class UserRole {

	private String userid;
	private String roleid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

}
