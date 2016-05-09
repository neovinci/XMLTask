package by.epamlab.xml;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import by.epamlab.beans.Airline;

public class XmlUtils {

	public static void exportCatalogToXML(String fileName, List<Airline> airlines) {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("airlines");
			doc.appendChild(populateRootElement(doc, rootElement));

			for (Airline item : airlines) {
				Element staff = doc.createElement("airline");
				rootElement.appendChild(populateAirlineElement(doc, staff, item));
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File(fileName));
			transformer.transform(source, result);

			System.out.println("Done");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}

	private static Element populateAirlineElement(Document doc, Element item, Airline airlines) {

		// set attribute to airline element
		Attr attr = doc.createAttribute("id");
		attr.setValue(airlines.getId());
		item.setAttributeNode(attr);

		attr = doc.createAttribute("dates");
		attr.setValue(airlines.getDate().toString());
		item.setAttributeNode(attr);
		
		attr = doc.createAttribute("gates");
		attr.setValue(airlines.getGates());
		item.setAttributeNode(attr);
		
		attr = doc.createAttribute("aircraft-number");
		attr.setValue(Integer.toString(airlines.getAircraftNumber()));
		item.setAttributeNode(attr);
		
		attr = doc.createAttribute("type");
		attr.setValue(airlines.getType());
		item.setAttributeNode(attr);

		/**
		 * TODO Do the same for all fields of Airlines
		 */
		return item;
	}
	
	private static Element populateRootElement(Document doc, Element item) {

		// set attribute to root element
		Attr attr = doc.createAttribute("xmlns:xsi");
		attr.setValue("http://www.w3.org/2001/XMLSchema-instance");
		item.setAttributeNode(attr);

		attr = doc.createAttribute("xmlns");
		attr.setValue("http://www.example.com/airlines");
		item.setAttributeNode(attr);
		
		attr = doc.createAttribute("xsi:schemaLocation");
		attr.setValue("http://www.example.com/airlines airline.xsd");
		item.setAttributeNode(attr);
		
		/**
		 * TODO Do the same for root element
		 */
		return item;
	}

	public static List<Airline> readCatalogFromFile(String fileName) {
		List<Airline> airlines = new ArrayList<Airline>();
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			NodeList nList = doc.getElementsByTagName("airline");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Airline tempAirlines = new Airline();
													
					tempAirlines.setId(eElement.getAttribute("id"));
					tempAirlines.setDate(Date.valueOf(eElement.getAttribute("dates")));
					tempAirlines.setGates(eElement.getAttribute("gates"));
					tempAirlines.setAircraftNumber(Integer.parseInt(eElement.getAttribute("aircraft-number")));
					tempAirlines.setType(eElement.getAttribute("type"));
									
					airlines.add(tempAirlines);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return airlines;
	}

}
