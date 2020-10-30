package com.godrej.properties.serviceimpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.ContactReportDao;
import com.godrej.properties.dao.EOIPaymentDtlDao;
import com.godrej.properties.dao.EnquiryReportDao;
import com.godrej.properties.dao.TokenDao;
import com.godrej.properties.dao.TowerMasterDao;
import com.godrej.properties.dao.UserContactDao;
import com.godrej.properties.dto.GPLAppBookingAPIDto;
import com.godrej.properties.dto.ProjectLaunchDao;
import com.godrej.properties.dto.SysConfigEnum;
import com.godrej.properties.master.service.SysConfigService;
import com.godrej.properties.model.ContactReport;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.EOIPreferenceDtl;
import com.godrej.properties.model.EnquiryReport;
import com.godrej.properties.model.ProjectLaunch;
import com.godrej.properties.model.Token;
import com.godrej.properties.model.TowerMaster;
import com.godrej.properties.service.EOIPreferenceDtlService;
import com.godrej.properties.service.GPLAppsWebService;
import com.godrej.properties.service.InventoryService;
import com.godrej.properties.service.PropOtherChargesService;
import com.godrej.properties.util.SendMailThreadUtil;
import com.godrej.properties.webservices.DrupalInventoryStatusUpdate;

/**
 * @author sathish.kyatham
 *
 */
@Service("gplAppsWebServiceImpl")
//@Transactional
public class GPLAppsWebServiceImpl implements GPLAppsWebService{
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	TokenDao tokenDao;
	
	@Autowired
	ProjectLaunchDao projectLaunchDao;
	
	@Autowired
	private EOIPreferenceDtlService eOIPreferenceDtlService;
	
	@Autowired
	private EOIPaymentDtlDao eOIPaymentDtlDao;
	@Autowired
	private UserContactDao userContactDao;
	
	@Autowired
	private ContactReportDao contactReportDao;
	
	@Autowired
	private EnquiryReportDao enquiryReportDao;
	
	@Autowired
 	private InventoryService inventoryService;
	
	@Autowired
	DrupalInventoryStatusUpdate drupalInventoryStatusUpdate;
	
	@Autowired
	TowerMasterDao towerMasterDao;
	
