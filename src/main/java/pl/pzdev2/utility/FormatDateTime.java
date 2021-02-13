package pl.pzdev2.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDateTime {
	
	private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";

	public static String getDateTimeAsString() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
		return localDateTime.format(formatter);
	}
	
	public static LocalDateTime convertStringToLocalDateTime(String createdDate) {
		return LocalDateTime.parse(createdDate,
		        DateTimeFormatter.ofPattern(DATE_FORMATTER));
	}

	public static Integer getYear(String createdDate) {
		return convertStringToLocalDateTime(createdDate).getYear();
	}

	public static Integer getMonthValue(String createdDate) {
		return convertStringToLocalDateTime(createdDate).getMonthValue();
	}
}
