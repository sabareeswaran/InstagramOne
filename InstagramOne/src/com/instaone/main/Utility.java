package com.instaone.main;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.TimeZone;

public class Utility {

	protected int getTime() {

		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("IST"));
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	protected boolean validateFollowTime() {

		if ((getTime() > 5 && getTime() <= 23)) {
			return true;
		}
		return false;
	}

	protected boolean validateUnfollowTime() {

		if (getTime() >= 0 && getTime() < 2) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Utility instaTimeValidations = new Utility();
		System.out.println(instaTimeValidations.getProperties("password"));
	}

	public String goToSleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			System.out.println("Exception Occured: " + e.getMessage());
		}
		return null;
	}

	public String getProperties(String property) {
		try {
			FileReader reader = new FileReader("C:\\Users\\sabarisamrat\\git\\repository\\InstagramOne\\src\\config.properties");
			Properties p = new Properties();
			p.load(reader);
			return p.getProperty(property);
		} catch (Exception e) {
			System.out.println("Exception is "+e.getLocalizedMessage());
			return null;
		}
	}
}
