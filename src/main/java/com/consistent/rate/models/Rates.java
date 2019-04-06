package com.consistent.rate.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rates")
public class Rates {
	
	 List<Rate> rates = new ArrayList<Rate>();
	 
	@XmlElement(name = "rate")
	public List<Rate> getRates() {
		return this.rates;
	}

	public void setRates(Rate rate) {
		this.rates.add(rate);
	}
	
	public Rates(List<Rate> rates) {
		super();
		this.rates = rates;
	}

	public Rates(){
	 this.rates = new ArrayList<Rate>(); 
	}
	
	
}
