package com.consistent.rate.mapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.consistent.rate.configuration.Otherconfig;
import com.consistent.rate.constants.Contants;
import com.consistent.rate.models.Brand;
import com.consistent.rate.models.Content;
import com.consistent.rate.models.Contents;
import com.consistent.rate.models.Rate;
import com.consistent.rate.models.Rates;
import com.consistent.rate.models.hotel.MediaLinks;
import com.consistent.rate.models.hotel.Hotel;
import com.consistent.rate.models.hotel.MediaLink;
import com.consistent.rate.models.hotel.Multimedia;
import com.consistent.rate.models.room.room;
import com.consistent.rate.models.room.rooms;
import com.consistent.rate.util.Util;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.thoughtworks.xstream.XStream;


@Component(
		immediate=true,
		configurationPid = "com.consistent.rate.configuration.Otherconfig",
		configurationPolicy = ConfigurationPolicy.OPTIONAL,
		property={"jaxrs.application=true"})
public class GetMappingRate {
	private static final Log log = LogFactoryUtil.getLog(GetMappingRate.class);
	com.consistent.rate.util.Util _util_rate=  new Util();
	
	
		// Metodo que filtra por codigo
		public List<Rate> getArticlesByCodeBrand() throws PortalException{
			
			List<JournalArticle> articlesFilterCategories = Util.getWebContentRate();// Obtiene los datos ya filtrados por query
			
			List<JournalArticle> articlesFilterContract = new ArrayList<JournalArticle>();
			
			GetMappingRate rate = new GetMappingRate();
		
			List<Rate> rates = new ArrayList<>();
			
			String locale = Contants.getLanguaje();// Contiene el lenguaje
			
			log.info("Contratos: "+ Contants.CONTRACTCODES);
			// condicion para filtrar contratos
			if(!Contants.CONTRACTCODES.equals("")){
				
				String codes = Contants.CONTRACTCODES;// vienen los contratos filtrados
				Contants.CONTRACTCODES = "";// se restablece el valor
				String[] codesSplit = codes.split(",");
				
				for(JournalArticle a:articlesFilterCategories){
					
					for(int i = 0; i < codesSplit.length;i++)
					{
						if(a.getContent().contains(codesSplit[i]))
						   {articlesFilterContract.add(a); break;}
					}
				}
				
				rates = rate.RatesContents(articlesFilterContract, locale);
			}else{
				rates = rate.RatesContents(articlesFilterCategories, locale);
			}
			return rates;
		}
	
	
	public Contents getXML() throws PortalException{
		String nameBrand = Contants.getNameBrand(Contants.CODIGODEMARCA);
		
		//Se crea el brand que contendra toda la información
		Brand brand = new Brand();
		
		// Se iteran los rates para la inserción en marca
		List<Rate> rates = getArticlesByCodeBrand();
		List<Rates> rates2 = new ArrayList<>();
		rates2.add(new Rates(rates));
		brand.setHotel(getHotel());
		brand.setRates(rates2);
		brand.setCode(Contants.CODIGODEMARCA);
		brand.setLanguage(Contants.LENGUAJE);
		brand.setChannel(Contants.CHANNEL);
		brand.setOrder("0");
		brand.setName(nameBrand);
		
		List <Content> content = new  ArrayList<Content>();
		content.add(new Content(brand));
		Contents contents = new Contents();
		contents.setContent(content);
		return contents;
	
	}
	
	public Hotel getHotel() throws PortalException{
		long id_base= _util_rate.getFolderBaseByConfiguration(Contants.SITE_ID);
        long id_brand = _util_rate.journalRootFolder(id_base,Contants.CODIGODEMARCA.toUpperCase(),Contants.SITE_ID);
        Long ids=_util_rate.journalByCodeFolder(id_brand,Contants.CODIGODEHOTEL,Contants.SITE_ID);
        if(!ids.equals(id_brand)){
        	return _util_rate.getJournalArticleByFolderId(ids,Contants.CODIGODEMARCA.toUpperCase(),Contants.getLanguaje() ,Contants.SITE_ID);
        }else{
        	return new com.consistent.rate.models.hotel.Hotel();
        }
	}
	

	
	
	public final List<Rate> RatesContents(List<JournalArticle> articles, String locale) throws PortalException {
		log.info("<------ Metodo RatesContents ------>");
		List<Rate> rates = new ArrayList<Rate>();
		
		long TInicio, TFin, tiempo;
		TInicio = System.currentTimeMillis(); 
		
		for(JournalArticle article: articles){
			Rate rate = new Rate();
			//rate.setArticleId(article.getArticleId());
			rate.setTitle(article.getTitle(locale));
			Document document = null;
			try {
				
				// Asignando valores al objeto haciendo el filtrado por el nombre de la categoria
				document = SAXReaderUtil.read(article.getContentByLocale(locale));
				rate.setCode(document.valueOf("//dynamic-element[@name='codeRate']/dynamic-content/text()"));
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
				
				rate.setMediaLinks(mediaLinks);
				rates.add(rate);
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				log.error("Error de mapeo de rates: "+e.getMessage());
			}
		}
		TFin = System.currentTimeMillis();
		tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
		log.info("Fin de proceso de parseo de datos " + tiempo); 
		log.error("Finalizacion de parseo");
		return rates;
	}
	
	@Activate
	@Modified
	public void activate(Map<String, Object> properties) {
	
		System.out.println("The sample DXP REST app has been activated/updated at " + new Date().toString());
  
		_sampleRESTConfiguration = ConfigurableUtil.createConfigurable(Otherconfig.class, properties);
		
		if (_sampleRESTConfiguration != null) {
			if(_sampleRESTConfiguration.folderName()!= null && _sampleRESTConfiguration.folderName().size() > 0){
				Contants.FOLDERS = _sampleRESTConfiguration.folderName();
				log.info("For sample DXP REST config, info="+Contants.FOLDERS);
		
			}else {
				Contants.FOLDERS = new ArrayList<>();
				Contants.FOLDERS.add("Posadas");
				Contants.FOLDERS.add("Hotel");
				log.info("For sample DXP REST config, info="+Contants.FOLDERS);
			}
			if(_sampleRESTConfiguration.NameDefaultStructure()!= null && !_sampleRESTConfiguration.NameDefaultStructure().isEmpty()){
				Contants.NAME_STRUCTURE_DEFAULT = _sampleRESTConfiguration.NameDefaultStructure();
				log.info("For sample DXP REST config, info="+Contants.NAME_STRUCTURE_DEFAULT);
		
			}else {
				Contants.NAME_STRUCTURE_DEFAULT ="Hoteles";
				log.info("For sample DXP REST config, info="+Contants.NAME_STRUCTURE_DEFAULT);
			}
			
			} else {
			System.out.println("The sample DXP REST config object is not yet initialized");
		}
	}
	
	
	private Otherconfig _sampleRESTConfiguration;
}
