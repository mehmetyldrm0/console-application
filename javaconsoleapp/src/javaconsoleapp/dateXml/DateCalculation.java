package javaconsoleapp.dateXml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateCalculation {
	public static long getDelayToNextDay() {
	
		long currentTime = System.currentTimeMillis(); // şimdiki zaman

		// Bir sonraki günün 00:00:00'a olan farkı hesapla
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date date = new Date(currentTime + TimeUnit.DAYS.toMillis(1));
		String nextDay = sdf.format(date);

		try {// Bir sonraki günün başlangıcına kadar kalan süreyi hesapla
			
			long nextDayTime = sdf.parse(nextDay).getTime(); 
			return nextDayTime - currentTime;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
