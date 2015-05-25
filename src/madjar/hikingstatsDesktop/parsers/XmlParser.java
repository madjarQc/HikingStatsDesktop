package madjar.hikingstatsDesktop.parsers;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Parse and write call IO to append the parsed track with sorted points in file
 *
 * @author Alex
 */
public class XmlParser {

    File file;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document doc;

    public XmlParser(String filePath) throws ParserConfigurationException, IOException, SAXException {

        this.file = new File(filePath);
        this.factory = DocumentBuilderFactory.newInstance();
        this.builder = factory.newDocumentBuilder();
        this.doc = builder.parse(file);

        doc.getDocumentElement().normalize();

    }

    /**
     * To retrieve a single tag
     *
     * @param pTag
     * @return ArrayList, the list of the content of the found tag
     *//*
    public ArrayList getContentArray(String pTag) {

        ArrayList contentList = new ArrayList();
        NodeList nodes;

        nodes = doc.getElementsByTagName(pTag);

        for (int i = 0; i < nodes.getLength(); i++) {

            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                contentList.add(node.getTextContent());

            }
        }
        return contentList;
    }*/

    /**
     * Get the content of two children from pParent in the form of Key -> value
     *
     * @param pParent
     * @param pTagKey
     * @param pTagValue
     * @return TreeMap, the sorted map of the found text
     */
    public TreeMap getContentMap(String pParent, String pTagKey, String pTagValue) {

        System.out.println("----------------------------");
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        System.out.println("----------------------------");

        TreeMap contentMap = new TreeMap();

        NodeList nodesTrack;

        nodesTrack = doc.getElementsByTagName(pParent);

        for (int track = 0; track < nodesTrack.getLength(); track++) {

            Node nodeTrack = nodesTrack.item(track);

            System.out.println("Current element : " + nodeTrack.getNodeName());

            if (nodeTrack.getNodeType() == Node.ELEMENT_NODE) {

                Element trackElement = (Element) nodeTrack;

                System.out.println("lenght of trackElement : " + trackElement.getElementsByTagName(pTagKey).getLength());

                for (int key = 0; key < trackElement.getElementsByTagName(pTagKey).getLength(); key++) {

                    //System.out.println("key : " + trackElement.getElementsByTagName(pTagKey).item(key).getTextContent());
                    //System.out.println("value : " + trackElement.getElementsByTagName(pTagValue).item(key).getTextContent());

                    contentMap.put(
                            trackElement.getElementsByTagName(pTagKey).item(key).getTextContent(),
                            trackElement.getElementsByTagName(pTagValue).item(key).getTextContent()
                    );

                }

            }

        }

        return contentMap;
    }
    
    
    
    
    
}