	@Autowired
	PropOtherChargesService propOtherChargesService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Override
	public GPLAppBookingAPIDto insertGPLBookingData(GPLAppBookingAPIDto bookingData) {
		log.info("insertGPLBookingData......{}",bookingData.getEnquiryName());
		String updateData="";
		try
		{
			//Call Enquiry SFID to get enquiry PK ID
			int enquiryPKID = tokenDao.getEnquiryIDFromSFID(bookingData.getEnquirySfid());
			int contcatPKID = userContactDao.getContactPKID(bookingData.getContactSfid());
			if(enquiryPKID==0 || contcatPKID==0)
			{
				bookingData.setResp_mesg("No Enquiry/Contact Found OR Somthing Went Wrong");
				return bookingData;
			}
			else
			{
				bookingData.setEnquiryid(enquiryPKID);
				bookingData.setContactid(contcatPKID);
			
			
				//Site HEad ID and Email pick from Nv_project table
				ProjectLaunch projectData =projectLaunchDao.getProjectSaleMgrID(bookingData.getProjectsfid());
				if(projectData.getSitehead_user_id()!=null)
				{
					//bookingData.setWindow_Assign(projectData.getSitehead_user_id().toString());
					bookingData.setSiteheadEmail(projectData.getSitehead_user_mail());
					bookingData.setSiteheadID(projectData.getSitehead_user_id());
					bookingData.setSiteheadName(projectData.getSitehead_user_name());
				}
				else
				{
					//bookingData.setWindow_Assign("594");
					bookingData.setSiteheadEmail("sathish.kyatham@godrejproperties.com");
					bookingData.setSiteheadID(594);
					bookingData.setSiteheadName("Sathish Kyatham");
				}
				
				//Contact Report & Enquiry Report Data Insert
				ContactReport contactRepprt = insertContactReport(bookingData);
				EnquiryReport enquiryReport = insertEnquiryReport(bookingData);
				//Insert NV_Token table with site head id
				 
				//if(eoiJourneyFlag==True && bookingJourneyFlag==False)
				TowerMaster towerMaster =null;
				String propertyName="";
				if(bookingData.getEoiJourneyFlag().equals("T") && bookingData.getBookingJourneyFlag().equals("F"))
				{
					//if(paymentStatus=="Success")
					if(bookingData.getPaymentStatus().equals("Success"))
					{
						bookingData.setNv_token_type("MS");
						Token token = insertIntoTokenTable(bookingData);
						bookingData.setNv_token_type(token.getType()+token.getQueue());
						bookingData.setNv_tokenno(token.getQueue());
						//Call Tower Data
						
						if(bookingData.getTowersfid()!=null && bookingData.getTowersfid().length()>0)
						{
							towerMaster= towerMasterDao.getTowerMasterDetails(bookingData.getTowersfid());
							bookingData.setTowersfid(towerMaster.getSfid());
							bookingData.setTowername(towerMaster.getTower_name__c());
						}
						//Call Unit Data
						
						if(bookingData.getPropertysfid()!=null && bookingData.getPropertysfid().length()>0)
						{
							propertyName= propOtherChargesService.getPropertyName(bookingData.getPropertysfid());
							bookingData.setPropertysfid(bookingData.getPropertysfid());
							bookingData.setPropertyname(propertyName);
						}
						bookingData = insertEOIPreference(bookingData);
						
						bookingData = insertPaymentDtl(bookingData);
						
						//update query for EOI status Update in Enquiry
						updateData=" PropStrength__Request_Status__c='EOI', EOI_Enquiry__c='true',date_of_eoi__c='"+new Date()+"' ";
						//bookingData.setTokenType("F");
						//bookingData.setTokenName("REFUNDABLE");
						
						//bookingData = insertIntoHoldUnit(bookingData);
						
					}
					else if(bookingData.getPaymentStatus().equals("Fail"))//else(paymentStatus=="Fail")
					{
						bookingData.setNv_token_type("MF");
						
						Token token = insertIntoTokenTable(bookingData);
						bookingData.setNv_token_type(token.getType()+token.getQueue());
						bookingData.setNv_tokenno(token.getQueue());
						bookingData.setResp_mesg(bookingData.getResp_mesg());
						updateData=" PropStrength__Request_Status__c='Assigned to Sales', User__c='"+projectData.getSalesmanager_sfid()+"' ";//site head
						//update query for Enquiry Status to Assigned to sales in Update in Enquiry
					}
					
				}
				else if(bookingData.getEoiJourneyFlag().equals("F") && bookingData.getBookingJourneyFlag().equals("T"))//else(eoiJourneyFlag==False && bookingJourneyFlag==True)
				{
					//if(paymentStatus=="Success")
					if(bookingData.getPaymentStatus().equals("Success"))
					{
						bookingData.setNv_token_type("MS");
						Token token = insertIntoTokenTable(bookingData);
						bookingData.setResp_mesg(bookingData.getResp_mesg());
						bookingData.setNv_token_type(token.getType()+token.getQueue());
						bookingData.setNv_tokenno(token.getQueue());
						//Call Tower Data
						if(bookingData.getTowersfid()!=null)
						{
							towerMaster= towerMasterDao.getTowerMasterDetails(bookingData.getTowersfid());
							bookingData.setTowersfid(towerMaster.getSfid());
							bookingData.setTowername(towerMaster.getTower_name__c());
						}
						//Call Unit Data
						
						if(bookingData.getPropertysfid()!=null)
						{
							propertyName= propOtherChargesService.getPropertyName(bookingData.getPropertysfid());
							bookingData.setPropertysfid(bookingData.getPropertysfid());
							bookingData.setPropertyname(propertyName);
						}
						
						bookingData = insertEOIPreference(bookingData);
						
						bookingData = insertPaymentDtl(bookingData);
						
						updateData=" PropStrength__Request_Status__c='Initiate Offer' ";
						bookingData.setTokenType("F");
						bookingData.setTokenName("REFUNDABLE");
						
						bookingData = insertIntoHoldUnit(bookingData);
						
					}
					else if(bookingData.getPaymentStatus().equals("Fail"))//else(paymentStatus=="Fail")
					{
						bookingData.setNv_token_type("MF");
						Token token = insertIntoTokenTable(bookingData);
						bookingData.setNv_token_type(token.getType()+token.getQueue());
						bookingData.setNv_tokenno(token.getQueue());
						bookingData.setResp_mesg(bookingData.getResp_mesg());
						updateData=" PropStrength__Request_Status__c='Assigned to Sales', User__c='"+projectData.getSalesmanager_sfid()+"' ";
					}
				}
				
				tokenDao.updateEnquiryData(enquiryPKID, updateData+",External_Contact_ID__c="+contcatPKID+", NVHC_Enquiry_ID__c="+enquiryReport.getEnquiryReportId());//+",External_Contact_ID__c="+contcatPKID
				tokenDao.updateContactData(contcatPKID, "External_Contact_ID__c="+contactRepprt.getContactReportId()+"");
				
				//Update contact External_Contact_ID__c
				
				
//				String text = readContentFromFile("D://SW//apache-tomcat-9.0.0.M22//apache-tomcat-9.0.0.M22//htmldoc//d4u-prebookingapi-email.htm");
				String text = readContentFromFile("D://D Drive//SW//Tomcat Server 8085//apache-tomcat-9.0.12//htmldoc//d4u-prebookingapi-email.htm");
				text = text.replaceAll("@SiteHead@", bookingData.getSiteheadName());
				text = text.replaceAll("@CustomerName@", bookingData.getContactName());
				text = text.replaceAll("@CustomerMobile@", bookingData.getCountryCode()+bookingData.getMobileno());
				text = text.replaceAll("@Tower@", bookingData.getTowersfid());
				text = text.replaceAll("@InventoryNo@", bookingData.getPropertysfid());
				text = text.replaceAll("@PaymentStatus@", bookingData.getPaymentStatus());
				text = text.replaceAll("@TokenNo@", bookingData.getNv_token_type());
				
				/*String emailTempl = "Please find herewith the <a href=\"@Link@\">link</a> to update your KYC details. </br></br>Regards</br>Godrej Properties";
				emailTempl = emailTempl.replaceAll("@Link@", kyclink);*/
				String projectName = bookingData.getProjectName()+" : EOI received through Mobile App ";
				/*if(projectData.getSitehead_user_mail()!=null && projectData.getSitehead_user_mail().length()>0) {
					
					SendMailThreadUtil mail =new SendMailThreadUtil("sathish.kyatham@godrejproperties.com",	projectData.getSitehead_user_mail(), projectName, text);
					
				} */
				
				String smtpip = sysConfigService.getValue(SysConfigEnum.SMTP_IP, "SMTP_IP");
				String smtpPort = sysConfigService.getValue(SysConfigEnum.SMTP_PORT, "SMTP_PORT");
				SendMailThreadUtil mail =new SendMailThreadUtil(projectData.getSitehead_user_mail(),"", projectName, text,smtpip,smtpPort);		
			}
		}
		catch (Exception e) {
			log.error("Somthing went wrong.....{}",e);
			bookingData.setResp_mesg(bookingData.getResp_mesg()+" & Somthing went wrong.....");
		}
		return bookingData;
	}
	
	
	public GPLAppBookingAPIDto insertEOIPreference(GPLAppBookingAPIDto bookingData)
	{
		List<EOIPreferenceDtl> chargesList=new ArrayList<>();
		try
		{
		EOIPreferenceDtl eoiPref = new EOIPreferenceDtl();
		eoiPref.setEnq_sfid(bookingData.getEnquirySfid());
		eoiPref.setRequest_id(bookingData.getEnquiryid());
		eoiPref.setProject_name(bookingData.getProjectName());
		eoiPref.setProject_sfid(bookingData.getProjectsfid());
		eoiPref.setUser_email(bookingData.getSiteheadEmail());
		eoiPref.setUser_name(bookingData.getSiteheadName());
		eoiPref.setUserid(bookingData.getSiteheadID());
		eoiPref.setTower_id(bookingData.getTowersfid());
		//eoiPref.setTower_id("All");
		eoiPref.setTower_name(bookingData.getTowername());
		eoiPref.setTypology_id(bookingData.getTypology());
		eoiPref.setTypology_name(bookingData.getTypology());
		eoiPref.setUnit_id(bookingData.getPropertysfid());
		eoiPref.setUnit_name(bookingData.getPropertyname());
		eoiPref.setFloor_band(bookingData.getFloorband());
		eoiPref.setDescription("Tower Name:"+bookingData.getTowername()+",Floor Band:-"+bookingData.getFloorband()+",Property SFID:-"+bookingData.getPropertysfid());
		eoiPref.setCreatedby(bookingData.getSiteheadID().toString());
		eoiPref.setUpdatedby(bookingData.getSiteheadID().toString());
		eoiPref.setIsactive("Y");
		eoiPref.setToken_no(bookingData.getNv_tokenno());
		eoiPref.setEoi_date_string("");
		eoiPref.setUserid(bookingData.getSiteheadID());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Date eoi_date;
		try {
			eoi_date = df.parse(bookingData.getTransactionDate());
			eoiPref.setEoi_date(eoi_date);
		} catch (ParseException e) {
			log.error(" Error ParseException :-{}",e);
		}
		
		eoiPref.setTokenTypeId(1);
		eoiPref.setTokenTypeName("REFUNDABLE");
		chargesList.add(eoiPref);
		eOIPreferenceDtlService.insertEOI(chargesList);
		}
		catch (Exception e) {
			bookingData.setResp_mesg(bookingData.getResp_mesg()+" & EOI insertEOIPreference Issue");
			log.error(" Error insertEOIPreference :-{}",e);
		}
		return bookingData;
	}
	//Insert Into Payment Table
	public GPLAppBookingAPIDto insertPaymentDtl(GPLAppBookingAPIDto bookingData)
	{
		try
		{
		EOIPaymentDtl pymtDtl = new EOIPaymentDtl();
		pymtDtl.setEnq_sfid(bookingData.getEnquirySfid());
		pymtDtl.setPayment_type(bookingData.getPaymentType());
		pymtDtl.setBank_name(bookingData.getBankName());
		pymtDtl.setUserid(bookingData.getSiteheadID());
		String respAmount="";
		if(bookingData.getAmount()!=null)
		{
			respAmount=bookingData.getAmount().toString();
		}
		else
			respAmount="0.00";
		pymtDtl.setTransaction_amount(respAmount);
		pymtDtl.setTransaction_date(bookingData.getTransactionDate());
		pymtDtl.setTransaction_id(bookingData.getTransactionID());
		String description="DrupalBookingID:"+bookingData.getDrupal_booking_id()+",Comments:"+bookingData.getComments()+","+bookingData.getPaymentDescription()+",agreementamount_a_total:"+bookingData.getAgreementamount_a_total()+","
				+ "Otherchargesamount:"+bookingData.getOtherchargesamount_b_total()+",taxes_sdr_c_total:"+bookingData.getTaxes_sdr_c_total()+",totalsalesconsideration_minusdiscount:"+bookingData.getTotalsalesconsideration_minusdiscount()+",discounttype:"+bookingData.getDiscounttype()+",discountvalue:"+bookingData.getDiscountvalue()+",paymentplan_selectedsfid:"+bookingData.getPaymentplan_selectedsfid()+",paymentplan_name:"+bookingData.getPaymentplan_name();
		pymtDtl.setDescription(description);
		pymtDtl.setProject_sfid(bookingData.getProjectsfid());
		pymtDtl.setIsactive("Y");
		pymtDtl.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		pymtDtl.setPayment_mode("Online Payment");
		eOIPaymentDtlDao.insertOnePaymentDtl(pymtDtl);
		}
		catch (Exception e) {
			bookingData.setResp_mesg(bookingData.getResp_mesg()+" & EOI insertPaymentDtl Issue");
			log.error(" Error insertPaymentDtl :-{}",e);
		}
		return bookingData;
	}
	//Insert Into ContactReport Table
	public ContactReport insertContactReport(GPLAppBookingAPIDto bookingData)
	{
		ContactReport respContReport= contactReportDao.getContactReportData(bookingData.getContactid());
		if(respContReport==null)
		{
			ContactReport contactReport = new ContactReport();
			contactReport.setIsUpdated("Y");
			contactReport.setContactSfid(bookingData.getContactSfid());
			contactReport.setContactId(bookingData.getContactid());
			contactReport.setContactName(bookingData.getContactName());
			contactReport.setMobileNo(bookingData.getCountryCode()+bookingData.getMobileno());
			contactReport.setProjectId(bookingData.getProjectsfid());
			return contactReportDao.insertContactReport(contactReport);
		}
		else
			return respContReport;
		
	}
	//Insert Into EnquiryReport Table
	public EnquiryReport insertEnquiryReport(GPLAppBookingAPIDto bookingData)
	{
		EnquiryReport getEnquiryReport = enquiryReportDao.getEnquiryReportEnquiryID(bookingData.getEnquiryid());
		if(getEnquiryReport==null)
		{
			EnquiryReport enquiryReport = new EnquiryReport();
			enquiryReport.setBudget("200000");
			enquiryReport.setCarpetAreaRequirement("200");
			enquiryReport.setIsUpdated("Y");
			enquiryReport.setContactId(bookingData.getContactid());
			enquiryReport.setEnquirySfid(bookingData.getEnquirySfid());
			enquiryReport.setEnquiryId(bookingData.getEnquiryid());
			enquiryReport.setProjectId(bookingData.getProjectsfid());
			enquiryReport.setPurpose("Self Use");
			return enquiryReportDao.insertEnquiryReport(enquiryReport);
		}
		else
			return getEnquiryReport;
		
		
	}
	
