package javaconsoleapp.OperationsXml;

public class XmlTransactionsTest {
	public static void main(String[] args) throws Exception {
		TcmbRatesXmlReader.scheduledExecutor();
		XSLTTransformer.transformAndSaveWithTimestamp();

	}

}
