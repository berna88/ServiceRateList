package com.consistent.rate.util;


import java.io.IOException;
import java.util.HashSet;

import javax.xml.stream.XMLStreamException;

import com.consistent.rate.constants.Constants;
import com.consistent.rate.interfaces.Mapping;
import com.consistent.rate.interfaces.SAX;
import com.consistent.rate.sax.HotelMapping;
import com.consistent.rate.sax.MarcaMapping;
import com.consistent.rate.sax.RateMapping;
import com.liferay.portal.kernel.exception.PortalException;
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;

public class Util extends RateMapping implements SAX{
	
	//private static final Log log = LogFactoryUtil.getLog(Util.class);

	@Override
	public String getXML() throws PortalException, XMLStreamException, IOException {
		// Se iteran los rates para la inserción en marca
				final HashSet<RateMapping> rates = getArticlesByCodeBrand();
				final HotelMapping hotels = new HotelMapping();
				final Mapping mapping = new MarcaMapping("", Constants.CODIGODEMARCA, Constants.getNameBrand(Constants.CODIGODEMARCA), "", Constants.LENGUAJE, rates, hotels.getHotels());
				return mapping.getMapping();
	}	  
}