	public Token insertIntoTokenTable(GPLAppBookingAPIDto bookingData)
	{
		Token token = new Token();
		try {
			
			token.setCreated(new Timestamp(new Date().getTime()));
			token.setMobileno(bookingData.getMobileno());
			token.setProjectname(bookingData.getProjectsfid());
			token.setAmount("");
			token.setUniqe_no("999999");
			token.setUniqe_str("999999");
			token.setIsactive("Y");
			//Added Token From Ex: GPLAPPS
			token.setTokenfrom("GPLAPPS");
			token.setType(bookingData.getNv_token_type());
			token.setEnquiry_18(bookingData.getEnquiryid().toString());
			token.setCountrycode(bookingData.getCountryCode());
			token.setStarteddate(new Timestamp(new Date().getTime()));
			//token.setWindow_assign(bookingData.getWindow_Assign());
			//token=tokenDao.generateToken(token);
			return tokenDao.generateToken(token);
		}
		catch (Exception e) {
			token.setMsg(bookingData.getResp_mesg()+" & Token generation Failed");
			log.error("Error insertIntoTokenTable :-{}",e);
			return token;
		}
	}
	
	public GPLAppBookingAPIDto insertIntoHoldUnit(GPLAppBookingAPIDto bookingData)
	{
		try
		{
		//eoi_block
		 drupalInventoryStatusUpdate.inventoryStatusUpdate(bookingData.getPropertysfid(), "true");
		 inventoryService.holdInventoryAdmin(bookingData.getProjectsfid(), bookingData.getSiteheadID().toString(), bookingData.getPropertysfid(), "eoi_block", "GPL APPS EOI BLOCK", bookingData.getSiteheadName(), bookingData.getSiteheadID(), bookingData.getEnquirySfid());
		}
		catch (Exception e) {
			bookingData.setResp_mesg(bookingData.getResp_mesg()+" & EOI Unit Holding Issue");
			log.error("insertIntoHoldUnit Error : {}",e);
		}
		return bookingData;
	}
	private static String readContentFromFile(String fileName)  {
		StringBuilder contents = new StringBuilder();
		try {
			// use buffering, reading one line at a time
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			try {
				String line = null;
				while ((line = reader.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				reader.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			
		}
		return contents.toString();
	}

}
