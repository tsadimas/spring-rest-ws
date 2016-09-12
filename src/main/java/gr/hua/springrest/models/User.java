package gr.hua.springrest.models;

import java.io.Serializable;

public class User implements Serializable {

	private String name;

	private String email;
	private int id;
	private String country;
	private String password;

	private String phone;
	
	public User(int id, String name, String email, String country, String password, String phone){
		this.id=id;
		this.name=name;
		this.email = email;
		this.country = country;
		this.password = password;
		this.phone = phone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public String getCountry() {
		return country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Name=" + this.name + ", Email=" + this.email + ", Country=" + this.country;
	}

}