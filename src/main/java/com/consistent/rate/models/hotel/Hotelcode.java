package com.consistent.rate.models.hotel;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
public class Hotelcode {

	List<String> code;

	public Hotelcode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hotelcode(List<String> code) {
		super();
		this.code = code;
	}
	@XmlElement(name = "hotelcode")
	public List<String> getCode() {
		return code;
	}

	public void setCode(List<String> code) {
		this.code = code;
	}
	
	
}
