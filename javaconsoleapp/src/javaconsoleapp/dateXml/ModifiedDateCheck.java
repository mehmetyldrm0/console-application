package javaconsoleapp.dateXml;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ModifiedDateCheck {

	public static LocalDate getLastModifiedDate(String url) throws IOException, ParseException {

		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestMethod("HEAD");

		long lastModifiedTimestamp = connection.getLastModified();
		Date lastModifiedDate = new Date(lastModifiedTimestamp);

		// Belirli bir tarih formatına dönüştürme
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String dateValue = dateFormat.format(lastModifiedDate);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate parsedDate = LocalDate.parse(dateValue, formatter);

		return parsedDate;

	}

}
