package com.godrej.kycsync.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.godrej.kyc.connection.ConnectionClass;
import com.godrej.kyc.connection.ConnectionClassHeruko;
import com.godrej.kyc.util.ConnectionUtility;
import com.godrej.kycsync.model.ApplicantDate;

public class InsertUpdateService {
	

	
	public void documentStoragePan(ApplicantDate app,String contactID)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ConnectionClassHeruko connection = new ConnectionClassHeruko();
		StringBuffer query = new StringBuffer("INSERT INTO salesforce.document_storage__c (document_category__c,document_status__c,Document_Type__c,contact__c,KYCID__c)");
								//query.append("Values('KYC Documents','Sent for Approval','PAN Card','"+contactID+"','"+app.getExternalID()+"');");
		query.append("Values('KYC Documents','Sent for Approval','Address Proof','"+contactID+"','KYCP"+app.getExternalID()+app.getApplicanttype()+"');");
		
		con =connection.getCon();
		System.out.println("Pending KYC Document Inser Query Sync:::::"+query);
		try
		{
			pstmt = con.prepareStatement(query.toString());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ConnectionUtility.closeConnection(null, pstmt, con);
			
		}
	}
	public void documentStorageAddressProf(ApplicantDate app,String contactID)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ConnectionClassHeruko connection = new ConnectionClassHeruko();
		StringBuffer query = new StringBuffer("INSERT INTO salesforce.document_storage__c (document_category__c,document_status__c,Document_Type__c,contact__c,KYCID__c)");
								//query.append("Values('KYC Documents','Sent for Approval','PAN Card','"+contactID+"','"+app.getExternalID()+"');");
		query.append("Values('KYC Documents','Sent for Approval','PAN Card','"+contactID+"','KYCA"+app.getExternalID()+app.getApplicanttype()+"');");
		
		con =connection.getCon();
		System.out.println("Pending KYC Document Inser Query Sync:::::"+query);
		try
		{
			pstmt = con.prepareStatement(query.toString());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ConnectionUtility.closeConnection(null, pstmt, con);
			
		}
	}
	
	
	public void updateSyncStatus(ApplicantDate app)
	{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ConnectionClass connection = new ConnectionClass();
		StringBuffer query = new StringBuffer(" update nv_eoi_applicant_data set issync='Y',syncdate=now() where eoi_applicant_data_id="+app.getApplicantID());
		
		con =connection.getCon();
		System.out.println("Pending KYC Sync Update Query Sync:::::"+query);
		try
		{
			pstmt = con.prepareStatement(query.toString());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public String getStorageDocumentID(String externalID)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		ConnectionClassHeruko connection = new ConnectionClassHeruko();
		StringBuffer query = new StringBuffer("select sfid from salesforce.document_storage__c where KYCID__c ='"+externalID+"'");
		
		con =connection.getCon();
		System.out.println("Pending KYC GET Document Storage ID Query Sync:::::"+query);
		String storageID="";
		try
		{
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				storageID = rs.getString("sfid");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ConnectionUtility.closeConnection(null, pstmt, con);
		}
	
		
		return storageID;
	}
	
	public String updateContact(ApplicantDate app)
	{
		//StringBuffer query = new StringBuffer(" update salesforce.contact set lastname="+app.getLastName()+",birthday__c="+app.getDob()+",recordtypeid='012O00000000kxqIAA' where sfid='"+app.getContactID()+"'");
		
		StringBuffer query = new StringBuffer(" update salesforce.contact set firstname='.',lastname='"+app.getLastName()+"',birthday__c='"+app.getDob()+"',email='"+app.getEmail()+"',"
						        + " Nationality_a__c='"+app.getResident()+"',propstrength__income_tax_permanent_account_no__c='"+app.getPan_no()+"',"
								+ "Mailing_Street1__c='"+app.getMailing_Street1__c()+"',Mailing_Street2__c='"+app.getMailing_Street2__c()+"',Mailing_Street3__c='"+app.getMailing_Street3__c()+"',"
								+ " Mailing_City__c='"+app.getMailing_City__c()+"',Mailing_State__c='"+app.getMailing_State__c()+"',Country__c='"+app.getCountry__c()+"',Postal_Code__c='"+app.getPostal_Code__c()+"',"
								+ "Residential_Street1__c='"+app.getResidential_Street1__c()+"' , Residential_Street2__c='"+app.getResidential_Street2__c()+"' ,Residential_Street3__c='"+app.getMailing_Street3__c()+"', "
								+ " Residential_State__c='"+app.getResidential_State__c()+"', Residential_City__c='"+app.getResidential_City__c()+"',Residential_Country__c='"+app.getResidential_Country__c()+"',Residential_Post_Code__c='"+app.getResidential_Post_Code__c()+"'"
								+ ",recordtypeid='01290000000uHV8AAM',Customer_Group__c='Indian',Service_Category_Profession__c='Service',Purchase_Reason__c='Self-Use',Buying_Pattern__c='Self-Investment',Legal_status__c='Individual',PropStrength__Customer_Classification__c='Standard/External',Industry_Code_1_ASM_Territory__c='Others' where sfid='"+app.getContactID()+"'");
				//01290000000uHV8AAM - Prd - RecordtypeID
		//012O00000000kxpIAA - Sand - RecordTypeID
								
		System.out.println("Pending KYC Update Query Sync:::::"+query);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ConnectionClassHeruko connection = new ConnectionClassHeruko();
		
		con =connection.getCon();
		try
		{
			pstmt = con.prepareStatement(query.toString());
			pstmt.executeUpdate();
			//rs = pstmt.executeQuery();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ConnectionUtility.closeConnection(null, pstmt, con);
			//storage
		}
		return "";
	}
	public String insertContact(ApplicantDate app)
	{
		String contactID="";
		Connection con = null;
		PreparedStatement pstmt = null;
		//System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		ConnectionClassHeruko connection = new ConnectionClassHeruko();
		
		//StringBuffer query = new StringBuffer(" update salesforce.contact set lastname="+app.getLastName()+",birthday__c="+app.getDob()+",recordtypeid='012O00000000kxqIAA' where sfid='"+app.getContactID()+"'");
		con =connection.getCon();
		StringBuffer query = new StringBuffer("INSERT INTO salesforce.contact (KYCID__c,firstname,lastname,birthday__c,email,Nationality_a__c,propstrength__income_tax_permanent_account_no__c,");
		                 query.append("Mailing_Street1__c,Mailing_Street2__c,Mailing_Street3__c,Mailing_City__c,Mailing_State__c,Country__c,Postal_Code__c,");
		                 query.append("Residential_Street1__c, Residential_Street2__c,Residential_Street3__c,Residential_State__c,Residential_City__c,Residential_Country__c,Residential_Post_Code__c,recordtypeid, ");
		                 query.append("Customer_Group__c,Service_Category_Profession__c,Purchase_Reason__c,Buying_Pattern__c,Legal_status__c,PropStrength__Customer_Classification__c,Industry_Code_1_ASM_Territory__c) ");
						
		                 query.append("Values('"+app.getExternalID()+"','.','"+app.getLastName()+"','"+app.getDob()+"','"+app.getEmail()+"','"+app.getResident()+"','"+app.getPan_no()+"'"
		                 		+ ",'"+app.getMailing_Street1__c()+"','"+app.getMailing_Street2__c()+"','"+app.getMailing_Street3__c()+"','"+app.getMailing_City__c()+"','"+app.getMailing_State__c()+"','"+app.getCountry__c()+"','"+app.getPostal_Code__c()+"'"
		                 		+ ",'"+app.getResidential_Street1__c()+"','"+app.getResidential_Street2__c()+"','"+app.getResidential_Street3__c()+"','"+app.getResidential_State__c()+"','"+app.getResidential_City__c()+"','"+app.getResidential_Country__c()+"','"+app.getResidential_Post_Code__c()+"','01290000000uHV8AAM'"
		                 		+ ",'Indian','Service','Self-Use','Self-Investment','Individual','Standard/External','Others') ");
		                 
		System.out.println("Pending KYC Update Query Sync:::::"+query);
		
		
		try
		{
			pstmt = con.prepareStatement(query.toString());
			pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ConnectionUtility.closeConnection(null, pstmt, con);
			//Get Created ContactID
			contactID = getContactID(app.getExternalID());
			//update contactNumber
			getUpdateContactID(contactID,app);
			
		}
		return contactID;
		
	}
	public void getUpdateContactID(String contactID,ApplicantDate app) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ConnectionClass connection = new ConnectionClass();
		StringBuffer query = new StringBuffer(" update nv_eoi_applicant_data set contactid='"+contactID+"' where eoi_applicant_data_id="+app.getApplicantID());
		
		con =connection.getCon();
		System.out.println("Pending KYC Coapplicant ContactID Update Query Sync:::::"+query);
		try
		{
			pstmt = con.prepareStatement(query.toString());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getContactID(String externalID)
	{

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		ConnectionClassHeruko connection = new ConnectionClassHeruko();
		StringBuffer query = new StringBuffer("select sfid from salesforce.contact where KYCID__c ='"+externalID+"'");
		
		con =connection.getCon();
		System.out.println(" Inserted KYC GET Contact ID Query Sync:::::"+query);
		String contactID="";
		try
		{
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				contactID = rs.getString("sfid");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ConnectionUtility.closeConnection(null, pstmt, con);
		}
	
		
		return contactID;
	
	}
	

}
