package com.consistent.rate.configuration;


import java.util.List;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Posadas", scope = ExtendedObjectClassDefinition.Scope.SYSTEM)
@Meta.OCD( localization = "content/Language",id = "com.consistent.rate.configuration.Otherconfig",name = "Service Hotel Room Rates Configuration")
public interface Otherconfig {
	@Meta.AD(required = false,description = "Get path base folder search Hotels")
    public List<String> folderName();
    
    @Meta.AD( deflt = "Hoteles", required = false,description = "Get name from structure search queries")
    public String NameDefaultStructure();
}

