package com.consistent.rate.service;

 
/*import java.util.ArrayList;
import java.util.List;*/

import javax.xml.bind.annotation.XmlRootElement;
/*
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;*/

 
@XmlRootElement // Permite parsear nuestra entidad a XML o JSON según el Accept de la petición
//@Deprecated
/**
 * 
 * @deprecated should probably be removed
 *
 */
public class GetMappingHotel {
	/*
	private static final Log log = LogFactoryUtil.getLog(GetMappingHotel.class);  
    Converter maping with SAX
    public GetMappingHotel() {
    }
    
    
    public void HotelContentsMapping(JournalArticle content, String locale) throws PortalException {
    	this.articleId=content.getArticleId();
        this.title=content.getTitle(locale); 
        Document docXML=null;
        try {
			docXML = SAXReaderUtil.read(content.getContentByLocale(locale));			
			this.hotelCode=docXML.valueOf("//dynamic-element[@name='codeHotel']/dynamic-content/text()");
			this.travelClickCode=docXML.valueOf("//dynamic-element[@name='codeTravelClickHotel']/dynamic-content/text()");
			this.name=docXML.valueOf("//dynamic-element[@name='nameHotel']/dynamic-content/text()");
			this.description=docXML.valueOf("//dynamic-element[@name='descriptionHotel']/dynamic-content/text()");
			this.shortDescription=docXML.valueOf("//dynamic-element[@name='shortDescriptionHotel']/dynamic-content/text()");
			this.bepDescription=docXML.valueOf("//dynamic-element[@name='BEPDescription']/dynamic-content/text()");
			this.corporateDescription=docXML.valueOf("//dynamic-element[@name='corpoRateDescription']/dynamic-content/text()");
			this.roomDescription=docXML.valueOf("//dynamic-element[@name='descriptionRoomsHotel']/dynamic-content/text()");	
			
			List<Node> amenitiesNodes = docXML.selectNodes("//dynamic-element[@name='amenitiesHotel']/dynamic-element");		
			
			this.amenities=new ArrayList<String>();
			for(Node amenitiesNode : amenitiesNodes){
				String nombre= amenitiesNode.valueOf("@name");
				String valor= amenitiesNode.valueOf("dynamic-content/text()");
				if(nombre.equals("otherHotel") && !valor.trim().equals("")){
					this.amenities.add(valor);
				}else  if(valor.equals("true")){
					this.amenities.add(nombre);
				}				
            }			
			
			List<Node> mediaNodes = docXML.selectNodes("//dynamic-element[@name='mediaLinksHotel']/dynamic-element");
			List<String> mediaArray=new ArrayList<String>();
			for(Node mediaNode : mediaNodes){				
				String pie= mediaNode.valueOf("dynamic-element[@name='Pie']/dynamic-content/text()");				
				String link= mediaNode.valueOf("dynamic-content/text()");				
				String type_image= mediaNode.valueOf("dynamic-element[@name='typeHotel']/dynamic-content/text()");						
				if(!link.trim().equals("")){
					JSONObject object=JSONFactoryUtil.createJSONObject();
					object.put("link", link);
					object.put("pie", pie);
					object.put("type_image", type_image);
					mediaArray.add(object.toJSONString());
				}	
								
            }
			this.mediaLinks=sanitizeArray(mediaArray);
			
			
			List<Node> roomLinkNodes = docXML.selectNodes("//dynamic-element[@name='roomLinksHotel']/dynamic-element");		
			List<String> roomsArray=new ArrayList<String>();
			for(Node roomLinkNode : roomLinkNodes){				
				String valor= roomLinkNode.valueOf("dynamic-content/text()");
				if(!valor.trim().equals("")){
					JSONObject object=null;
					try {
						object=JSONFactoryUtil.createJSONObject(valor);
					} catch (JSONException e) {
						log.error("ERROR AL OBTENER JSON",e);
					}					
					roomsArray.add(object.toJSONString());
				}			
            }
						
			this.roomLinks=sanitizeArray(roomsArray);
			
			
			List<Node> facilityLinkNodes = docXML.selectNodes("//dynamic-element[@name='facilityLinksHtotel']/dynamic-element");		
			List<String> facilitiesArray=new ArrayList<String>();
			for(Node facilityLinkNode : facilityLinkNodes){				
				String valor= facilityLinkNode.valueOf("dynamic-content/text()");
				if(!valor.trim().equals("")){
					JSONObject object=null;
					try {
						object=JSONFactoryUtil.createJSONObject(valor);
					} catch (JSONException e) {
						log.error("Error get conversion json",e);
					}					
					facilitiesArray.add(object.toJSONString());
				}			
            }
						
			this.facilityLinks=sanitizeArray(facilitiesArray);
			
			List<Node> destinationLinkNodes = docXML.selectNodes("//dynamic-element[@name='destinationLinksHotel']/dynamic-element");		
			List<String> destinationsArray=new ArrayList<String>();
			for(Node destinationLinkNode : destinationLinkNodes){				
				String valor= destinationLinkNode.valueOf("dynamic-content/text()");
				if(!valor.trim().equals("")){
					JSONObject object=null;
					try {
						object=JSONFactoryUtil.createJSONObject(valor);
					} catch (JSONException e) {
						log.error("Error get conversion json",e);
					}					
					destinationsArray.add(object.toJSONString());
				}			
            }
								
			this.destinationLinks=sanitizeArray(destinationsArray);
			
			
			this.contact=docXML.valueOf("//dynamic-element[@name='contactHotel']/dynamic-content/text()");
			
			
			List<Node> addressNodes = docXML.selectNodes("//dynamic-element[@name='addressHotel']/dynamic-element");
			
			for(Node addressNode : addressNodes){
				String nombre= addressNode.valueOf("@name");
				String valor= addressNode.valueOf("dynamic-content/text()");
				if(nombre.equals("addressDetailHotel")){
					this.address=valor;
				}
				if(nombre.equals("countryHotel")){
					this.country=valor;
				}
				if(nombre.equals("stateHotel")){
					this.state=valor;
				}
				if(nombre.equals("cityHotel")){
					this.city=valor;
				}
				if(nombre.equals("zipHotel")){
					this.zipCode=valor;
				}
				if(nombre.equals("latitudHotel")){
					this.latitude=valor;
				}
				if(nombre.equals("longitudHotel")){
					this.longitude=valor;
				}
				if(nombre.equals("referencesHotel")){
					this.references=valor;
				}
				if(nombre.equals("directionsHotel")){
					this.addresses=valor;
				}
            }
			
			List<Node> telefonoNodes = docXML.selectNodes("//dynamic-element[@name='telephoneHotel']/dynamic-element");		
			List<String> telefonosArray=new ArrayList<String>();
			for(Node telefonoNode : telefonoNodes){				
				String valor= telefonoNode.valueOf("dynamic-content/text()");
				if(!valor.trim().equals("")){					
					telefonosArray.add(valor);
				}			
            }
			
			this.phones=sanitizeArray(telefonosArray);
			List<Node> alternativeHotels = docXML.selectNodes("//dynamic-element[@name='hotelesAlternos']/dynamic-element");		
			List<String> alternativeHotelsArray=new ArrayList<String>();
			for(Node alternativeHotel : alternativeHotels){				
				String valor= alternativeHotel.valueOf("dynamic-content");
				if(!valor.trim().equals("") && !valor.trim().equals("{}")){
					alternativeHotelsArray.add(valor);
				}
							
            }
			this.alternativeHotels=sanitizeArray(alternativeHotelsArray);
			this.floors=docXML.valueOf("//dynamic-element[@name='floorsHotels']/dynamic-content/text()");
			this.rooms=docXML.valueOf("//dynamic-element[@name='roomsHotels']/dynamic-content/text()");
			this.elevators=docXML.valueOf("//dynamic-element[@name='elevatorsHotels']/dynamic-content/text()");
			this.restaurants=docXML.valueOf("//dynamic-element[@name='restaurantsHotels']/dynamic-content/text()");
			this.bars=docXML.valueOf("//dynamic-element[@name='barsHotels']/dynamic-content/text()");
			this.swimmingPools=docXML.valueOf("//dynamic-element[@name='poolsHotels']/dynamic-content/text()");
			this.stars=docXML.valueOf("//dynamic-element[@name='starRatingHotel']/dynamic-content/text()");
			this.openingTime=docXML.valueOf("//dynamic-element[@name='openingDateHotels']/dynamic-content/text()");
			this.closingTime=docXML.valueOf("//dynamic-element[@name='closingDateHotels']/dynamic-content/text()");
			this.announcements=docXML.valueOf("//dynamic-element[@name='avisosHotels']/dynamic-content/text()");
			this.announcementStartingTime=docXML.valueOf("//dynamic-element[@name='fechaInicioAvisoHotels']/dynamic-content/text()");
			this.announcementFinishingTime=docXML.valueOf("//dynamic-element[@name='fechaFinAvisoHotels']/dynamic-content/text()");
			this.checkIn=docXML.valueOf("//dynamic-element[@name='horaCheckinHotels']/dynamic-content/text()");
			this.checkOut=docXML.valueOf("//dynamic-element[@name='horaCheckoutHotels']/dynamic-content/text()");
			
			
			List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getCategories(JournalArticle.class.getName(), content.getResourcePrimKey());
			
			List<String> citiesArray=new ArrayList<String>();
			List<String> contentArray=new ArrayList<String>();
			List<String> countryArray=new ArrayList<String>();
			List<String> featuresArray=new ArrayList<String>();
			List<String> marcasArray=new ArrayList<String>();
			List<String> resortsArray=new ArrayList<String>();
			List<String> servicesArray=new ArrayList<String>();
			List<String> stateArray=new ArrayList<String>();
			
			for(AssetCategory category : categories){				
				AssetVocabulary vocabulary=AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(category.getVocabularyId());
				if(vocabulary.getName().equals("Cities")){
					citiesArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Content Type")){
					contentArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Country")){
					countryArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Features Room")){
					featuresArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Marcas")){
					marcasArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Resorts")){
					resortsArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Services and Facilities")){
					servicesArray.add(category.getName());
				}
				if(vocabulary.getName().equals("State")){
					stateArray.add(category.getName());
				}
			}
			
			this.metaCities=sanitizeArrayString(citiesArray);						
			this.metaContentType=sanitizeArrayString(contentArray);						
			this.metaCountry=sanitizeArrayString(countryArray);
			this.metaFeaturesRoom=sanitizeArrayString(featuresArray);
			this.metaBrands=sanitizeArrayString(marcasArray);
			this.metaResorts=sanitizeArrayString(resortsArray);
			this.metaServFacilities=sanitizeArrayString(servicesArray);
			this.metaState=sanitizeArrayString(stateArray);
			
			
			String[] tags= AssetTagLocalServiceUtil.getTagNames(JournalArticle.class.getName(), content.getResourcePrimKey());
			List<String> tagsArray=new ArrayList<String>();
			for(String tag : tags){
				tagsArray.add(tag);
			}
			this.metaTags=sanitizeArray(tagsArray);
			
			
		} catch (DocumentException e) {
			log.error("ERROR to get XML",e);
		}
    }

    
    
    public void HotelContents(JournalArticle content, String locale) throws PortalException {
    	this.articleId=content.getArticleId();
        this.title=content.getTitle(locale); 
        Document docXML=null;
        try {
			docXML = SAXReaderUtil.read(content.getContentByLocale(locale));			
			this.hotelCode=docXML.valueOf("//dynamic-element[@name='codeHotel']/dynamic-content/text()");
			this.travelClickCode=docXML.valueOf("//dynamic-element[@name='codeTravelClickHotel']/dynamic-content/text()");
			this.name=docXML.valueOf("//dynamic-element[@name='nameHotel']/dynamic-content/text()");
			this.description=docXML.valueOf("//dynamic-element[@name='descriptionHotel']/dynamic-content/text()");
			this.shortDescription=docXML.valueOf("//dynamic-element[@name='shortDescriptionHotel']/dynamic-content/text()");
			this.bepDescription=docXML.valueOf("//dynamic-element[@name='BEPDescription']/dynamic-content/text()");
			this.corporateDescription=docXML.valueOf("//dynamic-element[@name='corpoRateDescription']/dynamic-content/text()");
			this.roomDescription=docXML.valueOf("//dynamic-element[@name='descriptionRoomsHotel']/dynamic-content/text()");	
			List<Node> amenitiesNodes = docXML.selectNodes("//dynamic-element[@name='amenitiesHotel']/dynamic-element");		
			this.amenities=new ArrayList<String>();
			for(Node amenitiesNode : amenitiesNodes){
				String nombre= amenitiesNode.valueOf("@name");
				String valor= amenitiesNode.valueOf("dynamic-content/text()");
				if(nombre.equals("otherHotel") && !valor.trim().equals("")){
					this.amenities.add(valor);
				}else  if(valor.equals("true")){
					this.amenities.add(nombre);
				}				
            }			
			List<Node> mediaNodes = docXML.selectNodes("//dynamic-element[@name='mediaLinksHotel']/dynamic-element");
			List<String> mediaArray=new ArrayList<String>();
			for(Node mediaNode : mediaNodes){				
				String pie= mediaNode.valueOf("dynamic-element[@name='Pie']/dynamic-content/text()");				
				String link= mediaNode.valueOf("dynamic-content/text()");				
				String type_image= mediaNode.valueOf("dynamic-element[@name='typeHotel']/dynamic-content/text()");						
				if(!link.trim().equals("")){
					JSONObject object=JSONFactoryUtil.createJSONObject();
					object.put("link", link);
					object.put("pie", pie);
					object.put("type_image", type_image);
					mediaArray.add(object.toJSONString());
				}
				}
			this.mediaLinks=sanitizeArray(mediaArray);
			List<Node> roomLinkNodes = docXML.selectNodes("//dynamic-element[@name='roomLinksHotel']/dynamic-element");		
			List<String> roomsArray=new ArrayList<String>();
			for(Node roomLinkNode : roomLinkNodes){				
				String valor= roomLinkNode.valueOf("dynamic-content/text()");
				if(!valor.trim().equals("")){
					JSONObject object=null;
					try {
						object=JSONFactoryUtil.createJSONObject(valor);
					} catch (JSONException e) {
						log.error("ERROR AL OBTENER JSON",e);
					}					
					roomsArray.add(object.toJSONString());
				}
				}
			this.roomLinks=sanitizeArray(roomsArray);
			List<Node> facilityLinkNodes = docXML.selectNodes("//dynamic-element[@name='facilityLinksHtotel']/dynamic-element");		
			List<String> facilitiesArray=new ArrayList<String>();
			for(Node facilityLinkNode : facilityLinkNodes){				
				String valor= facilityLinkNode.valueOf("dynamic-content/text()");
				if(!valor.trim().equals("")){
					JSONObject object=null;
					try {
						object=JSONFactoryUtil.createJSONObject(valor);
					} catch (JSONException e) {
						log.error("Error get conversion json",e);
					}					
					facilitiesArray.add(object.toJSONString());
				}			
            }
			this.facilityLinks=sanitizeArray(facilitiesArray);
			List<Node> destinationLinkNodes = docXML.selectNodes("//dynamic-element[@name='destinationLinksHotel']/dynamic-element");		
			List<String> destinationsArray=new ArrayList<String>();
			for(Node destinationLinkNode : destinationLinkNodes){				
				String valor= destinationLinkNode.valueOf("dynamic-content/text()");
				if(!valor.trim().equals("")){
					JSONObject object=null;
					try {
						object=JSONFactoryUtil.createJSONObject(valor);
					} catch (JSONException e) {
						log.error("Error get conversion json",e);
					}					
					destinationsArray.add(object.toJSONString());
				}			
            }
			this.destinationLinks=sanitizeArray(destinationsArray);
			this.contact=docXML.valueOf("//dynamic-element[@name='contactHotel']/dynamic-content/text()");
			List<Node> addressNodes = docXML.selectNodes("//dynamic-element[@name='addressHotel']/dynamic-element");
			for(Node addressNode : addressNodes){
				String nombre= addressNode.valueOf("@name");
				String valor= addressNode.valueOf("dynamic-content/text()");
				if(nombre.equals("addressDetailHotel")){
					this.address=valor;
				}
				if(nombre.equals("countryHotel")){
					this.country=valor;
				}
				if(nombre.equals("stateHotel")){
					this.state=valor;
				}
				if(nombre.equals("cityHotel")){
					this.city=valor;
				}
				if(nombre.equals("zipHotel")){
					this.zipCode=valor;
				}
				if(nombre.equals("latitudHotel")){
					this.latitude=valor;
				}
				if(nombre.equals("longitudHotel")){
					this.longitude=valor;
				}
				if(nombre.equals("referencesHotel")){
					this.references=valor;
				}
				if(nombre.equals("directionsHotel")){
					this.addresses=valor;
				}
            }
			List<Node> telefonoNodes = docXML.selectNodes("//dynamic-element[@name='telephoneHotel']/dynamic-element");		
			List<String> telefonosArray=new ArrayList<String>();
			for(Node telefonoNode : telefonoNodes){				
				String valor= telefonoNode.valueOf("dynamic-content/text()");
				if(!valor.trim().equals("")){					
					telefonosArray.add(valor);
				}			
            }
			this.phones=sanitizeArray(telefonosArray);
			List<Node> alternativeHotels = docXML.selectNodes("//dynamic-element[@name='hotelesAlternos']/dynamic-element");		
			List<String> alternativeHotelsArray=new ArrayList<String>();
			for(Node alternativeHotel : alternativeHotels){				
				String valor= alternativeHotel.valueOf("dynamic-content");
				if(!valor.trim().equals("") && !valor.trim().equals("{}")){
					alternativeHotelsArray.add(valor);
				}
							
            }
			this.alternativeHotels=sanitizeArray(alternativeHotelsArray);
			this.floors=docXML.valueOf("//dynamic-element[@name='floorsHotels']/dynamic-content/text()");
			this.rooms=docXML.valueOf("//dynamic-element[@name='roomsHotels']/dynamic-content/text()");
			this.elevators=docXML.valueOf("//dynamic-element[@name='elevatorsHotels']/dynamic-content/text()");
			this.restaurants=docXML.valueOf("//dynamic-element[@name='restaurantsHotels']/dynamic-content/text()");
			this.bars=docXML.valueOf("//dynamic-element[@name='barsHotels']/dynamic-content/text()");
			this.swimmingPools=docXML.valueOf("//dynamic-element[@name='poolsHotels']/dynamic-content/text()");
			this.stars=docXML.valueOf("//dynamic-element[@name='starRatingHotel']/dynamic-content/text()");
			this.openingTime=docXML.valueOf("//dynamic-element[@name='openingDateHotels']/dynamic-content/text()");
			this.closingTime=docXML.valueOf("//dynamic-element[@name='closingDateHotels']/dynamic-content/text()");
			this.announcements=docXML.valueOf("//dynamic-element[@name='avisosHotels']/dynamic-content/text()");
			this.announcementStartingTime=docXML.valueOf("//dynamic-element[@name='fechaInicioAvisoHotels']/dynamic-content/text()");
			this.announcementFinishingTime=docXML.valueOf("//dynamic-element[@name='fechaFinAvisoHotels']/dynamic-content/text()");
			this.checkIn=docXML.valueOf("//dynamic-element[@name='horaCheckinHotels']/dynamic-content/text()");
			this.checkOut=docXML.valueOf("//dynamic-element[@name='horaCheckoutHotels']/dynamic-content/text()");
			List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getCategories(JournalArticle.class.getName(), content.getResourcePrimKey());
			List<String> citiesArray=new ArrayList<String>();
			List<String> contentArray=new ArrayList<String>();
			List<String> countryArray=new ArrayList<String>();
			List<String> featuresArray=new ArrayList<String>();
			List<String> marcasArray=new ArrayList<String>();
			List<String> resortsArray=new ArrayList<String>();
			List<String> servicesArray=new ArrayList<String>();
			List<String> stateArray=new ArrayList<String>();
			for(AssetCategory category : categories){				
				AssetVocabulary vocabulary=AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(category.getVocabularyId());
				if(vocabulary.getName().equals("Cities")){
					citiesArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Content Type")){
					contentArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Country")){
					countryArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Features Room")){
					featuresArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Marcas")){
					marcasArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Resorts")){
					resortsArray.add(category.getName());
				}
				if(vocabulary.getName().equals("Services and Facilities")){
					servicesArray.add(category.getName());
				}
				if(vocabulary.getName().equals("State")){
					stateArray.add(category.getName());
				}
			}
			this.metaCities=sanitizeArrayString(citiesArray);						
			this.metaContentType=sanitizeArrayString(contentArray);						
			this.metaCountry=sanitizeArrayString(countryArray);
			this.metaFeaturesRoom=sanitizeArrayString(featuresArray);
			this.metaBrands=sanitizeArrayString(marcasArray);
			this.metaResorts=sanitizeArrayString(resortsArray);
			this.metaServFacilities=sanitizeArrayString(servicesArray);
			this.metaState=sanitizeArrayString(stateArray);
			String[] tags= AssetTagLocalServiceUtil.getTagNames(JournalArticle.class.getName(), content.getResourcePrimKey());
			List<String> tagsArray=new ArrayList<String>();
			for(String tag : tags){
				tagsArray.add(tag);
			}
			this.metaTags=sanitizeArray(tagsArray);
			} catch (DocumentException e) {
				log.error("ERROR to get XML",e);
			}
    }
    
    public boolean hasMarca(String marca){    	
	    	for(String metaMarca:this.metaBrands){
	    		if(metaMarca.equals(marca)){
	    			return true;
	    		}
	    	}
	    	return false;
	}
    
    public boolean hasDestino(String destino){    	
    	for(String metaDestino:this.metaCities){
    		if(metaDestino.equals(destino)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public List<String> sanitizeArray(List<String> arraySan){
    	if(arraySan.size()>0){
	    	while(arraySan.size()<2){
				JSONObject object=JSONFactoryUtil.createJSONObject();
				arraySan.add(object.toJSONString());				
			}
    	}
    	return arraySan;    	
    }
    @Override
	public String toString() {
		return "HotelContent [articleId=" + articleId + 
				"\n,title=" + title +
				"\n,hotelCode=" + hotelCode + 
				"\n,travelClickCode=" + travelClickCode + 
				"\n,name=" + name +
				"\n,description=" + description + 
				"\n,shortDescription=" + shortDescription + 
				"\n,bepDescription=" + bepDescription + 
				"\n,corporateDescription=" + corporateDescription + 
				"\n,roomDescription=" + roomDescription + 
				"\n,amenities=" + amenities + 
				"\n,mediaLinks=" + mediaLinks + 
				"\n,roomLinks=" + roomLinks+ 
				"\n,facilityLinks=" + facilityLinks + 
				"\n,destinationLinks=" + destinationLinks + 
				"\n,contact=" + contact+ 
				"\n,address=" + address + 
				"\n,country=" + country + 
				"\n,state=" + state + 
				"\n,city=" + city + 
				"\n,zipCode="+ zipCode + 
				"\n,latitude=" + latitude + 
				"\n,longitude=" + longitude + 
				"\n,references=" + references+ 
				"\n,addresses=" + addresses + 
				"\n,phones=" + phones + 
				"\n,alternativeHotels=" + alternativeHotels+ 
				"\n,floors=" + floors + 
				"\n,rooms=" + rooms + 
				"\n,elevators=" + elevators + 
				"\n,restaurants="+ restaurants + 
				"\n,bars=" + bars + 
				"\n,swimmingPools=" + swimmingPools + 
				"\n,stars=" + stars+ 
				"\n,openingTime=" + openingTime + 
				"\n,closingTime=" + closingTime + 
				"\n,announcements=" + announcements + 
				"\n,announcementStartingTime=" + announcementStartingTime + 
				"\n,announcementFinishingTime="+ announcementFinishingTime + 
				"\n,checkIn=" + checkIn + 
				"\n,checkOut=" + checkOut + 
				"\n,tripAdvisor="+ tripAdvisor + 
				"\n,metaCities=" + metaCities + 
				"\n,metaContentType=" + metaContentType + 
				"\n,metaCountry="+ metaCountry + 
				"\n,metaFeaturesRoom=" + metaFeaturesRoom + 
				"\n,metaBrands=" + metaBrands+ 
				"\n,metaResorts=" + metaResorts + 
				"\n,metaServFacilities=" + metaServFacilities + 
				"\n,metaState="+ metaState + 
				"\n,metaTags=" + metaTags + "]";
	}

	public List<String> sanitizeArrayString(List<String> arraySan){
    	while(arraySan.size()<2){			
			arraySan.add("");				
		}
    	return arraySan;    	
    }
   

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String codigo) {
		this.hotelCode = codigo;
	}

	public String getTravelClickCode() {
		return travelClickCode;
	}

	public void setTravelClickCode(String codigoTravelClick) {
		this.travelClickCode = codigoTravelClick;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descripcion) {
		this.description = descripcion;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String descripcionCorta) {
		this.shortDescription = descripcionCorta;
	}

	public String getBepDescription() {
		return bepDescription;
	}

	public void setBepDescription(String descripcionBEP) {
		this.bepDescription = descripcionBEP;
	}

	public String getCorporateDescription() {
		return corporateDescription;
	}

	public void setCorporateDescription(String descripcionCorporate) {
		this.corporateDescription = descripcionCorporate;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String descripcionHabitaciones) {
		this.roomDescription = descripcionHabitaciones;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contacto) {
		this.contact = contacto;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String direccion) {
		this.address = direccion;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String pais) {
		this.country = pais;
	}

	public String getState() {
		return state;
	}

	public void setState(String estado) {
		this.state = estado;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String ciudad) {
		this.city = ciudad;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String codPostal) {
		this.zipCode = codPostal;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitud) {
		this.latitude = latitud;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitud) {
		this.longitude = longitud;
	}

	public String getReferences() {
		return references;
	}

	public void setReferences(String referencias) {
		this.references = referencias;
	}

	public String getAddresses() {
		return addresses;
	}

	public void setAddresses(String direcciones) {
		this.addresses = direcciones;
	}

	public String getFloors() {
		return floors;
	}

	public void setFloors(String pisos) {
		this.floors = pisos;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms(String habitaciones) {
		this.rooms = habitaciones;
	}

	public String getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(String restaurantes) {
		this.restaurants = restaurantes;
	}

	public String getBars() {
		return bars;
	}

	public void setBars(String bares) {
		this.bars = bares;
	}

	public String getSwimmingPools() {
		return swimmingPools;
	}

	public void setSwimmingPools(String albercas) {
		this.swimmingPools = albercas;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String estrellas) {
		this.stars = estrellas;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String fechaApertura) {
		this.openingTime = fechaApertura;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String fechaCierre) {
		this.closingTime = fechaCierre;
	}

	public String getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(String avisos) {
		this.announcements = avisos;
	}

	

	public String getAnnouncementStartingTime() {
		return announcementStartingTime;
	}

	public void setAnnouncementStartingTime(String fechaInicioAviso) {
		this.announcementStartingTime = fechaInicioAviso;
	}

	public String getAnnouncementFinishingTime() {
		return announcementFinishingTime;
	}

	public void setAnnouncementFinishingTime(String fechaFinAviso) {
		this.announcementFinishingTime = fechaFinAviso;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public String getTripAdvisor() {
		return tripAdvisor;
	}

	public void setTripAdvisor(String tripAdvisor) {
		this.tripAdvisor = tripAdvisor;
	}
	
	public List<String> getRoomLinks() {
		return roomLinks;
	}

	public void setRoomLinks(List<String> roomLinks) {
		this.roomLinks = roomLinks;
	}

	
	public List<String> getAlternativeHotels() {
		return alternativeHotels;
	}

	public void setAlternativeHotels(List<String> hotelesAlternos) {
		this.alternativeHotels = hotelesAlternos;
	}

	public String getElevators() {
		return elevators;
	}

	public void setElevators(String ascensores) {
		this.elevators = ascensores;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenitieArray) {
		this.amenities = amenitieArray;
	}

	public List<String> getMediaLinks() {
		return mediaLinks;
	}

	public void setMediaLinks(List<String> mediaLinksObj) {
		this.mediaLinks = mediaLinksObj;
	}

	public List<String> getFacilityLinks() {
		return facilityLinks;
	}

	public void setFacilityLinks(List<String> facilityLinks) {
		this.facilityLinks = facilityLinks;
	}

	public List<String> getDestinationLinks() {
		return destinationLinks;
	}

	public void setDestinationLinks(List<String> destinationLinks) {
		this.destinationLinks = destinationLinks;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> telefonos) {
		this.phones = telefonos;
	}

	public List<String> getMetaCities() {
		return metaCities;
	}

	public void setMetaCities(List<String> metaCities) {
		this.metaCities = metaCities;
	}

	public List<String> getMetaContentType() {
		return metaContentType;
	}

	public void setMetaContentType(List<String> metaContentType) {
		this.metaContentType = metaContentType;
	}

	public List<String> getMetaCountry() {
		return metaCountry;
	}

	public void setMetaCountry(List<String> metaCountry) {
		this.metaCountry = metaCountry;
	}

	public List<String> getMetaFeaturesRoom() {
		return metaFeaturesRoom;
	}

	public void setMetaFeaturesRoom(List<String> metaFeaturesRoom) {
		this.metaFeaturesRoom = metaFeaturesRoom;
	}

	public List<String> getMetaBrands() {
		return metaBrands;
	}

	public void setMetaBrands(List<String> metaMarcas) {
		this.metaBrands = metaMarcas;
	}

	public List<String> getMetaResorts() {
		return metaResorts;
	}

	public void setMetaResorts(List<String> metaResorts) {
		this.metaResorts = metaResorts;
	}

	public List<String> getMetaServFacilities() {
		return metaServFacilities;
	}

	public void setMetaServFacilities(List<String> metaServFacilities) {
		this.metaServFacilities = metaServFacilities;
	}

	public List<String> getMetaState() {
		return metaState;
	}

	public void setMetaState(List<String> metaState) {
		this.metaState = metaState;
	}

	public List<String> getMetaTags() {
		return metaTags;
	}

	public void setMetaTags(List<String> metaTags) {
		this.metaTags = metaTags;
	}
	
	private String articleId;
    private String title;
    private String hotelCode;
    private String travelClickCode;
    private String name;
    private String description;
    private String shortDescription;
    private String bepDescription;
    private String corporateDescription;
    private String roomDescription;
    private List<String> amenities;
        
    private List<String> mediaLinks;
    private List<String> roomLinks;
    private List<String> facilityLinks;
    private List<String> destinationLinks;
    
    private String contact;
    private String address;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String latitude;
    private String longitude;
    private String references;
    private String addresses;
    
    private List<String> phones;
    private List<String> alternativeHotels;
    
    private String floors;
    private String rooms;
    private String elevators;
    private String restaurants;
    private String bars;
    private String swimmingPools;
    private String stars;
    private String openingTime;
    private String closingTime;
    private String announcements;
    private String announcementStartingTime;
    private String announcementFinishingTime;
    private String checkIn;
    private String checkOut;
    private String tripAdvisor;    
    
    private List<String> metaCities;
    private List<String> metaContentType;
    private List<String> metaCountry;
    private List<String> metaFeaturesRoom;
    private List<String> metaBrands;
    private List<String> metaResorts;
    private List<String> metaServFacilities;
    private List<String> metaState;
    private List<String> metaTags;
	
 */
}
