package com.consistent.rate.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contents")
public class Contents {
	
	List<Content> content;
	
	@XmlElement(name = "content")
	public List<Content> getContent() {
		return content;
	}

	public void setContent(List<Content> content) {
		this.content = content;
	}

	public Contents(List<Content> content) {
		super();
		this.content = content;
	}
	
	public Contents(){
		content = new ArrayList<Content>();
	}
	
	

}
