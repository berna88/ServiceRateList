package com.consistent.rate.configuration;

import java.util.Date;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.consistent.rate.constants.Constants;
import com.consistent.rate.interfaces.Otherconfig;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


@Component(
		immediate=true,
		configurationPid = "com.consistent.rate.configuration.Otherconfig",
		configurationPolicy = ConfigurationPolicy.OPTIONAL,
		property={"jaxrs.application=true"})

public class Configure{
	
	private static final Log log = LogFactoryUtil.getLog(Configure.class);
	
	
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
