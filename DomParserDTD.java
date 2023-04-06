package xml_operations;   

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParserDTD {
	public static void main(String[] args) {
		try {
			File xmlFile = new File("src/xml_operations/courses.xml");

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setValidating(true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document parsedDocument = documentBuilder.parse(xmlFile);

			System.out.println("XML document is valid and verified aganist its DTD.");
			System.out.println();
			System.out.println("Parsing data....");
			System.out.println();

			parsedDocument.getDocumentElement().normalize();
			NodeList nodes = parsedDocument.getElementsByTagName("course");

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.println(
							"--------------------------------------------------------------------------------------------");
					System.out
							.println("CourseId: " + element.getElementsByTagName("course_id").item(0).getTextContent());
					System.out.println("Course Name: " + element.getElementsByTagName("name").item(0).getTextContent());
					System.out
							.println("Course Major: " + element.getElementsByTagName("major").item(0).getTextContent());
					System.out.println("Credits: " + element.getElementsByTagName("credits").item(0).getTextContent());
				}
			}
			System.out.println(
					"--------------------------------------------------------------------------------------------");

		} catch (SAXException e) {
			System.out.println("XML document is not valid. " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Exception Occured " + e.getMessage());

		} catch (Exception e) {
			System.out.println("Exception Occured " + e.getMessage());
		}
	}
}
