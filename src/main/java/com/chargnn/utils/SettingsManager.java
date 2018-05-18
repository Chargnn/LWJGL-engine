package com.chargnn.utils;

import org.lwjgl.opengl.GL11;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SettingsManager {

    private DocumentBuilderFactory factory;
    private String path;

    public SettingsManager(String path) {
        this.path = path;

        factory = DocumentBuilderFactory.newInstance();
    }

    public Document parse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(path);
    }

    public Integer getElementsByTagNameI(String name) throws IOException, SAXException, ParserConfigurationException {
        NodeList elements = parse().getElementsByTagName(name);

        for(int i = 0; i < elements.getLength(); i++){
            Element tag = (Element) elements.item(i);
            return Integer.parseInt(tag.getFirstChild().getNodeValue());
        }

        return null;
    }

    public String getElementsByTagName(String name) throws IOException, SAXException, ParserConfigurationException {
        NodeList elements = parse().getElementsByTagName(name);

        for(int i = 0; i < elements.getLength(); i++){
            Element tag = (Element) elements.item(i);
            return tag.getFirstChild().getNodeValue();
        }

        return null;
    }

}
