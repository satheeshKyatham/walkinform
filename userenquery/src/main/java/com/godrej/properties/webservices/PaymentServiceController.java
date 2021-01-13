package com.godrej.properties.webservices;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.godrej.properties.dto.SysConfigEnum;
import com.godrej.properties.master.service.SysConfigService;
import com.godrej.properties.model.PaymentPlanDue;
import com.godrej.properties.model.PaymentPlanLineItem;
import com.godrej.properties.model.RefundInitiate;
import com.godrej.properties.model.TowerPPExclusion;
import com.godrej.properties.service.EOIPaymentDtlService;
import com.godrej.properties.service.PaymentPlanDueService;
import com.godrej.properties.service.PaymentPlanLineItemService;
import com.godrej.properties.service.PrePaymentReceivedService;
import com.godrej.properties.service.RefundInitiateService;
import com.godrej.properties.util.SendMailThreadUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class PaymentServiceController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EOIPaymentDtlService eoiPaymentService;
	
	@Autowired
	private PrePaymentReceivedService prePaymentService;
	
	@Autowired
	private PaymentPlanDueService paymentPlanDueService;
	
	@Autowired
	private	PaymentPlanLineItemService paymentPlanLineItemService;
	
	@Autowired
	private	RefundInitiateService refundInitiateService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@GetMapping(value = "/getPendingPaymentData", produces = "application/json")
	public String getPendingPaymentData(@RequestParam("projectid") String projectid)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition=" where project_sfid='"+projectid+"' and isactive='N'";
		return gson.toJson(eoiPaymentService.getCommonEOIPaymentEntrys(whereCondition));	
	}
	
	@GetMapping(value = "/getApprovedPaymentData", produces = "application/json")
	public String getApprovedPaymentData(@RequestParam("projectid") String projectid)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition=" where project_sfid='"+projectid+"' and isactive='Y'";
		return gson.toJson(eoiPaymentService.getCommonEOIPaymentEntrys(whereCondition));	
	}
	@GetMapping(value = "/getRejectedPaymentData", produces = "application/json")
	public String getRejectedPaymentData(@RequestParam("projectid") String projectid)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition=" where project_sfid='"+projectid+"' and isactive='R'";
		return gson.toJson(eoiPaymentService.getCommonEOIPaymentEntrys(whereCondition));	
	}
	@PostMapping(value = "/updateEOIPayment", produces = "application/json")
	public void updateEOIPayment(@RequestParam("paymentEOIDtlJson") String paymentEOIDtlJson)
	{
		eoiPaymentService.paymentEOIApproveReject(paymentEOIDtlJson);	
	}
	@GetMapping(value = "/getPrePayemntData", produces = "application/json")
	public String getPrePayemntData(@RequestParam("offersfid") String offersfid)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(prePaymentService.getPrePaymentDetails(offersfid));	
	}
	/*	Added By Satheesh Kyatham - 23-04-2020
	 *  Task : Get Payment plan Due Data for CIP App*/
	@GetMapping(value = "/getpaymentplanlist_due", produces = "application/json")
	public String getpaymentplanlist(@RequestParam("project_sfid") String project_sfid,@RequestParam("tower_sfid") String tower_sfid,@RequestParam("payment_plan_sfid") String payment_plan_sfid) { 
		Gson gson = new GsonBuilder().serializeNulls().create();
		//call payment table
		List<PaymentPlanLineItem> paymentPlanLineItems=null;
		String paymentPlanLineItems_str = paymentPlanDueService.getPaymentDueList(project_sfid,tower_sfid,payment_plan_sfid);
		if(paymentPlanLineItems_str==null)
		{
		  paymentPlanLineItems= paymentPlanLineItemService.getpaymentplanlist(payment_plan_sfid);
		  return "{\"id\":0,\"bookingamount\":0,\"dues_amount\":0,\"days\":0,\"msg\":\"\",\"paymentPlanList\":"+gson.toJson(paymentPlanLineItems)+"}";
		}
		else
		{
			return paymentPlanLineItems_str;
		}
	}
	/*	Added By Satheesh Kyatham - 18-12-2020
	 *  Task : Refund Process for D4U App*///
	/*@PostMapping(value = "/refund_initiateData", produces = "application/json")
	public @ResponseBody String refund_initiateData(@RequestBody RefundInitiate refund,HttpServletRequest request,
			HttpServletResponse response) { //,@RequestParam("cancelled_check_file") MultipartFile cancelled_check_file
		Gson gson = new GsonBuilder().serializeNulls().create();
		Log.info("refundData{}",refund.getAccount_no());
		//Log.info("cancelled_check_file{}",cancelled_check_file.getOriginalFilename());
		if(receiptAttach!=null) {
		File ad_dir = new File(rootPath + File.separator + "EOIbookingReference" + File.separator + enq_sfid + File.separator + rowid);
		String ad_path =ad_dir +File.separator+rowid+"Receipt"+"_"+receiptAttach.getOriginalFilename();
		if (!ad_dir.exists()) {
			ad_dir.mkdirs();	
		}
		byte[] abytes;
		abytes = receiptAttach.getBytes();
		File aserverFile = new File(ad_path);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(aserverFile));
		stream.write(abytes);
		stream.close();
	}
		refundInitiateService.insertRefundInitiateRequest(refund);
		return "";
	}*/
	@PostMapping(value = "/refund_initiateData",headers = "content-type=multipart/*", produces = "application/json")
	public @ResponseBody String refund_initiateData(
			@RequestParam("ac_holder_name") String ac_holder_name,@RequestParam("bank_name") String bank_name,@RequestParam("branch_name") String branch_name
			,@RequestParam("account_no") String account_no,@RequestParam("ifsc_code") String ifsc_code,@RequestParam("account_type") String account_type,
			@RequestParam("reason_for_cancel_refund") String reason_for_cancel_refund,@RequestParam("description") String description,@RequestParam("enquiry_sfid") String enquiry_sfid,@RequestParam("project_sfid") String project_sfid,
			@RequestParam("refund_initiated_by") Integer refund_initiated_by,@RequestParam("refund_amount") Double refund_amount,
			//@RequestParam("enquiry_sfid") String enquiry_sfid,
			@RequestParam("region_name") String region_name,@RequestParam("projectname") String projectname,
			@RequestParam(value = "cancelled_check_file", required = false) MultipartFile cancelled_check_file
			,HttpServletRequest request,
			HttpServletResponse response) throws IOException { 
		Log.info("refundData{}",ac_holder_name);
		Log.info("cancelled_check_file{}",cancelled_check_file.getOriginalFilename());
		String rootPath = System.getProperty("catalina.home");
		RefundInitiate refund = new RefundInitiate();
		refund.setAc_holder_name(ac_holder_name);
		refund.setBank_name(bank_name);
		refund.setBranch_name(branch_name);
		refund.setAccount_no(account_no);
		refund.setIfsc_code(ifsc_code);
		refund.setAccount_type(account_type);
		refund.setReason_for_cancel_refund(reason_for_cancel_refund);
		refund.setDescription(description);
		refund.setEnquiry_sfid(enquiry_sfid);
		refund.setRefund_initiated_by(refund_initiated_by);
		refund.setRefund_amount(refund_amount);
		refund.setProject_sfid(project_sfid);
		//refund.setApproval_refund_status("Not Processed");
		
		if(cancelled_check_file != null) {
			File ad_dir = new File(rootPath+ File.separator +"D4U File Storage"+ File.separator +region_name + File.separator +projectname+ File.separator + "EOIREFUNDCHEQUE" + File.separator + enquiry_sfid);
			String ad_path =ad_dir +File.separator+"REFUND"+"_"+cancelled_check_file.getOriginalFilename();
			if (!ad_dir.exists()) {
				ad_dir.mkdirs();	
			}
			byte[] abytes;
			File aserverFile = new File(ad_path);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(aserverFile));
			try {
				abytes = cancelled_check_file.getBytes();
				stream.write(abytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.info("Exception : ",e);
			}
			finally {
				stream.close();
			}
			refund.setCancelled_check_file_name(cancelled_check_file.getOriginalFilename());
			refund.setCancelled_check_path(ad_path);
		}
		RefundInitiate retungData = refundInitiateService.insertRefundInitiateRequest(refund);
		refundInitiateService.updateEOIRefundEnty(enquiry_sfid,retungData.getTrx_id(), "");
		return "{'data':'success'}";
	}
	
	@GetMapping(value = "/getInitiateRefundData", produces = "application/json")
	public String getInitiateRefundData(@RequestParam("userid") String userid,@RequestParam("project_sfid") String project_sfid)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition="";
		if(userid!=null && userid.length()>0)
		{
			whereCondition =" where refund.project_sfid='"+project_sfid+"' and refund.refund_initiated_by='"+userid+"' order by refund.refund_initiated_date desc";
			
		}
		else
			whereCondition=" where refund.project_sfid='"+project_sfid+"' order by refund.refund_initiated_date desc";
		return gson.toJson(refundInitiateService.getRefundInitiatedData(whereCondition));
			
	}
	
	@PostMapping(value = "/approvalInitiateRefundProcess")
	public String approvalInitiateRefundProcess(@RequestParam("id") Integer id,@RequestParam("rtgs_no") String rtgs_no,@RequestParam("comments") String comments
			,@RequestParam("status") String status,@RequestParam("loged_useremail") String loged_useremail,@RequestParam("loged_userid") String loged_userid,@RequestParam("trx_id") String trx_id)
	{
//		Log.info("ID {}",id+rtgs_no+comments+status+loged_useremail+loged_userid);
		Log.info("Refund ID - {}, RTGS No - {}, Comments - {}, Status - {}, Logged Email - {}, userID - {}",id,rtgs_no,comments,status,loged_useremail,loged_userid);
		String whereCondtion = " WHERE id="+id;
		String updateValues = " neft_rtgs_utr_no='"+rtgs_no+"',refund_comments='"+comments+"',approval_refund_status='"+status+"',refund_updated_by="+loged_userid+",refund_updated_date='"+new Timestamp(System.currentTimeMillis())+"'";
		refundInitiateService.approveRejectRefund(id, updateValues, whereCondtion);
		String smtpip = sysConfigService.getValue(SysConfigEnum.SMTP_IP, "SMTP_IP");
		String smtpPort = sysConfigService.getValue(SysConfigEnum.SMTP_PORT, "SMTP_PORT");
		String subject="EOI Refund Status:"+status;
		String mailBody= "Dear Manager, </br></br> Initiated Refund has been "+status+", kindly check in your report with "+trx_id+"</br></br> Regards,</br>D4U Team.";
		SendMailThreadUtil mail =new SendMailThreadUtil(loged_useremail,	"sathish.kyatham@godrejproperties.com", subject, mailBody,smtpip,smtpPort);
		return "{'data':'success'}";
		/*if(userid!=null && userid.length()>0)
		{
			whereCondition =" where project_sfid='"+project_sfid+"' and refund_initiated_by='"+userid+"' order by refund_initiated_date desc";
			
		}
		else
			whereCondition=" where project_sfid='"+project_sfid+"' order by refund_initiated_date desc";
		return gson.toJson(refundInitiateService.getRefundInitiatedData(whereCondition));*/
			
	}
	
	@GetMapping(value = "/getInitiatedRefundByTrx_id", produces = "application/json")
	public String getInitiatedRefundByTrx_id(@RequestParam("trx_id") String trx_id)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		//String whereCondition=" where project_sfid='"+projectid+"' and isactive='Y'";
		return gson.toJson(eoiPaymentService.getInitiatedRefundEntrysByTrx_id(trx_id));	
	}
}
