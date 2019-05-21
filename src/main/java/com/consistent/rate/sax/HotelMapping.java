package com.consistent.rate.sax;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.consistent.rate.constants.Constants;
import com.consistent.rate.mapping.GetMappingHotel;
import com.consistent.rate.models.hotel.Hotel;
import com.liferay.dynamic.data.mapping.exception.NoSuchStructureException;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.impl.JournalArticleImpl;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

public class HotelMapping implements Mapping{
	
	private static final Log log = LogFactoryUtil.getLog(HotelMapping.class);
	
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
	
	//Metodo que obtienes todos los hoteles
	public HashSet<Hotel> getHotels() throws PortalException{
		
			HashSet<HotelMapping> content = new HashSet<>();
			Hotel mapping = new Hotel();
			
			final HashSet<String> hotels = new HashSet<>();
			
			try {
				//Obtiene el Id de la estructura
			    DDMStructure results = DDMStructureLocalServiceUtil.getStructure(Constants.STRUCTURE_HOTEL_ID);
				log.info("Estructura: "+results.getStructureKey());
				//Obtiene el Id de la carpeta 
				JournalFolder folder = JournalFolderLocalServiceUtil.fetchFolder(Constants.SITE_ID, Constants.FOLDER_ID, Constants.CODIGODEMARCA);
				long folderId = folder.getFolderId();
				log.info("Folder id: "+folderId);
				DynamicQuery dynamicQueryJournal = DynamicQueryFactoryUtil.forClass(JournalArticleImpl.class, "folder", PortalClassLoaderUtil.getClassLoader());
				dynamicQueryJournal.add(PropertyFactoryUtil.forName("DDMStructureKey").eq(results.getStructureKey()));
				dynamicQueryJournal.add(PropertyFactoryUtil.forName("groupId").eq(new Long(Constants.SITE_ID)));
				dynamicQueryJournal.add(PropertyFactoryUtil.forName("treePath").like("%"+folderId+"%"));
				final HashSet<JournalArticle> journalArticles = new HashSet<JournalArticle>(JournalArticleLocalServiceUtil.dynamicQuery(dynamicQueryJournal));
				for (JournalArticle journal : journalArticles) {
					if(!journal.isInTrash()){
						if(JournalArticleLocalServiceUtil.isLatestVersion(Constants.SITE_ID, journal.getArticleId(), journal.getVersion(),WorkflowConstants.STATUS_APPROVED)){
							 content.add(HotelContentsMapping(journal, Constants.getLanguaje()));
							 //mapping = getHotelRooms(content,Constants.CODIGODEMARCA.toLowerCase().toString(),Constants.getLanguaje(),Constants.SITE_ID);
							 if(Constants.CODIGODEHOTEL!=null){
								 if(mapping.getCode().equalsIgnoreCase(Constants.CODIGODEHOTEL)){
									 log.info("Codigo de hotel: "+mapping.getCode());
									 hotels.add(mapping);
								 }
							 }else{
								 hotels.add(mapping);
							 }
							 
						   	}
					}
					 
				}
				log.info("Total de hoteles: "+hotels.size());
			}  catch (IndexOutOfBoundsException ie) {
				log.error("El nombre de la carpeta Hotel No coincide");
				log.error("Causa: " + ie.getCause());
				ie.fillInStackTrace();
			} catch (NoSuchStructureException e) {
				log.error("La estructura no existe");
				log.error("Causa: " + e.getCause());
			}catch (NullPointerException e) {
				log.error("El nombre de la Marca");
				log.error("Causa: " + e.getCause());
			}
			return hotels;
		}

