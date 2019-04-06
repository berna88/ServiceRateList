package com.consistent.rate.models.room;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "room")
public class rooms {

	List<room> room;

	public rooms() {

	}

	
	public rooms(List<room> room) {
		super();
		this.room = room;
	}

	public List<room> getRoom() {
		return room;
	}

	public void setRoom(List<room> room) {
		this.room = room;
	}

	
	
	
	
	
}
