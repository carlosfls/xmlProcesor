package com.example.xmlporcessor.resource;

import com.example.xmlporcessor.model.complex_xml.BookContainer;
import com.example.xmlporcessor.service.XMLProcessorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/xml")
@RequiredArgsConstructor
public class XMLController {

    private final XMLProcessorService xmlProcessorService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> get() throws JsonProcessingException {
        return ResponseEntity.ok(xmlProcessorService.getXml());
    }

    @GetMapping(value = "/{tag}/value", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getValuesFromXmlTag(@PathVariable String tag) throws IOException, ParserConfigurationException, SAXException {
        return ResponseEntity.ok(xmlProcessorService.getValuesFromXmlTags(tag));
    }

    @GetMapping(value = "/toJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJson() throws JsonProcessingException {
        return ResponseEntity.ok(xmlProcessorService.convertXmlToJson());
    }

    @GetMapping(value = "/toPojo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookContainer> getBookContainer() throws JsonProcessingException {
        return ResponseEntity.ok(xmlProcessorService.convertToPojo());
    }
}
