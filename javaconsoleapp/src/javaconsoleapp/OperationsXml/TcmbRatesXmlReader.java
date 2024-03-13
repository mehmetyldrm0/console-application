package javaconsoleapp.OperationsXml;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javaconsoleapp.dateXml.DateCalculation;

public class TcmbRatesXmlReader {
	private static final String TCMB_URL = "https://www.tcmb.gov.tr/kurlar/today.xml";
	private static final String IF_MODIFIED_SINCE_HEADER = "If-Modified-Since";
	private static String lastModifiedDate = null;

	protected static void scheduledExecutor() {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		scheduler.scheduleAtFixedRate(() -> {
			try {
				checkXmlChanges();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, DateCalculation.getDelayToNextDay(), 1, TimeUnit.DAYS);
	}

	private static void checkXmlChanges() throws Exception {

		try {
			// URL oluşturma
			URL url = new URL(TCMB_URL);

			// HttpURLConnection oluşturma
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Eğer daha önce bir tarih belirlenmişse, "If-Modified-Since" başlığını ayarla
			if (lastModifiedDate != null) {
				connection.setRequestProperty(IF_MODIFIED_SINCE_HEADER, lastModifiedDate);
			}
			startConnection(connection);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void startConnection(HttpURLConnection connection) throws Exception {
		connection.connect(); // Bağlantıyı başlatma

		int responseCode = connection.getResponseCode(); // HTTP yanıt kodunu kontrol etme

		if (responseCode == HttpURLConnection.HTTP_NOT_MODIFIED) {

			System.out.println("XML has not changed, no processing is needed.");

		} else if (responseCode == HttpURLConnection.HTTP_OK) {

			startXMLProcess();
			saveLastModifiedDate(connection);
		}

		connection.disconnect(); // Bağlantıyı kapatma

	}

	private static void startXMLProcess() {
		XmlTransactions xmlTransactions = new XmlTransactions();
		xmlTransactions.processXML();
	}

	private static void saveLastModifiedDate(HttpURLConnection connection) {
		lastModifiedDate = connection.getHeaderField("Last-Modified");
	}

}
