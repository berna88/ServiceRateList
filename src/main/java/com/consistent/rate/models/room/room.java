package com.consistent.rate.models.room;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.consistent.rate.models.hotel.MediaLink;
@XmlType(propOrder = {"guid","code","name","title","language", "keyword","shortDescription","description","order","channel","medialinks","room"})
public class room {
	
	String room;
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
	List<MediaLink> medialinks;
	
	@XmlElement(name = "medialinks")
	public List<MediaLink> getMedialinks() {
		return medialinks;
	}
	public void setMedialinks(List<MediaLink> medialinks) {
		this.medialinks = medialinks;
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

	public void setRoom(String room) {
		this.room = room;
	}
	
	@XmlElement(name = "room")
	public String getRoom() {
		return room;
	}
	public void setRoomtype(String room) {
		this.room = room;
	}
	
	@XmlElement(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@XmlElement(name = "guid")
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	
}
