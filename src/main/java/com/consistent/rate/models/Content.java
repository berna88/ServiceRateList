package com.consistent.rate.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "content")
public class Content {
	
	Brand brand;
	
	@XmlElement(name = "brand")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Content(Brand brand) {
		super();
		this.brand = brand;
	}
	
	public Content(){
		brand = new Brand();
	}

	
	
	
	
}
