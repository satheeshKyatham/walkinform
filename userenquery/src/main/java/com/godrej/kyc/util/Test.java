package com.godrej.kyc.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test  {

   
    
    public static void main(String args[]){
    /*	Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher("I am a$%%# string");
    boolean b = m.find();

    if (b)
       System.out.println("There is a special chaasdq34racter in my string");*/
    	
    	/*Date date = new Date();  
        Timestamp ts=new Timestamp(date.getTime());  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");  
        System.out.println(formatter.format(ts)); */
    	System.out.println(new Date(System.currentTimeMillis()));
    
    }

}
 