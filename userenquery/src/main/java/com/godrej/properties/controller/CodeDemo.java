package com.godrej.properties.controller;

public class CodeDemo {

	
	public static void main(String[] args) {
		int[] intArray = new int[4];
		intArray[0]=2;
		intArray[1]=7;
		intArray[2]=11;
		intArray[3]=15;
		twoSum(intArray, 18);
	}
	public static int[] twoSum(int[] nums, int target) {//[2,7,11,15] ,//18
        
        int[] oldArray = nums;
        int[] secondArray = nums;
        System.out.println("Lenght"+oldArray.length);
        for(int i=0;i<oldArray.length-1;i++)
        {
        	int indexSearch=oldArray[i];
             for(int j=0;j<secondArray.length-1;j++)
            {
            	 System.out.println("ff");
                int towsum = indexSearch+secondArray[j];
                System.out.println(towsum);
                if(towsum==target)
                {
                    int [] finalReturn = new int[2];
                    finalReturn[0]=oldArray[i];
                    finalReturn[1]=secondArray[i]; 
                    System.out.println(finalReturn);
                    return finalReturn;
                 }
            
            }
            
        }
        return null;
        
    }

}
