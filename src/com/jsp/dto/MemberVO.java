package com.jsp.dto;

public class MemberVO {
	private String id;			//아이디
	private String pwd;			//비밀번호
	private String phone;		//전화번호
	private String email;		//이메일
	private String picture;		//사진파일명
	private String authority;	//권한
	private int enabled;		//사용여부
	private String name;		//이름
	
	public MemberVO() {}

	public MemberVO(String id, String pwd, String phone, String email, String picture, String authority, int enabled,
			String name) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.email = email;
		this.picture = picture;
		this.authority = authority;
		this.enabled = enabled;
		this.name = name;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", phone=" + phone + ", email=" + email + ", picture=" + picture
				+ ", authority=" + authority + ", enabled=" + enabled + ", name=" + name + "]";
	}
}
