package com.example.demo.Entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Affiliates")
public class Affiliates {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="affiliateId")
	private int affiliateId;
	
	@Column(name="name",nullable= false)
	private String name;
	
	@Column(name="age")
	private int age;
	
	@Column(name="mail",nullable= false)
	private String mail;

	public int getAffiliateId() {
		return affiliateId;
	}

	public void setAffiliateId(int id) {
		this.affiliateId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
