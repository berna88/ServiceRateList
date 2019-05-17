package com.consistent.rate.sax;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

public interface Mapping {
	public final String order = "0";
	public final String channel = "www";
	public String getMapping() throws XMLStreamException, IOException;
}
