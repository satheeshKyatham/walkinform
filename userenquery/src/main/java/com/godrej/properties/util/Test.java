package com.godrej.properties.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test  {

   
    
    public static void main(String args[]){
    	
    	String str="ThaneThaneThaneThaneThaneThaneThaneThaneThaneThaneThaneThaneThaneThaneThaneThaneThaneThaneThanea";
    	System.out.println(str.length());
    	Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher("I am a$%%# string");
    boolean b = m.find();

    if (b)
       System.out.println("There is a special chaasdq34racter in my string");}

}
 