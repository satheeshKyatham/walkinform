package com.godrej.kycsync.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.godrej.kyc.connection.ConnectionClass;
import com.godrej.kyc.util.ConnectionUtility;
import com.godrej.kyc.util.DateFormateUtil;
import com.godrej.kycsync.model.ApplicantDate;
import com.godrej.kycsync.service.InsertUpdateService;

public class HerukoSyncController {

	public static void main(String[] args) throws InterruptedException {

		List<ApplicantDate> list = getPendingKYCSync();
		for(ApplicantDate apll : list)
		{
			InsertUpdateService service = new InsertUpdateService();
			if(apll.applicanttype.equals("1"))
			{
				System.out.println("Upldate Contact Method");
				service.updateContact(apll);
				
				service.documentStoragePan(apll,apll.getContactID());
				service.documentStorageAddressProf(apll,apll.getContactID());
				
				
				String doctStorageID= service.getStorageDocumentID("P"+apll.getExternalID()+apll.getApplicanttype());
				//Thread.sleep(10000);
				String doctStorageID2= service.getStorageDocumentID("A"+apll.getExternalID()+apll.getApplicanttype());
				//Thread.sleep(10000);
				TestAttachment attachment  = new TestAttachment();
				attachment.uploadDocumnetsPan(apll,doctStorageID2);
				////Thread.sleep(10000);
				attachment.uploadDocumnetsAddress1(apll,doctStorageID);
				//Thread.sleep(10000);
				
				if(apll.getAddress_doc_type().equals("Passport"))
				{
					attachment.uploadDocumnetsAddress2(apll,doctStorageID2);
				}
				service.updateSyncStatus(apll);
				
				//break;
			}
			else//appl.applicanttype
			{
				//String contactID = service.insertContact(apll);
				//service.documentStorage(apll,contactID);
				System.out.println("Create Contact Method");
				String contactID = service.insertContact(apll);
				System.out.println("Create Contact ID:-"+contactID);
				service.documentStoragePan(apll,contactID+apll.getApplicanttype());
				service.documentStorageAddressProf(apll,contactID+apll.getApplicanttype());
				
				
				String doctStorageID= service.getStorageDocumentID("P"+apll.getExternalID()+apll.getApplicanttype());
				String doctStorageID2= service.getStorageDocumentID("A"+apll.getExternalID()+apll.getApplicanttype());
				TestAttachment attachment  = new TestAttachment();
				attachment.uploadDocumnetsPan(apll,doctStorageID2);
				attachment.uploadDocumnetsAddress1(apll,doctStorageID);
				
				if(apll.getAddress_doc_type().equals("Passport"))
				{
					attachment.uploadDocumnetsAddress2(apll,doctStorageID2);
				}
				service.updateSyncStatus(apll);
				
				
			}
		}
	}
	
	
	public static List<ApplicantDate> getPendingKYCSync()
	{
		List<ApplicantDate> list = new ArrayList<ApplicantDate>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ConnectionClass connection = new ConnectionClass();
		
		StringBuffer query = new StringBuffer(" select appl.eoi_applicant_data_id,appl.enquiry_id,efm.contact18digitid,appl.applicanttype,appl.first_name,appl.email,appl.dob,appl.resident,appl.pan_no,(CASE WHEN appl.house_no is null then '.' else appl.house_no end) as house_no,appl.contact_address, ");
								query.append(" appl.city,appl.state,appl.country,appl.pincode,(CASE WHEN appl.p_house_no is null then '.' else appl.p_house_no end) as p_house_no,appl.permanent_address ,appl.phone_no,appl.permanent_address,appl.p_city ");
								query.append(" ,appl.p_state,appl.p_country,appl.p_pincode ");
								query.append(" ,att.enquiryid,att.pancard_path,att.panfilename,att.address_doc_type,att.address_doc_path,att.address_doc_path2,att.addressfilename,att.addressfilename2 ");
								query.append(" from nv_eoi_applicant_data appl ");
								query.append(" inner join nv_eoi_form efm on(appl.eoi_form_id=efm.id) inner join nv_enq_attach_path att on(appl.eoi_applicant_data_id = att.eoi_applicant_data_id) where appl.issync='N' and appl.isactive='Y' and appl.applicanttype not in ('1')");// and appl.eoi_applicant_data_id='74'// and appl.eoi_applicant_data_id='70'
		System.out.println("Pending KYC Sync:::::"+query);
		
		con =connection.getCon();
		try
		{
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ApplicantDate app = new ApplicantDate();
				app.setApplicantID(rs.getString("eoi_applicant_data_id"));
				app.setEnquiryID(rs.getString("enquiry_id"));
				app.setContactID(rs.getString("contact18digitid"));
				app.setApplicanttype(rs.getString("applicanttype"));
				app.setLastName(rs.getString("first_name"));
				app.setEmail(rs.getString("email"));
				
				if(rs.getString("dob")!=null)
				{
					String inputDOB = rs.getString("dob");
					DateFormateUtil mmddyyyy = new DateFormateUtil();
					String dob = mmddyyyy.get_d_M_y__M_d_ySlash(inputDOB);
					System.out.println("DOB : -"+dob);
					app.setDob(dob);
				}
				else
				{
					app.setDob("");
				}
					if(rs.getString("resident").contains("India"))
					{
						app.setResident("Domestic");
					}
					else
					{
						app.setResident(rs.getString("resident"));
					}
				
				app.setPan_no(rs.getString("pan_no"));
				//app.setMailing_Street1__c(rs.getString("house_no"));
				
				String strmailAddre = rs.getString("house_no")+rs.getString("contact_address").toString();
				int length = strmailAddre.length();
				String street2Contact_address="";
				String street1Contact_address="";
				String street3Contact_address="";
				
				/*if(length<=32)
				{
					street1Contact_address=strmailAddre.substring(0, length);;
				}
				if(length>32)
				{
					street1Contact_address=strmailAddre.substring(0, 32);
					if(length<=64)
					{
						street2Contact_address = strmailAddre.substring(33, 64);
						
					}
					if(length>64)
					{
						street3Contact_address = strmailAddre.substring(65, length);
					}
				}*/
				if(length<=32)
				{
					
					street1Contact_address=strmailAddre.substring(0, length);;
				}
				else if(length>32 && length<64)
				{
					
					street1Contact_address=strmailAddre.substring(0, 32);
					if(length<=64)
					{
						
						street2Contact_address = strmailAddre.substring(32, length);
					}
				}
				
				else if(length>64 && length<96)
				{
					
					street1Contact_address=strmailAddre.substring(0, 32);
					street2Contact_address = strmailAddre.substring(32, 64);
					street3Contact_address = strmailAddre.substring(64, length);
				}
				
				app.setMailing_Street1__c(street1Contact_address);
				app.setMailing_Street2__c(street2Contact_address);
				app.setMailing_Street3__c(street3Contact_address);
				app.setMailing_City__c(rs.getString("city"));
				app.setMailing_State__c(rs.getString("state"));
				app.setCountry__c(rs.getString("country"));
				app.setPostal_Code__c(rs.getString("pincode"));
				
				String strPermAddre = rs.getString("p_house_no")+rs.getString("permanent_address").toString();
				
				int lengthPerm = strPermAddre.length();
				String street2Permanent_address ="";
				String street1Permanent_address ="";
				String street3Permanent_address="";
				
				if(lengthPerm<=32)
				{
					
					street1Permanent_address=strPermAddre.substring(0, lengthPerm);;
				}
				else if(lengthPerm>32 && lengthPerm<64)
				{
					
					street1Permanent_address=strPermAddre.substring(0, 32);
					if(lengthPerm<=64)
					{
						
						street2Permanent_address = strPermAddre.substring(32, lengthPerm);
					}
				}
				
				else if(lengthPerm>64 && lengthPerm<96)
				{
					
					street1Permanent_address=strPermAddre.substring(0, 32);
					street2Permanent_address = strPermAddre.substring(32, 64);
					street3Permanent_address = strPermAddre.substring(64, lengthPerm);
				}
				
				/*if(lengthPerm<=32)
				{
					street1Permanent_address=strOutPerm.substring(0, lengthPerm);;
				}
				
				if(lengthPerm>32)
				{
					street1Permanent_address=strOutPerm.substring(0, 32);
					if(lengthPerm<=64)
					{
						street2Permanent_address = strOutPerm.substring(33, 64);
						
					}
					if(lengthPerm>64)
						street3Permanent_address = strOutPerm.substring(65, lengthPerm);
				}*/
				
				
				app.setResidential_Street1__c(street1Permanent_address);
				app.setResidential_Street2__c(street2Permanent_address);
				app.setResidential_Street2__c(street3Permanent_address);
				app.setResidential_City__c(rs.getString("p_city"));
				app.setResidential_State__c(rs.getString("p_state"));
				app.setResidential_Country__c(rs.getString("p_country"));
				app.setResidential_Post_Code__c(rs.getString("p_pincode"));
				app.setPancard_path(rs.getString("pancard_path"));
				app.setPanfilename(rs.getString("panfilename"));
				app.setAddress_doc_type(rs.getString("address_doc_type"));
				app.setAddress_doc_path1(rs.getString("address_doc_path"));
				app.setAddress_doc_path2(rs.getString("address_doc_path2"));
				app.setAddressfilename1(rs.getString("addressfilename"));
				app.setAddressfilename2(rs.getString("addressfilename2"));
				String str = rs.getString("enquiry_id");
				String s = str.split("\\-")[1];
				System.out.println("System:-"+s.trim());
				
				
				app.setExternalID(rs.getString("eoi_applicant_data_id"));
				list.add(app);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ConnectionUtility.closeConnection(rs, pstmt, con);
		}
		return list;	
	}

}
