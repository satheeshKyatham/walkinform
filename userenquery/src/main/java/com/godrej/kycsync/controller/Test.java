package com.godrej.kycsync.controller;

public class Test {

	public static void main(String[] args) {
		String name ="HEllo test shdfjhsdfjk uewhfds ksdfklfs";
		
		int length = name.length();
		System.out.println("LEngth"+length);
		String street2Contact_address="";
		String street1Contact_address="";
		String street3Contact_address="";
		
		if(length<=32)
		{
			System.out.println("LEngth contains below 32 length");
			street1Contact_address=name.substring(0, length);;
		}
		if(length>32 && length>64)
		{
			System.out.println("LEngth contains above 32 length");
			street1Contact_address=name.substring(0, 32);
			if(length<=64)
			{
				System.out.println("LEngth contains below 64 length");
				street2Contact_address = name.substring(33, length);
				
			}
			if(length>64)
			{
				System.out.println("LEngth contains above 64 length");
				street2Contact_address = name.substring(33, 64);
				street3Contact_address = name.substring(65, length);
			}
		}

	}

}
