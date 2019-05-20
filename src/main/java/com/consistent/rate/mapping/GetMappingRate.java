package com.consistent.rate.mapping;

import com.consistent.rate.configuration.Otherconfig;
import com.consistent.rate.constants.Constants;
import com.consistent.rate.models.Brand;
import com.consistent.rate.models.Content;
import com.consistent.rate.models.Contents;
import com.consistent.rate.models.Rates;
import com.consistent.rate.sax.Mapping;
import com.consistent.rate.sax.MarcaMapping;
import com.consistent.rate.sax.RateMapping;
import com.consistent.rate.util.Util;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;


@Component(
		immediate=true,
		configurationPid = "com.consistent.rate.configuration.Otherconfig",
		configurationPolicy = ConfigurationPolicy.OPTIONAL,
		property={"jaxrs.application=true"})

public class GetMappingRate extends RateMapping{
	
	private static final Log log = LogFactoryUtil.getLog(GetMappingRate.class);
	
		// Metodo que filtra por codigo
		public HashSet<RateMapping> getArticlesByCodeBrand() throws PortalException{
			
			HashSet<RateMapping> rates = new HashSet<>();
			
			String locale = Constants.getLanguaje();// Contiene el lenguaje
			
			log.info("Contratos: "+ Constants.CONTRACTCODES);
			// condicion para filtrar contratos
			if(!Constants.CONTRACTCODES.equals("")){
				String codes = Constants.CONTRACTCODES;// vienen los contratos filtrados
				Constants.CONTRACTCODES = "";// se restablece el valor
				String[] codesSplit = codes.split(",");
				rates = getWebContentRateFilter(codesSplit,locale);
			}else{
				rates = getWebContentRate(locale);
				log.info("Sin contratos");
			}
			return rates;
		}
	
	
	public final String getXML() throws PortalException, XMLStreamException, IOException{
		String nameBrand = Constants.getNameBrand(Constants.CODIGODEMARCA);
		
		
		//Se crea el brand que contendra toda la información
		Brand brand = new Brand();
		
		// Se iteran los rates para la inserción en marca
		HashSet<RateMapping> rates = getArticlesByCodeBrand();
		Mapping mapping = new MarcaMapping("", "", "", "", "", rates);
		List<Rates> rates2 = new ArrayList<>();
		//rates2.add(new Rates(rates));
		Util.getHotels();
		//brand.setHotel();
		
		brand.setRates(rates2);
		brand.setCode(Constants.CODIGODEMARCA);
		brand.setLanguage(Constants.LENGUAJE);
		brand.setChannel(Constants.CHANNEL);
		brand.setOrder("0");
		brand.setName(nameBrand);
		
		List <Content> content = new  ArrayList<Content>();
		content.add(new Content(brand));
		final Contents contents = new Contents();
		contents.setContent(content);
		return mapping.getMapping();
	
	}
	/*
	public Hotel getHotel() throws PortalException{
		long base=Contants.FOLDER_ID;
        long id_brand = _util_rate.journalByCodeFolder(base,Contants.CODIGODEMARCA.toUpperCase(),Contants.SITE_ID);
        Long ids=_util_rate.journalByCodeFolder(id_brand,Contants.CODIGODEHOTEL,Contants.SITE_ID);
        if(!ids.equals(id_brand)){
        	return _util_rate.getJournalArticleByFolderId(ids,Contants.CODIGODEMARCA.toUpperCase(),Contants.getLanguaje() ,Contants.SITE_ID);
        }else{
        	return new com.consistent.rate.models.hotel.Hotel();
        }
	}*/
	

	@Activate
	@Modified
	public void activate(Map<String, Object> properties) {
				log.info("Se ha cardo la configuracion del portal " + new Date().toString());
				_restConfigurationApi = ConfigurableUtil.createConfigurable(Otherconfig.class, properties);
				
		if (_restConfigurationApi != null) {
			if(_restConfigurationApi.structureHotelId()!=0){
				Constants.STRUCTURE_HOTEL_ID =_restConfigurationApi.structureHotelId();
				log.info("Estructura de hotel localizada="+Constants.STRUCTURE_HOTEL_ID);
			}
			else{
				Constants.STRUCTURE_HOTEL_ID = new Long(1516944);
				log.info("For sample DXP REST config, info="+Constants.STRUCTURE_HOTEL_ID);
			}
			if(_restConfigurationApi.structureRatesId()!=0){
				Constants.STRUCTURE_RATE_ID =_restConfigurationApi.structureRatesId();
				log.info("For sample DXP REST config, info="+Constants.STRUCTURE_RATE_ID);
			}
			else{
				Constants.STRUCTURE_RATE_ID = new Long(1516944);
				log.info("For sample DXP REST config, info="+Constants.STRUCTURE_RATE_ID);
			}
			if(_restConfigurationApi.folderId()!=0){
				Constants.FOLDER_ID =_restConfigurationApi.folderId();
				log.info("For sample DXP REST config, info="+Constants.FOLDER_ID);
			}
			else{
				Constants.FOLDER_ID = new Long(1516944);
				log.info("For sample DXP REST config, info="+Constants.FOLDER_ID);
			}
			} else {
			System.out.println("The sample DXP REST config object is not yet initialized");
		}
	}

	private Otherconfig _restConfigurationApi;
}
