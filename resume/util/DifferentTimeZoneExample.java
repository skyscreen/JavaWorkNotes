package resume.java.util;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DifferentTimeZoneExample {

	public static void main(String[] args) {
		example1();
		example2();

	}

	public static void example1() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");
		LocalDateTime ldt = LocalDateTime.of(2016, Month.AUGUST, 22, 14, 30);
		System.out.println("LocalDateTime : " + format.format(ldt));
		// UTC+8
		ZonedDateTime klDateTime = ldt.atZone(ZoneId.of("Asia/Kuala_Lumpur"));
		System.out.println("Depart : " + format.format(klDateTime));
		// UTC+9 and flight duration = 7 hours
		ZonedDateTime japanDateTime = klDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo")).plusHours(7);
		System.out.println("Arrive : " + format.format(japanDateTime));
		System.out.println("\n---Detail---");
		System.out.println("Depart : " + klDateTime);
		System.out.println("Arrive : " + japanDateTime);
	}

	public static void example2() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");
		// Convert String to LocalDateTime
		String date = "2016-08-22 14:30";
		LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		System.out.println("LocalDateTime : " + format.format(ldt));
		// Paris, 2016 Apr-Oct = DST, UTC+2, other months UTC+1
		// UTC+2
		ZonedDateTime parisDateTime = ldt.atZone(ZoneId.of("Europe/Paris"));
		System.out.println("Depart : " + format.format(parisDateTime));
		// hard code a zoneoffset like this, UTC-5
		ZoneOffset nyOffSet = ZoneOffset.of("-05:00");
		ZonedDateTime nyDateTime = parisDateTime.withZoneSameInstant(nyOffSet).plusHours(8).plusMinutes(10);
		System.out.println("Arrive : " + format.format(nyDateTime));
		System.out.println("\n---Detail---");
		System.out.println("Depart : " + parisDateTime);
		System.out.println("Arrive : " + nyDateTime);
	}

}
