package xml_operations;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class DomParserSchema {
	public static void main(String[] args) {
		try {

			File xml = new File("src/xml_operations/courses.xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document parsedDocument = documentBuilder.parse(xml);

			File xsd = new File("src/xml_operations/courses.xsd");
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(xsd);

			Validator validator = schema.newValidator();
			Source source = new DOMSource(parsedDocument);
			validator.validate(source);

			System.out.println("XML document is valid and verified aganist its schema.");
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

		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfigurationException : " + e.getMessage());
		} catch (SAXException e) {
			System.out.println("XML document is not valid because: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Exception Occured " + e.getMessage());

		} catch (Exception e) {
			System.out.println("Exception Occured " + e.getMessage());
		}
	}
}
