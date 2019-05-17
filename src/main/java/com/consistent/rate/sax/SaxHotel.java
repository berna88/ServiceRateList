package com.consistent.rate.sax;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.consistent.rate.mapping.GetMappingHotel;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class SaxHotel {

	public String MappingWithSAX(GetMappingHotel content,String brand,String laguage,Long siteID) throws IOException, XMLStreamException{
		 StringWriter stringWriter = new StringWriter();
   	 XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
   	 
        XMLStreamWriter xMLStreamWriter =
        xMLOutputFactory.createXMLStreamWriter(stringWriter);
        	 xMLStreamWriter.writeStartDocument();
	         xMLStreamWriter.writeStartElement("content");	
	         xMLStreamWriter.writeStartElement("brandcode");
	         xMLStreamWriter.writeCharacters(brand); 
	         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeStartElement("guid");
	         xMLStreamWriter.writeCharacters(content.getArticleId()); 
	         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeStartElement("code");
	         xMLStreamWriter.writeCharacters(content.getHotelCode());
	         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeStartElement("name");
	         xMLStreamWriter.writeCharacters(content.getName());
	         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeStartElement("title");
	         xMLStreamWriter.writeCharacters(content.getTitle());
	         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeStartElement("language");
	        // xMLStreamWriter.writeCharacters(Contants.getLanguaje());
	         xMLStreamWriter.writeEndElement();
	     	
	         
		    xMLStreamWriter.writeStartElement("keyword");
	        xMLStreamWriter.writeCharacters(content.getMetaTags().toString());
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("shortDescription");
	        xMLStreamWriter.writeCharacters(content.getShortDescription());
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("description");
	        xMLStreamWriter.writeCharacters(content.getDescription());
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("order");
	        xMLStreamWriter.writeCharacters("0");
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeStartElement("channel");
	        xMLStreamWriter.writeCharacters("www");
	        xMLStreamWriter.writeEndElement();
			
	        
	        
	      
	      //telephone section
	        xMLStreamWriter.writeStartElement("telephones");
	        for (String phone : content.getPhones()) {
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
		         xMLStreamWriter.writeCharacters(content.getAddress());
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("city");
		         xMLStreamWriter.writeCharacters(content.getCity());
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("country");
		         xMLStreamWriter.writeCharacters(content.getCountry());
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("directions");
		         xMLStreamWriter.writeCharacters(content.getAddresses());
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("latitude");
		         xMLStreamWriter.writeCharacters(content.getLatitude());
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("longitude");
		         xMLStreamWriter.writeCharacters(content.getLongitude());
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("references");
		         xMLStreamWriter.writeCharacters(content.getReferences());
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("state");
		         xMLStreamWriter.writeCharacters(content.getState());
		         xMLStreamWriter.writeEndElement();
		         xMLStreamWriter.writeStartElement("zip");
		         xMLStreamWriter.writeCharacters(content.getZipCode());
		         xMLStreamWriter.writeEndElement();
	         xMLStreamWriter.writeEndElement();
	         //location section
	         
	         /*mediaLink section*/
	         JSONArray ArrayMediaLinks = JSONFactoryUtil.createJSONArray();
	         List<String> MeliaLinkList = content.getMediaLinks();
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
		    List<String> alternatives = content.getAlternativeHotels();
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
}
