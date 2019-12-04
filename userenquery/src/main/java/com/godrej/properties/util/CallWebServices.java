package com.godrej.properties.util;
 

 
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;
 
 
public class CallWebServices    {
 
	private List<RequestProperty> properties = new ArrayList<RequestProperty>() ;
	private String wsURL, wsName, methodName, wsPort, wsHttp, wsPorg, wsIsSSL, wsServer,NAMESPACE;
	private boolean error = false;
	private PostExecute postExecute;
	private OnErrorExecute onErrorExecute;
	 
	
	public CallWebServices(String wsName, String methodName) {
	 
		this.wsName = wsName;
		this.methodName = methodName;
		//https://mobi.godrejite.com:4040/WebService/MobileService.asmx?wsdl
		//https://10.2.4.32:9966/WebService/MobileService.asmx?wsdl
		  // Production
 	    wsPort = "443";
		wsHttp = "https://mobi.gplapps.com";
		wsPorg = "/WebService/";
		wsIsSSL = "Y";
		wsServer = "mobi.gplapps.com";
		NAMESPACE = "http://tempuri.org/";
		wsURL = this.wsHttp + ":" + this.wsPort + this.wsPorg + this.wsName;    
 
		//Quality 
	 
		/*  wsPort = "9966";
		 wsHttp = "http://10.2.4.32";
		 wsPorg = "/WebService/";
		 wsIsSSL = "N";
		 wsServer = "10.2.4.32";
		 NAMESPACE="http://tempuri.org/";
		 wsURL = this.wsHttp + ":" + this.wsPort + this.wsPorg + this.wsName;   */
		 
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	 
	public void setPostExecute(PostExecute postExecute) {
		this.postExecute = postExecute;
	}
	
	public List<RequestProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<RequestProperty> properties) {
		this.properties = properties;
	}
	
	public OnErrorExecute getOnErrorExecute() {
		return onErrorExecute;
	}

	public void setOnErrorExecute(OnErrorExecute onErrorExecute) {
		this.onErrorExecute = onErrorExecute;
	}

 
	protected Object doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		return CallWS();
	}
	
	
 
	protected void onPostExecute(Object resultString) {
		try {
			if(!error){
				if(postExecute != null){
					postExecute.OnPost(resultString);
					 
				}	
			}
			else{
				if(onErrorExecute != null){
		        	onErrorExecute.OnError();
		        	 
	        	}
			}
		} catch (Exception ex) {
			System.out.println("Error onPostExecute: " + ex.getMessage());
			 
		}
	}
	protected void onProgressUpdate(String... progress) {
		// setting progress percentage
      //  pDialog.setProgress(Integer.parseInt(progress[0]));
   }
 
	public Object CallWS() {
		try {
			error = false;
			String SOAP_ACTION = NAMESPACE + methodName;
			SoapObject Request = new SoapObject(NAMESPACE, methodName);
		 
			for (RequestProperty  property: properties) {
				Request.addProperty(property.getName(), property.getValue());
			}
		 	SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.setOutputSoapObject(Request);
			
			if(wsIsSSL.equals("Y")){
				Certificate.allowAllSSL();
				HttpsTransportSE transport = new HttpsTransportSE(wsServer, Integer.valueOf(wsPort), wsPorg+wsName, 40000);
				transport.call(SOAP_ACTION, soapEnvelope);
			}
			else{
				HttpTransportSE transport = new HttpTransportSE(wsURL);
				transport.call(SOAP_ACTION, soapEnvelope);
			}
			
			return (Object) soapEnvelope.getResponse();
			
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			error = true;
			return null;
			
		}
		
	}

	public class PostExecute{
		public void OnPost(Object resultString){
			 
			
		}
	}	
 
 
	public class OnErrorExecute{
		public void OnError(){ System.out.println("Error In Connection");}
		
		
	}

	public class RequestProperty{
		String name;
		String value;
		
		public RequestProperty(String name, String value){
			setName(name);
			setValue(value);
		}
		
		public RequestProperty(String name, int value){
			setName(name);
			setValue(String.valueOf(value));
		}
		public RequestProperty(String name, long value) {
			setName(name);
			setValue(String.valueOf(value));
		}
		public RequestProperty(String name, DateTime value) {
			setName(name);
			setValue(String.valueOf(value));
		}
	 
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	 
}
