package com.jsp.request;

import java.util.Arrays;

import com.jsp.dto.MemberVO;

public class MemberRegistRequest {
	private String id;
	private String pwd;
	private String authority;
	private String email;
	private String picture;
	private String[] phone;
	private String name;

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPwd() {
		return pwd;
	}



	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	public String getAuthority() {
		return authority;
	}



	public void setAuthority(String authority) {
		this.authority = authority;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPicture() {
		return picture;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}



	public String[] getPhone() {
		return phone;
	}



	public void setPhone(String[] phone) {
		this.phone = phone;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "MemberRegistRequest [id=" + id + ", pwd=" + pwd + ", authority=" + authority + ", email=" + email
				+ ", picture=" + picture + ", phone=" + Arrays.toString(phone) + ", name=" + name + "]";
	}

	public MemberRegistRequest(String id, String pwd, String authority, String email, String picture, String[] phone,
			String name) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.authority = authority;
		this.email = email;
		this.picture = picture;
		this.phone = phone;
		this.name = name;
	}

	public MemberVO toMemberVO() {
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setEmail(email);
		member.setAuthority(authority);
		member.setName(name);
		member.setPicture(picture);
		member.setPhone(phone[0]+phone[1]+phone[2]);
		
		return member;		
	}
}                 
