package com.consistent.rate.mapping;

import java.util.ArrayList;
import java.util.List;

import com.consistent.rate.models.hotel.Multimedia;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;


public class RoomMapping{

    private static final Log log = LogFactoryUtil.getLog(RoomMapping.class);
    public RoomMapping() {
    }

    public void  RoomContent(JournalArticle content, String locale) {
    	
        this.articleId = content.getArticleId();
        this.title = content.getTitle(locale);
        this.articleTitle = content.getTitle(locale);
        Document docXML = null;
        try {
            docXML = SAXReaderUtil.read(content.getContentByLocale(locale));
            this.code = docXML.valueOf("//dynamic-element[@name='codeRoom']/dynamic-content/text()");
            this.hotelCode = docXML.valueOf("//dynamic-element[@name='hotelCodeRoom']/dynamic-content/text()");
            this.name = docXML.valueOf("//dynamic-element[@name='nameRoom']/dynamic-content/text()");
            this.keywords = docXML.valueOf("//dynamic-element[@name='keywordsRoom']/dynamic-content/text()");
            List<Node> descriptionsNodes = docXML.selectNodes("//dynamic-element[@name='descriptionsRoom']/dynamic-element");
            this.descriptions = new ArrayList<String>();
            for (Node descriptionNode : descriptionsNodes) {
                String valor = descriptionNode.valueOf("dynamic-content/text()");
                this.descriptions.add(valor);
            } 
            List<Node> mediaLinkNodes = docXML.selectNodes("//dynamic-element[@name='mediaLinksRoom']/dynamic-element");
            List<com.consistent.rate.models.hotel.Multimedia> mediaArray=new ArrayList<com.consistent.rate.models.hotel.Multimedia>();
            com.consistent.rate.models.hotel.Multimedia media=null;
            for(Node mediaNode : mediaLinkNodes){
            	
            	String link= mediaNode.valueOf("dynamic-content/text()");
            	
            	String type_image= mediaNode.valueOf("dynamic-element[@name='typeRoom']/dynamic-content/text()");
            	
				if(!link.trim().equals("")){
					media= new Multimedia();
					media.setType(type_image);
					media.setUrl(link);
					mediaArray.add(media);
				}}
            	
				this.mediaLinks=mediaArray;
        	} catch (DocumentException e) {
	            log.error("ERROR get to XML", e);
	        }
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
	
  
    public List<Multimedia> getMediaLinks() {
		return mediaLinks;
	}

	public void setMediaLinks(List<Multimedia> mediaLinks) {
		this.mediaLinks = mediaLinks;
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

    public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public String getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(String totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public String getChildCapacity() {
		return childCapacity;
	}

	public void setChildCapacity(String childCapacity) {
		this.childCapacity = childCapacity;
	}

	public String getAdultCapacity() {
		return adultCapacity;
	}

	public void setAdultCapacity(String adultCapacity) {
		this.adultCapacity = adultCapacity;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getInventary() {
		return inventary;
	}

	public void setInventary(String inventary) {
		this.inventary = inventary;
	}

	public String getBeds() {
		return beds;
	}

	public void setBeds(String beds) {
		this.beds = beds;
	}

	
	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public static Log getLog() {
		return log;
	}

	private String articleId;
    private String title;
    private String code;
    private String hotelCode;
    private String name;
    private String keywords;
    private List<String> descriptions;
    private List<Multimedia> mediaLinks;
    private String totalCapacity;
    private String childCapacity;
    private String adultCapacity;
    private String dimension;
    private String inventary;
    private String beds;
    private String articleTitle;
    private String category;
    

}

