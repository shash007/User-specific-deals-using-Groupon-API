/*Final Project
 UserDetails.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;

public class UserDetails {
	private String userId;
	private String pwd;
	private String fullName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", pwd=" + pwd + ", fullName="
				+ fullName + "]";
	}
	

}
