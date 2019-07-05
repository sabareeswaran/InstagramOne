package com.instaone.main;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Utility {
	
	/*
	 * protected boolean checkTimeBeforeUnfollow() {
	 * 
	 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	 * LocalDateTime now = LocalDateTime.now(); String currentTime =
	 * dtf.format(now).substring(11, 13); if (currentTime.equals("03") ||
	 * currentTime.equals("01") || currentTime.equals("02") ||
	 * currentTime.equals("04")) { // System.out.println("Time is " + currentTime +
	 * ". So system is Shut Down."); try { //
	 * System.out.println("System Shut Down for 4 hours"); //
	 * Thread.sleep(14780000); } catch (Exception e) {
	 * System.out.println("Error Occured: " + e.getMessage()); } return false; }
	 * return true; }
	 */
	
	protected int getTime() {
		
		/*Date date = new Date();
		String time = date.getTime();*/
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("IST"));
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		
		//int hour = LocalDateTime.now().getHour();
		
		return hour;
	}
	
	protected boolean validateFollowTime() {
		
		if((getTime() > 5 && getTime() <= 23)) {
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
		System.out.println(instaTimeValidations.getTime());
	}
	
	public String goToSleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			System.out.println("Exception Occured: " + e.getMessage());
		}
		
		return null;
	}

}
