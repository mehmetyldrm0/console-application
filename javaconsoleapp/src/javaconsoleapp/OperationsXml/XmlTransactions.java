package javaconsoleapp.OperationsXml;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlTransactions {
	private static final String XML_URL = "https://www.tcmb.gov.tr/kurlar/today.xml";

	public void processXML() {
		try {
			String content = fetchXMLContent();
			saveXMLToFile(content);
			processXMLSave();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String fetchXMLContent() throws IOException {
		StringBuilder content = new StringBuilder();

		HttpURLConnection connection = (HttpURLConnection) new URL(XML_URL).openConnection();
		connection.setRequestMethod("GET");

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
		}

		return content.toString();

	}

	private void saveXMLToFile(String content) {
		try {
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream("output.xml", false));

			byte[] data = content.getBytes();
			bufferedOutputStream.write(data);
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processXMLSave() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(XML_URL);
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName("Currency");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);

				// İlgili tabloya kaydı ekle
				SaveDatabaseOperations saveDatabaseOperations = new SaveDatabaseOperations();
				saveDatabaseOperations.addToDatabase(element);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
