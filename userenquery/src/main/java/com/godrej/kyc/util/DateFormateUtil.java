package com.godrej.kyc.util;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormateUtil {

	 
	public static String get_M_d_y__d_M_y(String dateStr)   {
	 
		DateFormat parser = new SimpleDateFormat("MM-dd-yyyy"); 
		Date date;
		try {
			date = (Date) parser.parse(dateStr);
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			 
			String finalString =formatter.format(date);
			return finalString;
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "";
	}
	public static String get_d_M_y__M_d_y(String dateStr)   {
		 
		DateFormat parser = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date;
		try {
			date = (Date) parser.parse(dateStr);
			DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy"); 
		 
			String finalString =formatter.format(date);
			return finalString;
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "";
	}
	
	public static String get_d_M_y__M_d_ySlash(String dateStr)   {
		 
		DateFormat parser = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date;
		try {
			date = (Date) parser.parse(dateStr);
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
		 
			String finalString =formatter.format(date);
			return finalString;
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "";
	}
	
}
