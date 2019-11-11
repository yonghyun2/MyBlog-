package com.ssafy.vo;

public class Member {
	int m_no;
	String m_name;
	String m_email;
	String m_password;
	String m_role;
	
	
	
	public Member() {
		super();
	}



	public Member(int m_no, String m_name, String m_email, String m_password, String m_role) {
		super();
		this.m_no = m_no;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_password = m_password;
		this.m_role = m_role;
	}



	public int getM_no() {
		return m_no;
	}



	public void setM_no(int m_no) {
		this.m_no = m_no;
	}



	public String getM_name() {
		return m_name;
	}



	public void setM_name(String m_name) {
		this.m_name = m_name;
	}



	public String getM_email() {
		return m_email;
	}



	public void setM_email(String m_email) {
		this.m_email = m_email;
	}



	public String getM_password() {
		return m_password;
	}



	public void setM_password(String m_password) {
		this.m_password = m_password;
	}



	public String getM_role() {
		return m_role;
	}



	public void setM_role(String m_role) {
		this.m_role = m_role;
	}



	@Override
	public String toString() {
		return "Member [m_no=" + m_no + ", m_name=" + m_name + ", m_email=" + m_email + ", m_password=" + m_password
				+ ", m_role=" + m_role + "]";
	}
	
	
	
	
	
	
	
	
	

}
