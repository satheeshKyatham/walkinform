package com.godrej.kycsync.controller;

public class TestString {

	
	public static void main(String[] args) {
		

		String name ="HEllo test shdfjhsd";
		
		int length = name.length();
		System.out.println("LEngth"+length);
		String street2Contact_address="";
		String street1Contact_address="";
		String street3Contact_address="";
		
		if(length<=32)
		{
			
			street1Contact_address=name.substring(0, length);;
		}
		else if(length>32 && length<64)
		{
			
			street1Contact_address=name.substring(0, 32);
			if(length<=64)
			{
				
				street2Contact_address = name.substring(32, length);
			}
		}
		
		else if(length>64)
		{
			
			street1Contact_address=name.substring(0, 32);
			street2Contact_address = name.substring(32, 64);
			street3Contact_address = name.substring(64, length);
		}
	}
}
