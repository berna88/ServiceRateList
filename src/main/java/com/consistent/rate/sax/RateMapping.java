package com.consistent.rate.sax;

import com.consistent.rate.constants.Contants;
import com.consistent.rate.models.Rate;
import com.consistent.rate.singleton.Portal;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleResource;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleResourceLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashSet;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class RateMapping extends Portal implements Mapping{
	private static final Log log = LogFactoryUtil.getLog(RateMapping.class);
	
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
	
	//Metodo que contiene todos los elementos en Rate
	public HashSet<RateMapping> getWebContentRate(String locale) throws PortalException{
		log.info("<---------- Metodo getWebContentRate Sin contrato---------->");
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
		//Coleccion donde se van a guardar lista de rates
		final HashSet<RateMapping> rates = new HashSet<RateMapping>();
		Long categoryId = getCategory(Contants.CODIGODEMARCA);
		assetEntryQuery.setAnyCategoryIds(new long[] { categoryId });
		assetEntryQuery.setClassName("com.liferay.journal.model.JournalArticle");
		final HashSet<AssetEntry> assetEntryList = new HashSet<AssetEntry>(AssetEntryLocalServiceUtil.getEntries(assetEntryQuery));//convirtiendo la lista en hashSet
		log.info("Tamaño de elemento por categorias: "+assetEntryList.size());
		RateMapping mapping;
		try {
			final Long startHashSetTime = System.currentTimeMillis();
			for (AssetEntry ae : assetEntryList) {
			    JournalArticleResource journalArticleResource = JournalArticleResourceLocalServiceUtil.getJournalArticleResource(ae.getClassPK());
			    JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(journalArticleResource.getResourcePrimKey());
			    
			    if(journalArticle.getGroupId() == Contants.SITE_ID){
			    	if(journalArticle.getDDMStructure().getStructureId() == Contants.STRUCTURE_RATE_ID){
			    				
								//article.add(journalArticle);
								final Rate rate = new Rate();
								rate.setTitle(journalArticle.getTitle(locale));
								Document document = null;
								
								document = SAXReaderUtil.read(journalArticle.getContentByLocale(locale));

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
									rate.setGuid(journalArticle.getArticleId());
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
									rates.add(rate);*/
									rates.add(mapping);
						
				   // article.add(journalArticle);
				    }
			    }
			}
			final Long endHashSetTime = System.currentTimeMillis();
			log.info("Tiempo de conversion de todos los rate en segundos: " + (endHashSetTime - startHashSetTime)/1000);
		} catch (Exception e) {
			log.error("module getWebContentRate: "+e);
		}
		return rates;
		
	}
	
	// Metodo que obtiene los rate por filtro
	public HashSet<RateMapping> getWebContentRateFilter(String[] codesSplit,String locale) throws PortalException{
		log.info("<---------- Metodo getWebContentRate Con filtros ---------->");
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
		final HashSet<RateMapping> rates = new HashSet<>();
		Long categoryId = getCategory(Contants.CODIGODEMARCA);
		assetEntryQuery.setAnyCategoryIds(new long[] { categoryId });
		assetEntryQuery.setClassName("com.liferay.journal.model.JournalArticle");
		HashSet<AssetEntry> assetEntryList = new HashSet<AssetEntry> (AssetEntryLocalServiceUtil.getEntries(assetEntryQuery));
		log.info("Tamaño de elemento filtrados: "+assetEntryList.size());
		RateMapping mapping;
		try {
			for (AssetEntry ae : assetEntryList) {
				//System.out.println(ae.getClassName() + " = "+ae.getClassNameId());
			    JournalArticleResource journalArticleResource = JournalArticleResourceLocalServiceUtil.getJournalArticleResource(ae.getClassPK());
			    JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(journalArticleResource.getResourcePrimKey());
			    if(journalArticle.getGroupId() == Contants.SITE_ID){
			    	if(journalArticle.getDDMStructure().getStructureId() == Contants.STRUCTURE_RATE_ID){
			    		for(int i = 0; i < codesSplit.length;i++)
						{
							if(journalArticle.getContent().contains(codesSplit[i]))
							   {
								//article.add(journalArticle);
								final Rate rate = new Rate();
								rate.setTitle(journalArticle.getTitle(locale));
								Document document = null;
								document = SAXReaderUtil.read(journalArticle.getContentByLocale(locale));
								
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
								rate.setGuid(journalArticle.getArticleId());
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
							   
							   break;}
						}
				   // article.add(journalArticle);
				    }
			    }
			}
		} catch (Exception e) {
			log.error("module getWebContentRate: "+e);
		}
		return rates;
		
	}
	
	// Obteniendo los web content de rate por categorias
	public HashSet<JournalArticle> getWebContentRate() throws PortalException{
				log.info("<---------- Metodo getWebContentRate ---------->");
				AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
				Long categoryId = getCategory(Contants.CODIGODEMARCA);
				assetEntryQuery.setAnyCategoryIds(new long[] { categoryId });
				assetEntryQuery.setClassName("com.liferay.journal.model.JournalArticle");
				HashSet<AssetEntry> assetEntryList = new HashSet<>(AssetEntryLocalServiceUtil.getEntries(assetEntryQuery));
				log.info("Size:"+assetEntryList.size());
				HashSet<JournalArticle> article = new HashSet<JournalArticle>();
				try {
					for (AssetEntry ae : assetEntryList) {
					    JournalArticleResource journalArticleResource = JournalArticleResourceLocalServiceUtil.getJournalArticleResource(ae.getClassPK());
					    JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(journalArticleResource.getResourcePrimKey());
					    if(journalArticle.getGroupId() == Contants.SITE_ID){
					    	if(journalArticle.getDDMStructure().getStructureId() == Contants.STRUCTURE_RATE_ID){
					    		
						    article.add(journalArticle);
						    }
					    }
					}
				} catch (Exception e) {
					log.error("module getWebContentRate: "+e);
				}
				return article;
				
			}

	
}
