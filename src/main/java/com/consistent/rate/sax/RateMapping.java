package com.consistent.rate.sax;

import java.io.IOException;
import java.io.StringWriter;


import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class RateMapping extends MarcaMapping implements Mapping{
	private String guid;
	private String code;
	private String name;
	private String title;
	private String language;
	private String keyword;
	private String shortDescription;
	private String description;
	private String order;
	private String channel;
	private String benefits;
	private String restrictions;
	private String enddate;
	private String currency;
	

	public String getGuid() {
		return guid;
	}



	public void setGuid(String guid) {
		this.guid = guid;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public String getKeyword() {
		return keyword;
	}



	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



	public String getShortDescription() {
		return shortDescription;
	}



	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getOrder() {
		return order;
	}



	public void setOrder(String order) {
		this.order = order;
	}



	public String getChannel() {
		return channel;
	}



	public void setChannel(String channel) {
		this.channel = channel;
	}



	public String getBenefits() {
		return benefits;
	}



	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}



	public String getRestrictions() {
		return restrictions;
	}



	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}



	public String getEnddate() {
		return enddate;
	}



	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}



	public String getCurrency() {
		return currency;
	}



	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public RateMapping(){
		
		this.code = "";
		this.name = "";
		this.title = "";
		this.language = "";
		this.keyword = "";
		this.shortDescription = "";
		this.description = "";
		this.order = "";
		this.channel = "";
		this.benefits = "";
		this.restrictions = "";
		this.enddate = "";
		this.currency = "";
	}
	
	public RateMapping(String guid, String code, String name, String title, String language, String keyword,
			String shortDescription, String description, String order, String channel, String benefits,
			String restrictions, String enddate, String currency) {
		super();
		this.guid = guid;
		this.code = code;
		this.name = name;
		this.title = title;
		this.language = language;
		this.keyword = keyword;
		this.shortDescription = shortDescription;
		this.description = description;
		this.order = order;
		this.channel = channel;
		this.benefits = benefits;
		this.restrictions = restrictions;
		this.enddate = enddate;
		this.currency = currency;
	}



	@Override
	public String getMapping() throws XMLStreamException, IOException{
		StringWriter stringWriter = new StringWriter();
		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
		XMLStreamWriter xMLStreamWriter = xmlOutputFactory.createXMLStreamWriter(stringWriter);
		
			xMLStreamWriter.writeStartElement("rate");
				xMLStreamWriter.writeStartElement("guid");
					xMLStreamWriter.writeCharacters(guid);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("code");
					xMLStreamWriter.writeCharacters(code);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("name");
					xMLStreamWriter.writeCharacters(name);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("title");
					xMLStreamWriter.writeCharacters(title);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("language");
					xMLStreamWriter.writeCharacters(language);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("keyword");
					xMLStreamWriter.writeCharacters(keyword);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("shortDescription");
					xMLStreamWriter.writeCharacters(shortDescription);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("description");
					xMLStreamWriter.writeCharacters(description);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("order");
					xMLStreamWriter.writeCharacters(order);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("channel");
					xMLStreamWriter.writeCharacters(channel);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("benefits");
					xMLStreamWriter.writeCharacters(benefits);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("restrictions");
					xMLStreamWriter.writeCharacters(restrictions);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("enddate");
					xMLStreamWriter.writeCharacters(enddate);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("currency");
					xMLStreamWriter.writeCharacters(currency);
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeStartElement("mediaLinks");
					xMLStreamWriter.writeStartElement("mediaLink");
						xMLStreamWriter.writeStartElement("type");
							xMLStreamWriter.writeCharacters("0");
						xMLStreamWriter.writeEndElement();
						xMLStreamWriter.writeStartElement("keyword");
							xMLStreamWriter.writeCharacters("0");
						xMLStreamWriter.writeEndElement();
						xMLStreamWriter.writeStartElement("multimedia");
							xMLStreamWriter.writeStartElement("url");
								xMLStreamWriter.writeCharacters("0");
							xMLStreamWriter.writeEndElement();
						xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndElement();
		xMLStreamWriter.flush();
		xMLStreamWriter.close();
		String result = stringWriter.getBuffer().toString();
		stringWriter.close(); 
		return result;
	}

}
