package com.consistent.rate.models.hotel;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name = "medialink")
@XmlType(propOrder = {"type", "keyword","multimedia","thumbnail"})
public class MediaLink {
	
	String type;
	String keyword;
	String thumbnail;
	List<Multimedia> multimedia;
	
	@XmlElement(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@XmlElement(name = "keyword")
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@XmlElement(name="thumbnail")
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	@XmlElement(name = "multimedia")
	public List<Multimedia> getMultimedia() {
		return multimedia;
	}
	public void setMultimedia(List<Multimedia> multimedia) {
		this.multimedia = multimedia;
	}
	
	public MediaLink(String type, String keyword){
		super();
		this.type = type;
		this.keyword = keyword;
	}
	public MediaLink(){
		super();
	
	}
	
	
}
