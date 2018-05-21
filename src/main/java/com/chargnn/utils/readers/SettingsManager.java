package com.chargnn.utils.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class SettingsManager {

    public static final String DEFAULTS_SETTINGS = "res/defaults/settings.xml";

    private static DocumentBuilderFactory factory;
    private static String path = DEFAULTS_SETTINGS;

    private static Document parse() throws ParserConfigurationException, IOException, SAXException {
        factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(path);
    }

    public static String getElementsByTagName(String name) throws IOException, SAXException, ParserConfigurationException {
        NodeList elements = parse().getElementsByTagName(name);

        for(int i = 0; i < elements.getLength(); i++){
            Element tag = (Element) elements.item(i);
            return tag.getFirstChild().getNodeValue();
        }

        return null;
    }

    public static Integer getElementsByTagNameI(String name) throws IOException, SAXException, ParserConfigurationException {
        return Integer.parseInt(getElementsByTagName(name));
    }

    public static Boolean getElementsByTagNameBool(String name) throws IOException, SAXException, ParserConfigurationException {
        return Boolean.parseBoolean(getElementsByTagName(name));
    }

    public static void setPath(String p){
        path = p;
    }

}
