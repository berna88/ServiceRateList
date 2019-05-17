package com.consistent.rate.models.hotel;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.consistent.rate.models.room.rooms;

@XmlRootElement(name = "hotel")
@XmlType(propOrder = {"guid","code","name","title","language", "keyword","shortDescription","description","order","channel","mediaLinks","locations","telephones","rooms"})
public class Hotel {
	
	String hotel;
	//String brandcode;
	String guid;
	String code;
	String name;
	String title;
	String language;
	String keyword;
	String shortDescription;
	String description;
	String order;
	String channel;
	List<Telephones> telephones;
	List<Location> locations;
	List<MediaLinks> mediaLinks;
	rooms room;
	
	@XmlElement(name = "rooms")
	public rooms getRooms() {
		return room;
	}
	public void setRooms(rooms room) {
		this.room = room;
	}
	/*	
	@XmlElement(name = "brandcode")
	public String getBrandcode() {
		return brandcode;
	}
	public void setBrandcode(String brandcode) {
		this.brandcode = brandcode;
	}*/
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
	@XmlElement(name = "shortDescription")
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	@XmlElement(name = "telephones")
	public List<Telephones> getTelephones() {
		return telephones;
	}
	public void setTelephones(List<Telephones> telephones) {
		this.telephones = telephones;
	}
	
	@XmlElement(name = "locations")
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	@XmlElement(name = "medialinks")
	public List<MediaLinks> getMediaLinks() {
		return mediaLinks;
	}
	public void setMediaLinks(List<MediaLinks> mediaLinks) {
		this.mediaLinks = mediaLinks;
	}
	

}
