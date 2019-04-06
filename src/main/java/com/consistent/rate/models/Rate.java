package com.consistent.rate.models;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.consistent.rate.models.hotel.MediaLinks;

@XmlRootElement(name = "rate")
@XmlType(propOrder = {"guid","code","name","title","language", "keyword","shortDescription","description","order","channel","benefits","restrictions","enddate","currency","mediaLinks"})
public class Rate {
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
	 //String promocode;
	 String benefits;
	 String restrictions;
	 String currency;
	 String enddate;
	 //String articleId;
	 List<MediaLinks> mediaLinks;
	 
	 @XmlElement(name = "mediaLinks")
	 public List<MediaLinks> getMediaLinks() {
		return mediaLinks;
	}
	public void setMediaLinks(List<MediaLinks> mediaLinks) {
		this.mediaLinks = mediaLinks;
	}
	@XmlElement(name = "enddate")
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	/*@XmlElement(name = "articleid")
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
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
/*	@XmlElement(name = "promocode")
	public String getPromocode() {
		return promocode;
	}
	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}*/
	@XmlElement(name = "benefits")
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	@XmlElement(name = "restrictions")
	public String getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}
	@XmlElement(name = "currency")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	
	public Rate(String guid, String code, String name, String title, String language, String keyword,
			String shortDescription, String description, String order, String channel, String promocode,
			String benefits, String restrictions, String currency, String articleId) {
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
		//this.promocode = promocode;
		this.benefits = benefits;
		this.restrictions = restrictions;
		this.currency = currency;
		//this.articleId = articleId;
	}
	
	public Rate(){
		//this.articleId = "";
		this.guid = "";
		this.code = "";
		this.name = "";
		this.title = "";
		this.language = "";
		this.keyword = "";
		this.shortDescription = "";
		this.description = "";
		this.order = "";
		this.channel = "";
		//this.promocode = "";
		this.benefits = "";
		this.restrictions = "";
		this.currency = "";
	}

}
