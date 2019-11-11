package com.ssafy.vo;

public class Post {
	int p_no;
	String p_title;
	String p_body;
	String p_date;
	String p_email;
	
	
	public Post() {
		super();
	}


	public Post(int p_no, String p_title, String p_body, String p_date, String p_email) {
		super();
		this.p_no = p_no;
		this.p_title = p_title;
		this.p_body = p_body;
		this.p_date = p_date;
		this.p_email = p_email;
	}


	public int getP_no() {
		return p_no;
	}


	public void setP_no(int p_no) {
		this.p_no = p_no;
	}


	public String getP_title() {
		return p_title;
	}


	public void setP_title(String p_title) {
		this.p_title = p_title;
	}


	public String getP_body() {
		return p_body;
	}


	public void setP_body(String p_body) {
		this.p_body = p_body;
	}


	public String getP_date() {
		return p_date;
	}


	public void setP_date(String p_date) {
		this.p_date = p_date;
	}


	public String getP_email() {
		return p_email;
	}


	public void setP_email(String p_email) {
		this.p_email = p_email;
	}


	@Override
	public String toString() {
		return "Post [p_no=" + p_no + ", p_title=" + p_title + ", p_body=" + p_body + ", p_date=" + p_date
				+ ", p_email=" + p_email + "]";
	}
	
	
	
	
	
	
	
	
}
