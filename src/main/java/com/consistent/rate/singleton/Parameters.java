package com.consistent.rate.singleton;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class Parameters {
	
	private static final Log log = LogFactoryUtil.getLog(Parameters.class);
	private static Parameters p;
	
	private String siteId;
	private String brandCode;
	private String lenguaje;
	private String channel;
	private String bookingDate;
	private String hotelCode;
	private String ckeckInDate;
	private String checkOutDate;
	
	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getCkeckInDate() {
		return ckeckInDate;
	}

	public void setCkeckInDate(String ckeckInDate) {
		this.ckeckInDate = ckeckInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	private Parameters(){}
	
	public static Parameters getInstance(){
		if(p == null){
			p = new Parameters();
		}
		return p;
	}
	
}
