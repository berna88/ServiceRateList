package com.consistent.rate.models.hotel;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class MediaLinks {
	
	List<MediaLink> medialinks;
	
	@XmlElement(name = "medialink")
	public List<MediaLink> getMedialinks() {
		return medialinks;
	}

	public void setMedialinks(List<MediaLink> medialinks) {
		this.medialinks = medialinks;
	}
	public MediaLinks(){
		super();
	}
	
	public MediaLinks(List<MediaLink> mediaLinks){
		super();
		this.medialinks = mediaLinks;
	}
	
	

}
