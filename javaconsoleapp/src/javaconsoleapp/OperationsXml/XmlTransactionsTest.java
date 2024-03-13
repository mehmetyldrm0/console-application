package javaconsoleapp.OperationsXml;


public class XmlTransactionsTest {
	public static void main(String[] args) throws Exception {
		TcmbRatesXmlControl.scheduledExecutor();
		XSLTTransformer.transformAndSaveWithTimestamp();

	}

}
