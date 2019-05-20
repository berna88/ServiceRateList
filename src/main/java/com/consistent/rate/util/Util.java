package com.consistent.rate.util;

import com.consistent.rate.constants.Contants;
import com.consistent.rate.mapping.GetMappingHotel;
import com.consistent.rate.mapping.RoomMapping;
import com.consistent.rate.models.hotel.Hotel;
import com.consistent.rate.models.hotel.MediaLink;
import com.consistent.rate.singleton.Portal;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.exception.NoSuchStructureException;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.impl.JournalArticleImpl;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleResourceLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Util extends Portal{
	
	private static final Log log = LogFactoryUtil.getLog(Util.class);
	static String marca = "";
	
	
	//Metodo que obtienes todos los hoteles
	public static HashSet<Hotel> getHotels() throws PortalException{
		GetMappingHotel content = new GetMappingHotel();
		Hotel mapping = new Hotel();
		
		final HashSet<Hotel> hotels = new HashSet<>();
		
		try {
			//Obtiene el Id de la estructura
		    DDMStructure results = DDMStructureLocalServiceUtil.getStructure(Contants.STRUCTURE_HOTEL_ID);
			log.info("Estructura: "+results.getStructureKey());
			//Obtiene el Id de la carpeta 
			JournalFolder folder = JournalFolderLocalServiceUtil.fetchFolder(Contants.SITE_ID, Contants.FOLDER_ID, Contants.CODIGODEMARCA);
			long folderId = folder.getFolderId();
			log.info("Folder id: "+folderId);
			DynamicQuery dynamicQueryJournal = DynamicQueryFactoryUtil.forClass(JournalArticleImpl.class, "folder", PortalClassLoaderUtil.getClassLoader());
			dynamicQueryJournal.add(PropertyFactoryUtil.forName("DDMStructureKey").eq(results.getStructureKey()));
			dynamicQueryJournal.add(PropertyFactoryUtil.forName("groupId").eq(new Long(Contants.SITE_ID)));
			dynamicQueryJournal.add(PropertyFactoryUtil.forName("treePath").like("%"+folderId+"%"));
			final HashSet<JournalArticle> journalArticles = new HashSet<JournalArticle>(JournalArticleLocalServiceUtil.dynamicQuery(dynamicQueryJournal));
			for (JournalArticle journal : journalArticles) {
				if(!journal.isInTrash()){
					if(JournalArticleLocalServiceUtil.isLatestVersion(Contants.SITE_ID, journal.getArticleId(), journal.getVersion(),WorkflowConstants.STATUS_APPROVED)){
						 content.HotelContentsMapping(journal, Contants.getLanguaje());
						 mapping = getHotelRooms(content,Contants.CODIGODEMARCA.toLowerCase().toString(),Contants.getLanguaje(),Contants.SITE_ID);
						 if(Contants.CODIGODEHOTEL!=null){
							 if(mapping.getCode().equalsIgnoreCase(Contants.CODIGODEHOTEL)){
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
	
		//obteniendo subcategorias
				public void getSubCategory(){
					log.info("<---------- Metodo getCategory ---------->");
					DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class, "category", PortalClassLoaderUtil.getClassLoader());
					Long parentCategory = getCategory(marca);
					try {
						List<AssetCategory> assetCategories = AssetCategoryLocalServiceUtil.dynamicQuery(dynamicQuery);
						for(AssetCategory category : assetCategories){
							if(category.getParentCategoryId() == parentCategory){
								log.info(category.getName());
							}
						}
					} catch (Exception e) {
						log.error("module getSubCategory: "+e);
					}
				}
				
				public long journalRootFolder1(long parentFolder,String nameFolder,Long siteID){
					DynamicQuery query_journal_folder = DynamicQueryFactoryUtil.forClass(com.liferay.journal.model.impl.JournalFolderImpl.class, "journalFolder",PortalClassLoaderUtil.getClassLoader());
					query_journal_folder.add(RestrictionsFactoryUtil.eq("name", nameFolder));
					query_journal_folder.add(RestrictionsFactoryUtil.eq("parentFolderId", new Long(parentFolder)));
					query_journal_folder.add(RestrictionsFactoryUtil.eq("groupId",new Long(siteID)));
					List<com.liferay.journal.model.impl.JournalFolderImpl> ja_1 = JournalArticleResourceLocalServiceUtil.dynamicQuery(query_journal_folder);
					return ja_1.get(0).getFolderId();
					}
				
				public List<Long> journalByBrandFolder1(long parentFolder,Long siteID){
					List<Long> listIdFolders = new ArrayList<>();
					DynamicQuery query_journal_folder = DynamicQueryFactoryUtil.forClass(com.liferay.journal.model.impl.JournalFolderImpl.class, "journalFolder",PortalClassLoaderUtil.getClassLoader());
					query_journal_folder.add( RestrictionsFactoryUtil.eq("parentFolderId", new Long(parentFolder)));
					query_journal_folder.add( RestrictionsFactoryUtil.eq("groupId",new Long(siteID)));
					List<com.liferay.journal.model.impl.JournalFolderImpl> ja_1 = JournalArticleResourceLocalServiceUtil.dynamicQuery(query_journal_folder);
						for (com.liferay.journal.model.impl.JournalFolderImpl journalArticleResourceImpl : ja_1) {
					    	 listIdFolders.add(journalArticleResourceImpl.getFolderId());
							}
					return listIdFolders;
					}
				
			  	@SuppressWarnings("deprecation")
				public com.consistent.rate.models.hotel.Hotel getJournalArticleByFolderId(long folderId,String brand,String language,Long siteID) throws PortalException{
					log.info("Get data folder:"+folderId);
					com.consistent.rate.mapping.GetMappingHotel content = new com.consistent.rate.mapping.GetMappingHotel();
					com.consistent.rate.models.hotel.Hotel mapping=new com.consistent.rate.models.hotel.Hotel();
					
					
					List<DDMStructure> ddmStructures = new ArrayList<>();
					if(ddmStructures==null || ddmStructures.size() < 1){
					if(getStruct("%>"+Contants.NAME_STRUCTURE_DEFAULT+"<%",siteID).size() > 0){
						ddmStructures= getStruct("%>"+Contants.NAME_STRUCTURE_DEFAULT+"<%",siteID);
					}
					}

					for (DDMStructure ddmStructure : ddmStructures) {
						log.info("Get metadata by structure ID:"+ddmStructure.getStructureId());
						String structureId = ddmStructure.getStructureKey(); // replace with real structure ID
						DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(com.liferay.journal.model.impl.JournalArticleImpl.class, "journal",PortalClassLoaderUtil.getClassLoader());			
						dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(new Long(siteID)));
						dynamicQuery.add(PropertyFactoryUtil.forName("DDMStructureKey").eq(new String(structureId)));
						dynamicQuery.add(PropertyFactoryUtil.forName("folderId").eq(new Long(folderId)));
						    if(JournalArticleLocalServiceUtil.dynamicQuery(dynamicQuery).size()>0){
						    	List<com.liferay.journal.model.impl.JournalArticleImpl> ja=JournalArticleLocalServiceUtil.dynamicQuery(dynamicQuery);
						    	  List<JournalArticle> lastest = getLatestVersionArticle(ja,siteID);
								    for (JournalArticle journalArticleImpl : lastest) {
									   		content.HotelContents(journalArticleImpl, language);
									   		mapping = getHotelRooms(content,brand,language,siteID);
								    }
						    }
					}
					
					
						return mapping;    
			}

			  	public static List<JournalArticle> getLatestVersionArticle(List<JournalArticleImpl> ja,Long siteID) {
			  		List<JournalArticle> journalList = new ArrayList<JournalArticle>();
			  			JournalArticle latestArticle ;
			  			for (JournalArticle journalArticle : ja) {
			  				try {
			  					 latestArticle = JournalArticleLocalServiceUtil.getLatestArticle(journalArticle.getResourcePrimKey());
			  					if (journalList.contains(latestArticle)) {
			  						continue;
			  					} else {
			  						journalList.add(latestArticle);
			  					}
			  				} catch (PortalException | SystemException e) {
			  					e.printStackTrace();
			  				}
			  			}
			  			return journalList;

			  		}
			  	
			  	public List<DDMStructure> getStruct(String nameStructure,Long siteID) {
				        DynamicQuery query = DDMStructureLocalServiceUtil.dynamicQuery()
				                .add(PropertyFactoryUtil.forName("name").like(nameStructure))
				                 .add(PropertyFactoryUtil.forName("groupId").eq(new Long(siteID)));
				        List<DDMStructure> structures = DDMStructureLocalServiceUtil.dynamicQuery(query);
				        if (structures.size() < 1) {
				        	log.error("Error get Estrucrure in method getStruct with name:"+nameStructure);
				            return new ArrayList<>();
				        }
				        return structures;
				    }
			  	
			  	public Long journalByCodeFolder(long parentFolder,String nameFolder,Long siteID){
					DynamicQuery query_journal_folder = DynamicQueryFactoryUtil.forClass(com.liferay.journal.model.impl.JournalFolderImpl.class, "journalFolder",PortalClassLoaderUtil.getClassLoader());
					query_journal_folder.add(PropertyFactoryUtil.forName("groupId").eq(new Long(siteID)));
					query_journal_folder.add(PropertyFactoryUtil.forName("parentFolderId").eq(new Long(parentFolder)));
					List<com.liferay.journal.model.impl.JournalFolderImpl> ja_1 = JournalArticleResourceLocalServiceUtil.dynamicQuery(query_journal_folder);
					for (com.liferay.journal.model.impl.JournalFolderImpl journalArticleResourceImpl : ja_1) {
				    	 if(journalArticleResourceImpl.getName().equals(nameFolder)){
				    	 return journalArticleResourceImpl.getFolderId();
				    	 
				    	 }
					}
					return parentFolder;

					}
			  	
			  	@SuppressWarnings("deprecation")
				public static com.consistent.rate.models.hotel.Hotel getHotelRooms(com.consistent.rate.mapping.GetMappingHotel content,String brand,String laguage,Long siteID) throws NumberFormatException, PortalException  {
				 
				    com.consistent.rate.models.hotel.Hotel hote= new com.consistent.rate.models.hotel.Hotel();
					//hote.setBrandcode(brand);
					hote.setGuid(content.getArticleId());
					hote.setCode(content.getHotelCode());
					hote.setName(content.getName());
					hote.setTitle(content.getTitle());
					hote.setLanguage(Contants.LENGUAJE);
					hote.setKeyword(content.getMetaTags().toString());
					hote.setShortDescription(content.getShortDescription());
					hote.setDescription(content.getDescription());
					hote.setOrder("0");
					hote.setChannel("www");

					/*Inicia sección recupera los números telefónicos*/
					List<com.consistent.rate.models.hotel.Telephone> telephne= new ArrayList<>();;
					com.consistent.rate.models.hotel.Telephone tel= new com.consistent.rate.models.hotel.Telephone();
					if(content.getPhones()!=null){
					List<String> phones = content.getPhones();
					for (String phone : phones) {
						if(!phone.equals("{}")){
							tel.setNumber(phone);
							tel.setType("telephone");
							telephne.add(tel);
						}}
					}else{
						tel.setNumber("");
						tel.setType("telephone");
					}
					com.consistent.rate.models.hotel.Telephones telephone= new com.consistent.rate.models.hotel.Telephones();
					telephone.setTelephone(telephne);
					List<com.consistent.rate.models.hotel.Telephones> telephones= new ArrayList<>();
					telephones.add(telephone);
					hote.setTelephones(telephones);
					/*Fin sección recupera los números telefónicos*/
					
					/*Inicia sección recupera locations*/
					com.consistent.rate.models.hotel.Location location= new com.consistent.rate.models.hotel.Location();
					location.setAddress(content.getAddress());
					location.setCity(content.getCity());
					location.setCountry(content.getCountry());
					location.setDirections(content.getAddresses());
					location.setLatitude(content.getLatitude());
					location.setLongitude(content.getLongitude());
					location.setReferences(content.getReferences());
					location.setState(content.getState());
					location.setZip(content.getZipCode());
					List<com.consistent.rate.models.hotel.Location> locations= new ArrayList<>();
					locations.add(location);
					hote.setLocations(locations);
					/*Fin sección recupera locations*/
							
					/*Inicio sección recupera medialinks*/
					JSONArray ArrayMediaLinks = JSONFactoryUtil.createJSONArray();
			          
			          
					List<String> MeliaLinkList = content.getMediaLinks();
					for (String mediaLinkItem : MeliaLinkList) {
						JSONObject myObject;
						try {
							myObject = JSONFactoryUtil.createJSONObject(mediaLinkItem);
							ArrayMediaLinks.put(myObject);
						} catch (JSONException e) {
							log.error("Error converter json"+e);
						}
						
					}
					
					
					List<com.consistent.rate.models.hotel.Multimedia> multimedias= new ArrayList<>();
					com.consistent.rate.models.hotel.Multimedia multimedia= null;
					com.consistent.rate.models.hotel.MediaLink medialnk= new com.consistent.rate.models.hotel.MediaLink();
					
					for (int i = 0; i < ArrayMediaLinks.length(); i++) {
						multimedia= new com.consistent.rate.models.hotel.Multimedia();
						JSONObject jsonobject = ArrayMediaLinks.getJSONObject(i);
					    String link = jsonobject.getString("link");
					    String type_image = jsonobject.getString("type_image");
						multimedia.setUrl(link);
						multimedia.setType(type_image);
						multimedias.add(multimedia);
						
					}
					medialnk.setKeyword("");
					medialnk.setMultimedia(multimedias);
					medialnk.setThumbnail("");
					medialnk.setType("image");
					
					List<com.consistent.rate.models.hotel.MediaLink> medialinks= new ArrayList<>();
					medialinks.add(medialnk);
					
					com.consistent.rate.models.hotel.MediaLinks medialink= new com.consistent.rate.models.hotel.MediaLinks();
					medialink.setMedialinks(medialinks);
					
					List<com.consistent.rate.models.hotel.MediaLinks> mediaLinks= new ArrayList<>();
					mediaLinks.add(medialink);
					hote.setMediaLinks(mediaLinks);
					/*Fin sección recupera medialinks*/

					
					
					/*Room*/
					JSONArray ArrayRooms = JSONFactoryUtil.createJSONArray();
					List<String> Rooms = content.getRoomLinks();
					for (String room : Rooms) {
						if(!room.equals("{}")){
							JSONObject myObject;
							try {
								myObject = JSONFactoryUtil.createJSONObject(room);
								ArrayRooms.put(myObject);
							}catch (JSONException e) {
								log.error("Error converter json"+e);
							}
							
						}
					
					}
					
					
					List<String> room_string= new ArrayList<>();
					for (int i = 0; i < ArrayRooms.length(); i++) {
						JSONObject jsonobject = ArrayRooms.getJSONObject(i);
					    Long pk = jsonobject.getLong("classPK");
					    room_string.add(""+pk);
					    
					}
					
					com.consistent.rate.models.room.room rom= null;
					List<com.consistent.rate.models.room.room> drfs= new ArrayList<>();
					for (int i = 0; i < room_string.size(); i++) {
						rom =new com.consistent.rate.models.room.room();
						List<com.consistent.rate.models.hotel.MediaLink> mec= new ArrayList<>();
						com.consistent.rate.models.hotel.MediaLink med= new MediaLink();
						RoomMapping ro = getJournalArticleByClassPkRoom(Long.parseLong(room_string.get(i)),laguage,siteID);
						rom.setGuid(room_string.get(i));
						rom.setCode(ro.getCode());
						rom.setName(ro.getName());
						rom.setTitle(ro.getTitle());
						rom.setLanguage(Contants.LENGUAJE);
						rom.setKeyword(ro.getKeywords());
						rom.setShortDescription(ro.getDescriptions().toString());
						rom.setDescription(ro.getDescriptions().toString());
						rom.setOrder("0");
						rom.setChannel("www");
						med.setMultimedia(ro.getMediaLinks());
						med.setType("image");
						med.setKeyword(ro.getKeywords());
						mec.add(med);
						rom.setMedialinks(mec);
						drfs.add(rom);
					}
					com.consistent.rate.models.room.rooms room_root= new com.consistent.rate.models.room.rooms();
					room_root.setRoom(drfs);

					hote.setRooms(room_root);	
					return  hote;
			}
			  		
			  	public static RoomMapping getJournalArticleByClassPkRoom(Long classPk,String laguage,Long siteID) throws PortalException{
					 com.consistent.rate.mapping.RoomMapping roomMapping = new com.consistent.rate.mapping.RoomMapping();
					 DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(com.liferay.journal.model.impl.JournalArticleImpl.class, "journal",PortalClassLoaderUtil.getClassLoader());			
					 dynamicQuery.add((PropertyFactoryUtil.forName("resourcePrimKey").eq(classPk)));
			     	 dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId", new Long(siteID)));
					 List<com.liferay.journal.model.impl.JournalArticleImpl> ja = JournalArticleLocalServiceUtil.dynamicQuery(dynamicQuery);
						for (com.liferay.journal.model.impl.JournalArticleImpl journalArticleImpl : ja) {
							roomMapping.RoomContent(JournalArticleLocalServiceUtil.getLatestArticle(siteID, journalArticleImpl.getArticleId()),laguage);
							return roomMapping;
						}
						return new RoomMapping();
						}
			  	
			  
			  /*	
			  	private static boolean getIntervals(String i, String f, String date){
					
					boolean estado = false;
					try {
						String d = date.replace('/','-');
						String init = i.replace('/','-');
						String end = f.replace('/','-');
						String fi = (end.equals(""))? DateTime.now().toString():end;
						DateTime inicio = new DateTime(init);
						DateTime fin = new DateTime(fi);
						Interval interval = new Interval(inicio, fin);
						estado = interval.contains(new DateTime(d));
						} catch (IllegalArgumentException e) {
						// TODO: handle exception
							e.getStackTrace();
						}
					return estado;
				}*/
			  
}


