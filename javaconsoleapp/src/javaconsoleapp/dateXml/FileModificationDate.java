package javaconsoleapp.dateXml;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FileModificationDate {
	public static LocalDate getXMLDate(String filePath) {

		LocalDate parsedDate = null;
		
		try {
			File file = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);

			document.getDocumentElement().normalize();

			NodeList dateList = document.getElementsByTagName("Tarih_Date");
			Element element = (Element) dateList.item(0);
			String dateValue = element.getAttribute("Tarih");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			parsedDate = LocalDate.parse(dateValue, formatter);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return parsedDate;
	}


}
