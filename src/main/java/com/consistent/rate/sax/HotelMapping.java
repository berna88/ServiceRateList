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
import com.consistent.rate.singleton.Portal;
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
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

public class HotelMapping extends Portal implements Mapping{
	
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
	public HashSet<String> getHotels() throws PortalException, XMLStreamException, IOException{
		
			final HashSet<String> hotels = new HashSet<>();
				
			try {
				//Obtiene el Id de la estructura
			    DDMStructure results = DDMStructureLocalServiceUtil.getStructure(Constants.STRUCTURE_HOTEL_ID);
				log.info("Estructura: "+results.getStructureKey());
				//Obtiene el Id de la carpeta 
				JournalFolder folder = JournalFolderLocalServiceUtil.fetchFolder(Constants.SITE_ID, Constants.FOLDER_ID, Constants.CODIGODEMARCA);
				long folderId = folder.getFolderId();
				log.info("Folder id: "+folderId);
				
				if(!Constants.CODIGODEHOTEL.isEmpty()){
					DynamicQuery dynamicQueryJournal = DynamicQueryFactoryUtil.forClass(JournalArticleImpl.class, "folder", PortalClassLoaderUtil.getClassLoader());
					dynamicQueryJournal.add(PropertyFactoryUtil.forName("DDMStructureKey").eq(results.getStructureKey()));
					dynamicQueryJournal.add(PropertyFactoryUtil.forName("groupId").eq(new Long(Constants.SITE_ID)));
					dynamicQueryJournal.add(PropertyFactoryUtil.forName("folderId").eq(getFolderId(Constants.CODIGODEHOTEL)));
					final HashSet<JournalArticle> journalArticles = new HashSet<JournalArticle>(JournalArticleLocalServiceUtil.dynamicQuery(dynamicQueryJournal));
					for (JournalArticle journal : journalArticles) {
							if(JournalArticleLocalServiceUtil.isLatestVersion(Constants.SITE_ID, journal.getArticleId(), journal.getVersion(),WorkflowConstants.STATUS_APPROVED) && !journal.isInTrash()){
								hotels.add(HotelContentsMapping(journal, Constants.getLanguaje()));
								//hotels.add(journal.toString());
							} 
					}
				}else{
					DynamicQuery dynamicQueryJournal = DynamicQueryFactoryUtil.forClass(JournalArticleImpl.class, "folder", PortalClassLoaderUtil.getClassLoader());
					dynamicQueryJournal.add(PropertyFactoryUtil.forName("DDMStructureKey").eq(results.getStructureKey()));
					dynamicQueryJournal.add(PropertyFactoryUtil.forName("groupId").eq(new Long(Constants.SITE_ID)));
					dynamicQueryJournal.add(PropertyFactoryUtil.forName("treePath").like("%"+folderId+"%"));
					final HashSet<JournalArticle> journalArticles = new HashSet<JournalArticle>(JournalArticleLocalServiceUtil.dynamicQuery(dynamicQueryJournal));
					for (JournalArticle journal : journalArticles) {
							if(JournalArticleLocalServiceUtil.isLatestVersion(Constants.SITE_ID, journal.getArticleId(), journal.getVersion(),WorkflowConstants.STATUS_APPROVED) && !journal.isInTrash()){
								hotels.add(HotelContentsMapping(journal, Constants.getLanguaje()));
								//hotels.add(journal.toString());
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
	        xMLStreamWriter.writeCharacters("");
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("shortDescription");
	        xMLStreamWriter.writeCharacters(shortDescription);
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("description");
	        xMLStreamWriter.writeCharacters(description);
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("order");
	        xMLStreamWriter.writeCharacters(Mapping.order);
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("channel");
	        xMLStreamWriter.writeCharacters(Mapping.channel);
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
	         /*JSONArray ArrayMediaLinks = JSONFactoryUtil.createJSONArray();
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
	         xMLStreamWriter.writeEndElement();*/
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
	
	private String HotelContentsMapping(JournalArticle content, String locale) throws XMLStreamException, IOException{
		
		HotelMapping hotelMapping = new HotelMapping();
		Document docXML=null;
	        try {
	        	
				docXML = SAXReaderUtil.read(content.getContentByLocale(locale));
				hotelMapping = new HotelMapping();
				hotelMapping.articleId = content.getArticleId();
				hotelMapping.title =content.getTitle(locale);
				hotelMapping.hotelCode= docXML.valueOf("//dynamic-element[@name='codeHotel']/dynamic-content/text()");
				hotelMapping.name =docXML.valueOf("//dynamic-element[@name='nameHotel']/dynamic-content/text()");
				hotelMapping.description=docXML.valueOf("//dynamic-element[@name='descriptionHotel']/dynamic-content/text()");
				hotelMapping.shortDescription=docXML.valueOf("//dynamic-element[@name='shortDescriptionHotel']/dynamic-content/text()");
				hotelMapping.bepDescription=docXML.valueOf("//dynamic-element[@name='BEPDescription']/dynamic-content/text()");
				hotelMapping.corporateDescription=docXML.valueOf("//dynamic-element[@name='corpoRateDescription']/dynamic-content/text()");
				hotelMapping.roomDescription=docXML.valueOf("//dynamic-element[@name='descriptionRoomsHotel']/dynamic-content/text()");
				
				List<Node> addressNodes = docXML.selectNodes("//dynamic-element[@name='addressHotel']/dynamic-element");
				
				for(Node addressNode : addressNodes){
					String nombre= addressNode.valueOf("@name");
					String valor= addressNode.valueOf("dynamic-content/text()");
					if(nombre.equals("addressDetailHotel")){
						hotelMapping.address=valor;
					}
					if(nombre.equals("countryHotel")){
						hotelMapping.country=valor;
					}
					if(nombre.equals("stateHotel")){
						hotelMapping.state=valor;
					}
					if(nombre.equals("cityHotel")){
						hotelMapping.city=valor;
					}
					if(nombre.equals("zipHotel")){
						hotelMapping.zipCode=valor;
					}
					if(nombre.equals("latitudHotel")){
						hotelMapping.latitude=valor;
					}
					if(nombre.equals("longitudHotel")){
						hotelMapping.longitude=valor;
					}
					if(nombre.equals("referencesHotel")){
						hotelMapping.references=valor;
					}
					if(nombre.equals("directionsHotel")){
						hotelMapping.addresses=valor;
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
				
				hotelMapping.phones=sanitizeArray(telefonosArray);
				List<Node> alternativeHotels = docXML.selectNodes("//dynamic-element[@name='hotelesAlternos']/dynamic-element");		
				List<String> alternativeHotelsArray=new ArrayList<String>();
				for(Node alternativeHotel : alternativeHotels){				
					String valor= alternativeHotel.valueOf("dynamic-content");
					if(!valor.trim().equals("") && !valor.trim().equals("{}")){
						alternativeHotelsArray.add(valor);
					}
								
	            }
				
				hotelMapping.alternativeHotels=sanitizeArray(alternativeHotelsArray);
				/*
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
				this.checkOut=docXML.valueOf("//dynamic-element[@name='horaCheckoutHotels']/dynamic-content/text()");*/
				
				//List<Node> roomLinkNodes = docXML.selectNodes("//dynamic-element[@name='roomLinksHotel']/dynamic-element");		
				
				/*JSONArray ArrayRoom = JSONFactoryUtil.createJSONArray();
				for(Node roomLinkNode : roomLinkNodes){				
					String valor= roomLinkNode.valueOf("dynamic-content/text()");
					if(!valor.trim().equals("")){
						JSONObject object=null;
						try {
							object=JSONFactoryUtil.createJSONObject(valor);
							String classpk=object.getString("classPk");
							if(com.liferay.portal.kernel.util.Validator.isNotNull(classpk)){
								
								
								
							}
							
							
						} catch (JSONException e) {
							log.error("ERROR AL OBTENER JSON",e);
						}					
						ArrayRoom.put(object);
						}			
	            }*/
							
	        }catch (DocumentException e) {
				log.error("ERROR to get XML",e);
			}
	        return hotelMapping.getMapping();
	        
	}
	
	 private List<String> sanitizeArray(List<String> arraySan){
	    	if(arraySan.size()>0){
		    	while(arraySan.size()<2){
					JSONObject object=JSONFactoryUtil.createJSONObject();
					arraySan.add(object.toJSONString());				
				}
	    	}
	    	return arraySan;    	
	    }
	/*private String getJournalArticleByClassPk(Long classPk,String laguage,Long siteID){
		 DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(com.liferay.journal.model.impl.JournalArticleImpl.class, "journal",PortalClassLoaderUtil.getClassLoader());			
		 dynamicQuery.add((PropertyFactoryUtil.forName("resourcePrimKey").eq(classPk)));
     	 dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId",Constants.SITE_ID));
		 HashSet<com.liferay.journal.model.impl.JournalArticleImpl> ja= new HashSet<>(JournalArticleLocalServiceUtil.dynamicQuery(dynamicQuery));
		 Document docXML=null;
			for (JournalArticleImpl journalArticleImpl : ja) {
				try {
					if(JournalArticleLocalServiceUtil.isLatestVersion(Constants.SITE_ID, journalArticleImpl.getArticleId(), journalArticleImpl.getVersion(),WorkflowConstants.STATUS_APPROVED) && !journalArticleImpl.isInTrash()){
							docXML = SAXReaderUtil.read(journalArticleImpl.getContentByLocale(laguage));
					}
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "";
			}*/
	
}

