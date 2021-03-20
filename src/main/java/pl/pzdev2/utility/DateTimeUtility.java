package pl.pzdev2.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class DateTimeUtility {
	
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

	public static int getTheCurrentYear() {
		return LocalDate.now().getYear();
	}

	public static List<Integer> getYears() {
		List<Integer> years = new LinkedList<>();
		LocalDate today = LocalDate.now();
		int year = today.getYear();

		for (int i = 2020; i <= year; i++) {
			years.add(i);
		}
		return years;
	}
}
