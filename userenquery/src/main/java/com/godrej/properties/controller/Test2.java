package com.godrej.properties.controller;

import java.util.Scanner;

public class Test2 {
 
	
	 public static void main(String[] args) {
	        int num, a = 0,b=0, c =1;
	        Scanner in = new Scanner(System.in);
	        System.out.println("Enter the number of times");
	        num = in.nextInt();
	        System.out.println("Fibonacci Series of the number is:");
	        for (int i=0; i<num; i++) {
	            a = b;//0
	            b = c;//1
	            c = a+b;//1
	            System.out.println(a);    //if you want to print on the same line, use print()
	        }       
	    }
    /*public static void main(String[] args) {
        // TODO Auto-generated method stub
        int temp, num;
        boolean isPrime = true;
        Scanner in = new Scanner(System.in);
        num = in.nextInt();
        in.close();
        for (int i = 2; i<= num/2; i++) {
            temp = num%i;
            if (temp == 0) {
                isPrime = false;
                break;
            }
        }
        if(isPrime) 
            System.out.println(num + "number is prime");
            else
                System.out.println(num + "number is not a prime");
             
         
    }*/
    
    
 
}