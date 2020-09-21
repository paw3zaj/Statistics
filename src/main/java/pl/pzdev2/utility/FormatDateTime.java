package pl.pzdev2.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDateTime {
	
	private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";

	public static String getDateTime() {

		LocalDateTime localDateTime = LocalDateTime.now(); // get current date time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

		return localDateTime.format(formatter);
	}
	
	public static LocalDateTime convertToLocalDateTime(String createdDate) {
		
		return LocalDateTime.parse(createdDate,
		        DateTimeFormatter.ofPattern(DATE_FORMATTER));
	}

}
