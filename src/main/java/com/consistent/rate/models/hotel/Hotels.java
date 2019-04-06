package com.consistent.rate.models.hotel;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contents")
public class Hotels {
	
	List<Hotel> hotels;
	
	@XmlElement(name = "content")
	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public Hotels(List<Hotel> hotels) {
		super();
		this.hotels = hotels;
	}
	
	public Hotels(){
		super();
	}
}
