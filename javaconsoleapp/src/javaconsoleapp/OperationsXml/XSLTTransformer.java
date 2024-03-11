package javaconsoleapp.OperationsXml;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XSLTTransformer {

	public static void transformAndSaveWithTimestamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String dateStr = dateFormat.format(new Date());

		try {
			File xmlFile = new File("output.xml");
			File xslFile = new File("context/isokur.xsl");

			String outputFileName = "Rates_" + dateStr + ".xml";
			File outputFile = new File(outputFileName);

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xslFile));
			transformer.transform(new StreamSource(xmlFile), new StreamResult(outputFile));

			System.out.println("Transformation completed successfully. Output file: " + outputFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
