package com.consistent.rate.mapping;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.consistent.rate.constants.Contants;
import com.consistent.rate.models.Contents;
import com.consistent.rate.util.Util;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@Path("/services/posadas")
public class ServicesRest {
	private static final Log log = LogFactoryUtil.getLog(GetMappingRate.class);

	GetMappingRate _rates= new GetMappingRate();
    
	com.consistent.rate.util.Util _util_rate=  new Util();
	
	
	@GET
	@Path("/getHotelRoomRates")
	@Produces(MediaType.APPLICATION_XML)
	public Contents getRate(
			@QueryParam("siteID") String siteID,
			@QueryParam("brandcode") String brandcode,
			@QueryParam("language") String languaje,
			@QueryParam("channel") String channel,
			@QueryParam("bookingdate") String bookingdate,
			@QueryParam("hotelcode") String hotelcode,
			@QueryParam("checkindate") String checkindate,
			@QueryParam("checkoutdate") String checkoutdate) throws PortalException {
		log.info("<-------- Metodo getRateList Normal getHotelRoomRates--------->");
		// Estableciendo el siteId del sitio
		Contants.SITE_ID = Long.parseLong(siteID);
		// Estableciendo la marca
		Contants.CODIGODEMARCA = brandcode;
		// Estableciendo el lenguaje
		Contants.LENGUAJE = languaje;
		log.info("language select: "+Contants.LENGUAJE);
		// Codigo del hotel
		Contants.CODIGODEHOTEL = hotelcode;
		//Estableciendo canal
		Contants.CHANNEL = channel;
		
		Contents xml = _rates.getXML();
		log.info("<-------- Proceso finalizado getHotelRoomRates--------->");
		return xml;
	}

	@GET
	@Path("/getHotelRoomRatesOptimizado")
	@Produces(MediaType.APPLICATION_XML)
	public Contents getRatesOptimizado(
			@QueryParam("siteID") String siteID,
			@QueryParam("brandcode") String brandcode,
			@QueryParam("language") String languaje,
			@QueryParam("channel") String channel,
			@QueryParam("bookingdate") String bookingdate,
			@QueryParam("hotelcode") String hotelcode,
			@QueryParam("checkindate") String checkindate,
			@QueryParam("checkoutdate") String checkoutdate,
			@QueryParam("contractcodes") String contractcodes) throws PortalException {
		log.info("<-------- Metodo getRateList Optimizado --------->");		
		// Estableciendo el siteId del sitio
		Contants.SITE_ID = Long.parseLong(siteID);
		// Estableciendo la marca
		Contants.CODIGODEMARCA = brandcode;
		// Estableciendo el lenguaje
		Contants.LENGUAJE = languaje;
		log.info("language select: "+Contants.LENGUAJE);
		// Estrableciendo contractcodes
		Contants.validContractCodes(contractcodes);
		// Codigo del hotel
		Contants.CODIGODEHOTEL = hotelcode;
		//Estableciendo canal
		Contants.CHANNEL = channel;
		
		Contents xml = _rates.getXML();
		log.info("<-------- Proceso finalizado --------->");
		return xml;
	}
	
	
	
	
}
