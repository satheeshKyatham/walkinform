package com.godrej.kyc.util;
import java.util.*; 
  
public class OtpGenerate 
{ 
   public static String OTP() 
    { 
	    int len=6;
        String numbers = "0123456789"; 
        Random rndm_method = new Random(); 
        char[] otp = new char[len]; 
        for (int i = 0; i < len; i++) 
        { 
            otp[i] =  numbers.charAt(rndm_method.nextInt(numbers.length())); 
        } 
        return String.valueOf(otp); 
    } 
     
} 