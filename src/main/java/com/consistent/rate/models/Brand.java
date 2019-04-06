package com.consistent.rate.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.consistent.rate.models.hotel.Hotel;


@XmlRootElement(name = "brand")
@XmlType(propOrder = { "guid","code","name","title","language", "keyword","order","channel","rates","hotel"})
public class Brand {
	
	 String guid;
	 String code;
	 String name;
	 String title;
	 String language;
	 String keyword;
	 String order;
	 String channel;
	 Medialinks medialinks;
	 List<Rates> rates;
	 Hotel hotel;
	/*
	@XmlElement(name = "medialinks")
	public Medialinks getMedialinks() {
		return medialinks;
	}

	public void setMedialinks(Medialinks medialinks) {
		this.medialinks = medialinks;
	}
	*/
	@XmlElement(name = "hotel")
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	@XmlElement(name = "rates")
	public List<Rates> getRates() {
		return rates;
	}

	public void setRates(List<Rates> rates) {
		this.rates = rates;
	}
	
	@XmlElement(name = "guid")
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	@XmlElement(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement(name = "language")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@XmlElement(name = "keyword")
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@XmlElement(name = "order")
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	@XmlElement(name = "channel")
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}

	
	
	public Brand(String guid, String code, String name, String title, String language, String keyword, String order,
			String channel, Medialinks medialinks, List<Rates> rates, Hotel hotel) {
		super();
		this.guid = guid;
		this.code = code;
		this.name = name;
		this.title = title;
		this.language = language;
		this.keyword = keyword;
		this.order = order;
		this.channel = channel;
		this.medialinks = medialinks;
		this.rates = rates;
		this.hotel = hotel;
	}

	public Brand(){
		this.guid = "";
		this.code = "";
		this.name = "";
		this.title = "";
		this.language = "";
		this.keyword = "";
		this.order = "";
		this.channel = "";
		this.medialinks = new Medialinks();
		this.rates = new ArrayList<Rates>();
		this.hotel = new Hotel();
	}

}
