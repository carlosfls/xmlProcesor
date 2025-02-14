package com.example.xmlporcessor.service;

import com.example.xmlporcessor.model.complex_xml.BookContainer;
import com.example.xmlporcessor.util.XMLUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class XMLProcessorServiceImpl implements XMLProcessorService {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper xmlMapper;
    private final ObjectMapper jsonMapper;

    @Override
    public String getXml() throws JsonProcessingException {
        Object xmlObject = getXmlObjectFromResources();
        return xmlMapper.writeValueAsString(xmlObject);
    }


    @Override
    public List<String> getValuesFromXmlTags(String tag) throws ParserConfigurationException, IOException, SAXException {
        Resource resource = resourceLoader.getResource("classpath:/xml/more-complex-structure.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(resource.getInputStream());
        document.getDocumentElement().normalize();

        Element nodeRoot = document.getDocumentElement();
        List<String> values = new ArrayList<>();

        XMLUtil.findNodeValuesByTag(nodeRoot, values, tag);
        return values;
    }

    @Override
    public String convertXmlToJson() throws JsonProcessingException {
        Object xmlObject = getXmlObjectFromResources();
        return jsonMapper.writeValueAsString(xmlObject);
    }

    @Override
    public BookContainer convertToPojo() throws JsonProcessingException {
        String json = convertXmlToJson();
        return jsonMapper.readValue(json, BookContainer.class);
    }

    private Object getXmlObjectFromResources(){
        Object xmlObject = null;
        Resource resource = resourceLoader.getResource("classpath:/xml/complex-structure.xml");
        try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            String xml = FileCopyUtils.copyToString(reader);
            ObjectMapper xmlMapper = new XmlMapper();
            xmlObject = xmlMapper.readValue(xml, Object.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return xmlObject;
    }
}
