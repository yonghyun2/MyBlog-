package com.ssafy.vo;

public class Log {
	int no;
	String time;
	String email;
	
	
	public Log(String time, String email) {
		super();
		this.time = time;
		this.email = email;
	}

	public Log(int no, String time, String email) {
		super();
		this.no = no;
		this.time = time;
		this.email = email;
	}

	public Log() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Log [no=" + no + ", time=" + time + ", email=" + email + "]";
	}
	
	
	
	

}
