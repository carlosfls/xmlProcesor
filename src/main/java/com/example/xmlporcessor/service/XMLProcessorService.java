package com.example.xmlporcessor.service;

import com.example.xmlporcessor.model.complex_xml.BookContainer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.util.List;

public interface XMLProcessorService {

    String getXml() throws JsonProcessingException;

    List<String> getValuesFromXmlTags(String tag) throws ParserConfigurationException, IOException, SAXException;

    String convertXmlToJson() throws JsonProcessingException;

    BookContainer convertToPojo() throws JsonProcessingException;
}
