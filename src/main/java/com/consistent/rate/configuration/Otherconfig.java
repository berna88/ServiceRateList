package com.consistent.rate.configuration;


import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Posadas", scope = ExtendedObjectClassDefinition.Scope.SYSTEM)
@Meta.OCD( localization = "content/Language",id = "com.consistent.rate.configuration.Otherconfig",name = "Service Hotel Room Rates Configuration")
public interface Otherconfig {
	@Meta.AD(required = false,description = "Get web content folder id")
    public Long folderId();
    
    @Meta.AD(required = false,description = "Get web content structure hotel id")
    public Long structureHotelId();
   
    @Meta.AD(required = false,description = "Get web content structure rate id")
    public Long structureRatesId();
    
}

