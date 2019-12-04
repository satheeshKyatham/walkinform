package com.godrej.properties.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Varsha Patil
 *
 */
public class DateUtil {

	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
	private static final String DEFAULT_FORMAT = "yyyyMMdd hh:mm:ss";
	public static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy";
	public static final String DEFAULT_DATETIME_FORMAT = "dd-MM-yyyy HH:mm";

	private DateUtil() {

	}

	public static Date getParsedDate(String dateString, String format) {
		Date parsedDate = null;
		String dateFormat = format;
		if (dateFormat == null)
			dateFormat = DEFAULT_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			parsedDate = sdf.parse(dateString);
		} catch (Exception e) {
			log.error("Error", e);
		}
		return parsedDate;
	}

	public static String convertDateFormat(String dateString, String currentFormat, String format) {
		Date parsedDate = null;
		String dateFormat = format;
		String outputDateString = null;
		if (dateFormat == null)
			dateFormat = DEFAULT_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		SimpleDateFormat curDateFormat = new SimpleDateFormat(currentFormat);
		/*
		 * if("Y".equals(Configuration.getInstance().getProperty(Constants.
		 * ENABLE_TIMEZONE_UTC))){
		 * curDateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); }
		 */
		try {
			parsedDate = curDateFormat.parse(dateString);
			outputDateString = sdf.format(parsedDate);
		} catch (Exception e) {
			log.error("Error", e);
		}

		return outputDateString;
	}

	public static String getTimeStampToString(Timestamp timeStamp) {

		String strDate = "";
		if (timeStamp != null)

			try {
				SimpleDateFormat sdfDate = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss.s");// dd/MM/yyyy
				String strDate1 = sdfDate.format(timeStamp);

				strDate = strDate1;
			} catch (Exception e) {
				log.error("Error", e);
			}

		return strDate;
	}

	public static String getDateString(Date date, String format) {
		String parsedDate = null;
		String dateFormat = format;
		if (dateFormat == null)
			dateFormat = DEFAULT_FORMAT;
		try {
			parsedDate = new SimpleDateFormat(dateFormat).format(date);
		} catch (Exception e) {
			log.error("Error", e);
		}

		return parsedDate;
	}
	  public static Date getDateWithoutTime(Date date)
	  {
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(date);
	    	cal.set(Calendar.HOUR_OF_DAY, 0);
	    	cal.set(Calendar.MINUTE, 0);
	    	cal.set(Calendar.SECOND, 0);
	    	cal.set(Calendar.MILLISECOND, 0);
	    	date = cal.getTime();
	    	return date; 
	  }
	
	 public static int getDays(Date fromDate, Date toDate){
		 if(fromDate==null){
			 return 0;
		 }
		 long difference = toDate.getTime() - fromDate.getTime();
	     float daysBetween = (difference / (1000*60*60*24));
	     return (int) daysBetween;
	 }
	 public static int getYearsDiff(Date date, Date validTo){
		  Calendar a= Calendar.getInstance();
		    a.setTime(date);
		    Calendar b = Calendar.getInstance();
		    b.setTime(validTo);
      int diff = a.get(Calendar.YEAR) - b.get(Calendar.YEAR);
      if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
          diff++;
      }
      return diff;
    }
	 public static Date getUKDateTime() {
		 Calendar cal = Calendar.getInstance();
		// remove next line if you're always using the current time.
		cal.setTime(new Date());
		cal.add(Calendar.HOUR, -5);
		cal.add(Calendar.MINUTE, -30);
		Date hourback = cal.getTime();
		return hourback;
	 
	 }
	 
	 public static Date getUKDateTime(Date date) {
		 Calendar cal = Calendar.getInstance();
		// remove next line if you're always using the current time.
		cal.setTime(date);
		cal.add(Calendar.HOUR, -5);
		cal.add(Calendar.MINUTE, -30);
		Date hourback = cal.getTime();
		return hourback;
	 }
	 public static void main(String args[]){
		 SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		 String dateBeforeString = "01 05 2019";
		 String dateAfterString = "08 05 2019";

		String dateString = getDateString(new Date(), null);
		System.out.println(dateString);
		 /*try {
		       Date dateBefore = myFormat.parse(dateBeforeString);
		       Date dateAfter = myFormat.parse(dateAfterString);
		       long difference = dateAfter.getTime() - dateBefore.getTime();
		       float daysBetween = (difference / (1000*60*60*24));
		       System.out.println("Number of Days between dates: "+(int)daysBetween);
		 } catch (Exception e) {
		       e.printStackTrace();
		 }*/
	   }
	  
 }
