package com.example.xmlporcessor.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
}
