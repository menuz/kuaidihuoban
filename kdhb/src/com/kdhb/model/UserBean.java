package com.kdhb.model;

public class UserBean {

	int id;
	String username;
	String password;
	String name;
	String phone;
	String admit_time;
	String college;
	String major_name;
	int graduate;
	String floor;
	String lab;
	int tag;
	
	int release_task_no;
	int accept_task_no;
	
	public UserBean() {}
	
	public UserBean(int id, String username, String password, String name,
			String phone, String admit_time, String college, String major_name,
			int graduate, String floor, String lab, int tag,
			int release_task_no, int accept_task_no) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.admit_time = admit_time;
		this.college = college;
		this.major_name = major_name;
		this.graduate = graduate;
		this.floor = floor;
		this.lab = lab;
		this.tag = tag;
		this.release_task_no = release_task_no;
		this.accept_task_no = accept_task_no;
	}
	
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", phone=" + phone
				+ ", admit_time=" + admit_time + ", college=" + college
				+ ", major_name=" + major_name + ", graduate=" + graduate
				+ ", floor=" + floor + ", lab=" + lab + ", tag=" + tag
				+ ", release_task_no=" + release_task_no + ", accept_task_no="
				+ accept_task_no + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdmit_time() {
		return admit_time;
	}
	public void setAdmit_time(String admit_time) {
		this.admit_time = admit_time;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	public int getGraduate() {
		return graduate;
	}
	public void setGraduate(int graduate) {
		this.graduate = graduate;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public int getRelease_task_no() {
		return release_task_no;
	}
	public void setRelease_task_no(int release_task_no) {
		this.release_task_no = release_task_no;
	}
	public int getAccept_task_no() {
		return accept_task_no;
	}
	public void setAccept_task_no(int accept_task_no) {
		this.accept_task_no = accept_task_no;
	}
	
	

}