	@Override
	public String getMapping() throws XMLStreamException, IOException {

		 StringWriter stringWriter = new StringWriter();
		 XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
  	 
       XMLStreamWriter xMLStreamWriter =
       xMLOutputFactory.createXMLStreamWriter(stringWriter);
       	 xMLStreamWriter.writeStartDocument();
	         xMLStreamWriter.writeStartElement("hotel");	
	         xMLStreamWriter.writeStartElement("brandcode");
	         xMLStreamWriter.writeCharacters(Constants.CODIGODEMARCA); 
	         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeStartElement("guid");
	         xMLStreamWriter.writeCharacters(articleId); 
	         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeStartElement("code");
	         xMLStreamWriter.writeCharacters(hotelCode);
	         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeStartElement("name");
	         xMLStreamWriter.writeCharacters(name);
	         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeStartElement("title");
	         xMLStreamWriter.writeCharacters(title);
	         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeStartElement("language");
	         xMLStreamWriter.writeCharacters(Constants.LENGUAJE);
	         xMLStreamWriter.writeEndElement();
	     	
	         
		    xMLStreamWriter.writeStartElement("keyword");
	        xMLStreamWriter.writeCharacters(getMetaTags().toString());
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("shortDescription");
	        xMLStreamWriter.writeCharacters(shortDescription);
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("description");
	        xMLStreamWriter.writeCharacters(description);
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("order");
	        xMLStreamWriter.writeCharacters("0");
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("channel");
	        xMLStreamWriter.writeCharacters("www");
	        xMLStreamWriter.writeEndElement();
			
	        
	        
	      
	      //telephone section
	        xMLStreamWriter.writeStartElement("telephones");
	        for (String phone : getPhones()) {
	        	if(!phone.equals("{}")){
	            xMLStreamWriter.writeStartElement("telephone");
	  	       
	            xMLStreamWriter.writeStartElement("number");
	  	        xMLStreamWriter.writeCharacters(phone);
	  	        xMLStreamWriter.writeEndElement();
	  	        
	  	        xMLStreamWriter.writeStartElement("type");
		        xMLStreamWriter.writeCharacters("telephone");
		        xMLStreamWriter.writeEndElement();
		        
		        xMLStreamWriter.writeEndElement();
	        	}
			}
	        xMLStreamWriter.writeEndElement();
	        //telephone section
	          
	        //location section
	         xMLStreamWriter.writeStartElement("location");
		         xMLStreamWriter.writeStartElement("address");
		         xMLStreamWriter.writeCharacters(address);
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("city");
		         xMLStreamWriter.writeCharacters(city);
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("country");
		         xMLStreamWriter.writeCharacters(country);
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("directions");
		         xMLStreamWriter.writeCharacters(getAddresses());
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("latitude");
		         xMLStreamWriter.writeCharacters(latitude);
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("longitude");
		         xMLStreamWriter.writeCharacters(longitude);
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("references");
		         xMLStreamWriter.writeCharacters(references);
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("state");
		         xMLStreamWriter.writeCharacters(state);
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("zip");
		         xMLStreamWriter.writeCharacters(zipCode);
		         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeEndElement();
	         //location section
	         
	         /*mediaLink section*/
	         JSONArray ArrayMediaLinks = JSONFactoryUtil.createJSONArray();
	         List<String> MeliaLinkList = getMediaLinks();
				for (String mediaLinkItem : MeliaLinkList) {
					JSONObject myObject;
					try {
						myObject = JSONFactoryUtil.createJSONObject(mediaLinkItem);
						ArrayMediaLinks.put(myObject);
					} catch (JSONException e) {
					//	log.error("Error converter json"+e);
					}
					
				}
	         xMLStreamWriter.writeStartElement("medialinks");		   
		         xMLStreamWriter.writeStartElement("medialink");
		         
				   xMLStreamWriter.writeStartElement("keyword");
				   xMLStreamWriter.writeEndElement();
				         for (int i = 0; i < ArrayMediaLinks.length(); i++) {
								JSONObject jsonobject = ArrayMediaLinks.getJSONObject(i);
							    String link = jsonobject.getString("link");
							    String type_image = jsonobject.getString("type_image");
								xMLStreamWriter.writeStartElement("multimedia");
					            xMLStreamWriter.writeAttribute("type",type_image);
						        xMLStreamWriter.writeStartElement("url");
						        xMLStreamWriter.writeCharacters(link);
						        xMLStreamWriter.writeEndElement();
					            xMLStreamWriter.writeEndElement();
							}
				         xMLStreamWriter.writeStartElement("thumbnail");
				         xMLStreamWriter.writeEndElement();
				         xMLStreamWriter.writeStartElement("type");
				         xMLStreamWriter.writeEndElement();
			      xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeEndElement();
	          //mediaLink section
	         
	         /*destinations
	         JSONArray ArrayDestinations = JSONFactoryUtil.createJSONArray();
				List<String> Destinations = content.getDestinationLinks();
				for (String destination : Destinations) {
					if(!destination.equals("{}")){
						JSONObject myObject;
						try {
							myObject = JSONFactoryUtil.createJSONObject(destination);
							ArrayDestinations.put(myObject);
						}catch (JSONException e) {
							//log.error("Error converter json"+e);
						}
						
					}
				
				}
			List<String> codes=new ArrayList<>();
		    for (int i = 0; i < ArrayDestinations.length(); i++) {
					JSONObject jsonobject = ArrayDestinations.getJSONObject(i);
				    Long pk = jsonobject.getLong("classPK");
				    codes.add(getJournalArticleByClassPk(pk,laguage,siteID));
			}
	         
	         xMLStreamWriter.writeStartElement("destinations");
	        for (int i = 0; i < codes.size(); i++) {
					xMLStreamWriter.writeStartElement("destination");
				    xMLStreamWriter.writeAttribute("code",codes.get(i));
				    xMLStreamWriter.writeAttribute("guid","");
				    xMLStreamWriter.writeEndElement();
				}
		    xMLStreamWriter.writeEndElement();
	         //destinations
	       */
		    //AlternativeHotels
		    List<String> alternatives = getAlternativeHotels();
		    xMLStreamWriter.writeStartElement("alternatehotels");
			List<String> cos=null;
			for (String alternative : alternatives) {
				if(!alternative.equals("{}")){
					cos = new ArrayList<>();
					String sinSaltosDeLinea = alternative.replaceAll(" ","");
					String[] arregloString = sinSaltosDeLinea.split("\n"); 
					for(int x=0; x < arregloString.length;x++){ 
						String aux="";
						if(arregloString[x].length() > 0){
							if(x == arregloString.length -1){}
							else{
								aux=arregloString[x].replaceAll("					","");
								cos.add(aux);
							}
							
								
					}
						
					}
				}
				}
			if(cos!=null){
				for (String alter : cos) {
					xMLStreamWriter.writeStartElement("hotelcode");
					xMLStreamWriter.writeCharacters(alter);
					xMLStreamWriter.writeEndElement();	
				}	
			}
		    xMLStreamWriter.writeEndElement();	
			//AlternativeHotels	
			//Root 
	         xMLStreamWriter.writeEndDocument();
	           
	         xMLStreamWriter.flush();
	         xMLStreamWriter.close();

	         String xmlString = stringWriter.getBuffer().toString();
	      
	         stringWriter.close();

	        xmlString=xmlString.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
	        return xmlString;
	
	        
	}

	
	public HotelMapping(String articleId, String title, String hotelCode, String name) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.hotelCode = hotelCode;
		this.name = name;
	}
	
	public HotelMapping() {
		super();
		this.articleId = "";
		this.title = "";
		this.hotelCode = "";
		this.name = "";
	}
	
	public HotelMapping HotelContentsMapping(JournalArticle content, String locale){
		
		HotelMapping hotelMapping = new HotelMapping();
		Document docXML=null;
	        try {
				docXML = SAXReaderUtil.read(content.getContentByLocale(locale));
				hotelCode=docXML.valueOf("//dynamic-element[@name='codeHotel']/dynamic-content/text()");
				name=docXML.valueOf("//dynamic-element[@name='nameHotel']/dynamic-content/text()");
				hotelMapping = new HotelMapping(content.getArticleId(),content.getTitle(locale),docXML.valueOf("//dynamic-element[@name='codeHotel']/dynamic-content/text()"),docXML.valueOf("//dynamic-element[@name='nameHotel']/dynamic-content/text()"));
				
	        }catch (DocumentException e) {
				log.error("ERROR to get XML",e);
			}
	        return hotelMapping;
	        
	}
}
