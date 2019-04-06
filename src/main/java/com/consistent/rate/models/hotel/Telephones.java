package com.consistent.rate.models.hotel;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "telephones")
public class Telephones {
	
	List<Telephone> telephone;
	
	@XmlElement(name = "telephone")
	public List<Telephone> getTelephone() {
		return telephone;
	}

	public void setTelephone(List<Telephone> telephone) {
		this.telephone = telephone;
	}
	
	public Telephones(){
		super();
	}

	public Telephones(List<Telephone> telephone) {
		super();
		this.telephone = telephone;
	}
}
