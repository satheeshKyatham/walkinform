package com.godrej.properties.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.godrej.properties.model.GeneratePayment;
import com.godrej.properties.model.ProjectLaunch;

/**
 * 
 * @author Varsha Patil
 * 
 *  *
 */
public class CommonUtil {
	private static Logger Log = LoggerFactory.getLogger(CommonUtil.class);
	/*private static final Logger LOG =  LoggerFactory.getLogger(CommonUtil.class);*/
	private CommonUtil(){
		
	}
	/**
	 * 
	 * @param object - String object to check against
	 * @return true if String is null or blank after trimming space, false otherwise
	 */
	public static boolean isStringEmpty(String object){
		boolean empty =false;
		if(object==null || object.trim().length() ==0)
			empty =true;
		return empty;
	}
	
	/**
	 * 
	 * @param list
	 * @return true if list is null or having size 0
	 */			
	public static boolean isListEmpty(List<?> list){
		boolean empty =false;
		if(list == null || list.isEmpty())
			empty=true;
		return empty;
	}
	

	/**
	 * 
	 * @param list
	 * @return true if list is null or having size 0
	 */			
	public static boolean isCollectionEmpty(Collection<?> collection){
		boolean empty =false;
		if(collection == null || collection.isEmpty())
			empty=true;
		return empty;
	}

	/**
	 * 
	 * @param number 
	 * @return -1 if number is less than zero, 0 if number is equal to zero and 1 if number is greater than zero
	 */
	public static int compareToZero(BigDecimal number){		
		BigDecimal inputNumber = number.setScale(2, RoundingMode.HALF_UP);
		return inputNumber.compareTo(new BigDecimal("0"));
	}

	/**
	 * covert string to number and then compare it with Zero.
	 * @param number 
	 * @return -1 if number is less than zero, 0 if number is equal to zero and 1 if number is greater than zero
	 */

	public static int compareToZero(String number){
		
		if(number == null)
			throw new NumberFormatException("Null value");
		BigDecimal inputNumber =null;
		
		try{
			inputNumber = new BigDecimal(number);	
		}catch(NumberFormatException e){
			
			throw new NumberFormatException("Invalid String ");
		}
			
		
		return compareToZero(inputNumber);
	}
	
	public static boolean isZeroOrLess(String number){
			int result =  compareToZero(number);
			return result==-1||result==0?true:false;
		
	}
	
	public static <T> T castObject(Object obj, Class<T> type){
		T t =null;
		
		try{
			t = type.cast(obj);
		}catch(ClassCastException e){
			/*LOG.error("Exception", e);*/
		}
		
		return t;
	}
	/**
	 * 
	 * @param number
	 * @return BigDecimal value of string, or 0 in case of error/ exception
	 */
	
	public static BigDecimal getBDFromString(String number){
		
		BigDecimal value = BigDecimal.valueOf(0.0);
		
		try{
		
			value = new BigDecimal(number);
		}catch(Exception e){
			/*LOG.error("Conversion ERROR: " ,e);*/
		}
		return value;
	}
	
	/**
	 * check if two Long objects are equal
	 * @param first
	 * @param second
	 * @return true if value of first is equal to second, false otherwise.
	 */
	
	public static boolean isEqual(Long first,  Long second){
		if(first==null && second == null){
			return true;
		}
		else if ((first==null && second !=null) || (first!=null && second ==null)){
			return false;
		}
		else if (first.compareTo(second)==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static ProjectLaunch getTowerWiseCCAvenueDetails(ProjectLaunch project,String towerCode)
	{
		

		JSONArray jsonArry;
		try {
			jsonArry = new JSONArray(project.getTower_mid_access_code_json());
			Log.info("Json Length:{}",jsonArry.length());
			for(int i=0;i<jsonArry.length();i++)
			{
				JSONObject objEx = jsonArry.getJSONObject(i);
				if(towerCode.trim().equals(objEx.get("towercode")))
				{
					project.setCcavenue_merchant_id(Integer.parseInt(objEx.get("merchant_id").toString()));
					//project.setCcavenue_merchant_id(218829);
					project.setCcavenue_workingkey(objEx.get("workingkey").toString());
					project.setCcavenue_accesscode(objEx.get("accesscode").toString());
				}
				//JSONObject objEx = new JSONObject(objNew.get(i));
				Log.info("Data:{}",objEx);
			}
		} catch (JSONException e) {
			Log.error("Json Error:-",e);
		}
		
	return project;
	}
	
}

