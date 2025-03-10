package com.example.xmlporcessor.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.util.List;

public class XMLUtil {

    private XMLUtil(){}

    /**
     * Search for the value in an specific tag in the xml, if exist more than one tag retrieves the values for each
     *
     * @param node the first node of the xml document
     * @param values  the list of matches
     * @param tag  the search tag
     */
    public static void findNodeValuesByTag(Node node, List<String> values, String tag){
        if (node != null){
            if (node.getNodeType() == Node.TEXT_NODE){
                if (!node.getTextContent().isBlank()){
                    Element element = (Element) node.getParentNode();
                    if (tag.equals(element.getTagName())){
                        values.add(node.getTextContent().trim());
                    }
                }
            }
            else if (node.getNodeType() == Node.ELEMENT_NODE && node.hasChildNodes()){
                NodeList nodeList = node.getChildNodes();
                for (int i = 0; i < nodeList.getLength(); i++){
                    findNodeValuesByTag(nodeList.item(i), values, tag);
                }
            }
        }
    }

    /**
     * Processing the xml document with xpath which is better for get the values from the xml document
     * @param document The xml document
     * @return The value from that tag
     */
    public static String getExampleValueFromDocumentXmlWithXPath(Document document) {
        String result = "";
        XPath xPath = XPathFactory.newInstance().newXPath();
        try {
            result = xPath.evaluate("/letter/salutation", document);
            System.out.println("The value for that tag is: "+ result);
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
