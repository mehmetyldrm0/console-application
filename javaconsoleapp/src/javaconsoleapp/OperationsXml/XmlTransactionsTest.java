package javaconsoleapp.OperationsXml;

import java.time.LocalDate;

import javaconsoleapp.dateXml.FileModificationDate;
import javaconsoleapp.dateXml.ModifiedDateCheck;

public class XmlTransactionsTest {
	public static void main(String[] args) throws Exception{
		LocalDate lastModifiedDate = ModifiedDateCheck
				.getLastModifiedDate("https://www.tcmb.gov.tr/kurlar/today.xml");
		
		LocalDate fileDate = FileModificationDate.getXMLDate("output.xml");
		XmlTransactions xmlTransactions = new XmlTransactions();

		if (lastModifiedDate.equals(fileDate)) {
			System.out.println("XML not modified since last check.");
			
		} else {
			xmlTransactions.processXML();
			XSLTTransformer.transformAndSaveWithTimestamp();
		}
	}

}
