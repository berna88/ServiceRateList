package com.consistent.rate.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "medialinks")
public class Medialinks {
	
	 List<Medialink> medialink;
	@XmlElement(name = "medialink")
	public List<Medialink> getMedialink() {
		return medialink;
	}

	public void setMedialink(List<Medialink> medialink) {
		this.medialink = medialink;
	}

	public Medialinks(List<Medialink> medialink) {
		super();
		this.medialink = medialink;
	}
	
	public Medialinks(){
		this.medialink = new ArrayList<>();
	}

}
