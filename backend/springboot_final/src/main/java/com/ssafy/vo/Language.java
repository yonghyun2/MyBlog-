package com.ssafy.vo;

public class Language {
	int l_no;
	String l_img;
	String l_title;
	String l_body;
	String l_date;
	String l_email;
	
	
	
	public Language() {
		super();
	}



	public Language(int l_no, String l_img, String l_title, String l_body, String l_date, String l_email) {
		super();
		this.l_no = l_no;
		this.l_img = l_img;
		this.l_title = l_title;
		this.l_body = l_body;
		this.l_date = l_date;
		this.l_email = l_email;
	}



	public int getL_no() {
		return l_no;
	}



	public void setL_no(int l_no) {
		this.l_no = l_no;
	}



	public String getL_img() {
		return l_img;
	}



	public void setL_img(String l_img) {
		this.l_img = l_img;
	}



	public String getL_title() {
		return l_title;
	}



	public void setL_title(String l_title) {
		this.l_title = l_title;
	}



	public String getL_body() {
		return l_body;
	}



	public void setL_body(String l_body) {
		this.l_body = l_body;
	}



	public String getL_date() {
		return l_date;
	}



	public void setL_date(String l_date) {
		this.l_date = l_date;
	}



	public String getL_email() {
		return l_email;
	}



	public void setL_email(String l_email) {
		this.l_email = l_email;
	}



	@Override
	public String toString() {
		return "Language [l_no=" + l_no + ", l_img=" + l_img + ", l_title=" + l_title + ", l_body=" + l_body
				+ ", l_date=" + l_date + ", l_email=" + l_email + "]";
	}
	
	
	

	

}
