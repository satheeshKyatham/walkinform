package com.godrej.kycsync.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.godrej.kycsync.model.ApplicantDate;
import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.Error;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Attachment;
import com.sforce.ws.ConnectorConfig;//
public class TestAttachment {
	
//	static final String USERNAME = "sachin_more@magicsoftware.com";    
//	static final String PASSWORD = "Godrej@2018N96qqGVtAstcSut9a0dhqF2lo"; 
	
	static final String USERNAME = "external.app@godrejproperties.com";    
	static final String PASSWORD = "Nov@12345Svgw3cZob0A7HWlJs8I07Jcdb"; 
	
	static EnterpriseConnection connection;    
	public static void main(String[] args) {        
		        
		//String path = "D:\\Projects\\KYC\\3200000813_0010026321.pdf";        
		try {                      
			//byte[] data = loadFileAsByteArray(path);                       
			ConnectorConfig config = new ConnectorConfig();            
			config.setUsername(USERNAME);            
			config.setPassword(PASSWORD);            
			connection = Connector.newConnection(config);
			File f = new File("D:\\Projects\\KYC\\3200000813_0010026321.pdf");
	        InputStream is = new FileInputStream(f);
	        byte[] inbuff = new byte[(int)f.length()];        
	        is.read(inbuff);

	        Attachment attach = new Attachment();
	        attach.setBody(inbuff);
	        attach.setName("Test Receipt.pdf");
	        attach.setIsPrivate(false);
	        attach.setParentId("a3qO0000000Z21N");

	        SaveResult saveResults = connection.create(new com.sforce.soap.enterprise.sobject.SObject[] {attach})[0];
	        if (saveResults.isSuccess()) {
	            System.out.println("Successfully added attachment.");
	        } else {
	            System.out.println("Error adding attachment: " + saveResults.getErrors());
	        }
				if (saveResults.isSuccess()) {                    
					System.out.println(". Successfully created record - Id: "                            + saveResults.getId());                
					} else {                    Error[] errors = saveResults.getErrors();                    
					for (int j = 0; j < errors.length; j++) {                        
						System.out.println("ERROR creating record: "                                + errors[j].getMessage()); 
						}                
					}            //}        
			} catch (Exception e) {            
				e.printStackTrace();        
			}    
		}
	
	
	public void uploadDocumnetsPan(ApplicantDate app,String doctStorageID)
	{
		
		try {                      
			ConnectorConfig config = new ConnectorConfig();            
			config.setUsername(USERNAME);            
			config.setPassword(PASSWORD);            
			connection = Connector.newConnection(config);
			File f = new File(app.getPancard_path());
	        InputStream is = new FileInputStream(f);
	        byte[] inbuff = new byte[(int)f.length()];        
	        is.read(inbuff);

	        Attachment attach = new Attachment();
	        attach.setBody(inbuff);
	        attach.setName(app.getPanfilename());
	        attach.setIsPrivate(false);
	        attach.setParentId(doctStorageID);

	        SaveResult saveResults = connection.create(new com.sforce.soap.enterprise.sobject.SObject[] {attach})[0];
	        if (saveResults.isSuccess()) {
	            System.out.println("Successfully added attachment.");
	        } else {
	            System.out.println("Error adding attachment: " + saveResults.getErrors());
	        }
				if (saveResults.isSuccess()) {                    
					System.out.println(". Successfully created record - Id: "                            + saveResults.getId());                
					} else {                    Error[] errors = saveResults.getErrors();                    
					for (int j = 0; j < errors.length; j++) {                        
						System.out.println("ERROR creating record: "                                + errors[j].getMessage()); 
						}                
					}            //}        
			} catch (Exception e) {            
				e.printStackTrace();        
			} 
	}
	public void uploadDocumnetsAddress1(ApplicantDate app,String doctStorageID)
	{
		
		try {                      
			ConnectorConfig config = new ConnectorConfig();            
			config.setUsername(USERNAME);            
			config.setPassword(PASSWORD);            
			connection = Connector.newConnection(config);
			File f = new File(app.getAddress_doc_path1());
	        InputStream is = new FileInputStream(f);
	        byte[] inbuff = new byte[(int)f.length()];        
	        is.read(inbuff);

	        Attachment attach = new Attachment();
	        attach.setBody(inbuff);
	        attach.setName(app.getAddressfilename1());
	        attach.setIsPrivate(false);
	        attach.setParentId(doctStorageID);

	        SaveResult saveResults = connection.create(new com.sforce.soap.enterprise.sobject.SObject[] {attach})[0];
	        if (saveResults.isSuccess()) {
	            System.out.println("Successfully added attachment.");
	        } else {
	            System.out.println("Error adding attachment: " + saveResults.getErrors());
	        }
				if (saveResults.isSuccess()) {                    
					System.out.println(". Successfully created record - Id: "                            + saveResults.getId());                
					} else {                    Error[] errors = saveResults.getErrors();                    
					for (int j = 0; j < errors.length; j++) {                        
						System.out.println("ERROR creating record: "                                + errors[j].getMessage()); 
						}                
					}            //}        
			} catch (Exception e) {            
				e.printStackTrace();        
			} 
	}
	
	public void uploadDocumnetsAddress2(ApplicantDate app,String doctStorageID)
	{
		
		try {                      
			ConnectorConfig config = new ConnectorConfig();            
			config.setUsername(USERNAME);            
			config.setPassword(PASSWORD);            
			connection = Connector.newConnection(config);
			File f = new File(app.getAddress_doc_path2());
	        InputStream is = new FileInputStream(f);
	        byte[] inbuff = new byte[(int)f.length()];        
	        is.read(inbuff);

	        Attachment attach = new Attachment();
	        attach.setBody(inbuff);
	        attach.setName(app.getAddressfilename2());
	        attach.setIsPrivate(false);
	        attach.setParentId(doctStorageID);

	        SaveResult saveResults = connection.create(new com.sforce.soap.enterprise.sobject.SObject[] {attach})[0];
	        if (saveResults.isSuccess()) {
	            System.out.println("Successfully added attachment.");
	        } else {
	            System.out.println("Error adding attachment: " + saveResults.getErrors());
	        }
				if (saveResults.isSuccess()) {                    
					System.out.println(". Successfully created record - Id: "                            + saveResults.getId());                
					} else {                    Error[] errors = saveResults.getErrors();                    
					for (int j = 0; j < errors.length; j++) {                        
						System.out.println("ERROR creating record: "                                + errors[j].getMessage()); 
						}                
					}            //}        
			} catch (Exception e) {            
				e.printStackTrace();        
			} 
	}
	
	
	
	private static byte[] loadFileAsByteArray(String path) throws IOException { 
		File file = new File(path);        
		InputStream is = new FileInputStream(file);        
		byte[] data = new byte[(int) file.length()];        is.read(data);        return data;    
		}

}
