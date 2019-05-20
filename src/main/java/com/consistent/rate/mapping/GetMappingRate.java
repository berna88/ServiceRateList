package com.consistent.rate.mapping;

import com.consistent.rate.configuration.Otherconfig;
import com.consistent.rate.constants.Contants;
import com.consistent.rate.models.Brand;
import com.consistent.rate.models.Content;
import com.consistent.rate.models.Contents;
import com.consistent.rate.models.Rate;
import com.consistent.rate.models.Rates;
import com.consistent.rate.sax.Mapping;
import com.consistent.rate.sax.MarcaMapping;
import com.consistent.rate.sax.RateMapping;
import com.consistent.rate.util.Util;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

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
public class GetMappingRate {
	private static final Log log = LogFactoryUtil.getLog(GetMappingRate.class);
	RateMapping _util_rate=  new RateMapping();
	
	
		// Metodo que filtra por codigo
		public HashSet<RateMapping> getArticlesByCodeBrand() throws PortalException{
			
			HashSet<JournalArticle> articlesFilterCategories = _util_rate.getWebContentRate();// Obtiene los datos ya filtrados por query
			
			HashSet<JournalArticle> articlesFilterContract = new HashSet<JournalArticle>();
			
			
			HashSet<RateMapping> rates = new HashSet<>();
			
			String locale = Contants.getLanguaje();// Contiene el lenguaje
			
			log.info("Contratos: "+ Contants.CONTRACTCODES);
			// condicion para filtrar contratos
			if(!Contants.CONTRACTCODES.equals("")){
				
				String codes = Contants.CONTRACTCODES;// vienen los contratos filtrados
				Contants.CONTRACTCODES = "";// se restablece el valor
				log.info("Con contratos");
				String[] codesSplit = codes.split(",");
				rates = _util_rate.getWebContentRateFilter(codesSplit,locale);
				for(JournalArticle a:articlesFilterCategories){
					
					for(int i = 0; i < codesSplit.length;i++)
					{
						if(a.getContent().contains(codesSplit[i]))
						   {articlesFilterContract.add(a); break;}
					}
				}
				rates = RatesContents(articlesFilterCategories, locale);
			}else{
				rates = _util_rate.getWebContentRate(locale);
				log.info("Sin contratos");
			}
			return rates;
		}
	
	
	public final String getXML() throws PortalException, XMLStreamException, IOException{
		String nameBrand = Contants.getNameBrand(Contants.CODIGODEMARCA);
		
		
		//Se crea el brand que contendra toda la información
		Brand brand = new Brand();
		
		// Se iteran los rates para la inserción en marca
		HashSet<RateMapping> rates = getArticlesByCodeBrand();
		Mapping mapping = new MarcaMapping("", "", "", "", "", rates);
		List<Rates> rates2 = new ArrayList<>();
		//rates2.add(new Rates(rates));
		
		brand.setHotel(Util.getHotels());
		
		brand.setRates(rates2);
		brand.setCode(Contants.CODIGODEMARCA);
		brand.setLanguage(Contants.LENGUAJE);
		brand.setChannel(Contants.CHANNEL);
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
	

	public final HashSet<RateMapping> RatesContents(HashSet<JournalArticle> articles, String locale) throws PortalException {
		log.info("<------ Metodo RatesContents ------>");
		HashSet<RateMapping> rates = new HashSet<>();
		
		long TInicio, TFin, tiempo;
		TInicio = System.currentTimeMillis(); 
		RateMapping mapping;
		for(JournalArticle article: articles){
			final Rate rate = new Rate();
			//rate.setArticleId(article.getArticleId());
			rate.setTitle(article.getTitle(locale));
			Document document = null;
			try {
				
				// Asignando valores al objeto haciendo el filtrado por el nombre de la categoria
				document = SAXReaderUtil.read(article.getContentByLocale(locale));
				/*log.info(document.valueOf("//dynamic-element[@name='codeRate']/dynamic-content/text()"));
				log.info(document.valueOf("//dynamic-element[@name='nameRate']/dynamic-content/text()"));
				log.info(document.valueOf("//dynamic-element[@name='keywordRate']/dynamic-content/text()"));
				log.info(document.valueOf("//dynamic-element[@name='descriptionLongRate']/dynamic-content/text()"));
				log.info(document.valueOf("//dynamic-element[@name='shortDescriptionRate']/dynamic-content/text()"));
				log.info(document.valueOf("//dynamic-element[@name='benefitsRate']/dynamic-content/text()"));
				log.info(document.valueOf("//dynamic-element[@name='Restrictions1']/dynamic-content/text()"));
				log.info(document.valueOf("//dynamic-element[@name='currencyRate']/dynamic-content/text()"));
				log.info(document.valueOf("//dynamic-element[@name='finalDateBooking']/dynamic-content/text()"));*/
				mapping = new RateMapping("", document.valueOf("//dynamic-element[@name='codeRate']/dynamic-content/text()"), document.valueOf("//dynamic-element[@name='nameRate']/dynamic-content/text()"), "", Contants.LENGUAJE, document.valueOf("//dynamic-element[@name='keywordRate']/dynamic-content/text()"), document.valueOf("//dynamic-element[@name='shortDescriptionRate']/dynamic-content/text()"), document.valueOf("//dynamic-element[@name='descriptionLongRate']/dynamic-content/text()"), Mapping.order, Mapping.channel, document.valueOf("//dynamic-element[@name='benefitsRate']/dynamic-content/text()"), document.valueOf("//dynamic-element[@name='Restrictions1']/dynamic-content/text()"), document.valueOf("//dynamic-element[@name='finalDateBooking']/dynamic-content/text()"), document.valueOf("//dynamic-element[@name='currencyRate']/dynamic-content/text()")); 
				
				/*rate.setCode(document.valueOf("//dynamic-element[@name='codeRate']/dynamic-content/text()"));
				rate.setName(document.valueOf("//dynamic-element[@name='nameRate']/dynamic-content/text()"));
				rate.setKeyword(document.valueOf("//dynamic-element[@name='keywordRate']/dynamic-content/text()"));
				rate.setDescription(document.valueOf("//dynamic-element[@name='descriptionLongRate']/dynamic-content/text()"));
				rate.setShortDescription(document.valueOf("//dynamic-element[@name='shortDescriptionRate']/dynamic-content/text()"));
				rate.setBenefits(document.valueOf("//dynamic-element[@name='benefitsRate']/dynamic-content/text()"));
				rate.setRestrictions(document.valueOf("//dynamic-element[@name='Restrictions1']/dynamic-content/text()"));
				rate.setCurrency(document.valueOf("//dynamic-element[@name='currencyRate']/dynamic-content/text()"));
				rate.setEnddate(document.valueOf("//dynamic-element[@name='finalDateBooking']/dynamic-content/text()"));
				rate.setGuid(article.getArticleId());
				rate.setLanguage(Contants.LENGUAJE);
				rate.setChannel("www");
				rate.setOrder("0");
				
				List<Multimedia> multimedia = new ArrayList<>();
				Multimedia multi = new Multimedia();
				multi.setUrl(document.valueOf("//dynamic-element[@name='mediaLinkRate']/dynamic-content/text()"));
				multi.setType("icon");
				multimedia.add(multi);
				
				List<MediaLink> medialink = new ArrayList<>();
				MediaLink link = new MediaLink();
				link.setKeyword("rate_icon");
				link.setType("image");
				link.setMultimedia(multimedia);
				medialink.add(link);
				
				List<MediaLinks> mediaLinks = new ArrayList<>();
				MediaLinks links = new MediaLinks();
				links.setMedialinks(medialink);
				mediaLinks.add(links);
				
				rate.setMediaLinks(mediaLinks);*/
				rates.add(mapping);
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				log.error("Error de mapeo de rates: "+e.getMessage());
			}
		}
		TFin = System.currentTimeMillis();
		tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
		log.info("Fin de proceso de parseo de datos " + tiempo); 
		log.info("Finalizacion de parseo");
		return rates;
	}
	
	@Activate
	@Modified
	public void activate(Map<String, Object> properties) {
				log.info("Se ha cardo la configuracion del portal " + new Date().toString());
				_restConfigurationApi = ConfigurableUtil.createConfigurable(Otherconfig.class, properties);
				
		if (_restConfigurationApi != null) {
			if(_restConfigurationApi.structureHotelId()!=0){
				Contants.STRUCTURE_HOTEL_ID =_restConfigurationApi.structureHotelId();
				log.info("Estructura de hotel localizada="+Contants.STRUCTURE_HOTEL_ID);
			}
			else{
				Contants.STRUCTURE_HOTEL_ID = new Long(1516944);
				log.info("For sample DXP REST config, info="+Contants.STRUCTURE_HOTEL_ID);
			}
			if(_restConfigurationApi.structureRatesId()!=0){
				Contants.STRUCTURE_RATE_ID =_restConfigurationApi.structureRatesId();
				log.info("For sample DXP REST config, info="+Contants.STRUCTURE_RATE_ID);
			}
			else{
				Contants.STRUCTURE_RATE_ID = new Long(1516944);
				log.info("For sample DXP REST config, info="+Contants.STRUCTURE_RATE_ID);
			}
			if(_restConfigurationApi.folderId()!=0){
				Contants.FOLDER_ID =_restConfigurationApi.folderId();
				log.info("For sample DXP REST config, info="+Contants.FOLDER_ID);
			}
			else{
				Contants.FOLDER_ID = new Long(1516944);
				log.info("For sample DXP REST config, info="+Contants.FOLDER_ID);
			}
			} else {
			System.out.println("The sample DXP REST config object is not yet initialized");
		}
	}

	private Otherconfig _restConfigurationApi;
}
