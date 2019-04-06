package com.consistent.rate.models.hotel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "medialinks")
public class Multimedia {
	
	String url;
	String type;
	
	public String getType() {
		return type;
	}
	@XmlAttribute(name = "type")
	public void setType(String type) {
		this.type = type;
	}
	@XmlElement(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Multimedia(String url) {
		super();
		this.url = url;
	}
	public Multimedia() {
		super();
		
	}

}
