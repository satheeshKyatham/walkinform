package com.godrej.properties.webservices;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.ksoap2.serialization.SoapObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.godrej.kyc.util.StringEncDec;
import com.godrej.kycsync.controller.HerukoSyncController;
import com.godrej.kycsync.controller.TestAttachment;
import com.godrej.kycsync.model.ApplicantDate;
import com.godrej.kycsync.service.InsertUpdateService;
import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.dto.AuditLogDto;
import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.dto.LdapUserDetailsDto;
import com.godrej.properties.dto.SysConfigEnum;
import com.godrej.properties.master.service.SysConfigService;
import com.godrej.properties.model.ADLoginPass;
import com.godrej.properties.model.AssignedUser;
import com.godrej.properties.model.BSPAgainstPymtPlan;
import com.godrej.properties.model.BSPTaxRecord;
import com.godrej.properties.model.BillingData;
import com.godrej.properties.model.CarParkCharges;
import com.godrej.properties.model.CoApplicant;
import com.godrej.properties.model.Contact;
import com.godrej.properties.model.CostSheet;
import com.godrej.properties.model.CostSheetHis;
import com.godrej.properties.model.CustomerDtl;
import com.godrej.properties.model.DailyUserMater;
import com.godrej.properties.model.EOIData;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.EOIPreferenceDtl;
import com.godrej.properties.model.Enquiry;
import com.godrej.properties.model.ExtraCharges;
import com.godrej.properties.model.ExtraChargesHis;
import com.godrej.properties.model.HoldInventoryAdmin;
import com.godrej.properties.model.HoldInventoryAdminLog;
import com.godrej.properties.model.InventoryAdmin;
import com.godrej.properties.model.OTPRequestOC;
import com.godrej.properties.model.OrderDataMapping;
import com.godrej.properties.model.OtherCharges;
import com.godrej.properties.model.PaymentDtl;
import com.godrej.properties.model.PaymentPlan;
import com.godrej.properties.model.PaymentPlanJson;
import com.godrej.properties.model.PaymentPlanLineItem;
import com.godrej.properties.model.PaymentPlanWithOtherCharge;
import com.godrej.properties.model.PaymentSheet;
import com.godrej.properties.model.ProjectDtl;
import com.godrej.properties.model.ProjectLaunch;
import com.godrej.properties.model.ProjectRegion;
import com.godrej.properties.model.RequestAction;
import com.godrej.properties.model.Scheme;
import com.godrej.properties.model.SchemeMapping;
import com.godrej.properties.model.SchemePromotional;
import com.godrej.properties.model.SchemeSite;
import com.godrej.properties.model.SchemeSource;
import com.godrej.properties.model.TnC;
import com.godrej.properties.model.TodaysFollowUp;
import com.godrej.properties.model.Token;
import com.godrej.properties.model.TowerBand;
import com.godrej.properties.model.UnitDtl;
import com.godrej.properties.model.UnitDtlHis;
import com.godrej.properties.model.UserMaster;
import com.godrej.properties.model.UserProjectMapping;
import com.godrej.properties.model.VW_Token;
import com.godrej.properties.model.ValueOldNew;
import com.godrej.properties.model.Vw_MISReport;
import com.godrej.properties.model.Vw_UserMaster;
import com.godrej.properties.model.Vw_UserProjectMapping;
import com.godrej.properties.model.WithoutOtherChargesPP;
import com.godrej.properties.service.AdLoginUserService;
import com.godrej.properties.service.ApplicantDtlService;
import com.godrej.properties.service.ApplicationDtlService;
import com.godrej.properties.service.AssignUserService;
import com.godrej.properties.service.AuditLogService;
import com.godrej.properties.service.BSPAgainstPymtPlanService;
import com.godrej.properties.service.BSPTaxRecordService;
import com.godrej.properties.service.BSPUpdateService;
import com.godrej.properties.service.BalanceDetailsService;
import com.godrej.properties.service.BillingViewService;
import com.godrej.properties.service.BookingOnMapService;
import com.godrej.properties.service.CarParkChargesService;
import com.godrej.properties.service.CostSheetExistsService;
import com.godrej.properties.service.CostSheetHisService;
import com.godrej.properties.service.CostSheetService;
import com.godrej.properties.service.CustomerDtlService;
import com.godrej.properties.service.DailyUserUplaodService;
import com.godrej.properties.service.EOIDataService;
import com.godrej.properties.service.EOIEnquiryService;
import com.godrej.properties.service.EOIPaymentDtlService;
import com.godrej.properties.service.EOIPreferenceDtlService;
import com.godrej.properties.service.EOIReportService;
import com.godrej.properties.service.EOIUpdateService;
import com.godrej.properties.service.EnqAndBookingDtlService;
import com.godrej.properties.service.EnqOnMapService;
import com.godrej.properties.service.EnquiryRequestService;
import com.godrej.properties.service.ExtraChargesExistsService;
import com.godrej.properties.service.ExtraChargesHisService;
import com.godrej.properties.service.ExtraChargesService;
import com.godrej.properties.service.HeaderSchemeService;
import com.godrej.properties.service.HoldIntervalService;
import com.godrej.properties.service.HoldInventoryEntryService;
import com.godrej.properties.service.InventoryService;
import com.godrej.properties.service.OTPRequestOCService;
import com.godrej.properties.service.OrderDataMapppingService;
import com.godrej.properties.service.OtherChargesService;
import com.godrej.properties.service.OtpService;
import com.godrej.properties.service.PaymentDtlService;
import com.godrej.properties.service.PaymentPlanDueService;
import com.godrej.properties.service.PaymentPlanLineItemService;
import com.godrej.properties.service.PaymentPlanListService;
import com.godrej.properties.service.PaymentPlanService;
import com.godrej.properties.service.PaymentPlanWithOtherChargeService;
import com.godrej.properties.service.PaymentSheetService;
import com.godrej.properties.service.ProjectDtlService;
import com.godrej.properties.service.ProjectLaunchService;
import com.godrej.properties.service.ProjectRegionService;
import com.godrej.properties.service.PropOtherChargesService;
import com.godrej.properties.service.PushEnquiryDataService;
import com.godrej.properties.service.ReceivedPaymentDtlService;
import com.godrej.properties.service.RequestActionService;
import com.godrej.properties.service.RqstProcessService;
import com.godrej.properties.service.SalesUnitHoldStatusService;
import com.godrej.properties.service.SchemeChargeService;
import com.godrej.properties.service.SchemeMappingService;
import com.godrej.properties.service.SchemePromotionalService;
import com.godrej.properties.service.SchemeSiteService;
import com.godrej.properties.service.SchemeSourceService;
import com.godrej.properties.service.TnCService;
import com.godrej.properties.service.TokenService;
import com.godrej.properties.service.TriggerLogArchiveService;
import com.godrej.properties.service.TriggerLogService;
import com.godrej.properties.service.UnitDtlHisService;
import com.godrej.properties.service.UnitDtlService;
import com.godrej.properties.service.UnitExistsService;
import com.godrej.properties.service.UserContactService;
import com.godrej.properties.service.UserMasterService;
import com.godrej.properties.service.UserProjectMappingService;
import com.godrej.properties.service.VW_MISReportService;
import com.godrej.properties.service.VW_OfferWithBalanceService;
import com.godrej.properties.service.VW_UserMasterService;
import com.godrej.properties.service.WithoutOtherChargesPPService;
import com.godrej.properties.util.CallWebServices;
import com.godrej.properties.util.GeneratePDF;
import com.godrej.properties.util.OtpGenerate;
import com.godrej.properties.util.SendMailThreadUtil;
import com.godrej.properties.util.SendSMS;
import com.godrej.properties.util.iTextHTMLtoPDF;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.sf.jasperreports.engine.JRException;

@CrossOrigin(origins = "*")
@RestController
public class WebServiceController<MultipartFormDataInput> {

	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	ServletContext context;

	@Autowired
	UserMasterService userMasterService;

	@Autowired
	PushEnquiryDataService pushEnqService;

	@Autowired
	UserContactService userContactService;

	@Autowired
	private PaymentPlanLineItemService paymentPlanLineItemService;

	@Autowired
	private PaymentSheetService paymentSheetService;

	@Autowired
	private PaymentPlanService paymentPlanService;

	@Autowired
	private BillingViewService billingViewService;

	@Autowired
	private OrderDataMapppingService orderDataMapppingService;

	@Autowired
	private CostSheetService costSheetService;

	@Autowired
	private ExtraChargesService extraChargesService;

	@Autowired
	private UnitDtlService unitDtlService;

	@Autowired
	private UnitDtlHisService unitDtlHisService;

	@Autowired
	private CustomerDtlService customerDtlService;

	@Autowired
	private CostSheetExistsService costSheetExistsService;

	@Autowired
	private VW_UserMasterService vW_UserMasterService;

	@Autowired
	private UnitExistsService unitExistsService;

	@Autowired
	private ExtraChargesExistsService extraChargesExistsService;

	@Autowired
	private ExtraChargesHisService extraChargesHisService;

	@Autowired
	private CostSheetHisService costSheetHisService;

	@Autowired
	private RequestActionService requestActionService;

	@Autowired
	private RqstProcessService rqstProcessService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private ProjectLaunchService projectLaunchService;

	@Autowired
	private EOIEnquiryService userEOIService;

	@Autowired
	private DailyUserUplaodService dailyUserUplaodService;


	@Autowired
	private EnquiryRequestService enquiryRequestService;

	
	@Autowired
	private PaymentPlanListService paymentPlanListService;

	// added for inventory -- By A
	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private PropOtherChargesService propOtherChargesService;

	@Autowired
	private OtherChargesService otherChargesService;

	@Autowired
	private SchemeChargeService schemeChargeService;

	@Autowired
	private VW_MISReportService vW_MISReportService;

	@Autowired
	private AssignUserService assignUserService;

	@Autowired
	OtpService otpService;

	@Autowired
	private ProjectRegionService projectRegionService;

	@Autowired
	private PaymentPlanWithOtherChargeService paymentPlanWithOtherChargeService;

	@Autowired
	private OTPRequestOCService oTPRequestOCService;

	@Autowired
	private BSPAgainstPymtPlanService bSPAgainstPymtPlanService;

	@Autowired
	private CarParkChargesService carParkChargesService;

	@Autowired
	private BalanceDetailsService balanceDetailsService;

	@Autowired
	private AdLoginUserService adUserLoginPassService;

	@Autowired
	private EnqOnMapService enqOnMapService;

	@Autowired
	private TnCService tnCService;

	@Autowired
	private PaymentDtlService paymentDtlService;

	@Autowired
	private BSPTaxRecordService bSPTaxRecordService;

	@Autowired
	private VW_OfferWithBalanceService vW_OfferWithBalanceService;

	@Autowired
	private ProjectDtlService projectDtlService;

	@Autowired
	private ApplicationDtlService applicationDtlService;

	@Autowired
	private ApplicantDtlService applicantDtlService;

	@Autowired
	private EnqAndBookingDtlService enqAndBookingDtlService;

	@Autowired
	private ReceivedPaymentDtlService receivedPaymentDtlService;

	@Autowired
	private WithoutOtherChargesPPService withoutOtherChargesPPService;

	@Autowired
	private EOIPaymentDtlService eOIpaymentDtlService;

	@Autowired
	private EOIDataService eOIDataService;

	@Autowired
	private EOIUpdateService eOIUpdateService;

	@Autowired
	private EOIPreferenceDtlService eOIPreferenceDtlService;

	@Autowired
	private TriggerLogService triggerLogService;

	@Autowired
	private TriggerLogArchiveService triggerLogArchiveService;

	@Autowired
	private SchemeSourceService schemeSourceService;
	@Autowired
	private SchemeSiteService schemeSiteService;

	@Autowired
	private SchemePromotionalService schemePromotionalService;

	@Autowired
	private HeaderSchemeService headerSchemeService;

	@Autowired
	private SchemeMappingService schemeMappingService;

	@Autowired
	private EOIReportService eOIReportService;

	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private LdapServiceController ldapServiceController;

	@Autowired
	private CreateOffer creatOffer;

	@Autowired
	private InventoryStatusController inventoryStatusController;

	@Autowired
	private GetEnquiryComments getEnquiryComments;

	@Autowired
	private SalesUnitHoldStatusService salesUnitHoldStatusService;

	@Autowired
	private SysConfigService sysConfigService;

	@Autowired
	private DrupalInventoryStatusUpdate drupalInventoryStatusUpdate;

	@Autowired
	private BookingOnMapService bookingOnMapService;
	
	@Autowired
	private UserProjectMappingService userProjectMappingService;

	
	@RequestMapping(value = "/activeproject", method = RequestMethod.GET, produces = "application/json")
	public String project() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		List<ProjectLaunch> adt = projectLaunchService.getActiveProjectList();
		return gson.toJson(adt);
	}

	@RequestMapping(value = "/getprojects", method = RequestMethod.GET, produces = "application/json")
	public String getProject(@RequestParam("regionid") String regionid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		List<ProjectLaunch> adt = projectLaunchService.getProjectList(regionid);
		return gson.toJson(adt);
	}

	@RequestMapping(value = "/updateproject", method = RequestMethod.GET, produces = "application/json")
	public String updateProject(@RequestParam("id") String id, @RequestParam("status") String status) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(projectLaunchService.UpdateProjectStatus(id, status));
	}

	@RequestMapping(value = { "/inventory" }, method = RequestMethod.GET)
	public ModelAndView claimForm() {
		ModelAndView model = new ModelAndView();
		model.setViewName("inventory");

		return model;
	}

	@RequestMapping(value = { "/otherCharges" }, method = RequestMethod.GET)
	public ModelAndView addOtherCharges() {
		ModelAndView model = new ModelAndView();
		model.setViewName("otherCharges");
		return model;
	}

	@RequestMapping(value = { "/schemeCharges" }, method = RequestMethod.GET)
	public ModelAndView addAchemeCharges() {
		ModelAndView model = new ModelAndView();
		model.setViewName("schemeCharges");
		return model;
	}

	@RequestMapping(value = { "/paymentPlanBSP" }, method = RequestMethod.GET)
	public ModelAndView bspAgainstPP() {
		ModelAndView model = new ModelAndView();
		model.setViewName("paymentPlanBSP");
		return model;
	}

	@RequestMapping(value = { "/tnc" }, method = RequestMethod.GET)
	public ModelAndView tncAgainstPP() {
		ModelAndView model = new ModelAndView();
		model.setViewName("tnc");
		return model;
	}

	@RequestMapping(value = { "/tncEOI" }, method = RequestMethod.GET)
	public ModelAndView tncAgainstEOI() {
		ModelAndView model = new ModelAndView();
		model.setViewName("tncEOI");
		return model;
	}

	@RequestMapping(value = { "/salesTnc" }, method = RequestMethod.GET)
	public ModelAndView salesTnc() {
		ModelAndView model = new ModelAndView();
		model.setViewName("salesTnc");
		return model;
	}
	
	@RequestMapping(value = { "/carParkCharges" }, method = RequestMethod.GET)
	public ModelAndView carParkCharges() {
		ModelAndView model = new ModelAndView();
		model.setViewName("carParkCharges");
		return model;
	}

	@RequestMapping(value = { "/costsheet" }, method = RequestMethod.GET)
	public ModelAndView costsheet() {
		ModelAndView model = new ModelAndView();
		model.setViewName("costsheet");
		return model;
	}

	@RequestMapping(value = { "/enqOnMap" }, method = RequestMethod.GET)
	public ModelAndView enqOnMap() {
		ModelAndView model = new ModelAndView();
		model.setViewName("enqOnMap");
		return model;
	}

	@RequestMapping(value = { "/tokenScreen" }, method = RequestMethod.GET)
	public ModelAndView tokenScreen() {
		ModelAndView model = new ModelAndView();
		model.setViewName("tokenScreen");
		return model;
	}
	
	@RequestMapping(value = {
			"/inventory/{token}/{projectsfid}/{enquirysfid}/{primarycontactsfid}/{tokentype}" }, method = RequestMethod.GET)
	public ModelAndView inventory(@PathVariable("token") String token, @PathVariable("enquirysfid") String enquirysfid,
			@PathVariable("projectsfid") String projectsfid,
			@PathVariable("primarycontactsfid") String primarycontactsfid,
			@PathVariable("tokentype") String tokentype) {

		ModelAndView model = new ModelAndView();
		model.addObject("token", token);
		model.addObject("tokentype", tokentype);
		model.addObject("projectsfid", projectsfid);
		model.addObject("primarycontactsfid", primarycontactsfid);
		model.addObject("enquirysfid", enquirysfid);
		model.setViewName("inventory");
		return model;
	}

	// Customer Dtl

	// GeneratePDF.PDFReport();

	public ModelAndView costsheet(@PathVariable("token") String token, @PathVariable("projectsfid") String projectsfid,
			@PathVariable("enquirysfid") String enquirysfid,
			@PathVariable("primarycontactsfid") String primarycontactsfid) {
		ModelAndView view = new ModelAndView("costsheet");

		view.addObject("token", token);
		view.addObject("projectsfid", projectsfid);
		view.addObject("enquirysfid", enquirysfid);
		view.addObject("primarycontactsfid", primarycontactsfid);

		return view;
	}

	@RequestMapping(value = { "/getContactDtl" }, method = RequestMethod.POST)
	public String contactDtl(@RequestParam("contactNo") String contactNo) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		CustomerDtl ccData = customerDtlService.getCustomerDeta(contactNo);
		/*
		 * if(ccData!= null) { model.addAttribute("ccData", ccData); }
		 */

		return gson.toJson(ccData);
		// return "UserDashBoard";
	}
	// END Customer Dtl

	/* insert other charges */
	@RequestMapping(value = "/insertOtherCharge", method = RequestMethod.POST)
	public OtherCharges getUnitOtherDtl(@RequestParam("region") String region,
			@RequestParam("projectName") String projectName, @RequestParam("project") String project,
			@RequestParam("tower") String tower, @RequestParam("towerName") String towerName,
			@RequestParam("typology") String typology, @RequestParam("chargeName") String chargeName,
			@RequestParam("fixedCharge") double fixedCharge, @RequestParam("percentage") double percentage,
			@RequestParam("ratePerUnit") double ratePerUnit, @RequestParam("chargeType") String chargeType) // add
																											// parameter
	{

		OtherCharges oc = new OtherCharges();

		oc.setPropstrength__other_charge_code__c(chargeName);
		oc.setPropstrength__fixed_charge__c(fixedCharge);
		oc.setPropstrength__rate_per_unit_area__c(ratePerUnit);
		oc.setSum(percentage);
		oc.setPropstrength__type__c(chargeType);

		oc.setIsactive("A");
		oc.setProject_id(project);
		oc.setProject_name(projectName);
		oc.setTypology(typology);
		oc.setRegion(region);
		oc.setTower(tower);

		otherChargesService.insertOtherCharge(oc);

		return (oc);
	}
	/* END insert other charges */

	/* insert scheme charges */
	@RequestMapping(value = "/insertSchemeCharge", method = RequestMethod.POST)
	public Scheme addSchemeCharge(@RequestParam("sourceName") String sourceName,
			@RequestParam("siteName") String siteName, @RequestParam("promotionalName") String promotionalName,
			@RequestParam("percentage") Double percentage, @RequestParam("absolute_amount") Double absolute_amount,
			@RequestParam("region") String region, @RequestParam("projectName") String projectName,
			@RequestParam("projectid") String projectid, @RequestParam("scheme_name") String schemeName,
			@RequestParam("scheme_rate") Double schemeRate, @RequestParam("zeroGovtCharges") Boolean zeroGovtCharges) // add
																														// parameter
	{
		ProjectLaunch regionData=projectLaunchService.getprojectDetails(projectid);
		Scheme scheCharge = new Scheme();
		scheCharge.setRegion_name(regionData.getRegionname());
		scheCharge.setProject_name(projectName);
		scheCharge.setScheme(schemeName);
		scheCharge.setRate(schemeRate);
		scheCharge.setProject_id(projectid);
		scheCharge.setIsactive("A");

		scheCharge.setPercentage(percentage);
		scheCharge.setAbsolute_amount(absolute_amount);

		scheCharge.setZero_govt_charges(zeroGovtCharges);

		scheCharge.setSite_name(siteName);
		scheCharge.setSource_name(sourceName);
		scheCharge.setPromotional_name(promotionalName);

		schemeChargeService.insertSchemeCharge(scheCharge);

		return (scheCharge);
	}
	/* END insert scheme charges */

	/* get scheme charges List */
	@RequestMapping(value = "/getSchemeCharge", method = RequestMethod.GET)
	public String addSchemeCharge(@RequestParam("schemeSource") int schemeSource,
			@RequestParam("schemeSite") int schemeSite, @RequestParam("schemePromotional") int schemePromotional,
			@RequestParam("projectid") String projectid, @RequestParam("cp_flag_yn") String cp_flag_yn) // add
																										// parameter
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(schemeChargeService.getSchemeChargs(schemeSource, schemeSite, schemePromotional, projectid,
				cp_flag_yn));
	}
	/* END get scheme charges List */

	/* insert BSP against Payment Plan */
	@RequestMapping(value = "/insertBSPForPP", method = RequestMethod.POST)
	public BSPAgainstPymtPlan insertBSPForPP(@RequestParam("bsp_amount_per") Double bsp_amount_per,
			@RequestParam("bsp_amount") String bsp_amount, @RequestParam("project_id") String project_id,
			@RequestParam("project_name") String project_name, @RequestParam("pymt_plan_id") String pymt_plan_id,
			@RequestParam("pymt_plan_name") String pymt_plan_name, @RequestParam("region_id") String region_id,
			@RequestParam("region_name") String region_name, @RequestParam("tower_id") String tower_id,
			@RequestParam("typology_name") String typology_name, @RequestParam("inventoryCatDD") String inventoryCatDD) // add
																														// parameter
	{
		BSPAgainstPymtPlan oc = new BSPAgainstPymtPlan();

		if (tower_id.equals("0")) {
			tower_id = null;
		}

		if (typology_name.equals("0")) {
			typology_name = null;
		}
		ProjectLaunch regionData=projectLaunchService.getprojectDetails(project_id);
		oc.setBsp_amount(bsp_amount);
		oc.setIsactive("A");
		oc.setProject_id(project_id);
		oc.setProject_name(project_name);
		oc.setPymt_plan_id(pymt_plan_id);
		oc.setPymt_plan_name(pymt_plan_name);
		oc.setRegion_id(regionData.getRegionname());
		oc.setRegion_name(regionData.getRegionname());
		oc.setCreatedby("9999");
		oc.setUpdatedby("9999");
		oc.setTowerid(tower_id);
		oc.setTypology(typology_name);
		oc.setBsp_per(bsp_amount_per);
		oc.setInventory_category(inventoryCatDD);
		/*
		 * int status = bSPAgainstPymtPlanService.checkBSPForPP(oc);
		 * if(status==0) {
		 */
		bSPAgainstPymtPlanService.insertBSPForPP(oc);
		oc.setInsertStatus("Successfully Submitted");
		/*
		 * } else oc.setInsertStatus("Already Exist");
		 */

		return (oc);
	}
	/* END insert BSP against Payment Plan */

	/* insert T&C against Payment Plan */
	@RequestMapping(value = "/insertTnCForPP", method = RequestMethod.POST)
	public TnC insertTnCForPP(@RequestParam("tnc_text") String tnc_text, @RequestParam("project_id") String project_id,
			@RequestParam("project_name") String project_name, @RequestParam("pymt_plan_id") String pymt_plan_id,
			@RequestParam("pymt_plan_name") String pymt_plan_name, @RequestParam("region_id") String region_id,
			@RequestParam("region_name") String region_name, @RequestParam("tower_sfid") String tower_sfid,
			@RequestParam("tower_name") String tower_name) // add parameter
	{
		TnC oc = new TnC();
		List<TnC> tncExist=tnCService.getSalesTncData(pymt_plan_id, project_id, tower_sfid);
		if(tncExist.size()>0){
			oc.setInsertStatus("NOT_OK");
		}else{
			if (tower_sfid.equals("")) {
				tower_sfid = null;
			}
			ProjectLaunch regionData=projectLaunchService.getprojectDetails(project_id);
			oc.setTnc_text(tnc_text);
			oc.setIsactive("A");
			oc.setProject_id(project_id);
			oc.setProject_name(project_name);
			oc.setPymt_plan_id(pymt_plan_id);
			oc.setPymt_plan_name(pymt_plan_name);
			oc.setRegion_id(regionData.getRegionname());
			oc.setRegion_name(regionData.getRegionname());
			oc.setTower_name(tower_name);
			oc.setCreatedby("9999");
			oc.setUpdatedby("9999");
			oc.setTower_sfid(tower_sfid);
			tnCService.insertTNCForPP(oc);
			oc.setInsertStatus("Successfully Submitted");	
		}
		return (oc);	
	}
	/* END insert T&C against Payment Plan */

	/* Get TNC Data */
	@RequestMapping(value = "/getTncData", method = RequestMethod.POST)
	public String insertTnCForPP(@RequestParam("ppId") String ppId, @RequestParam("projectid") String projectid,
			@RequestParam("tower_sfid") String tower_sfid) // add parameter
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		List<TnC> tnC = tnCService.getTncData(ppId, projectid, tower_sfid);

		return gson.toJson(tnC);
	}
	/* Get TNC Data */

	/* Print Data */
	/*
	 * @RequestMapping(value = "/printCSdata"}, method = RequestMethod.POST)
	 * public String printCSdata(@RequestParam("csData") String csData) throws
	 * JRException, IOException{ {
	 * 
	 * GeneratePDF solution = new GeneratePDF (); solution.PDFReport(20
	 * ,csData); return ""; }
	 */
	/* Print Data */

	
	@RequestMapping(value = { "/printCSdata" }, method = RequestMethod.POST)
	public synchronized String printCSdata(@RequestParam("USEREMAIL_GV") String USEREMAIL_GV,
			@RequestParam("unitTval") String unitTval, @RequestParam("floorTval") String floorTval,
			@RequestParam("towerName") String towerName, @RequestParam("regionName") String regionName,
			@RequestParam("projectSfid") String projectSfid, @RequestParam("unitSfid") String unitSfid,
			@RequestParam("enqSfid") String enqSfid, @RequestParam("csData") String csData,
			@RequestParam("projectName") String projectName, @RequestParam("currentDate") String currentDate, @RequestParam("generateFrom") String generateFrom, @RequestParam("logoPath") String logoPath)
			throws JRException, IOException {
		log.info("Enter Print Costsheet");
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		long timeId = new Date().getTime();

		/*
		 * GeneratePDF solution = new GeneratePDF (); solution.PDFReport(20
		 * ,csData);
		 */
		String csAnnexure = "";
		String annexure = sysConfigService.getValue(SysConfigEnum.CS_ANNEXURE, projectSfid);
		
		if (annexure != null) {
			csAnnexure = annexure;
		} else {
			csAnnexure = sysConfigService.getValue(SysConfigEnum.CS_ANNEXURE, null);			
		}
		
		log.info("");
		iTextHTMLtoPDF solution = new iTextHTMLtoPDF();
		solution.PDFReport(USEREMAIL_GV, unitTval, floorTval, towerName, regionName, projectSfid, unitSfid, timeId, csData, projectName, currentDate, enqSfid, csAnnexure, logoPath);
		
		//Base 64
		String response = "";
		String b64 = "";
		String b64Image = "";
		if (!generateFrom.equals("Email")) {
			return gson.toJson(timeId);
		} else {
			try {
				String floorName = "";
				if (floorTval.equals("")) {
			    	  floorName = "NoFloor";
			      } else {
			    	  floorName = floorTval;
			      }
				
				File filePath=null;
				String folderPath = "";
				String rootPath = System.getProperty("catalina.home");
				
				filePath = new File(rootPath+"\\costSheetPDF\\"+ regionName + File.separator + projectName + File.separator + towerName + File.separator + floorName + File.separator + unitTval + File.separator + enqSfid+"-"+unitSfid+"-"+projectSfid+".pdf");
				folderPath = rootPath+"\\costSheetPDF\\"+ regionName + File.separator + projectName + File.separator + towerName + File.separator + floorName + File.separator + unitTval + File.separator;
				
				pdfToImage(filePath, folderPath);
				
				//-------------- PDF ----------------
				String path = (rootPath + File.separator + "costSheetPDF" + File.separator + regionName + File.separator + projectName + File.separator + towerName + File.separator + floorName + File.separator + unitTval + File.separator + enqSfid+"-"+unitSfid+"-"+projectSfid+".pdf");
				
				File file = new File(path);
				byte [] bytes = Files.readAllBytes(file.toPath());

				b64 = Base64.getEncoder().encodeToString(bytes);
				 
				//-------------- IMAGE ----------------
				String pathImage = (rootPath + File.separator + "costSheetPDF" + File.separator + regionName + File.separator + projectName + File.separator + towerName + File.separator + floorName + File.separator + unitTval + File.separator + enqSfid+"-"+unitSfid+"-"+projectSfid+".png");
				File fileImage = new File(pathImage);
				byte [] bytesImage = Files.readAllBytes(fileImage.toPath());
				b64Image = Base64.getEncoder().encodeToString(bytesImage);
				
				response = "{\"status\":\"STATUS_OK\",\"pdf\":\""+b64+"\",\"image\":\""+b64Image+"\",\"error_msg\":\"\",\"error_id\":\"\"}";
				//response = "{\"status\":\""+b64+"\",\"error_msg\":\"Details is not updated on portal, please try again later\",\"error_id\":\"UPDATE_EOI_PAYMENT_ER1004\"}";
			} catch (Exception e) {
			      e.printStackTrace();
			      response = "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"\",\"error_id\":\"\"}";
		    }
			return response;
		}
		// END Base 64
	}

	@RequestMapping(value = { "/getCarParkCharges" }, method = RequestMethod.POST)
	public synchronized String getCarParkCharges(@RequestParam("parkType") String parkType,
			@RequestParam("projectSFID") String projectSFID) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		CarParkCharges carParkCharges = carParkChargesService.getCharges(parkType, projectSFID);

		return gson.toJson(carParkCharges);
	}

	/* insert carpark charges against Payment Plan */
	@RequestMapping(value = "/insertCarparkCharges", method = RequestMethod.POST)
	public CarParkCharges insertCarparkCharges(@RequestParam("project_id") String project_id,
			@RequestParam("project_name") String project_name, @RequestParam("region_id") String region_id,
			@RequestParam("region_name") String region_name, @RequestParam("parkType") int parkType,
			@RequestParam("parkTypeName") String parkTypeName, @RequestParam("carParkAmount") String carParkAmount) // add
																													// parameter
	{
		CarParkCharges oc = new CarParkCharges();
		ProjectLaunch regionData=projectLaunchService.getprojectDetails(project_id);
		oc.setAmount(carParkAmount);
		oc.setIsactive("A");
		oc.setPark_type(parkTypeName);
		oc.setProject_id(project_id);
		oc.setProject_name(project_name);
		oc.setRegion_id(regionData.getRegionname());
		oc.setRegion_name(regionData.getRegionname());
		oc.setCarpark_type_mst_id(parkType);
		carParkChargesService.insertCPAmount(oc);
		return (oc);
	}
	/* END insert carpark charges against Payment Plan */

	// Unit Other Details
	// @ResponseBody
	@RequestMapping(value = "/getUnitDtl", method = RequestMethod.POST)
	public long getUnitOtherDtl(@RequestParam("bspVal") String bspVal, @RequestParam("BSPrate") String BSPrate,
			@RequestParam("scheme_type") String scheme_type, @RequestParam("customerName") String customerName,
			@RequestParam("loginUserName") String loginUserName, @RequestParam("loginUserId") String loginUserId,
			@RequestParam("loginUserEmail") String loginUserEmail, @RequestParam("contactNo") String contactNo,
			@RequestParam("carpetSqm") String carpetSqm, @RequestParam("balTerSqm") String balTerSqm,
			@RequestParam("carParks") String carParks, @RequestParam("unitTvalGet") String unitTval,
			@RequestParam("towerTvalGet") String towerTval, @RequestParam("floorTvalGet") String floorTval,
			@RequestParam("typologyTvalGet") String typologyTval, @RequestParam("CostofCarpetGet") String CostofCarpet,
			@RequestParam("CostofExcBalconyGet") String CostofExcBalcony, @RequestParam("PropCostGet") String PropCost) // add
																														// parameter
	{

		UnitDtl uDtl = unitExistsService.getUnitData(contactNo);

		// float bsp =Float.parseFloat(BSPrate);

		System.out.println("bsp ::: " + BSPrate);

		long timeId = new Date().getTime();

		if (uDtl != null) {
			// TO Do new data set into model

			uDtl.setUnit_no(unitTval);
			uDtl.setTower(towerTval);
			uDtl.setFloor(floorTval);
			uDtl.setTypology(typologyTval);

			uDtl.setCarpetareasqm(carpetSqm);
			uDtl.setBalconysqm(balTerSqm);
			uDtl.setCarpark(carParks);

			uDtl.setCarpetareacost(CostofCarpet);
			uDtl.setBalconycost(CostofExcBalcony);
			uDtl.setCommonareacost(PropCost);
			uDtl.setUpdatedby(loginUserName);
			uDtl.setUpdated_uid(loginUserId);
			uDtl.setUpdated_email(loginUserEmail);

			uDtl.setScheme_type(scheme_type);

			uDtl.setBsp(BSPrate);

			// uDtl.setAdmin_status("NULL");
			uDtl.setTimeid(timeId);

			unitExistsService.updateUnitDtl(uDtl);

			System.out.println("Value Has");
		} else {
			System.out.println("Test ::: ");

			UnitDtl unitDtl = new UnitDtl();

			unitDtl.setUser_contact(contactNo);
			unitDtl.setUnit_no(unitTval);
			unitDtl.setTower(towerTval);
			unitDtl.setFloor(floorTval);
			unitDtl.setTypology(typologyTval);
			unitDtl.setCarpetareasqm(carpetSqm);
			unitDtl.setBalconysqm(balTerSqm);
			unitDtl.setCarpark(carParks);
			unitDtl.setCarpetareacost(CostofCarpet);
			unitDtl.setBalconycost(CostofExcBalcony);
			unitDtl.setCommonareacost(PropCost);
			unitDtl.setCreatedby(loginUserName);
			unitDtl.setCreated_uid(loginUserId);
			unitDtl.setCreated_email(loginUserEmail);

			unitDtl.setScheme_type(scheme_type);

			unitDtl.setAdmin_status("Z");
			unitDtl.setSendforapproval("Z");
			unitDtl.setSenttocrm("Z");

			// unitDtl.setAdmin_status("NULL");
			unitDtl.setTimeid(timeId);
			unitDtl.setCustomer_name(customerName);

			unitDtl.setBsp(BSPrate);

			unitDtl.setDiscount_val(bspVal);

			unitDtlService.setUnitDtl(unitDtl);
		}

		UnitDtlHis unitDtlHis = new UnitDtlHis();

		unitDtlHis.setUser_contact(contactNo);

		unitDtlHis.setUnit_no(unitTval);
		unitDtlHis.setTower(towerTval);
		unitDtlHis.setFloor(floorTval);
		unitDtlHis.setTypology(typologyTval);

		unitDtlHis.setCarpetareasqm(carpetSqm);
		unitDtlHis.setBalconysqm(balTerSqm);
		unitDtlHis.setCarpark(carParks);

		unitDtlHis.setCarpetareacost(CostofCarpet);
		unitDtlHis.setBalconycost(CostofExcBalcony);
		unitDtlHis.setCommonareacost(PropCost);

		unitDtlHis.setTimeid(timeId);
		unitDtlHis.setCustomer_name(customerName);

		unitDtlHis.setCreatedby(loginUserName);
		unitDtlHis.setCreated_uid(loginUserId);
		unitDtlHis.setCreated_email(loginUserEmail);

		unitDtlHis.setScheme_type(scheme_type);

		unitDtlHis.setBsp(BSPrate);

		unitDtlHisService.setUnitDtl(unitDtlHis);

		return (timeId);
	}

	// Charges & Amount
	@RequestMapping(value = "/getExtraCharges", method = RequestMethod.POST, produces = "application/json")
	public String getExtraChrg(@RequestParam("timeid") String timeid, @RequestParam("contactNo") String contactNo,
			@RequestParam("caDtl") String projectId) // add parameter
	{
		ExtraCharges ecData = extraChargesExistsService.getECData(contactNo);

		long timeId = Long.parseLong(timeid);

		if (ecData != null) {
			// long timeId = new Date().getTime();

			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();

			String str = projectId;

			Object object = null;
			JsonArray arrayObj = null;
			JsonParser jsonParser = new JsonParser();
			object = jsonParser.parse(str);
			arrayObj = (JsonArray) object;

			List<ExtraCharges> charges1 = new ArrayList<>();
			for (int i = 0; i < arrayObj.size(); i++) {
				ExtraCharges ecData1 = new ExtraCharges();
				ecData1 = gson.fromJson(arrayObj.get(i), ExtraCharges.class);
				ecData1.setUser_contact(contactNo);
				ecData1.setSeq(i);
				ecData1.setTimeid(timeId);
				charges1.add(ecData1);
				// System.out.println("Test XYZ ::: " + ecData1.toString());
			}

			System.out.println("Test XYZ ::: " + charges1.toString());
			extraChargesExistsService.updateExtraCharges(charges1);

			System.out.println("Value Has");
		} else {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();

			// long timeId = new Date().getTime();

			String str = projectId;

			Object object = null;
			JsonArray arrayObj = null;
			JsonParser jsonParser = new JsonParser();
			object = jsonParser.parse(str);
			arrayObj = (JsonArray) object;

			List<ExtraCharges> charges = new ArrayList<>();
			for (int i = 0; i < arrayObj.size(); i++) {
				ExtraCharges extraCharges = new ExtraCharges();
				extraCharges = gson.fromJson(arrayObj.get(i), ExtraCharges.class);
				extraCharges.setSeq(i);
				extraCharges.setUser_contact(contactNo);
				extraCharges.setTimeid(timeId);

				System.out.println("Test XYZ ::: " + extraCharges.toString());
				charges.add(extraCharges);
			}
			System.out.println("Size ::: " + charges.size());
			extraChargesService.setExtraChrgs(charges, "enq_id", contactNo);
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		// long timeId = new Date().getTime();

		String str = projectId;

		Object object = null;
		JsonArray arrayObj = null;
		JsonParser jsonParser = new JsonParser();
		object = jsonParser.parse(str);
		arrayObj = (JsonArray) object;

		List<ExtraChargesHis> charges = new ArrayList<>();
		for (int i = 0; i < arrayObj.size(); i++) {
			ExtraChargesHis extraChargesHis = new ExtraChargesHis();
			extraChargesHis = gson.fromJson(arrayObj.get(i), ExtraChargesHis.class);
			extraChargesHis.setSeq(i);
			extraChargesHis.setUser_contact(contactNo);
			extraChargesHis.setTimeid(timeId);

			System.out.println("Test XYZ ::: " + extraChargesHis.toString());
			charges.add(extraChargesHis);
		}
		System.out.println("Size ::: " + charges.size());
		extraChargesHisService.setExtraChrgs(charges, "enq_id", contactNo);

		// return "";
		return gson.toJson("");
	}

	/* @ResponseBody */
	@RequestMapping(value = "/getPaymentSchedule", method = RequestMethod.POST, produces = "application/json")
	public String getCostSheet(@RequestParam("timeid") String timeid, @RequestParam("contactNo") String contactNo,
			@RequestParam("caDtl") String projectId) throws JRException, IOException {

		CostSheet csData = costSheetExistsService.getCSData(contactNo);

		long timeId = Long.parseLong(timeid);

		if (csData != null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();

			// long timeId = new Date().getTime();

			// System.out.println("newTime ZZZZZZZZZZZZZ ::: " + timeId);

			String str = projectId;

			Object object = null;
			JsonArray arrayObj = null;
			JsonParser jsonParser = new JsonParser();
			object = jsonParser.parse(str);
			arrayObj = (JsonArray) object;

			List<CostSheet> csu = new ArrayList<>();
			for (int i = 0; i < arrayObj.size(); i++) {
				CostSheet costSheet = new CostSheet();
				costSheet = gson.fromJson(arrayObj.get(i), CostSheet.class);
				costSheet.setSeq(i);
				costSheet.setUser_contact(contactNo);
				costSheet.setTimeid(timeId);
				System.out.println("Test XYZ ::: " + costSheet.toString());
				csu.add(costSheet);
			}
			System.out.println("Size ::: " + csu.size());
			costSheetExistsService.updateCostSheet(csu);

			System.out.println("Value Has");
		} else {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();

			// long timeId = new Date().getTime();

			// System.out.println("newTime ZZZZZZZZZZZZZ ::: " + timeId);

			String str = projectId;

			Object object = null;
			JsonArray arrayObj = null;
			JsonParser jsonParser = new JsonParser();
			object = jsonParser.parse(str);
			arrayObj = (JsonArray) object;

			List<CostSheet> costSheets = new ArrayList<>();
			for (int i = 0; i < arrayObj.size(); i++) {
				CostSheet costSheet = new CostSheet();
				costSheet = gson.fromJson(arrayObj.get(i), CostSheet.class);
				costSheet.setSeq(i);
				costSheet.setUser_contact(contactNo);
				costSheet.setTimeid(timeId);
				System.out.println("Test XYZ ::: " + costSheet.toString());
				costSheets.add(costSheet);
			}
			System.out.println("Size ::: " + costSheets.size());
			costSheetService.setCostSheet(costSheets, "enq_id");
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		// long timeId = new Date().getTime();

		// System.out.println("newTime ::: " + timeId);

		String str = projectId;

		Object object = null;
		JsonArray arrayObj = null;
		JsonParser jsonParser = new JsonParser();
		object = jsonParser.parse(str);
		arrayObj = (JsonArray) object;

		List<CostSheetHis> costSheetsHis = new ArrayList<>();
		for (int i = 0; i < arrayObj.size(); i++) {
			CostSheetHis costSheetHis = new CostSheetHis();
			costSheetHis = gson.fromJson(arrayObj.get(i), CostSheetHis.class);
			costSheetHis.setSeq(i);
			costSheetHis.setUser_contact(contactNo);
			costSheetHis.setTimeid(timeId);
			System.out.println("Test XYZ ::: " + costSheetHis.toString());
			costSheetsHis.add(costSheetHis);
		}
		System.out.println("Size ::: " + costSheetsHis.size());
		costSheetHisService.setCostSheet(costSheetsHis, "enq_id");

		/* Do not remove */
		GeneratePDF solution = new GeneratePDF();
		solution.PDFReport(timeId, "20");

		return gson.toJson("");
		// return gson.toJson(userContactService.findMobileNoExist(mobileno,
		// projectId));
	}

	/* Get Request list for ADMIN */

	@RequestMapping(value = { "/adminRqst" }, method = RequestMethod.POST)
	public String rqstForAdmin(@RequestParam("userId") String userId) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		List<RequestAction> requestAction = requestActionService.rqstForAdmin(userId);
		// model.addAttribute("requestAction", requestAction);

		return gson.toJson(requestAction);
	}
	/* END Get Request list for ADMIN */

	@RequestMapping(value = { "/salesRqst" }, method = RequestMethod.POST)
	public String rqstForSales(@RequestParam("userId") String userId) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		List<RequestAction> requestAction = requestActionService.getRqstForSales(userId);

		return gson.toJson(requestAction);
	}

	@RequestMapping(value = { "/adminAction" }, method = RequestMethod.POST)
	public String rqstAction(@RequestParam("timeid") long timeid, @RequestParam("actionAR") String actionAR) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		UnitDtl action = new UnitDtl();

		action.setTimeid(timeid);
		action.setAdmin_status(actionAR);

		rqstProcessService.updateRqst(action);

		// unitExistsService.updateUnitDtl (uDtl);

		return gson.toJson("");
	}

	/* Added For Get Payment Plan Dropdown */

	@RequestMapping(value = "/getpaymentPlanListData", method = RequestMethod.GET, produces = "application/json")
	// @RequestMapping(value = { "/getpaymentPlanListData" }, method =
	// RequestMethod.POST)
	public String getPaymentPlanList(@RequestParam("projectcode") String projectcode) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		// System.out.println("Called");
		// System.out.println("getEnquirys:-"+enquiryservice.getEnquiryList(pancard,
		// fromdate,todate));
		return gson.toJson(paymentPlanListService.getPaymentPlanList(projectcode));
	}

	/*
	 * @RequestMapping(value = { "/holdExistData" }, method =
	 * RequestMethod.POST) public String holdExistData
	 * (@RequestParam("projectNameId") String
	 * projectNameId, @RequestParam("customerId") String customerId) {
	 */

	/* Get Enq Data for place on MAP */

	// @RequestMapping(value = "/getPaymentPlanOtherCharges", method =
	// RequestMethod.POST)
	// public String getPaymentPlanOtherCharges(@RequestParam("unitSfid") String
	// unitSfid,@RequestParam("paymentPlanSfid") String
	// paymentPlanSfid,@RequestParam("projectid") String projectid) {

	@RequestMapping(value = "/getEnqDataForMap", method = RequestMethod.POST)
	public String getEnqDataForMap(@RequestParam("projectId") String projectId,  @RequestParam("userVerticals") String userVerticals) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		
		// For multiple verticales
		String finalVerticales = "";
		if (userVerticals != null && !userVerticals.equals("null") && !userVerticals.equals("")) {
			String [] multiVerticales= userVerticals.split(",");
			
			StringBuilder modifiedVer = new StringBuilder();
			
			
			for (int i=0;i<multiVerticales.length;i++){
				modifiedVer.append("'"+multiVerticales[i]+"'");
				modifiedVer.append(",");
			}
			
			finalVerticales = modifiedVer.toString();
			
			if (finalVerticales != null && finalVerticales.length() > 0 && finalVerticales.charAt(finalVerticales.length() - 1) == ',') {
				finalVerticales = finalVerticales.substring(0, finalVerticales.length() - 1);
			}
		} else {
			finalVerticales = null;
		}
		// END For multiple verticales
		
		
		return gson.toJson(enqOnMapService.getEnqDtl(projectId, finalVerticales));
	}
	/* END Get Enq Data for place on MAP */

	@RequestMapping(value = "/getBookingDataForMap", method = RequestMethod.POST)
	public String getBookingDataForMap(@RequestParam("projectId") String projectId,  @RequestParam("userVerticals") String userVerticals) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		
		
		// For multiple verticales
		String finalVerticales = "";
		if (userVerticals != null && !userVerticals.equals("null") && !userVerticals.equals("")) {
			String [] multiVerticales= userVerticals.split(",");
			
			StringBuilder modifiedVer = new StringBuilder();
			
			
			for (int i=0;i<multiVerticales.length;i++){
				modifiedVer.append("'"+multiVerticales[i]+"'");
				modifiedVer.append(",");
			}
			
			finalVerticales = modifiedVer.toString();
			
			if (finalVerticales != null && finalVerticales.length() > 0 && finalVerticales.charAt(finalVerticales.length() - 1) == ',') {
				finalVerticales = finalVerticales.substring(0, finalVerticales.length() - 1);
			}
		} else {
			finalVerticales = null;
		}
		// END For multiple verticales
		
		return gson.toJson(bookingOnMapService.getEnqDtl(projectId, finalVerticales));
	}

	/* Get dynamic Properties Other Charges */
	@RequestMapping(value = "/getpropOtherCharges", method = RequestMethod.GET, produces = "application/json")
	public String getpropOtherCharges(@RequestParam("propSfid") String propSfid,
			@RequestParam("projectId") String projectId) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();

		// propOtherChargesServiceImpl.getCharges(propSfid);
		return gson.toJson(propOtherChargesService.getCharges(propSfid, projectId));
		// return
		// gson.toJson(paymentPlanListService.getPaymentPlanList(propSfid));
	}

	/* END Get dynamic Properties Other Charges */

	/*
	 * @RequestMapping(value = { "/adminRqst" }, method = RequestMethod.POST)
	 * public String rqstForAdmin(@RequestParam("userId") String userId) {
	 * 
	 * GsonBuilder gsonBuilder = new GsonBuilder(); Gson gson =
	 * gsonBuilder.create();
	 * 
	 * List<RequestAction> requestAction =
	 * requestActionService.rqstForAdmin(userId);
	 * //model.addAttribute("requestAction", requestAction);
	 * 
	 * return gson.toJson(requestAction); }
	 */

	@RequestMapping(value = "/projectDataList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getProjectDataList(@RequestParam("region") String region) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		List<ProjectRegion> projectList = projectRegionService.getProjectData(region);

		return gson.toJson(projectList);
	}

	@RequestMapping(value = { "/otpRequestOC" }, method = RequestMethod.POST)
	public synchronized String otpRequestOC(@RequestParam("herokuEnqId") String herokuEnqId) {
		// Send SMS
		String customerContact = "8356919019";
		String OTP = OtpGenerate.OTP();
		String text = OTP
				+ " is your OTP for verification of your KYC documents . OTP is confidential. For security reasons, DO NOT SHARE THIS OTP WITH ANYONE.";
		try {
			text = URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SendSMS.SMSSend(customerContact, text);

		// Update Other OTP Requests
		OTPRequestOC updateOTP = new OTPRequestOC();
		updateOTP.setIsactive("I");
		updateOTP.setEnquiry_id(herokuEnqId);
		oTPRequestOCService.updatePreviousOtp(updateOTP);

		// Insert New OTP Request
		OTPRequestOC action = new OTPRequestOC();
		System.out.println("OTP for Offer Creation: " + OTP);
		action.setOtp(OTP);
		action.setApp_type("CostsheetOfferRequest");
		action.setEnquiry_id(herokuEnqId);
		action.setIsactive("A");
		oTPRequestOCService.insertOTPRqst(action);

		return "";
	}

	@RequestMapping(value = { "/otpOCValidate" }, method = RequestMethod.POST)
	public synchronized String otpOCValidate(@RequestParam("OTPValidate") String OTPValidate,
			@RequestParam("herokuEnqId") String herokuEnqId) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		OTPRequestOC oTPRequestOC = oTPRequestOCService.validateOTP(OTPValidate, herokuEnqId);

		if (oTPRequestOC != null && !oTPRequestOC.equals("")) {
			String OTP = oTPRequestOC.getOtp();
			return OTP;
		}
		return "";
	}

	@RequestMapping(value = { "/sendForApproval" }, method = RequestMethod.POST)
	public String sendForApproval(@RequestParam("timeid") String timeid, @RequestParam("actionAR") String actionAR,
			@RequestParam("selable") String selable, @RequestParam("otherAmount") String otherAmount,
			@RequestParam("UnitName") String unitName, @RequestParam("towerName") String towerName,
			@RequestParam("projectsfid") String projectid, @RequestParam("herokuEnqId") String herokuEnqId,
			@RequestParam("userName") String userName, /*
														 * Added By Satheesh K -
														 * Date : 10-06-2020 -
														 * Property Name Added
														 * on Cost sheet Page
														 */
			@RequestParam("salesConA") String salesConA, @RequestParam("projectname") String projectname,
			@RequestParam("propertyname") String propertyname,@RequestParam("limit_amount") String limit_amount, HttpServletRequest req) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		HttpSession httpSession = req.getSession();

		/*
		 * GsonBuilder gsonBuilder = new GsonBuilder(); Gson gson = new
		 * GsonBuilder().disableHtmlEscaping().create(); List<ProjectLaunch>
		 * adt=projectLaunchService.getActiveProjectList(); return
		 * gson.toJson(adt);
		 */

		/* Commented By Satheesh K - Reason: No Use of Code */
		/*
		 * String mobileNo= "8356919010";
		 * 
		 * if (projectid.equals("a1l6F000003MOeRQAW") ||
		 * projectid.equals("a1l6F000002kSvMQAU") ||
		 * projectid.equals("a1l6F000002QyqOQAS") ||
		 * projectid.equals("a1l6F000002kSuPQAU") ||
		 * projectid.equals("a1l6F000002kSuVQAU") ||
		 * projectid.equals("a1l6F000002kSxTQAU") ||
		 * projectid.equals("a1l6F000002kSuZQAU") ||
		 * projectid.equals("a1l6F000002kSuYQAU") ||
		 * projectid.equals("a1l6F000002kSxVQAU") ||
		 * projectid.equals("a1l6F000002kSxPQAU") ||
		 * projectid.equals("a1l6F000002kSxNQAU") ||
		 * projectid.equals("a1l6F000002kSxLQAU") ||
		 * projectid.equals("a1l6F000005jSk0QAE") ||
		 * projectid.equals("a1l6F000002kSuQQAU") ||
		 * projectid.equals("a1l6F000002kSxSQAU") ||
		 * projectid.equals("a1l6F000003SDgIQAW") ||
		 * projectid.equals("a1l6F000002kT0zQAE") ||
		 * projectid.equals("a1l6F000002X6INQA0") ||
		 * projectid.equals("a1l6F000004Q3l6QAC") ||
		 * projectid.equals("a1l6F0000036fY5QAI") ||
		 * projectid.equals("a1l6F000008fqcuQAA") ||
		 * projectid.equals("a1l6F000002XIdMQAW") ||
		 * projectid.equals("a1l6F000002QyUwQAK") ||
		 * projectid.equals("a1l6F000003DRnNQAW") ||
		 * projectid.equals("a1l6F000002X6IOQA0") ||
		 * projectid.equals("a1l6F000002X6IMQA0") ||
		 * projectid.equals("a1l6F000002qLhcQAE") ) {
		 * System.out.println("123 Mumbai Project"); mobileNo = "9769495271";
		 * }else if(projectid.equals("a1l6F0000047Q1xQAE")) {
		 * System.out.println("South Project"); mobileNo = "9742750002";
		 * 
		 * 
		 * //mobileNo = "8356919019"; }else { mobileNo= "8356919019";
		 * System.out.println("Else 123"); }
		 */

		// Send SMS

		String OTP = OtpGenerate.OTP();

		// Update Other OTP Requests
		OTPRequestOC updateOTP = new OTPRequestOC();
		updateOTP.setIsactive("I");
		updateOTP.setEnquiry_id(herokuEnqId);
		oTPRequestOCService.updatePreviousOtp(updateOTP);

		// Insert New OTP Request
		OTPRequestOC action = new OTPRequestOC();
		System.out.println("OTP for Offer Creation: " + OTP);
		action.setOtp(OTP);
		action.setApp_type("CostsheetOfferRequest");
		action.setEnquiry_id(herokuEnqId);
		action.setIsactive("A");
		oTPRequestOCService.insertOTPRqst(action);

		List<Vw_UserProjectMapping> userList = vW_UserMasterService.getOfferApprovalUser(projectid);
		if (userList != null) {
			for (int i = 0; i < userList.size(); i++) {
				Vw_UserProjectMapping customerContact = userList.get(i);
				/*
				 * String text = "Dear "+customerContact.getUser_name()+
				 * ", Approval request from "+userName +
				 * "  for Unit{"+unitName+"}, " + " Tower {"+towerName+"}, " +
				 * "Discount Per Sqft{"+otherAmount+"} , Value ("+(Integer.
				 * valueOf(otherAmount)*
				 * Integer.valueOf(selable))+"). Kindly share the code "
				 * +OTP+" with Closing Manager to approve.";
				 */

				/*
				 * Added By Satheesh K - Date : 10-06-2020 - Property Name Added
				 * on Cost sheet others OTP
				 */
				/*
				 * String text = "Dear Approver"+
				 * ", Approval request from "+userName +
				 * "  for Unit{"+unitName+"}, " + " Tower {"+towerName+"}, " +
				 * "Discount Value ("+otherAmount+"). Kindly share the code "
				 * +OTP+" with Closing Manager to approve.";
				 */
				// + "Discount Per Sqft{"+otherAmount+"} , Value
				// ("+(Double.parseDouble(otherAmount)*Double.parseDouble(selable))+").
				// Kindly share the code "+OTP+" with Closing Manager to
				// approve.";
				Double apr = 0.00;

				apr = (Double.parseDouble(salesConA) / Double.parseDouble(selable));
				String text = userName.trim() + " has requested for a discount of Rs.( " + otherAmount + ") " + "for {"
						+ propertyname + "} in {" + projectname + "}, Effective APR {" + Math.round(apr)
						+ "}. Please share the OTP " + OTP + " to approve the discount. ";
				String emailText=text;
				// + " for Unit{"+unitName+"}, "
				// + " Tower {"+towerName+"}, "
				// + "Discount Value ("+otherAmount+"). Kindly share the code
				// "+OTP+" with Closing Manager to approve.";
				// + "Discount Per Sqft{"+otherAmount+"} , Value
				// ("+(Double.parseDouble(otherAmount)*Double.parseDouble(selable))+").
				// Kindly share the code "+OTP+" with Closing Manager to
				// approve.";

				// <SM Name> has requested for a discount of Rs. <Discount
				// Amount> for <Unit No.> in <Project Name>, Effective APR
				// [Sales Consideration (A) / Saleable Area]. Please share the
				// OTP ** to approve the discount.
				try {
					text = URLEncoder.encode(text, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				
				//SendSMS.ShreeSMSSend(customerContact.getMobileNo(), text);
				/*String otpbypass = sysConfigService.getValue(SysConfigEnum.APPROVAL_OTP_BYPASS,"APPROVAL_OTP_BYPASS");
				if(otpbypass.equals("true")) {*/
				String smtpip = sysConfigService.getValue(SysConfigEnum.SMTP_IP, "SMTP_IP");
				String smtpPort = sysConfigService.getValue(SysConfigEnum.SMTP_PORT, "SMTP_PORT");
				String subject="D4U - OTP for Offer Approval";
				if(limit_amount!=null && limit_amount.length()>0 )
				{
					double limit_amount_math=Double.parseDouble(limit_amount);
					double otherAmount_math=Double.parseDouble(otherAmount);
					
					if(limit_amount_math<otherAmount_math)
					{
						//send otp to region head
//						SendMailThreadUtil mail =new SendMailThreadUtil(customerContact.getSitehead_user_mail(),"", subject, emailText,smtpip,smtpPort);
						userList.get(i).setIsregionhead_approval(true);
						SendSMS.SMSSend(customerContact.getRegion_head_mobile(), text);
						new SendMailThreadUtil(customerContact.getRegion_head_email(),"", subject, emailText,smtpip,smtpPort);
						String siteheadMail = customerContact.getSitehead_user_name().trim()+" has requested for discount of Rs. ("+otherAmount+") "
								+ " for {"+propertyname+"} in {"+projectname+"}. Effective APR {"+Math.round(apr)+"}. OTP shared with "+customerContact.getSitehead_user_name().trim()+" for approval. Regards, Godrej Properties.";
						SendSMS.SMSSend(customerContact.getSitehead_user_mobile(), siteheadMail);
						new SendMailThreadUtil(customerContact.getSitehead_user_mail(),"", subject, siteheadMail,smtpip,smtpPort);
					}
					else
					{
						//send otp to site head
						SendSMS.SMSSend(customerContact.getMobileNo(), text);
						new SendMailThreadUtil(customerContact.getSitehead_user_mail(),"", subject, emailText,smtpip,smtpPort);
					}
				}
				else
				{
					SendSMS.SMSSend(customerContact.getMobileNo(), text);
					new SendMailThreadUtil(customerContact.getSitehead_user_mail(),"", subject, emailText,smtpip,smtpPort);
				}
				
					/*SendMailThreadUtil mail =new SendMailThreadUtil(customerContact.getEmailid(),	"sathish.kyatham@godrejproperties.com", subject, emailText,smtpip,smtpPort);*/
				/*}*/
				// SendSMS.SMSSend(mobileNo, text);

				// SendSMS.SMSSend("8356919019", text);

				// TO DO SEND SMS customerContact.getMobileNo()
				//
			}

			/* Commented by A 20190626 */
			/*
			 * UnitDtl actioDtl = new UnitDtl ();
			 * 
			 * actioDtl.setTimeid(Integer.valueOf(timeid));
			 * actioDtl.setSendforapproval(actionAR);
			 * 
			 * rqstProcessService.updateSendFApproval(actioDtl);
			 */

			// unitExistsService.updateUnitDtl (uDtl);

		} else {
			return "";
		}
		// OTP SEND IN RESPONCE
		return gson.toJson(userList);
	}

	private boolean isUnderPMAY(String offerId, String projectSfid, double basicSalePrice) {
		return (offerId != null && offerId.length() == 18 && "a1l2s00000000X5AAI".equals(projectSfid)
				&& basicSalePrice <= 4500000);
	}

	@RequestMapping(value = { "/getcreatedOffersList" }, method = RequestMethod.GET)
	public String getcreatedOffersList(@RequestParam("projectid") String projectid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return gson.toJson(balanceDetailsService.getCreatedOffersList(projectid));
	}

	@RequestMapping(value = { "/rqstExist" }, method = RequestMethod.POST)
	public String updateBSP(@RequestParam("unitNo") String unitNo) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		UnitDtl uDtl = unitExistsService.unitExist(unitNo);

		// System.out.println("Unit Exist ::: " + unitNo);

		return gson.toJson(uDtl);
	}

	/* ********* Download File ********* */

	@RequestMapping(value = { "/xyz" }, method = RequestMethod.GET)
	public String downloadPDF(HttpServletRequest request, HttpServletResponse response) throws IOException {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		/**
		 * Size of a byte buffer to read/write file
		 */
		final int BUFFER_SIZE = 14096;

		/**
		 * Path of the file to be downloaded, relative to application's
		 * directory
		 */
		// private String filePath = null;

		/**
		 * Method for handling file download request from client
		 */

		String fullPath = "C:\\costSheetPDF\\1.pdf";
		System.out.println("fullPath = " + fullPath);
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();

		return gson.toJson("");
	}

	/* ******** END Download File ********* */

	/* END Add by A */

	/*
	 * @RequestMapping(value = "/getUserDeatails", method = RequestMethod.POST,
	 * produces = "application/json") public String
	 * getSurveyDataOld(@RequestParam("mobileno") String mobileno,
	 * 
	 * @RequestParam("projectid") String projectId) { GsonBuilder gsonBuilder =
	 * new GsonBuilder(); Gson gson = gsonBuilder.create();
	 * System.out.println("Search Mobile =" + mobileno); // return
	 * gson.toJson(""); return
	 * gson.toJson(userContactService.findMobileNoExist(mobileno, projectId)); }
	 */
	@RequestMapping(value = "/setContact", method = RequestMethod.GET, produces = "application/json")
	public String setContact() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		Contact s = new Contact();
		s.setMobileNo("8898821453");
		s.setFirstName("Balram");
		/* userContactService.save(s); */
		return gson.toJson(s);
	}

	@RequestMapping(value = "/insetRequest", method = RequestMethod.GET, produces = "application/json")
	public String insertEnquiry() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		System.out.println("Search Mobile =");
		Enquiry enq = new Enquiry();
		/*
		 * enq.setPropstrength__primary_contact__c("003O000001AqVeU");
		 * enq.setPropstrength__request_source__c("Walk-in");
		 * enq.setPropstrength__enquiry_type__c("Direct");
		 * enq.setPropStrength__Request_Status__c("Open");
		 * enq.setPropstrength__project__c("a20O0000002XKut");
		 */
		pushEnqService.insertInputEnquiry(enq);
		// return gson.toJson("");
		return gson.toJson(enq);
	}

	// getTower?project_code
	@RequestMapping(value = "/getTower", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getTower(@RequestParam("project_code") String project_code) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(paymentPlanService.getTower(project_code));
	}

	@RequestMapping(value = "/regionList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getRegionList(@RequestParam("project_code") String project_code) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(projectRegionService.getRegion());
	}

	// getFloor?project_code&tower_code
	@RequestMapping(value = "/getFloor", method = RequestMethod.GET, produces = "application/json")
	public String getfloor(@RequestParam("project_code") String project_code,
			@RequestParam("tower_code") String tower_code) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<PaymentPlan> plans = paymentPlanService.getfloor(project_code, tower_code);

		return gson.toJson(plans);
	}

	// /getunittype?project_code&tower_code&floor_code
	@RequestMapping(value = "/getunittype", method = RequestMethod.GET, produces = "application/json")
	public String getUnitType(@RequestParam("project_code") String project_code,
			@RequestParam("tower_code") String tower_code, @RequestParam("floor_code") String floor_code) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<PaymentPlan> plans = paymentPlanService.getUnitType(project_code, tower_code, floor_code);

		return gson.toJson(plans);
	}

	/// gethouseunit?project_code=&tower_code&floor_code&unit
	@RequestMapping(value = "/gethouseunit", method = RequestMethod.GET, produces = "application/json")
	public String getHouseUnit(@RequestParam("project_code") String project_code,
			@RequestParam("tower_code") String tower_code, @RequestParam("floor_code") String floor_code,
			@RequestParam("unit") String unit) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<PaymentPlan> plans = paymentPlanService.getHouseUnit(project_code, tower_code, floor_code, unit);
		return gson.toJson(plans);
	}

	/* @ResponseBody */
	/*
	 * @RequestMapping(value = "/getPaymentSchedule", method =
	 * RequestMethod.POST, produces = "application/json") public String
	 * getCostSheet(@RequestParam("timeid") String
	 * timeid, @RequestParam("contactNo") String
	 * contactNo, @RequestParam("caDtl") String projectId) throws JRException,
	 * IOException {
	 */


	public String getPaymentTrxValidationDate(String paymentTrxDaysAllow) {

		
		 int days = 3;
		 
		 if (paymentTrxDaysAllow != null) {
			 try {
				 days = Integer.parseInt(paymentTrxDaysAllow);
			} catch (Exception e) {
				log.error("error", e);
			}
		 } else {
			days = 3;
		 }
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String newDate = sdf.format(cal.getTime());

		return newDate;

	}

	 
	 
	 @RequestMapping(value = "/getProjectPlan", method = RequestMethod.GET, produces = "application/json")
		public String getProjectPlan(@RequestParam("herokuEnqId") String herokuEnqId,  @RequestParam("pymtPlanSfid") String pymtPlanSfid, @RequestParam("project_code") String project_code ,@RequestParam("unit") String unit,  @RequestParam("towerCode") String towerCode,  @RequestParam("typology") String typology) throws JRException, IOException{
			
		 long timeId = Long.parseLong("1234");

		/*
		 * GeneratePDF solution = new GeneratePDF ();
		 * solution.PDFReport(timeId);
		 */

		// Update Other OTP Requests
		OTPRequestOC updateOTP = new OTPRequestOC();
		updateOTP.setIsactive("I");
		updateOTP.setEnquiry_id(herokuEnqId);
		oTPRequestOCService.updatePreviousOtp(updateOTP);

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<PaymentPlan> plans = paymentPlanService.getProjectPlan(project_code, unit, towerCode, pymtPlanSfid);
		List<PaymentPlanJson> paymentPlanJsons = new ArrayList<>();
		// Need to Change Here
		double bspAmount = 0;

		double bspPerVal = 0;

		if (plans.size() > 0)
			bspAmount = bSPAgainstPymtPlanService.getPaymentPlanBSPList(project_code, unit, towerCode, pymtPlanSfid,
					plans.get(0).getPropstrength__unit_type__c(), plans.get(0).getInventory_category__c());
		bspPerVal = bSPAgainstPymtPlanService.getPaymentPlanPerBSP(project_code, unit, towerCode, pymtPlanSfid,
				plans.get(0).getPropstrength__unit_type__c(), plans.get(0).getInventory_category__c());

		if (plans.size() > 0) {

			// DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			// String nowData = df.format(new Date());

			String paymentTrxDaysAllow = sysConfigService.getValue(SysConfigEnum.PAYMENT_TRX_ALLOWED_DAYS,
					project_code);

			String allowedUptoDate = null;

			if (paymentTrxDaysAllow != null) {
				allowedUptoDate = getPaymentTrxValidationDate(paymentTrxDaysAllow);
			}

			PaymentPlan paym = plans.get(0);
			PaymentPlanJson paymentPlanJson = new PaymentPlanJson();
			paymentPlanJson.setID(paym.getPropstrength__house_unit_no__c());
			paymentPlanJson.setTypology(paym.getPropstrength__unit_type__c());
			paymentPlanJson.setBasicSalePricepersquarefeet(paym.getPropstrength__rate_per_unit_area__c());
			paymentPlanJson.setCarpetArea(paym.getPropstrength__carpet_area__c());
			paymentPlanJson.setExclusiveBalconyAreaTerraceArea(paym.getTerrace_area_sq_ft__c());
			paymentPlanJson.setSaleableArea(paym.getSaleable_area__c());
			paymentPlanJson.setBasicSalePrice(paym.getPropstrength__rate_per_unit_area__c());
			paymentPlanJson.setGeneratorCharges(paym.getGenerator_charges());
			paymentPlanJson.setGasBankCharges(paym.getGas_bank_charges());
			paymentPlanJson.setBESCOMWaterSupplyandSewage(paym.getBescom_water_supply());
			paymentPlanJson.setLegalandKhataCharges(paym.getLegal_and_khata_charges());

			paymentPlanJson.setBalcony_area_sq_ft__c(paym.getBalcony_area_sq_ft__c());
			paymentPlanJson.setBalcony_area_sq_mt__c(paym.getBalcony_area_sq_mt__c());
			paymentPlanJson.setTerrace_area_sq_mt__c(paym.getTerrace_area_sq_mt__c());
			paymentPlanJson.setCarpet_area_converted__c(paym.getCarpet_area_converted__c());
			paymentPlanJson.setUnitsfid(paym.getSfid());

			paymentPlanJson.setFloorName(paym.getFloor_name__c());
			paymentPlanJson.setTowerName(paym.getTower_name__c());

			paymentPlanJson.setPmay(paym.isPmay());
			paymentPlanJson.setNew_tax(paym.getNew_tax());
			paymentPlanJson.setOld_tax(paym.getOld_tax());
			paymentPlanJson.setPropstrength__gst_status__c(paym.getPropstrength__gst_status__c());

			// paymentPlanJson.setBsp_amount(paym.getBsp_amount());
			paymentPlanJson.setBsp_amount(String.valueOf(bspAmount));// Added By
																		// Satheesh

			paymentPlanJson.setBsp_per(bspPerVal);

			paymentPlanJson.setAppurtenant_area_sq_mt__c(paym.getAppurtenant_area_sq_mt__c());

			paymentPlanJson.setPropstrength__completion_certificate_received__c(
					paym.isPropstrength__completion_certificate_received__c());
			paymentPlanJson.setPropstrength__category__c(paym.getPropstrength__category__c());
			paymentPlanJson.setPropstrength__pmay_abatement__c(paym.isPropstrength__pmay_abatement__c());
			paymentPlanJson.setBank__c(paym.getBank__c());
			paymentPlanJson.setProperty_facing__c(paym.getProperty_facing__c());
			paymentPlanJson.setPaymentTrxdaysVal(paymentTrxDaysAllow);

			paymentPlanJson.setNowData(allowedUptoDate);
			paymentPlanJson.setWing_block__c(paym.getWing_block__c());
			/*
			 * Added By Satheesh K - Date : 10-06-2020 - Property Name Added on
			 * Cost sheet Page
			 */
			paymentPlanJson.setPropstrength__property_name__c(paym.getPropstrength__property_name__c());
			
			
			paymentPlanJson.setLength_sqm__c(paym.getLength_sqm__c());
			paymentPlanJson.setBreadth_sqm__c(paym.getBreadth_sqm__c());
			paymentPlanJson.setPlot_area_sqyd__c(paym.getPlot_area_sqyd__c());
			paymentPlanJson.setDiscount_Limit_Amount__c(paym.getDiscount_Limit_Amount__c());
			
			// paymentPlanJson.set (paym.getSfid());

			/*
			 * List<PaymentSheet> paymentPlanLineItems=
			 * paymentSheetService.getpaymentsheet(plans.get(0).getSfid());
			 * 
			 * if(paymentPlanLineItems.size()>0) { for(int
			 * i=0;i<paymentPlanLineItems.size();i++) { PaymentSheet
			 * paymentSheet=paymentPlanLineItems.get(i);
			 * if(paymentSheet.getSequence().equals("1")) {
			 * paymentPlanJson.setPreferredLocationCharges(paymentSheet.
			 * getCasetest());
			 * if("Flexible".equals(paymentSheet.getPropstrength__type__c())) {
			 * paymentPlanJson.setBasicSalePricepersquarefeetYN("Y");
			 * paymentPlanJson.setFlexibleYN("Y");
			 * 
			 * } }else if(paymentSheet.getSequence().equals("2")) {
			 * paymentPlanJson.setFloorRiseCharges(paymentSheet.getCasetest());
			 * if("Flexible".equals(paymentSheet.getPropstrength__type__c()))
			 * paymentPlanJson.setFloorRiseChargesYN("Y"); } else
			 * if(paymentSheet.getSequence().equals("3")) {
			 * paymentPlanJson.setACoveredCarPark(paymentSheet.getCasetest());
			 * if("Flexible".equals(paymentSheet.getPropstrength__type__c()))
			 * paymentPlanJson.setACoveredCarParkYN("Y"); } else
			 * if(paymentSheet.getSequence().equals("4")) {
			 * paymentPlanJson.setAClubHouseCharges(paymentSheet.getCasetest());
			 * if("Flexible".equals(paymentSheet.getPropstrength__type__c()))
			 * paymentPlanJson.setAClubHouseChargesYN("Y"); } else
			 * if(paymentSheet.getSequence().equals("5")) {
			 * paymentPlanJson.setAdvanceMaintenance(paymentSheet.getCasetest())
			 * ; if("Flexible".equals(paymentSheet.getPropstrength__type__c()))
			 * paymentPlanJson.setAdvanceMaintenanceYN("Y"); } else
			 * if(paymentSheet.getSequence().equals("6")) {
			 * paymentPlanJson.setSinkingFundDeposit(paymentSheet.getCasetest())
			 * ; if("Flexible".equals(paymentSheet.getPropstrength__type__c()))
			 * paymentPlanJson.setSinkingFundDepositYN("Y"); } }
			 * paymentPlanJsons.add(paymentPlanJson);
			 * 
			 * }
			 
			 
			 
			 
			 String paymentTrxDaysAllow = sysConfigService.getValue(SysConfigEnum.PAYMENT_TRX_ALLOWED_DAYS, project_code);
			 
			 String allowedUptoDate = null;
			 
			 //if (paymentTrxDaysAllow != null) {
				 allowedUptoDate = getPaymentTrxValidationDate(paymentTrxDaysAllow);
			 //}
			  
			 
			 PaymentPlan paym =plans.get(0);
			 PaymentPlanJson paymentPlanJson= new PaymentPlanJson();
			 paymentPlanJson.setID(paym.getPropstrength__house_unit_no__c());
			 paymentPlanJson.setTypology(paym.getPropstrength__unit_type__c());
			 paymentPlanJson.setBasicSalePricepersquarefeet(paym.getPropstrength__rate_per_unit_area__c());
			 paymentPlanJson.setCarpetArea(paym.getPropstrength__carpet_area__c());
			 paymentPlanJson.setExclusiveBalconyAreaTerraceArea(paym.getTerrace_area_sq_ft__c());
			 paymentPlanJson.setSaleableArea(paym.getSaleable_area__c());
			 paymentPlanJson.setBasicSalePrice(paym.getPropstrength__rate_per_unit_area__c());
			 paymentPlanJson.setGeneratorCharges(paym.getGenerator_charges());
			 paymentPlanJson.setGasBankCharges(paym.getGas_bank_charges());
			 paymentPlanJson.setBESCOMWaterSupplyandSewage(paym.getBescom_water_supply());
			 paymentPlanJson.setLegalandKhataCharges(paym.getLegal_and_khata_charges());
			 
			 paymentPlanJson.setBalcony_area_sq_ft__c( paym.getBalcony_area_sq_ft__c());
			 paymentPlanJson.setBalcony_area_sq_mt__c( paym.getBalcony_area_sq_mt__c());
			 paymentPlanJson.setTerrace_area_sq_mt__c(paym.getTerrace_area_sq_mt__c());
			 paymentPlanJson.setCarpet_area_converted__c(paym.getCarpet_area_converted__c());
			 paymentPlanJson.setUnitsfid(paym.getSfid());
			 
			 paymentPlanJson.setFloorName(paym.getFloor_name__c());
			 paymentPlanJson.setTowerName(paym.getTower_name__c());
			 
			 
			 paymentPlanJson.setPmay(paym.isPmay());
			 paymentPlanJson.setNew_tax(paym.getNew_tax());
			 paymentPlanJson.setOld_tax(paym.getOld_tax());
			 paymentPlanJson.setPropstrength__gst_status__c(paym.getPropstrength__gst_status__c());
			 
//			 paymentPlanJson.setBsp_amount(paym.getBsp_amount());
			 paymentPlanJson.setBsp_amount(String.valueOf(bspAmount));//Added By Satheesh
			 
			 paymentPlanJson.setBsp_per(bspPerVal);
			 
			 paymentPlanJson.setAppurtenant_area_sq_mt__c(paym.getAppurtenant_area_sq_mt__c());
			 
			 
			 paymentPlanJson.setPropstrength__completion_certificate_received__c(paym.isPropstrength__completion_certificate_received__c());
			 paymentPlanJson.setPropstrength__category__c(paym.getPropstrength__category__c());
			 paymentPlanJson.setPropstrength__pmay_abatement__c(paym.isPropstrength__pmay_abatement__c());
			 paymentPlanJson.setBank__c(paym.getBank__c());
			 paymentPlanJson.setProperty_facing__c(paym.getProperty_facing__c());
			 paymentPlanJson.setPaymentTrxdaysVal(paymentTrxDaysAllow);
			 
			 paymentPlanJson.setNowData(allowedUptoDate);
			 paymentPlanJson.setWing_block__c(paym.getWing_block__c()); 
			 /*Added By Satheesh K - Date : 10-06-2020 - Property Name Added on Cost sheet Page*/
			 paymentPlanJson.setPropstrength__property_name__c(paym.getPropstrength__property_name__c());
			 //paymentPlanJson.set (paym.getSfid());
			 
			 
			 /*
			 List<PaymentSheet> paymentPlanLineItems= paymentSheetService.getpaymentsheet(plans.get(0).getSfid());
			 
			 if(paymentPlanLineItems.size()>0) {
				 for(int i=0;i<paymentPlanLineItems.size();i++) {
				 PaymentSheet paymentSheet=paymentPlanLineItems.get(i);
				 if(paymentSheet.getSequence().equals("1")) {
					 paymentPlanJson.setPreferredLocationCharges(paymentSheet.getCasetest());
					 if("Flexible".equals(paymentSheet.getPropstrength__type__c())) {
						 paymentPlanJson.setBasicSalePricepersquarefeetYN("Y");
						 paymentPlanJson.setFlexibleYN("Y");
						 
					 }
				 }else if(paymentSheet.getSequence().equals("2")) {
					 paymentPlanJson.setFloorRiseCharges(paymentSheet.getCasetest());
					 if("Flexible".equals(paymentSheet.getPropstrength__type__c()))
					 paymentPlanJson.setFloorRiseChargesYN("Y");
				 } else if(paymentSheet.getSequence().equals("3")) {
					 paymentPlanJson.setACoveredCarPark(paymentSheet.getCasetest());
					 if("Flexible".equals(paymentSheet.getPropstrength__type__c()))
					 paymentPlanJson.setACoveredCarParkYN("Y");
				 } else if(paymentSheet.getSequence().equals("4")) {
					 paymentPlanJson.setAClubHouseCharges(paymentSheet.getCasetest());
					 if("Flexible".equals(paymentSheet.getPropstrength__type__c()))
					 paymentPlanJson.setAClubHouseChargesYN("Y");
				 } else if(paymentSheet.getSequence().equals("5")) {
					 paymentPlanJson.setAdvanceMaintenance(paymentSheet.getCasetest());
					 if("Flexible".equals(paymentSheet.getPropstrength__type__c()))
					 paymentPlanJson.setAdvanceMaintenanceYN("Y");
				 } else if(paymentSheet.getSequence().equals("6")) {
					 paymentPlanJson.setSinkingFundDeposit(paymentSheet.getCasetest());	 
					 if("Flexible".equals(paymentSheet.getPropstrength__type__c()))
					 paymentPlanJson.setSinkingFundDepositYN("Y");
				 } 
				 }
				 paymentPlanJsons.add(paymentPlanJson);
				 
			 }
			 */

			paymentPlanJsons.add(paymentPlanJson);

		}
		return gson.toJson(paymentPlanJsons);
	}

	// @RequestParam("project_code") String project_code ,@RequestParam("unit")
	// String unit,
	@RequestMapping(value = "/getpaymentplanlist", method = RequestMethod.GET, produces = "application/json")
	public String getpaymentplanlist(@RequestParam("payment_plan_sfid") String payment_plan_sfid) { // @RequestParam("project_code")
																									// String
																									// project_code
																									// ,@RequestParam("unit")
																									// String
																									// unit
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		/*
		 * List<PaymentPlan>
		 * plans=paymentPlanService.getProjectPlan(project_code,unit);
		 * if(plans.size()>0) { PaymentPlan paym =plans.get(0); };
		 */

		List<PaymentPlanLineItem> paymentPlanLineItems = paymentPlanLineItemService
				.getpaymentplanlist(payment_plan_sfid);
		return gson.toJson(paymentPlanLineItems);
	}

	@RequestMapping(value = "/bspTaxRecord", method = RequestMethod.GET, produces = "application/json")
	public String bspTaxRecord(@RequestParam("projectcode") String payment_plan_sfid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		List<BSPTaxRecord> bSPTaxRecord = bSPTaxRecordService.getBSPTax();
		return gson.toJson(bSPTaxRecord);
	}

	/*
	 * @RequestMapping(value = { "/salesRqst" }, method = RequestMethod.POST)
	 * public String rqstForSales(@RequestParam("userId") String userId) {
	 * 
	 * GsonBuilder gsonBuilder = new GsonBuilder(); Gson gson =
	 * gsonBuilder.create();
	 * 
	 * List<RequestAction> requestAction =
	 * requestActionService.getRqstForSales(userId);
	 * 
	 * return gson.toJson(requestAction); }
	 */

	/* New payment plan with Other Charges */
	@RequestMapping(value = "/getPaymentPlanOtherCharges", method = RequestMethod.POST)
	public String getPaymentPlanOtherCharges(@RequestParam("unitSfid") String unitSfid,
			@RequestParam("paymentPlanSfid") String paymentPlanSfid, @RequestParam("projectid") String projectid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<PaymentPlanWithOtherCharge> paymentPlanLineItems = paymentPlanWithOtherChargeService
				.getppAndOtherCharges(unitSfid, paymentPlanSfid, projectid);
		System.out.println("Return Jsom:-" + gson.toJson(paymentPlanLineItems));
		return gson.toJson(paymentPlanLineItems);
	}
	/* New payment plan with Other Charges */

	@RequestMapping(value = "/getFirstMilstone", method = RequestMethod.POST)
	public String getFirstMilstone(@RequestParam("paymentPlanSfid") String paymentPlanSfid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		List<WithoutOtherChargesPP> withoutOtherChargesPPLineItems = withoutOtherChargesPPService
				.getPPData(paymentPlanSfid);
		return gson.toJson(withoutOtherChargesPPLineItems);
	}

	@RequestMapping(value = "/getpaymentsheet", method = RequestMethod.GET, produces = "application/json")
	public String getpaymentsheet() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		List<PaymentSheet> paymentPlanLineItems = paymentSheetService.getpaymentsheet("data");

		return gson.toJson(paymentPlanLineItems);
	}

	@RequestMapping(value = "/kycSysnc", method = RequestMethod.GET, produces = "application/json")
	public String kycSysnc() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		HerukoSyncController heru = new HerukoSyncController();
		List<ApplicantDate> list = heru.getPendingKYCSync();
		for (ApplicantDate apll : list) {
			InsertUpdateService service = new InsertUpdateService();
			if (apll.applicanttype.equals("1")) {
				System.out.println("Upldate Contact Method");
				// service.updateContact(apll);

				service.documentStoragePan(apll, apll.getContactID());
				service.documentStorageAddressProf(apll, apll.getContactID());

				String doctStorageID = service.getStorageDocumentID("P" + apll.getExternalID());
				// Thread.sleep(10000);
				String doctStorageID2 = service.getStorageDocumentID("A" + apll.getExternalID());
				// Thread.sleep(10000);
				TestAttachment attachment = new TestAttachment();
				attachment.uploadDocumnetsPan(apll, doctStorageID2);
				//// Thread.sleep(10000);
				attachment.uploadDocumnetsAddress1(apll, doctStorageID);
				// Thread.sleep(10000);

				if (apll.getAddress_doc_type().equals("Passport")) {
					attachment.uploadDocumnetsAddress2(apll, doctStorageID);
				}
				// service.updateSyncStatus(apll);

				// break;
			} else// appl.applicanttype
			{
				// String contactID = service.insertContact(apll);
				// service.documentStorage(apll,contactID);
				System.out.println("Create Contact Method");
				String contactID = service.insertContact(apll);

				// service.documentStoragePan(apll,contactID+apll.getApplicanttype());
				// service.documentStorageAddressProf(apll,contactID+apll.getApplicanttype());

				service.documentStoragePan(apll, contactID);
				service.documentStorageAddressProf(apll, contactID);

				String doctStorageID = service
						.getStorageDocumentID("P" + apll.getExternalID() + apll.getApplicanttype());
				String doctStorageID2 = service
						.getStorageDocumentID("A" + apll.getExternalID() + apll.getApplicanttype());
				TestAttachment attachment = new TestAttachment();
				attachment.uploadDocumnetsPan(apll, doctStorageID2);
				attachment.uploadDocumnetsAddress1(apll, doctStorageID);

				if (apll.getAddress_doc_type().equals("Passport")) {
					attachment.uploadDocumnetsAddress2(apll, doctStorageID2);
				}
				service.updateSyncStatus(apll);

			}
		}
		return "Done";
	}

	// /billingDataSysnc?page=21&count=420000&str_date=
	@RequestMapping(value = "/billingDataSysnc", method = RequestMethod.GET, produces = "application/json")
	public String DataSysnc(@RequestParam("page") String page, @RequestParam("count") String count,
			@RequestParam("str_date") String str_date) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<BillingData> billingDatas = billingViewService.getdataMapping(page, count, str_date);

		return gson.toJson(billingDatas);
	}

	// /billingDataCount?model=&str_date
	@RequestMapping(value = "/billingDataCount", method = RequestMethod.GET, produces = "application/json")
	public String DataSysnc(@RequestParam("model") String model, @RequestParam("str_date") String str_date) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String billingDatas = billingViewService.getdataCount(model, str_date);

		return gson.toJson(billingDatas);
	}

	@RequestMapping(value = "/OrderDataSysnc", method = RequestMethod.GET, produces = "application/json")
	public String OrderDataSysnc(@RequestParam("str_date") String str_date) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<OrderDataMapping> orderDataMappings = orderDataMapppingService.getdataMapping(str_date);

		return gson.toJson(orderDataMappings);
	}

	@RequestMapping(value = "/old_newValueDataSysnc", method = RequestMethod.GET, produces = "application/json")
	public String old_newValueDataSysnc() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<ValueOldNew> orderDataMappings = orderDataMapppingService.old_newValueDataSysnc();
		return gson.toJson(orderDataMappings);
	}

	@RequestMapping(value = "/insertUserMaster", method = RequestMethod.POST, produces = "application/json")
	public String insertUserMaster(@RequestParam("emailid") String emailid, @RequestParam("name") String name,
			@RequestParam("projectId") String projectId, @RequestParam("projectname") String projectname,
			@RequestParam("rolename") String rolename, @RequestParam("fromdate") java.sql.Date fromdate,
			@RequestParam("todate") java.sql.Date todate, @RequestParam("mobileno") String mobileno,
			@RequestParam("launchtype") String launchtype, HttpServletRequest req) {
		UserMaster master = new UserMaster();
		master.setUser_name(name);
		master.setEmailid(emailid);
		master.setProjectId(projectId);
		master.setProjectName(projectname);
		master.setPassword("pass@123");
		master.setRole(rolename);
		master.setIsActive("A");
		master.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		master.setLaunchtype(launchtype);
		master.setFromdate(fromdate);
		master.setTodate(todate);
		master.setMobileNo(mobileno);
		if (emailid != null && projectId != null & launchtype != null && fromdate != null && todate != null)
			return userMasterService.saveUser(master);
		else
			return "Enter mandatory values...";
	}

	@RequestMapping(value = "/getUserMaster", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getUserMaster(@RequestParam("projectid") String projectID) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(userMasterService.getUserList(projectID));
	}

	@RequestMapping(value = "/updateUserMaster", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String updateUserMaster(@RequestParam("userid") String userid, @RequestParam("status") String status,
			@RequestParam("mobileno") String mobileNo) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		UserMaster uMaster = new UserMaster();
		uMaster.setUser_id(Integer.parseInt(userid));
		uMaster.setIsActive(status);
		return gson.toJson(userMasterService.updateUserMaster(uMaster));
	}

	@RequestMapping(value = "/userValidateLogin", method = RequestMethod.POST, produces = "application/json")
	public String userValidateLogin(@RequestParam("emailid") String emailid, @RequestParam("password") String password,
			// @RequestParam("projectId") String projectId,
			HttpServletRequest req) {
		HttpSession session1 = req.getSession(true);
		/* session1.setMaxInactiveInterval(1 * 60); */
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		UserMaster master = new UserMaster();
		if (emailid.trim().equals("") || emailid.trim().equals("")) {
			master.setMsg("Credential can not be empty.");
			gson.toJson(master);
		}
		emailid = emailid.toLowerCase();
		master.setUser_name(emailid);
		master.setEmailid(emailid);
		master.setPassword(password);
		// master.setProjectId(projectId);
		if (emailid.contains("@godrejproperties.com")) {
			// changes required here
			LdapUserDetailsDto dto = new LdapUserDetailsDto();
			dto.setEmail(emailid);
			dto.setPassword(password);
			LdapUserDetailsDto ldap = ldapServiceController.getLdapUserDetails(dto);
			// boolean isAD=loginAD(master);
			boolean isAD = ldap.isIsvalid();
			if (isAD == false) {
				List<ADLoginPass> user = adUserLoginPassService.getUserList(emailid);
				if (user != null) {
					if (user.size() > 0) {
						isAD = true;
					}
				}
			}

			if (isAD) {
				master = validateUser(master, "A");
			} else {
				master.setMsg(
						"Please check your username and password. If you still can't login , your account might be locked, please send email to servicedesk@godrejinds.com or reset your password with https://passwordreset.microsoftonline.com");
				return gson.toJson(master);
			}
		} else {
			master = validateUser(master, "S");
		}
		// Changes By Satheesh
		Vw_UserMaster userMaster = vW_UserMasterService.getUserDetails(master.getUser_id());// ,projectId
		master.setPassword("");
		if (userMaster != null) {
			master.setProjectId(userMaster.getProjectName());
			master.setProjectName(userMaster.getProjectId());
			master.setRole(userMaster.getRole());

			HttpSession session = req.getSession();
			session.setAttribute("USERNAME", "" + userMaster.getUser_name());
			session.setAttribute("USERMOBILENO", "" + userMaster.getMobileNo());
			session.setAttribute("USERID", "" + master.getUser_id());
			session.setAttribute("USEREMAIL", master.getEmailid());
			session.setAttribute("ROLE", "" + master.getMst_user_rolemaster_id());
			session.setAttribute("PRONAME", userMaster.getProjectName());
			session.setAttribute("PROID", "" + userMaster.getProjectId());
			// session.setAttribute("PROID",""+
			// userMaster.getProjectId().toString());
			session.setAttribute("Assignto", "");
			session.setAttribute("Closingmgr", "" + userMaster.getClosingmgr());
			session.setAttribute("ISOTPADMIN", "" + master.getIsotpadmin());
			
			session.setAttribute("USER_VERTICALES", "" + master.getVerticales());

		} else {
			master.setMsg("You are not a user in D4U system.Please contact your site head for getting permission.");
		}
		return gson.toJson(master);
	}

	@RequestMapping(value = "/userValidate", method = RequestMethod.POST, produces = "application/json")
	public String deskLogin(@RequestParam("emailid") String emailid, @RequestParam("password") String password,
			HttpServletRequest req) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		UserMaster master = new UserMaster();
		master.setUser_name(emailid);
		master.setEmailid(emailid);
		master.setPassword(password);
		if (emailid.contains("@godrejproperties.com")) {
			master = validateUser(master, "A");
		} else {
			master = validateUser(master, "S");
		}
		Vw_UserMaster userMaster = vW_UserMasterService.getUserDetails(master.getUser_id());
		master.setPassword("");
		master.setProjectId(userMaster.getProjectId());
		master.setProjectName(userMaster.getProjectName());

		master.setPassword("");
		HttpSession session = req.getSession();
		session.setAttribute("USERID", master.getUser_id());
		session.setAttribute("USEREMAIL", master.getEmailid());
		session.setAttribute("ROLE", master.getMst_user_rolemaster_id());
		session.setAttribute("PRONAME", master.getProjectName());
		session.setAttribute("PROID", master.getProjectId());
		return gson.toJson(master);
	}

	// loginvalidator?emailid=&password=&employeeType=&deviceid&logintype=&lat=&log=
	@RequestMapping(value = "/loginvalidator", method = RequestMethod.GET, produces = "application/json")
	public String loginvalidator(@RequestParam("emailid") String emailid, @RequestParam("password") String password,
			@RequestParam("employeeType") String type, @RequestParam("deviceid") String deviceid,
			@RequestParam("logintype") String logintype, @RequestParam("lat") String lat,
			@RequestParam("log") String log) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		UserMaster master = new UserMaster();
		master.setUser_name(emailid);
		master.setEmailid(emailid);
		master.setPassword(password);
		master.setDeviceid(deviceid);
		master.setLatitude(lat);
		master.setLongitute(log);

		// master.setProjectId(projectid);
		/* Start */
		if (emailid.contains("@godrejproperties.com")) {
			type = "E";
		} else {
			type = "U";
		}

		if (type.equals("E")) {
			LdapUserDetailsDto dto = new LdapUserDetailsDto();
			dto.setEmail(emailid);
			dto.setPassword(password);
			LdapUserDetailsDto ldap = ldapServiceController.getLdapUserDetails(dto);
			// boolean isAD=loginAD(master);
			boolean isAD = ldap.isIsvalid();

			if (isAD) {
				master = validateUser(master, type);
				return gson.toJson(master);
			} else if (isAD == false) {
				master = validateUser(master, type);
				if (master.getUser_id() == 0) {
					master.setMsg("Invalid credentials.");
					return gson.toJson(master);
				}
				return gson.toJson(master);
			} else {
				master.setMsg("Invalid credentials.");
				return gson.toJson(master);

			}
			/*
			 * if(loginAD(master)) { master= validateUser(master,type); return
			 * gson.toJson(master); }else {
			 * master.setMsg("Invalid credentials."); return
			 * gson.toJson(master);
			 * 
			 * }
			 */

		} else if (type.equals("U")) {

			master = validateUser(master, type);
			return gson.toJson(master);
		} else {
			master.setMsg("Invalid credentials.");
			return gson.toJson(master);
		}

		/* End */
		// List<OrderDataMapping> orderDataMappings=
		// orderDataMapppingService.getdataMapping(str_date);

	}

	@SuppressWarnings("null")
	private UserMaster validateUser(UserMaster userMaster, String type) {
		UserMaster master = new UserMaster();
		master = userMasterService.searchUser(userMaster, type);
		if (master != null) {
			if (master.getIsActive().equals("A")) {
				if (master.getUser_id() == 0) {
					master.setLatitude(userMaster.getLatitude());
					master.setLongitute(userMaster.getLongitute());
					master.setMsg("Success");
					userMasterService.updateUser(master);
					return userMaster;
				} else {
					master.setMsg("Success");
					return master;
				}
			} else {
				userMaster.setMsg("Please contact  admin  for access app.");
				return userMaster;
			}

		} else {
			// userMaster.setIsActive("I");
			// userMasterService.saveUser(userMaster);
			userMaster.setMsg("Kindly contact admin for access.");
		}
		return userMaster;
	}

	public boolean loginAD(UserMaster master) {
		CallWebServices webService;
		boolean logins = false;
		try {
			webService = new CallWebServices("MobileService.asmx?wsdl", "GodrejiteLogin");
			webService.getProperties().add(webService.new RequestProperty("strUserName", master.getEmailid()));
			webService.getProperties().add(webService.new RequestProperty("strPassword", master.getPassword()));
			Object resultString = webService.CallWS();
			SoapObject result = (SoapObject) resultString;
			if (result != null) {
				if (result.getProperty("IsEmp").toString().equals("true")
						&& result.getProperty("IsValid").toString().equals("true")) {
					SoapObject so1 = (SoapObject) result.getProperty("DSusers");
					so1 = (SoapObject) so1.getProperty("diffgram");
					if (so1.getPropertyCount() > 0) {
						so1 = (SoapObject) so1.getProperty("NewDataSet");
						for (int i = 0; i < so1.getPropertyCount();) {
							so1 = (SoapObject) so1.getProperty(i);
							if (so1.toString().contains("UserID")) {

								master.setUser_name(so1.getPropertyAsString("FirstName").toString() + " "
										+ so1.getPropertyAsString("LastName").toString());
								master.setRole("AD");
								master.setPassword("");
								master.setMobileNo("9999999990");
								logins = true;
							}
							break;
						}
						// for end
					} else {
						logins = false;
					}
				} else {
					logins = false;
				}
			}

			else {
				logins = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return logins;
	}

	@RequestMapping(value = "/getNextVisitLast", method = RequestMethod.GET, produces = "application/json")
	public String getNextVisit(@RequestParam("counterNo") String counterNo, @RequestParam("type") String type,
			@RequestParam("useremail") String useremail) {
		// @RequestParam("counterNo") String counterNo,@RequestParam("type")
		// String type,@RequestParam("lastToken") String lastToken

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(tokenService.getNextType(type, counterNo, useremail));// tokenService.getNextType(type,
																					// counterNo,
																					// lastToken)

	}

	@RequestMapping(value = "/getDealDone", method = RequestMethod.GET, produces = "application/json")
	public String getDealDone(@RequestParam("tokenid") String tokenid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(tokenService.getDealDone(tokenid));// tokenService.getNextType(type,
																// counterNo,
																// lastToken)
	}

	@RequestMapping(value = "/getDealStarted", method = RequestMethod.GET, produces = "application/json")
	public String getDealStarted(@RequestParam("tokenid") String tokenid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(tokenService.getDealStarted(tokenid));// tokenService.getNextType(type,
																	// counterNo,
																	// lastToken)
	}

	@RequestMapping(value = "/getNextVisit", method = RequestMethod.GET, produces = "application/json")
	public String getNextVisitToken(@RequestParam("counterNo") String counterNo, @RequestParam("type") String type) {
		// @RequestParam("counterNo") String counterNo,@RequestParam("type")
		// String type,@RequestParam("lastToken") String lastToken

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(tokenService.getNextType(type, counterNo, ""));// tokenService.getNextType(type,
																			// counterNo,
																			// lastToken)

	}

	@RequestMapping(value = { "/gettokenEntrys" }, method = RequestMethod.GET)
	public String getTokenEntrys(@RequestParam("tokenType") String tokenType,
			@RequestParam("ProjectId") String projectId, @RequestParam("date") String inputDate,
			@RequestParam("todate") String toDate) {
		List<VW_Token> tokens = tokenService.getTokenList(tokenType, projectId, inputDate, toDate);
		// model.addAttribute("tokens",tokens );

		/*
		 * if(tokens!=null) { for(int i=0;i<tokens.size();i++) { List<EOIData>
		 * eOIData =
		 * userEOIService.findMobileNoExist(tokens.get(i).getMobileno());
		 * if(eOIData!=null) {
		 * tokens.get(i).setName(eOIData.get(0).getApplication_name()); } } }
		 */

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(tokens);
	}

	@RequestMapping(value = { "/gettokenassignentrys" }, method = RequestMethod.GET)
	public String getTokenAssignEntrys(@RequestParam("tokenType") String tokenType,
			@RequestParam("projectid") String projectid, @RequestParam("date") String inputDate,
			@RequestParam("todate") String toDate) {
		List<VW_Token> tokens = tokenService.getTokenAssignList(tokenType, projectid, inputDate, toDate);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(tokens);
	}

	@RequestMapping(value = { "/updateassignstatus" }, method = RequestMethod.GET)
	public String updateAssignStatus(@RequestParam("id") String id, @RequestParam("assinedto") String assinedto,
			@RequestParam("tokenType") String tokenType, @RequestParam("tokenNo") String tokenNo,
			@RequestParam("cust_name") String cust_name, @RequestParam("cust_mobileNo") String cust_mobileNo,
			@RequestParam("salesPerson") String salesperson) {
		tokenService.updateAssignStatus(id, assinedto);
		UserMaster usermaster = userMasterService.getUserDetails(assinedto);
		System.out.println("Mobile No:-" + usermaster.getMobileNo());
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

		// getMobileNo
		// <Customer Name> with token no. <Token no.> has been assigned to you
		// at <Time of assignment>. Please login and update comments on portal,
		// after the session.
		// "+usermaster.getUser_name()+"
		// Sales Desk User SMS
		String textsms = cust_name + " with token no. " + tokenNo + " has been assigned to you at "
				+ formatter.format(ts) + ". Please login and update comments on portal, after the session. Regards, Godrej Properties";
		String strencryptedText = "";
		try {
			strencryptedText = URLEncoder.encode(textsms, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendSMS.SMSSend(usermaster.getMobileNo(), strencryptedText);

		// Customer Message
		textsms = "Dear " + cust_name + ", you will be greeted shortly by your sales manager " + salesperson
				+ ". He/She can be reached at " + usermaster.getMobileNo()+". Regards, Godrej Properties";
		// "sales Person +"+salesperson+"/"+cust_name+" with token no.
		// "+tokenNo+" has been assigned to you at "+formatter.format(ts)+".
		// Please login and update comments on portal, after the session.";
		strencryptedText = "";
		try {
			strencryptedText = URLEncoder.encode(textsms, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("cust_mobileNo");
		SendSMS.SMSSend(cust_mobileNo, strencryptedText);

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return "Done";
	}

	@RequestMapping(value = { "/dailyuserupload" }, method = RequestMethod.GET)
	public String dailyUserUpload(@RequestParam("user_id") String user_id,
			@RequestParam("project_id") String project_id, @RequestParam("desk_no") String desk_no) {
		DailyUserMater daily = new DailyUserMater();
		daily.setCreateddate(new Timestamp(System.currentTimeMillis()));
		daily.setProject_id(project_id);
		daily.setUser_id(Integer.parseInt(user_id));
		daily.setIsactive("A");
		daily.setDesk_code(desk_no);
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(dailyUserUplaodService.uploadDailyUserMaster(daily));
	}

	@RequestMapping(value = { "/getdailyuseruploadlist" }, method = RequestMethod.GET)
	public String getDailyUserUploadList(@RequestParam("projectid") String projectid) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(dailyUserUplaodService.getViewUploadDailyUserList(projectid));
	}

	@RequestMapping(value = { "/updatedailyuseruploadlist" }, method = RequestMethod.GET)
	public String updateDailyUserUpload(@RequestParam("userid") String userid, @RequestParam("status") String status) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		DailyUserMater daily = new DailyUserMater();
		daily.setUser_id(Integer.parseInt(userid));
		daily.setIsactive(status);
		return gson.toJson(dailyUserUplaodService.updateDailyUserUpload(daily));
	}

	@RequestMapping(value = { "/projectassignUpdate" }, method = RequestMethod.GET)
	public String projectAssignUpdate(@RequestParam("user_id") String user_id,
			@RequestParam("project_id") String project_id, @RequestParam("project_name") String project_name) {
		UserProjectMapping userProject = new UserProjectMapping();
		userProject.setCreated_date(new Timestamp(System.currentTimeMillis()));
		userProject.setUser_id(Integer.parseInt(user_id));
		userProject.setProject_id(project_id);
		userProject.setProject_name(project_name.trim());
		userProject.setIsactive("A");
		userProject.setCreatedby("999999");
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(dailyUserUplaodService.projectassignUpdate(userProject));
	}

	@RequestMapping(value = { "/projectMappingUpdateStatus" }, method = RequestMethod.GET)
	public String projectAssignUpdate(@RequestParam("id") String id, @RequestParam("status") String status) {
		UserProjectMapping userProject = new UserProjectMapping();
		// userProject.setCreated_date(new
		// Timestamp(System.currentTimeMillis()));
		userProject.setGpl_user_project_mapping_id(Integer.parseInt(id));
		userProject.setIsactive(status);
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(dailyUserUplaodService.projectMappingUpdateStatus(userProject));// projectMappingUpdateStatus
	}

	@RequestMapping(value = { "/usermappinglist" }, method = RequestMethod.GET)
	public String userMappingList(@RequestParam("projectid") String projectid) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(dailyUserUplaodService.getProjectMappingList(projectid));
	}

	@RequestMapping(value = { "/misReport" }, method = RequestMethod.GET)
	public String userMappingList(@RequestParam("projectid") String projectid, @RequestParam("userid") String userid,
			@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate,  @RequestParam("userVerticals") String userVerticals) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(vW_MISReportService.getUserProjectList(projectid, userid, fromdate, todate, userVerticals));
	}

	@RequestMapping(value = "/downloadCSV")
	public void downloadCSV(HttpServletResponse response, HttpServletRequest resquest) throws IOException {

		response.setContentType("text/csv; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String reportName = "MIS_CSV_REPORT.csv";
		// String reportName = "MIS_CSV_REPORT.xls";
		response.setHeader("Content-disposition", "attachment;filename=" + reportName);
		Object[] state = null;
		ArrayList<String> rows = new ArrayList<String>();
		rows.add(
				"Project Name,Tokenno,Created,Enquiry Name,Mobile Phone,Customer Name,Email,have_we_met_before,age_a__c,residenceaddress,officelocation,"
						+ "empstatus,company_name__c,is_purchase_for_self_use_or_investment__c,budget"
						+ ",typology_requirement,walk_in_source__c,advertisementname,brokername,current_residence_configuration,current_residence_ownership,source_of_funding,customer_classification,ethnicity,unit_availability,accompanied_by,deal_negotiation,construction_status,timeframe_to_book,enquirynoneditcomment,verticle,sourcingname,closingname,closingemail,own_contribution_receipt,loan_eligibility,Assined To,IsAttended,CP Comments,FollowType,FollowDate"
						+ ",Trigger 1,Trigger 2,Barrier 1,Barrier 2,Lost Reason,Designation,Media Type,Media Sub Type,Type of visit,Revisit,Last site visit date,D4U Comments Log,Project Phase,Sourcing TL,Closing TL");
		rows.add("\n");
		String fromdate = resquest.getParameter("fromdate");
		log.info("fromdate:-{}",fromdate);

		String todate = resquest.getParameter("todate");
		log.info("todate:-{}",todate);
		
		String userVerticals = resquest.getParameter("userVerticals");
		
		List<Vw_MISReport> mislist = vW_MISReportService.getUserProjectList(resquest.getParameter("projectid"),
				resquest.getParameter("userid"), fromdate, todate, userVerticals);
		for (int i = 0; i < mislist.size(); i++) {

			rows.add(mislist.get(i).getProjectname().replaceAll(",", ""));
			rows.add(",");
			rows.add(mislist.get(i).getTokenno());
			rows.add(",");
			rows.add(mislist.get(i).getCreated().toString());
			rows.add(",");
			if (mislist.get(i).getEnquiryname() != null && mislist.get(i).getEnquiryname().length() > 0)
				rows.add(mislist.get(i).getEnquiryname());
			else
				rows.add("");
			rows.add(",");
			rows.add(mislist.get(i).getMobilephone());
			rows.add(",");
			rows.add(mislist.get(i).getName());
			rows.add(",");
			rows.add(mislist.get(i).getEmail());
			rows.add(",");
			if (mislist.get(i).getHave_we_met_before() != null && mislist.get(i).getHave_we_met_before().length() > 0)
				rows.add(mislist.get(i).getHave_we_met_before());
			else
				rows.add("");
			rows.add(",");
			if (mislist.get(i).getAge_a__c() != null && mislist.get(i).getAge_a__c().length() > 0)
				rows.add(mislist.get(i).getAge_a__c());
			else
				rows.add("");
			rows.add(",");
			rows.add(mislist.get(i).getResidenceaddress().replaceAll(",", ""));
			rows.add(",");
			rows.add(mislist.get(i).getOfficelocation().replaceAll(",", ""));
			rows.add(",");
			rows.add(mislist.get(i).getEmpstatus());
			rows.add(",");
			rows.add(mislist.get(i).getCompany_name__c().replaceAll("'", "").replaceAll(",", ""));
			rows.add(",");
			rows.add(mislist.get(i).getIs_purchase_for_self_use_or_investment__c());
			rows.add(",");
			rows.add(mislist.get(i).getBudget());
			rows.add(",");
			/*
			 * rows.add(mislist.get(i).getCarpet_area_requirement());
			 * rows.add(",");
			 */
			rows.add(mislist.get(i).getTypology_requirement());
			rows.add(",");
			rows.add(mislist.get(i).getWalk_in_source__c());
			rows.add(",");
			rows.add(mislist.get(i).getAdvertisementname().replaceAll(",", "").replaceAll("^", ""));
			rows.add(",");
			rows.add(mislist.get(i).getBrokername());
			rows.add(",");
			rows.add(mislist.get(i).getCurrent_residence_configuration());
			rows.add(",");
			rows.add(mislist.get(i).getCurrent_residence_ownership());
			rows.add(",");
			rows.add(mislist.get(i).getSource_of_funding());
			rows.add(",");
			rows.add(mislist.get(i).getCustomer_classification());
			rows.add(",");
			rows.add(mislist.get(i).getEthnicity());
			rows.add(",");
			rows.add(mislist.get(i).getUnit_availability());
			rows.add(",");
			rows.add(mislist.get(i).getAccompanied_by());
			rows.add(",");
			rows.add(mislist.get(i).getDeal_negotiation());
			rows.add(",");
			rows.add(mislist.get(i).getConstruction_status());
			rows.add(",");
			rows.add(mislist.get(i).getTimeframe_to_book());
			rows.add(",");
			/*
			 * System.out.println("Non Edit Comments::-"+mislist.get(i).
			 * getEnquirynoneditcomment().length());
			 * rows.add(mislist.get(i).getEnquirynoneditcomment().trim());
			 */

			if (mislist.get(i).getEnquirynoneditcomment() != null
					&& mislist.get(i).getEnquirynoneditcomment().length() > 0) {
				if (mislist.get(i).getEnquirynoneditcomment().contains(",")) {
					rows.add(mislist.get(i).getEnquirynoneditcomment().replaceAll(",", "").trim()
							.replaceAll("\\n|\\r|\"", " "));
				} else
					rows.add(mislist.get(i).getEnquirynoneditcomment().trim().replaceAll("\\n|\\r", " "));
				rows.add(",");
			} else
				rows.add(",");

			if (mislist.get(i).getVerticle__c() != null)
				rows.add(mislist.get(i).getVerticle__c());
			else
				rows.add("");
			rows.add(",");

			if (mislist.get(i).getSourcingname() != null)
				rows.add(mislist.get(i).getSourcingname());
			else
				rows.add("");
			rows.add(",");

			rows.add(mislist.get(i).getClosingname());
			rows.add(",");
			rows.add(mislist.get(i).getClosingemail());
			rows.add(",");
			rows.add(mislist.get(i).getOwn_contribution_receipt());
			rows.add(",");
			rows.add(mislist.get(i).getLoan_eligibility());
			rows.add(",");
			rows.add(mislist.get(i).getUser_name());
			rows.add(",");
			rows.add(mislist.get(i).getAttended());
			rows.add(",");
			rows.add(mislist.get(i).getCp_comments__c());
			rows.add(",");
			rows.add(mislist.get(i).getFollowtype());
			rows.add(",");
			if (mislist.get(i).getFollowdate() != null)
				rows.add(mislist.get(i).getFollowdate().toString());
			else
				rows.add("");
			rows.add(",");
			if (mislist.get(i).getTrigger1().trim() != null)
				rows.add(mislist.get(i).getTrigger1().trim());
			else
				rows.add("");
			rows.add(",");
			if (mislist.get(i).getTrigger2().trim() != null)
				rows.add(mislist.get(i).getTrigger2().trim());
			else
				rows.add("");
			rows.add(",");
			if (mislist.get(i).getBarrier1().trim() != null)
				rows.add(mislist.get(i).getBarrier1().trim());
			else
				rows.add("");
			rows.add(",");
			if (mislist.get(i).getBarrier2().trim() != null)
				rows.add(mislist.get(i).getBarrier2().trim());
			else
				rows.add("");
			rows.add(",");
			if (mislist.get(i).getLost_reason_c__c().trim() != null)
				rows.add(mislist.get(i).getLost_reason_c__c().trim());
			else
				rows.add("");
			rows.add(",");
			if (mislist.get(i).getDesignation__c().trim() != null)
				rows.add(mislist.get(i).getDesignation__c().trim().toString());
			else
				rows.add("");
			rows.add(",");
			if(mislist.get(i).getMedia_type__c() != null)
				rows.add(mislist.get(i).getMedia_type__c());
			else
				rows.add("");
			rows.add(",");
			if(mislist.get(i).getMedia_sub_type__c() != null)
				rows.add(mislist.get(i).getMedia_sub_type__c());
			else
				rows.add("");
			rows.add(",");

			if(mislist.get(i).getType_of_visit() != null)
				rows.add(mislist.get(i).getType_of_visit());
			else
				rows.add("");
			rows.add(",");
			if(mislist.get(i).getIs_revisit() != null)
				rows.add(mislist.get(i).getIs_revisit());
			else
				rows.add("");
			rows.add(",");
			if(mislist.get(i).getLastvisitdate() != null)
				rows.add(mislist.get(i).getLastvisitdate());
			else
				rows.add("");
			rows.add(",");
			if(mislist.get(i).getD4u_comments() != null)
				rows.add(mislist.get(i).getD4u_comments().replaceAll(",", "").trim().replaceAll("\\n|\\r|\"", " "));
			else
				rows.add("");
			rows.add(",");
		
			if(mislist.get(i).getPhase_name() != null)
				rows.add(mislist.get(i).getPhase_name());
			else
				rows.add("");
			rows.add(",");
			if(mislist.get(i).getSourcing_Team_Lead_Name__c() != null)
				rows.add(mislist.get(i).getSourcing_Team_Lead_Name__c());
			else
				rows.add("");
			rows.add(",");
			if(mislist.get(i).getClosing_Team_Lead_Name__c() != null)
				rows.add(mislist.get(i).getClosing_Team_Lead_Name__c());
			else
				rows.add("");
			rows.add(",");
			
			
			rows.add("\n");
		}

		ServletOutputStream out = response.getOutputStream();
		Iterator<String> iter = rows.iterator();
		while (iter.hasNext()) {
			String outputString = (String) iter.next();
			// response.getOutputStream().print(outputString);
			out.write(outputString.getBytes("UTF-8"));
		}
		// response.getOutputStream().flush();

		out.flush();
		// out.close();
	}

	@RequestMapping(value = { "/getAssignedUserToken" }, method = RequestMethod.GET)
	public String getAssignedUserWiseToken(@RequestParam("projectid") String projectid,
			@RequestParam("user_id") String userId, @RequestParam("fromdate") String fromdate,
			@RequestParam("todate") String todate,@RequestParam("source") String source) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		List<AssignedUser> assign = assignUserService.getassignedusers(userId, projectid, fromdate, todate, source);
		if (assign != null) {
			for (int i = 0; assign.size() > i; i++) {
				Timestamp later = new Timestamp(assign.get(i).getStarteddate().getTime() + (330 * 60 * 1000));
				assign.get(i).setStarteddate(later);
				// assign.add(assignData);
			}
		}
		return gson.toJson(assign);
	}

	@RequestMapping(value = "/getTowerBand", method = RequestMethod.GET, produces = "application/json")
	public String getTowerBand(@RequestParam("project_code") String project_code,
			@RequestParam("tower_code") String tower_code) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<TowerBand> plans = paymentPlanService.getTowerBand(project_code, tower_code);

		return gson.toJson(plans);
	}

	@RequestMapping(value = "/generateToken", method = RequestMethod.GET, produces = "application/json")
	public String setTokenOther(@RequestParam("mobileno") String mobileno, @RequestParam("type") String type) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		// tokenService.generateToken(mobileno,type);
		return gson.toJson("");
	}

	@RequestMapping(value = "/android_generateToken", method = RequestMethod.GET, produces = "application/json")
	public String setToken(@RequestParam("encryptedNo") String encrypt) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<EOIData> eOIData = userEOIService.searchUserQRCode(encrypt);
		Token token = new Token();
		if (eOIData != null) {
			if (eOIData.size() > 0) {

				EOIData data = eOIData.get(0);
				token = new Token();
				token.setCreated(new Timestamp(new Date().getTime()));
				token.setMobileno(data.getPhone_number());
				if (data.getEoi_amount().equals("500000"))
					token.setType("G");
				else
					token.setType("E");

				token.setAmount(data.getEoi_amount());
				token.setUniqe_no(data.getBarcode_numeric());
				token.setUniqe_str(data.getBarcode_str());
				token = tokenService.generateToken(token);
				userEOIService.UpdateToken(token);
				try {
					send_sms(token.getMobileno(), "" + token.getType() + token.getQueue(), "Godrej");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// String subject = "Thanks welcome";
				// new SendMailThread("balram.asati@godrejproperties.com", "",
				// subject, ""+ token.getType()+token.getQueue());

				/*
				 * EOIData data= eOIData.get(0);
				 * 
				 * token.setCreated(new Timestamp(new Date().getTime()));
				 * token.setMobileno(data.getPhone_number());
				 * token.setType("E");
				 * token.setUniqe_no(data.getBarcode_numeric());
				 * token.setUniqe_str(data.getBarcode_str()); token=
				 * tokenService.generateToken(token);
				 * userEOIService.UpdateToken(token);
				 * //tokenService.generateToken(token);
				 */
			}
		} else {

			token.setMsg("No record found");
		}

		return gson.toJson(token);
	}

	@RequestMapping(value = "/getotpData", method = RequestMethod.GET, produces = "application/json")
	public String getotpData(@RequestParam("mobileno") String mobileno) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		return gson.toJson(otpService.getOtp(mobileno));
	}

	@RequestMapping(value = "/android_generateTokenMobileEOI", method = RequestMethod.POST, produces = "application/json")
	public String setTokenMobileEOI(@RequestParam("countryCode") String countryCode,
			@RequestParam("mobileNo") String mobileNo, @RequestParam("projectname") String projectSfid,
			@RequestParam("createdBy") String createdBy) throws UnsupportedEncodingException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<EnquiryDto> enquiryList = enquiryRequestService.getEnquiriesByMobileNoEOI(countryCode, mobileNo,
				projectSfid);
		// List<EOIData> eOIData =userEOIService.findMobileNoExist(mobileNo);
		Token token = new Token();

		if (enquiryList.size() > 0) {
			EnquiryDto data = enquiryList.get(0);

			token = new Token();
			token.setCreated(new Timestamp(new Date().getTime()));
			token.setMobileno(data.getContact().getMobile());// mobile
			token.setProjectname(projectSfid.toString().trim());
			token.setCountrycode(countryCode);
			// Comment for Green Channel not now
			/*
			 * if( data.getEoi_preferred_unit__c()!=null &&
			 * data.getEoi_enquiry__c() ) token.setType("G"); else
			 */
			/*
			 * if(data.getEoi_enquiry__c()) token.setType("E"); else {
			 * token.setType("W"); return gson.toJson(token); }
			 */
			token.setType(userEOIService.getEOITokenType(data.getSfid()));
			if (token.getType().equals("W")) {
				return gson.toJson(token);
			}

			token.setEnqName(data.getName());
			token.setAmount(String.valueOf(data.getTransactionAmount()));
			token.setUniqe_no("" + data.getEnquiryId().toString().trim());
			token.setUniqe_str("" + data.getEnquiryId());
			token.setIsactive("Y");
			token.setEnquiry_18(data.getEnquiryId().toString().trim());
			token.setCreatedBy(createdBy);
			token = tokenService.generateToken(token);
			token.setAmount("" + data.getTransactionAmount());
			token.setDocNo("" + data.getEnquiryId());
			token.setName(data.getContact().getFirstName() + " " + data.getContact().getLastName());
			StringEncDec enc = new StringEncDec();
			String encStr = enc.encrypt(mobileNo);
			token.setEncStr(encStr);
			token.setEnqName(data.getName());
			token.setPriorityNo(data.getPriority_no__c());
			if (token.getType().equals("E") || token.getType().equals("G")) {
				// data
				// data.getPriority_no__c();
				send_smsEToken(mobileNo, "" + token.getType() + token.getQueue(), data.getProject().getPname(),
						projectSfid, data.getPriority_no__c());
			} else
				send_sms(mobileNo, "" + token.getType() + token.getQueue(), data.getProject().getPname());
		} else {
			token.setType("W");
		}

		return gson.toJson(token);
	}

	@RequestMapping(value = "/android_generateTokenMobileEOIData", method = RequestMethod.POST, produces = "application/json")
	public String generateTokenMobileEOIData(@RequestParam("countryCode") String countryCode,
			@RequestParam("mobileNo") String mobileNo, @RequestParam("projectname") String projectSfid,
			@RequestParam("createdBy") String createdBy) throws UnsupportedEncodingException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<EnquiryDto> enquiryList = enquiryRequestService.getEnquiriesByMobileNoEOI(countryCode, mobileNo,
				projectSfid);

		if (enquiryList.size() > 0) {
			enquiryList.get(0).setMessage("Success");
			return gson.toJson(enquiryList.get(0));
		} else {
			EnquiryDto enqDto = new EnquiryDto();
			enqDto.setMessage("No EOI found");
			return gson.toJson(enqDto);
		}

	}
	
	@RequestMapping(value = "/android_generateTokenMobile", method = RequestMethod.POST, produces = "application/json")
	public String setTokenMobile(@RequestParam("mobileNo") String mobileNo,
			@RequestParam("projectname") String projectname, @RequestParam("createdBy") String createdBy)
			throws UnsupportedEncodingException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		List<EOIData> eOIData = userEOIService.findMobileNoExist(mobileNo);
		Token token = new Token();
		try {
			if (eOIData != null) {
				EOIData data = eOIData.get(0);
				token = new Token();
				token.setCreated(new Timestamp(new Date().getTime()));
				token.setMobileno(data.getPhone_number());
				token.setProjectname(projectname);
				// Configuration of EOI Limit amount
				/*
				 * if(data.getEoi_amount().equals("500000")) token.setType("G");
				 * else token.setType("E");
				 */
				token.setType(userEOIService.getEOITokenType(data.getEnquiryid()));
				token.setAmount(data.getEoi_amount());
				token.setUniqe_no(data.getBarcode_numeric());
				token.setUniqe_str(data.getBarcode_str());
				token.setIsactive("Y");
				token.setTokenfrom("D4U");
				token = tokenService.generateToken(token);
				token.setProjectname(projectname);
				userEOIService.UpdateToken(token);
				// send_sms(token.getMobileno(),""+token.getType()+token.getQueue());
				// String subject = "Thanks welcome";
				// new SendMailThread("balram.asati@godrejproperties.com", "",
				// subject, ""+ token.getType()+token.getQueue());
				token.setAmount(data.getEoi_amount());
				token.setDocNo(data.getUserdocid());
				token.setName(data.getApplication_name());
				token.setAmount(data.getEoi_amount());
				token.setUniqe_no(data.getBarcode_numeric());
				token.setUniqe_str(data.getBarcode_str());

				// token.setIsKYCFilled(coApplicantService.getKYCFilledStatus(mobileNo));

			} else {
				token = new Token();
				token.setCreated(new Timestamp(new Date().getTime()));
				if (mobileNo.contains("+91"))
					token.setMobileno(mobileNo);
				else
					token.setMobileno("+91" + mobileNo);
				token.setProjectname(projectname);
				token.setType("W");
				token.setUniqe_no("999999");
				token.setUniqe_str("999999");
				token.setAmount("");
				token.setDocNo("");
				token.setIsKYCFilled("N");
				token.setIsactive("Y");
				token.setCreatedBy(createdBy);
				token = tokenService.generateToken(token);
			}
			StringEncDec enc = new StringEncDec();
			String encStr = enc.encrypt(mobileNo);
			token.setEncStr(encStr);
			send_sms(mobileNo, "-:" + token.getType() + token.getQueue(), "Godrej");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return gson.toJson(token);
	}

	// satheesh
	@RequestMapping(value = "/android_updateTokenMobile", method = RequestMethod.POST, produces = "application/json")
	public String updateTokenMobile(@RequestParam("mobileNo") String mobileNo) throws UnsupportedEncodingException {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		System.out.println("Number:-" + mobileNo);
		mobileNo = URLEncoder.encode(mobileNo, "UTF-8");
		System.out.println("Number After Encode:-" + mobileNo);
		return gson.toJson(tokenService.updateTokenStatus(mobileNo));
	}

	// satheesh
	@RequestMapping(value = "/android_updateTokenSFID", method = RequestMethod.POST, produces = "application/json")
	public String updateTokenSFID(@RequestParam("mobileNo") String mobileNo, @RequestParam("sfid") String sfid,
			@RequestParam("tokenid") String tokenid) throws UnsupportedEncodingException {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		// mobileNo = URLEncoder.encode(mobileNo, "UTF-8");
		System.out.println("Number After Encode:-" + mobileNo);
		return gson.toJson(tokenService.updateTokenEnquiryID(mobileNo, sfid, tokenid));
	}

	private void send_sms(String mobile, String token, String projectName) throws UnsupportedEncodingException {

		/*
		 * String msg= "Your token no is "+ token+
		 * " .Thank you for your interest in Godrej AQUA. You are now one step away from being a water smart citizen."
		 * + "\n Regards \n Godrej Properties.";
		 */

		// Thank you for your interest in Godrej Aqua. Your token no is E3.
		// Regards, Godrej Properties.
		String msg = "Thank you for your interest in " + projectName + ". Your token no is " + token
				+ ". Regards, Godrej Properties.";
		// http://203.212.70.200/smpp/sendsms?username=godrejhttp1&password=godrej12&to=9987677726&from=GPLPLB&
		// text=223134 is your OTP for verification of your KYC documents . OTP
		// is confidential. For security reasons, DO NOT SHARE THIS OTP WITH
		// ANYONE.
		msg = URLEncoder.encode(msg, "UTF-8");
		String value = null;
		try {
			value = new String(msg.getBytes(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendSMS.SMSSend(mobile, msg);
	}

	private void send_smsEToken(String mobile, String token, String projectName, String projectsfid, String prority)
			throws UnsupportedEncodingException {
		// Thank you for coming to the Landmark allotment of Godrej South
		// Estate. Your priority number is GODSExxx
		// String msg= "Thank you for your interest in "+projectName+". Your
		// token no is "+ token+ ". Regards, Godrej Properties.";

		if (prority != null && prority.length() > 0) {

		} else
			prority = "NA";
		String msg = "Thank you for coming to the Landmark allotment of " + projectName + ". Your priority number is "
				+ prority + ". Regards, Godrej Properties";
		msg = URLEncoder.encode(msg, "UTF-8");
		String value = null;
		try {
			value = new String(msg.getBytes(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendSMS.ShreeSMSSend(mobile, msg);
	}

	private void sendTokenSMS(String mobile, String token, String projName) throws UnsupportedEncodingException {// ,String
																													// cpflag,String
																													// cpName
		String msg = "";
		// if(cpflag.equals("D"))
		msg = "Thank you for your interest in " + projName + ". Your token no is " + token
				+ ". Regards, Godrej Properties.";
		
		/*msg = "Thank you for your interest in " + projName + ". Your token no is " + token
				+ "."
				+ "\nWhile our sales representatives attend to you, explore an immersive experience of " + projName + " on our App, through the below link:"
				+ "\niOS : https://cutt.ly/GPLIOS"
				+ "\nAndroid : https://cutt.ly/GPLAndroid " 
				+ "\nRegards, Godrej Properties.";*/

		/*
		 * if(cpflag.equals("CP") || cpflag.equals("O"))
		 * msg="Thank you for your interest in "
		 * +projName+" through our authorized Channel Partner "
		 * +cpName+". Your token no is "+ token+". Regards, Godrej Properties.";
		 */

		msg = URLEncoder.encode(msg, "UTF-8");
		String value = null;
		try {
			value = new String(msg.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendSMS.SMSSend(mobile, msg);
	}

	@RequestMapping(value = "/getdeatails", method = RequestMethod.GET, produces = "application/json")
	public String getOtp(@RequestParam("mobileno") String mobileno) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		return gson.toJson(otpService.createOtp(mobileno));
	}

	@RequestMapping(value = "/getdetailsCountry", method = RequestMethod.GET, produces = "application/json")
	public String getOtp(@RequestParam("countryCode") String countryCode, @RequestParam("mobileno") String mobileno, @RequestParam("cpdirectname") String cpdirectname) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		return gson.toJson(otpService.createOtpCountry(countryCode, mobileno,cpdirectname));
	}
	
	@RequestMapping(value = "/getotpvalid", method = RequestMethod.GET, produces = "application/json")
	public String getValidOtp(@RequestParam("mobileno") String mobileno, @RequestParam("OTP") String otp) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(otpService.getValidOtp(mobileno, otp));
	}

	@RequestMapping(value = "/getTowerMaster", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getTowerMaster(@RequestParam("project_code") String project_code) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(paymentPlanService.getTowerMaster(project_code));
	}

	@RequestMapping(value = "/getInventoryDetailsAdmin", method = RequestMethod.POST)
	public String getInventoryDetailsAdmin(@RequestParam("projectId") String projectId,
			@RequestParam("towerMst") String towerMst, @RequestParam("typoMst") String typoMst,
			@RequestParam("holdMst") String holdMst, @RequestParam("soldMst") String soldMst,
			@RequestParam("unitAvailable") String unitAvailable, @RequestParam("facing") String facing,
			@RequestParam("unitCategory") String unitCategory) {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		Boolean isPlot = false;
		
		if (projectId.equals("a1l2s000000XmaMAAS") || projectId.equals("a1l2s000000PJMJAA4") || projectId.equals("a1l2s000000PKjdAAG")) {
			isPlot = true;
		} else {
			isPlot = false;
		}
		
		List<InventoryAdmin> plans = inventoryService.getUnitDtlAdmin(projectId, towerMst, typoMst, holdMst, soldMst, unitAvailable, facing, unitCategory);

		//HashSet<Integer> floor = new HashSet<Integer>();
		
		HashSet<String> floorName=new HashSet<>();
		HashSet<Integer> floor=new HashSet<>();
		
		
		HashMap<String, ArrayList<InventoryAdmin>> hashMap = new HashMap<String, ArrayList<InventoryAdmin>>();
		ArrayList<InventoryAdmin> inventories = new ArrayList<InventoryAdmin>();

		ArrayList<ArrayList<InventoryAdmin>> mainList = new ArrayList<>();
		if (plans != null)
			for (int i = 0; i < plans.size(); i++) {
				//floor.add(Integer.valueOf(plans.get(i).getFloor_number__c()));
				
				if (isPlot) {
					floorName.add(plans.get(i).getFloor_name__c());
				} else {
					floor.add(Integer.valueOf(plans.get(i).getFloor_number__c()));
				}
			}

		//List<Integer> list = new ArrayList<Integer>(floor);
		//Collections.sort(list);
		
		List<String> list = new ArrayList<>();
		List<Integer> listInt = new ArrayList<>();
		
		if (isPlot) {
			list = new ArrayList<>(floorName);
			Collections.sort(list); 
		} else {
			listInt = new ArrayList<>(floor);
			Collections.sort(listInt); 
			
			for (int i = 0; i < listInt.size(); i++) {
				list.add(""+listInt.get(i));
			}
			
		}
		
        String floorData = "";
		

		for (int j = 0; j < list.size(); j++) {
			ArrayList<InventoryAdmin> intList = new ArrayList<>();
			for (int k = 0; k < plans.size(); k++) {

				if (isPlot) {
        			floorData = plans.get(k).getFloor_name__c();
        		} else {
        			floorData = plans.get(k).getFloor_number__c();
        		}
				
				
				if (list.get(j).toString().equals(floorData)) {
						
					plans.get(k).setFloor_number__c(floorData);
					
					if (plans.get(k).getCreated_at() != null && !(plans.get(k).getHoldstatusyn().equals("N"))
							&& !(plans.get(k).getHoldIntervalstatusAI().equals("I"))) {
						// System.out.println("Not null Value");
						int holdTime = plans.get(k).getHoldForTime();
						Timestamp timestampValue = new Timestamp(plans.get(k).getCreated_at().getTime() + holdTime);
						Timestamp currentTpm = new Timestamp(System.currentTimeMillis());

						if (timestampValue.compareTo(currentTpm) > 0) {
							plans.get(k).setFlagForHold("Hold");
							// System.out.println("Hold IF");
						} else {
							plans.get(k).setFlagForHold("Release");
							// System.out.println("Release ELSE");
						}

					}

					/*
					 * if(unitAvailable.equals("f") &&
					 * plans.get(k).isHold_status()!=null &&
					 * "t".equals(plans.get(k).isHold_status())) {
					 * plans.get(k).setPropstrength__allotted__c("t"); }else
					 * if(unitAvailable.equals("t") &&
					 * plans.get(k).isHold_status()!=null) {
					 * plans.get(k).setPropstrength__allotted__c("t");
					 * intList.add(plans.get(k)); }else {
					 * 
					 * }
					 */

					intList.add(plans.get(k));

				}
			}

			// System.out.println("intList ::: " + intList);
			mainList.add(intList);
		}

		return gson.toJson(mainList);
	}

	@RequestMapping(value = "/saveAdminUnit", method = RequestMethod.POST)
	public String saveAdminUnit(@RequestParam("projectid") String projectId, @RequestParam("userId") String userId,
			@RequestParam("unitsfid") String unitsfid, @RequestParam("holdmsg") String holdmsg,
			@RequestParam("reasonInput") String reasonInput,
			@RequestParam("holdBlockBehalfOfName") String holdBlockBehalfOfName,
			@RequestParam("holdBlockBehalfOfID") int holdBlockBehalfOfID, @RequestParam("enqSFID") String enqSFID) {

		try {
			inventoryService.holdInventoryAdmin(projectId, userId, unitsfid, holdmsg, reasonInput,
					holdBlockBehalfOfName, holdBlockBehalfOfID, enqSFID);
		} catch (Exception e) {
			log.error("error", e);
			return "duplicateRecords";
		}
		return "success";
	}

	@RequestMapping(value = "/updateAdminUnit", method = RequestMethod.POST)
	public String updateAdminUnit(@RequestParam("projectid") String projectId, @RequestParam("userId") String userId,
			@RequestParam("unitsfid") String unitsfid, @RequestParam("unitNames") String unitNames) {

		String[] data = unitsfid.split(",");
		String[] units = unitNames.split(",");
		StringBuilder error = new StringBuilder();

		StringBuilder successUnitUpdate = new StringBuilder();
		String drupalUpdateUnit = "";

		for (int i = 0; i < data.length; i++) {
			try {
				HoldInventoryAdmin inventoryAdmin = new HoldInventoryAdmin();
				inventoryAdmin.setUnitSfid(data[i]);
				inventoryAdmin.setProject_id(projectId);
				inventoryAdmin.setCreated_at(new Timestamp(new Date().getTime()));
				inventoryAdmin.setCustomer_id(userId);
				inventoryAdmin.setHold_reason("Release Admin");
				inventoryAdmin.setHold_status(false);
				inventoryAdmin.setEoi_unit_locked(false);

				inventoryService.updateHoldInventoryAdmin(inventoryAdmin);

				// -------------------------------------
				HoldInventoryAdminLog inventoryAdminLog = new HoldInventoryAdminLog();
				inventoryAdminLog.setUnitSfid(data[i]);
				inventoryAdminLog.setProject_id(projectId);
				inventoryAdminLog.setCreated_at(new Timestamp(new Date().getTime()));
				inventoryAdminLog.setCustomer_id(userId);
				inventoryAdminLog.setHold_reason("Release Admin");
				inventoryAdminLog.setHold_status(false);
				inventoryAdminLog.setEoi_unit_locked(false);

				inventoryService.saveHoldInventoryAdminLog(inventoryAdminLog);

				successUnitUpdate.append(data[i]);
				successUnitUpdate.append("_");
			} catch (Exception e) {
				log.error("error", e);
				error.append("/n Problem in releasing Unit - ").append(units[i]);
			}
		}

		drupalUpdateUnit = successUnitUpdate.toString();
		if (drupalUpdateUnit != null && drupalUpdateUnit.length() > 0
				&& drupalUpdateUnit.charAt(drupalUpdateUnit.length() - 1) == '_') {
			drupalUpdateUnit = drupalUpdateUnit.substring(0, drupalUpdateUnit.length() - 1);
		}

		drupalInventoryStatusUpdate.inventoryStatusUpdate(drupalUpdateUnit, "false");

		String errorMessage = error.toString();
		if (errorMessage != null && !errorMessage.isEmpty()) {
			return errorMessage;
		}
		return "success";
	}

	@RequestMapping(value = "/generateWalkinToken", method = RequestMethod.GET)
	public String success(@RequestParam("enquiryid") String enquiryId, @RequestParam("mobileno") String mobileNo,
			@RequestParam("projectSFID") String projectSFID, @RequestParam("projectName") String projectName,
			@RequestParam("countryCode") String countryCode// ,
	// @RequestParam("cpflag") String cpflag,
	// @RequestParam("cpname") String cpname
	) {
		// TODO GENERATE TOKEN

		Token token = new Token();
		token.setCreated(new Timestamp(new Date().getTime()));

		if (mobileNo.contains("+91"))
			token.setMobileno(mobileNo);
		else
			token.setMobileno(mobileNo);
		// Changes done By Satheesh Kyatham
		/*
		 * else token.setMobileno("+91"+mobileNo);
		 */
		token.setProjectname(projectSFID);
		token.setEnquiry_18(String.valueOf(enquiryId));
		token.setType("W");
		token.setUniqe_no("999999");
		token.setUniqe_str("999999");
		token.setAmount("");
		token.setDocNo("");
		token.setIsKYCFilled("N");
		token.setIsactive("Y");
		token.setCountrycode(countryCode);
		token = tokenService.generateToken(token);

		// TODO SEND SMS
		StringEncDec enc = new StringEncDec();
		String encStr = enc.encrypt(mobileNo);
		token.setEncStr(encStr);

		try {
			sendTokenSMS(mobileNo, token.getType() + token.getQueue(), projectName);// ,cpflag,cpname
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
	}

	@RequestMapping(value = "/authenticationDevice", method = RequestMethod.GET, produces = "application/json")
	public String authenticationDevice(@RequestParam("deviceId") String deviceId) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		otpService.insertDevice(deviceId);
		return gson.toJson(otpService.authenticationDevice(deviceId));
	}

	@RequestMapping(value = "/billingDataSysncTemp", method = RequestMethod.GET, produces = "application/json")
	public String billingDataSysncTemp() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<BillingData> billingDatas = billingViewService.getCreateTemp();

		return gson.toJson(billingDatas);
	}

	@RequestMapping(value = "/getPaymentDtl", method = RequestMethod.POST, produces = "application/json")
	public String getExtraChrg(@RequestParam("paymentDtlJson") String paymentDtlJson) // add
																						// parameter
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		String str = paymentDtlJson;

		Object object = null;
		JsonArray arrayObj = null;
		JsonParser jsonParser = new JsonParser();
		object = jsonParser.parse(str);
		arrayObj = (JsonArray) object;

		List<PaymentDtl> charges1 = new ArrayList<>();
		List<PaymentDtl> charges2 = new ArrayList<>();
		if (arrayObj != null && arrayObj.size() > 0) {
			for (int i = 0; i < arrayObj.size(); i++) {
				PaymentDtl ecData1 = new PaymentDtl();
				ecData1 = gson.fromJson(arrayObj.get(i), PaymentDtl.class);

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				JsonObject jobj = new Gson().fromJson(arrayObj.get(i), JsonObject.class);

				// java.sql.Date startDate = null;
				// String dateString = df.format( new Date() );

				try {

					if (!(jobj.get("transaction_date_string").getAsString()).isEmpty()) {
						Date date = df.parse(jobj.get("transaction_date_string").getAsString());

						ecData1.setTransaction_date(date);
					} else {
						Date date = df.parse("1999-09-09");
						ecData1.setTransaction_date(date);
					}

					charges1.add(ecData1);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					log.error(" Error ParseException:-", e);
				}
			}
			for (int i = 1; i < arrayObj.size(); i++) {
				PaymentDtl ecData1 = new PaymentDtl();
				ecData1 = gson.fromJson(arrayObj.get(i), PaymentDtl.class);
				charges2.add(ecData1);
			}
			try {
				if (charges2 != null && charges2.size() > 0) {
					String prePaymentID = OfferPrePayments.createOfferPrePayments(charges2);
					log.info(prePaymentID);
				}
			} catch (JRException e) {
				log.error(" Error JRException:-", e);
			} catch (IOException e) {
				log.error(" Error IOException:-", e);
			}
			paymentDtlService.insertPaymentDtl(charges1);
		}
		return gson.toJson("");
	}

	@RequestMapping(value = { "/FileUploadServlet" }, headers = "content-type=multipart/*", method = RequestMethod.POST)
	public @ResponseBody String fileUpload(@RequestParam(value = "panAttach", required = false) MultipartFile panAttach,
			@RequestParam(value = "receiptAttach", required = false) MultipartFile receiptAttach,
			@RequestParam("rowCount") String rowCount, @RequestParam("panAttachWebcam") String panAttachWebcam,
			@RequestParam("receiptAttachWebcam") String receiptAttachWebcam, @RequestParam("enqID") String enqID,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String rootPath = System.getProperty("catalina.home");

		if (panAttach != null) {
			File ad_dir = new File(rootPath + File.separator + "bookingReference" + File.separator + enqID
					+ File.separator + rowCount);
			String ad_path = ad_dir + File.separator + rowCount + "PAN" + "_" + panAttach.getOriginalFilename();
			if (!ad_dir.exists()) {
				ad_dir.mkdirs();
			}
			byte[] abytes;
			abytes = panAttach.getBytes();
			File aserverFile = new File(ad_path);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(aserverFile));
			stream.write(abytes);
			stream.close();
		}

		if (panAttachWebcam != null && !panAttachWebcam.equals("") && !panAttachWebcam.equals("undefined")) {
			String base64String = panAttachWebcam;
			String[] strings = base64String.split(",");
			String extension;
			switch (strings[0]) {// check image's extension
			case "data:image/jpeg;base64":
				extension = ".jpeg";
				break;
			case "data:image/png;base64":
				extension = ".png";
				break;
			default:// should write cases for more images types
				extension = ".jpg";
				break;
			}
			// convert base64 string to binary data
			File ad_dir = new File(rootPath + File.separator + "bookingReference" + File.separator + enqID
					+ File.separator + rowCount);
			String ad_path = ad_dir + File.separator + rowCount + "PAN" + extension;
			if (!ad_dir.exists()) {
				ad_dir.mkdirs();
			}

			byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
			// String path =
			// "C:\\Users\\gc.atulbhanushali\\Desktop\\test_image." + extension;
			File file = new File(ad_path);
			try (OutputStream outputStream = new FileOutputStream(file)) {
				outputStream.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (receiptAttach != null) {
			File ad_dir = new File(rootPath + File.separator + "bookingReference" + File.separator + enqID
					+ File.separator + rowCount);
			String ad_path = ad_dir + File.separator + rowCount + "Receipt" + "_" + receiptAttach.getOriginalFilename();
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

		if (receiptAttachWebcam != null && !receiptAttachWebcam.equals("")
				&& !receiptAttachWebcam.equals("undefined")) {
			String base64String = receiptAttachWebcam;
			String[] strings = base64String.split(",");
			String extension;
			switch (strings[0]) {// check image's extension
			case "data:image/jpeg;base64":
				extension = ".jpeg";
				break;
			case "data:image/png;base64":
				extension = ".png";
				break;
			default:// should write cases for more images types
				extension = ".jpg";
				break;
			}
			// convert base64 string to binary data
			File ad_dir = new File(rootPath + File.separator + "bookingReference" + File.separator + enqID
					+ File.separator + rowCount);
			String ad_path = ad_dir + File.separator + rowCount + "Receipt" + extension;
			if (!ad_dir.exists()) {
				ad_dir.mkdirs();
			}

			byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
			// String path =
			// "C:\\Users\\gc.atulbhanushali\\Desktop\\test_image." + extension;
			File file = new File(ad_path);
			try (OutputStream outputStream = new FileOutputStream(file)) {
				outputStream.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {

			// FileUploadService.save(att);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/getOfferList", method = RequestMethod.GET, produces = "application/json")
	public String getOfferList(@RequestParam("userid") String userid, @RequestParam("projectid") String projectid,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("userVerticals") String userVerticals) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();

		String finalProjectid = "";
		if (projectid != null) {
			// For multiple project report
			String[] multiProjectid = projectid.split(",");
			StringBuilder modifiedProid = new StringBuilder();
			for (int i = 0; i < multiProjectid.length; i++) {
				modifiedProid.append("'" + multiProjectid[i] + "'");
				modifiedProid.append(",");
			}
			finalProjectid = modifiedProid.toString();
			if (finalProjectid != null && finalProjectid.length() > 0
					&& finalProjectid.charAt(finalProjectid.length() - 1) == ',') {
				finalProjectid = finalProjectid.substring(0, finalProjectid.length() - 1);
			}
			// END For multiple project report
		} else {
			finalProjectid = "";
		}
		
		
		// For multiple verticales
		String finalVerticales = "";
		if (userVerticals != null && !userVerticals.equals("null") && !userVerticals.equals("")) {
			String [] multiVerticales= userVerticals.split(",");
			
			StringBuilder modifiedVer = new StringBuilder();
			
			
			for (int i=0;i<multiVerticales.length;i++){
				modifiedVer.append("'"+multiVerticales[i]+"'");
				modifiedVer.append(",");
			}
			
			finalVerticales = modifiedVer.toString();
			
			if (finalVerticales != null && finalVerticales.length() > 0 && finalVerticales.charAt(finalVerticales.length() - 1) == ',') {
				finalVerticales = finalVerticales.substring(0, finalVerticales.length() - 1);
			}
		} else {
			finalVerticales = null;
		}
		// END For multiple verticales
		
		String vertCondition= "";
		
		if (finalVerticales != null) {
			vertCondition = " and verticle__c in ("+finalVerticales+")  ";
		} else {
			vertCondition= "";
		}

		String whereCondition = "";

		if (userid != null && userid.length() > 0) {
			whereCondition = " AND  projectsfid = " + finalProjectid + " and userid=" + userid + " ";
		} else if ((fromDate != null && fromDate.length() > 0) && (toDate != null && toDate.length() > 0)) {
			whereCondition = " AND projectsfid in (" + finalProjectid + ") and Date(created) between '" + fromDate
					+ "' and '" + toDate + "' " + vertCondition;
		} else if (projectid != null && projectid.length() > 0) {
			whereCondition = " AND projectsfid in (" + finalProjectid + ")  " + vertCondition;
		} else {
			whereCondition = " AND projectsfid in (" + finalProjectid + ")  " + vertCondition;
		}

		/*
		 * if(userid!=null && userid.length()>0) whereCondition =
		 * " and userid="+userid+" "; if(projectid!=null &&
		 * projectid.length()>0) whereCondition =
		 * whereCondition+" and projectsfid='"+projectid+"' ";
		 */

		return gson.toJson(vW_OfferWithBalanceService.getOfferList(whereCondition));
	}

	@RequestMapping(value = "/getApplicationList", method = RequestMethod.GET, produces = "application/json")
	public String getApplicationList(@RequestParam("userid") String userid,
			@RequestParam("projectSfid") String projectSfid, @RequestParam("userVerticals") String userVerticals) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition = "";
		
		// For multiple verticales
		String finalVerticales = "";
		if (userVerticals != null && !userVerticals.equals("null") && !userVerticals.equals("")) {
			String [] multiVerticales= userVerticals.split(",");
			
			StringBuilder modifiedVer = new StringBuilder();
			
			
			for (int i=0;i<multiVerticales.length;i++){
				modifiedVer.append("'"+multiVerticales[i]+"'");
				modifiedVer.append(",");
			}
			
			finalVerticales = modifiedVer.toString();
			
			if (finalVerticales != null && finalVerticales.length() > 0 && finalVerticales.charAt(finalVerticales.length() - 1) == ',') {
				finalVerticales = finalVerticales.substring(0, finalVerticales.length() - 1);
			}
		} else {
			finalVerticales = null;
		}
		// END For multiple verticales
		
		String vertCondition= "";
		
		if (finalVerticales != null) {
			vertCondition = " and b.verticle__c in ("+finalVerticales+")  ";
		} else {
			vertCondition= "";
		}
		
		
		whereCondition = " a.propstrength__project__c=" + "'" + projectSfid + "' "+ vertCondition +" ";

		return gson.toJson(applicationDtlService.getApplicationDtl(whereCondition));
	}

	@RequestMapping(value = { "/printApplicationForm" }, method = RequestMethod.POST)
	public synchronized String printApplicationForm(@RequestParam("projectid") String projectid,
			@RequestParam("reraRegistrationNo") String reraRegistrationNo,
			@RequestParam("appFormData") String appFormData, @RequestParam("enqSfid") String enqSfid,
			@RequestParam("projectName") String projectName, @RequestParam("appFormLogo") String appFormLogo) throws JRException, IOException {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		long timeId = new Date().getTime();

		/*
		 * GeneratePDF solution = new GeneratePDF (); solution.PDFReport(20
		 * ,csData);
		 */

		iTextHTMLtoPDF solution = new iTextHTMLtoPDF();
		solution.ApplicationFormPDF(appFormData, enqSfid, projectName, reraRegistrationNo, projectid, appFormLogo);

		return gson.toJson("");
	}

	@RequestMapping(value = { "/getPropertyTypeStatus" }, method = RequestMethod.GET)
	public String getPropertyTypeStatus(@RequestParam("propertyid") String propertyid) throws JRException, IOException {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(propOtherChargesService.getPropertyTypeStatus(propertyid));
	}

	@RequestMapping(value = { "/getTokenDetails" }, method = RequestMethod.GET)
	public String getTokenDetails(@RequestParam("tokenid") String tokenid) throws JRException, IOException {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(tokenService.getTokenDetails(tokenid));
	}

	/*
	 * @RequestMapping(value = "/getTncData", method = RequestMethod.POST)
	 * public String insertTnCForPP(@RequestParam("ppId") String
	 * ppId, @RequestParam("projectid") String projectid) { GsonBuilder
	 * gsonBuilder = new GsonBuilder(); Gson gson = gsonBuilder.create();
	 * 
	 * 
	 * 
	 * List<TnC> tnC = tnCService.getTncData (ppId, projectid);
	 * 
	 * return gson.toJson(tnC); }
	 */

	// get project details
	@RequestMapping(value = "/getProjectDtl", method = RequestMethod.POST)
	public String getProjectDtl(@RequestParam("projectId") String projectId) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		List<ProjectDtl> dtl = projectDtlService.getProjectData(projectId);

		return gson.toJson(dtl);

		/*
		 * UnitDtl uDtl = unitExistsService.getUnitData (contactNo); ProjectDtl
		 * dtl = projectDtlService.getProjectData (projectId);
		 * 
		 * return ();
		 */
	}

	/* Get dynamic Properties Other Charges */
	@RequestMapping(value = "/getApplicantData", method = RequestMethod.POST)
	public String getApplicantData(@RequestParam("applicationSfid") String applicationSfid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		return gson.toJson(applicantDtlService.getApplicantData(applicationSfid));
	}

	@RequestMapping(value = "/getEnqAndBookingDtl", method = RequestMethod.POST)
	public String getEnqAndBookingDtl(@RequestParam("enqId") String enqId,
			@RequestParam("applicationBookingId") String applicationBookingId) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		return gson.toJson(enqAndBookingDtlService.getEnqAndBookingData(enqId, applicationBookingId));
	}

	@RequestMapping(value = "/getReceivedPaymentDtl", method = RequestMethod.POST)
	public String getReceivedPaymentDtl(@RequestParam("applicationBookingId18") String applicationBookingId18) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		return gson.toJson(receivedPaymentDtlService.getReceivedPaymentDtl(applicationBookingId18));
	}

	/*
	 * @RequestMapping(value = "/getPaymentPlanOtherCharges", method =
	 * RequestMethod.POST) public String
	 * getPaymentPlanOtherCharges(@RequestParam("unitSfid") String
	 * unitSfid,@RequestParam("paymentPlanSfid") String
	 * paymentPlanSfid,@RequestParam("projectid") String projectid) {
	 * GsonBuilder gsonBuilder = new GsonBuilder(); Gson gson =
	 * gsonBuilder.create();
	 * 
	 * List<PaymentPlanWithOtherCharge> paymentPlanLineItems=
	 * paymentPlanWithOtherChargeService.getppAndOtherCharges(unitSfid,
	 * paymentPlanSfid,projectid);
	 * System.out.println("Return Jsom:-"+gson.toJson(paymentPlanLineItems));
	 * return gson.toJson(paymentPlanLineItems); }
	 */

	@RequestMapping(value = "/insertEOIPaymentDtl", method = RequestMethod.POST)
	public String insertEOIPaymentDtl(@RequestParam("paymentDtlJson") String paymentDtlJson) // add
																								// parameter
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		String str = paymentDtlJson;

		Object object = null;
		JsonArray arrayObj = null;
		JsonParser jsonParser = new JsonParser();
		object = jsonParser.parse(str);
		arrayObj = (JsonArray) object;

		List<EOIPaymentDtl> charges1 = new ArrayList<>();

		if (arrayObj != null && arrayObj.size() > 0) {
			for (int i = 0; i < arrayObj.size(); i++) {
				EOIPaymentDtl ecData1 = new EOIPaymentDtl();
				ecData1 = gson.fromJson(arrayObj.get(i), EOIPaymentDtl.class);
				ecData1.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				charges1.add(ecData1);
			}

			eOIpaymentDtlService.insertPaymentDtl(charges1);
		}
		return gson.toJson("");
	}

	@RequestMapping(value = {
			"/EOIPaymentFileUploadServlet" }, headers = "content-type=multipart/*", method = RequestMethod.POST)
	public @ResponseBody String EOIPaymentFileUploadServlet(
			@RequestParam(value = "panAttachEoi", required = false) MultipartFile panAttach,
			@RequestParam(value = "receiptAttachEoi", required = false) MultipartFile receiptAttach,
			@RequestParam("rowCount") String rowCount, @RequestParam("enqID") String enqID, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String rootPath = System.getProperty("catalina.home");

		if (panAttach != null) {
			File ad_dir = new File(rootPath + File.separator + "EOIbookingReference" + File.separator + enqID
					+ File.separator + rowCount);
			String ad_path = ad_dir + File.separator + rowCount + "PAN" + "_" + panAttach.getOriginalFilename();
			if (!ad_dir.exists()) {
				ad_dir.mkdirs();
			}
			byte[] abytes;
			abytes = panAttach.getBytes();
			File aserverFile = new File(ad_path);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(aserverFile));
			stream.write(abytes);
			stream.close();

		}

		if(receiptAttach!=null) {
			File ad_dir = new File(rootPath + File.separator + "EOIbookingReference" + File.separator + enqID + File.separator + rowCount);
			String ad_path =ad_dir +File.separator+rowCount+"Receipt"+"_"+receiptAttach.getOriginalFilename();
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
		
		
		try {
				
			//FileUploadService.save(att);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "";
	} 

		
		
		
		@RequestMapping(value = "/getEOITabPreferencRecord", method = RequestMethod.POST)
		public String getEOITabPreferencRecord(@RequestParam("enqSfid") String enqSfid) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			
			return gson.toJson(eOIPreferenceDtlService.getEOIPreferenceRecord(enqSfid));
		}
		
		@RequestMapping(value = { "/triggerLog" }, method = RequestMethod.GET) public
		  ModelAndView triggerLog() { ModelAndView model = new ModelAndView();
		  model.setViewName("triggerLog");
		  return model; }
		
		@RequestMapping(value = { "/updatePaymentPlan" }, method = RequestMethod.GET) public
		  ModelAndView updatePaymentPlan() { ModelAndView model = new ModelAndView();
		  model.setViewName("updatePaymentPlan");
		  return model; }
		
		
		@RequestMapping(value = "/getTriggerlogList", method = RequestMethod.GET, produces = "application/json")
		public String getTriggerlogList(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
			Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
			String whereCondition = "";
			
			//whereCondition = "propstrength__project__c=" + "'"+projectSfid+"'";
			//whereCondition = "updated_at BETWEEN" + "'"+fromDate+"'"  + "AND" + "'"+toDate+"'";
			whereCondition = "DATE(updated_at) = " + "'"+fromDate+"'";
			
			return gson.toJson(triggerLogService.getTriggerLogDtl(whereCondition));
		}
		
		
		@RequestMapping(value = "/getTriggerlogArchiveList", method = RequestMethod.GET, produces = "application/json")
		public String getTriggerlogArchiveList(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
			Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
			String whereCondition = "";
			
			//updated_at BETWEEN '2019-09-09' AND '2019-09-14';
			//whereCondition = "updated_at BETWEEN" + "'"+fromDate+"'"  + "AND" + "'"+toDate+"'";
			whereCondition = "DATE(updated_at) = " + "'"+fromDate+"'";
			
			return gson.toJson(triggerLogArchiveService.getTriggerLogArchiveDtl(whereCondition));
		}
		
		
		//Update EOI payment from costsheet
		
		@PostMapping(value = "/getEOIPaymentRecord")
	public String getEOIPaymentRecord(@RequestParam("enqSfid") String enqSfid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		return gson.toJson(eOIpaymentDtlService.getEOIPaymentRecord(enqSfid,""));
	}
	
	@PostMapping(value = "/getEOIPaymentRecordForRefund")
	public String getEOIPaymentRecordForRefund(@RequestParam("enqSfid") String enqSfid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String whereCondition=" and isactive='Y' and (refund_trx_no is null or refund_trx_no='0')";
		return gson.toJson(eOIpaymentDtlService.getEOIPaymentRecord(enqSfid,whereCondition));
	}

	@RequestMapping(value = "/getEOIList", method = RequestMethod.GET, produces = "application/json")
	public String getEOIList(@RequestParam("projectSfid") String projectSfid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition = "";

		whereCondition = "propstrength__project__c=" + "'" + projectSfid + "' and eoi_enquiry__c = true ";

		return gson.toJson(eOIDataService.getEOIDtl(whereCondition));
	}

	@RequestMapping(value = "/updateEOIPriority", method = RequestMethod.POST, produces = "application/json")
	public String updateEOIPriority(@RequestParam("eoiDtl") String eoiDtl) // add
																			// parameter
	{

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		String str = eoiDtl;

		Object object = null;
		JsonArray arrayObj = null;
		JsonParser jsonParser = new JsonParser();
		object = jsonParser.parse(str);
		arrayObj = (JsonArray) object;

		List<Enquiry> eoiData = new ArrayList<>();
		for (int i = 0; i < arrayObj.size(); i++) {
			Enquiry ecData1 = new Enquiry();
			ecData1 = gson.fromJson(arrayObj.get(i), Enquiry.class);
			// ecData1.setPriority_no__c("Test");

			eoiData.add(ecData1);
			// System.out.println("Test XYZ ::: " + ecData1.toString());
		}

		System.out.println("Test XYZ ::: " + eoiData.toString());
		eOIUpdateService.updateEOI(eoiData);

		System.out.println("Value Has");

		return gson.toJson("");
	}

	@RequestMapping(value = "/insertEOIDtl", method = RequestMethod.POST, produces = "application/json")
	public String insertEOIDtl(@RequestParam("eoiDataJson") String eoiDataJson) // add
																				// parameter
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		String str = eoiDataJson;

		Object object = null;
		JsonArray arrayObj = null;
		JsonParser jsonParser = new JsonParser();
		object = jsonParser.parse(str);
		arrayObj = (JsonArray) object;

		List<EOIPreferenceDtl> charges1 = new ArrayList<>();

		if (arrayObj != null && arrayObj.size() > 0) {
			for (int i = 0; i < arrayObj.size(); i++) {
				EOIPreferenceDtl ecData1 = new EOIPreferenceDtl();
				ecData1 = gson.fromJson(arrayObj.get(i), EOIPreferenceDtl.class);

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				JsonObject jobj = new Gson().fromJson(arrayObj.get(i), JsonObject.class);

				// java.sql.Date startDate = null;
				// String dateString = df.format( new Date() );

				try {

					if (!(jobj.get("eoi_date_string").getAsString()).isEmpty()) {
						Date date = df.parse(jobj.get("eoi_date_string").getAsString());

						ecData1.setEoi_date(date);
					} else {
						Date date = df.parse("1999-09-09");
						ecData1.setEoi_date(date);
					}

					charges1.add(ecData1);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			eOIPreferenceDtlService.insertEOI(charges1);
		}
		return gson.toJson("");
	}



	// Update EOI payment from costsheet
	@RequestMapping(value = "/updateForSubmittedOffer", method = RequestMethod.POST, produces = "application/json")
	public String updateEOI(@RequestParam("paymentDtlJson") String paymentData) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		String str = paymentData;

		Object object = null;
		JsonArray arrayObj = null;
		JsonParser jsonParser = new JsonParser();
		object = jsonParser.parse(str);
		arrayObj = (JsonArray) object;

		List<EOIPaymentDtl> charges1 = new ArrayList<>();
		for (int i = 0; i < arrayObj.size(); i++) {
			EOIPaymentDtl ecData1 = new EOIPaymentDtl();
			ecData1 = gson.fromJson(arrayObj.get(i), EOIPaymentDtl.class);
			// ecData1.setUser_contact(contactNo);
			// ecData1.setSeq(i);
			// ecData1.setTimeid(timeId);
			charges1.add(ecData1);
		}

		eOIpaymentDtlService.updateEOIForOffer(charges1);

		System.out.println("Value Has");

		return gson.toJson("");
	}

	// END Update EOI payment from costsheet
	@RequestMapping(value = "/getPrepayment", method = RequestMethod.GET, produces = "application/json")
	public String getPrepaymentType(@RequestParam("enquirysfid") String enquirysfid) // add
																						// parameter
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(paymentDtlService.getPrePaymentData(enquirysfid));

	}

	/* get New scheme List */
	@RequestMapping(value = "/getSchemeSource", method = RequestMethod.GET)
	public String schemeSource(@RequestParam("projectid") String projectid,
			@RequestParam("cp_flag_yn") String cp_flag_yn) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(schemeSourceService.getSchemeSource(projectid, cp_flag_yn));
	}

	@RequestMapping(value = "/getSchemeSite", method = RequestMethod.GET)
	public String schemeSite(@RequestParam("projectid") String projectid,
			@RequestParam("cp_flag_yn") String cp_flag_yn) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(schemeSiteService.getSchemeSite(projectid, cp_flag_yn));
	}

	@RequestMapping(value = "/getSchemePromotional", method = RequestMethod.GET)
	public String schemePromotional(@RequestParam("projectid") String projectid,
			@RequestParam("cp_flag_yn") String cp_flag_yn) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(schemePromotionalService.getSchemePromotional(projectid, cp_flag_yn));
	}
	/* END get New scheme List */

	@RequestMapping(value = "/getHeaderSchemeDtl", method = RequestMethod.GET)
	public String headerSchemeDtl(@RequestParam("projectid") String projectid) // add
																				// parameter
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(headerSchemeService.getHeaderSchemeChargs(projectid));
	}

	/* insert scheme mapping */
	@RequestMapping(value = "/insertSchemeMapping", method = RequestMethod.POST)
	public String addSchemeMapping(@RequestParam("region") String region,
			@RequestParam("projectName") String projectName, @RequestParam("projectid") String projectid,
			@RequestParam("headerSchemeId") int headerSchemeId, @RequestParam("schemeSourceId") int schemeSourceId,
			@RequestParam("schemeSiteId") int schemeSiteId,
			@RequestParam("schemePromotionalId") int schemePromotionalId) // add
																			// parameter
	{

		SchemeMapping mappingDtl = schemeMappingService.getMappingDtl(headerSchemeId);

		if (mappingDtl != null) {

			return ("recordExist");

		} else {
			SchemeMapping scheCharge = new SchemeMapping();
			scheCharge.setRegion_name(region);
			scheCharge.setProject_name(projectName);
			scheCharge.setProject_id(projectid);
			scheCharge.setScheme_id(headerSchemeId);
			scheCharge.setScheme_source_id(schemeSourceId);
			scheCharge.setScheme_site_id(schemeSiteId);
			scheCharge.setScheme_promotional_id(schemePromotionalId);
			scheCharge.setCreatedby("999");
			scheCharge.setUpdatedby("999");
			scheCharge.setIsactive("A");

			schemeMappingService.insertSchemeMapping(scheCharge);

			return ("newRecord");
		}
	}
	/* END insert scheme mapping */

	/* Insert Scheme Master */

	@RequestMapping(value = "/insertSource", method = RequestMethod.POST)
	public String addSchemeSource(@RequestParam("region") String region,
			@RequestParam("projectName") String projectName, @RequestParam("projectid") String projectid,
			@RequestParam("inputVal") String inputVal) // add parameter
	{
		ProjectLaunch regionData=projectLaunchService.getprojectDetails(projectid);
		SchemeSource scheCharge = new SchemeSource();
		scheCharge.setRegion_name(regionData.getRegionname());
		scheCharge.setProject_name(projectName);
		scheCharge.setProject_id(projectid);
		scheCharge.setScheme(inputVal);
		scheCharge.setCreatedby("999");
		scheCharge.setUpdatedby("999");
		scheCharge.setIsactive("A");
		scheCharge.setCp_flag_yn("N");
		schemeSourceService.insertSchemeSource(scheCharge);
		return ("inserted");
	}

	@RequestMapping(value = "/insertSite", method = RequestMethod.POST)
	public String addSchemeSite(@RequestParam("region") String region, @RequestParam("projectName") String projectName,
			@RequestParam("projectid") String projectid, @RequestParam("inputVal") String inputVal) // add
																									// parameter
	{
		ProjectLaunch regionData=projectLaunchService.getprojectDetails(projectid);
		SchemeSite scheCharge = new SchemeSite();
		scheCharge.setRegion_name(regionData.getRegionname());
		scheCharge.setProject_name(projectName);
		scheCharge.setProject_id(projectid);
		scheCharge.setScheme(inputVal);
		scheCharge.setCreatedby("999");
		scheCharge.setUpdatedby("999");
		scheCharge.setIsactive("A");
		scheCharge.setCp_flag_yn("N");
		schemeSiteService.insertSchemeSite(scheCharge);
		return ("inserted");
	}

	@RequestMapping(value = "/insertPromotional", method = RequestMethod.POST)
	public String addSchemePromotional(@RequestParam("region") String region,
			@RequestParam("projectName") String projectName, @RequestParam("projectid") String projectid,
			@RequestParam("inputVal") String inputVal) // add parameter
	{
		ProjectLaunch regionData=projectLaunchService.getprojectDetails(projectid);
		SchemePromotional scheCharge = new SchemePromotional();
		scheCharge.setRegion_name(regionData.getRegionname());
		scheCharge.setProject_name(projectName);
		scheCharge.setProject_id(projectid);
		scheCharge.setScheme(inputVal);
		scheCharge.setCreatedby("999");
		scheCharge.setUpdatedby("999");
		scheCharge.setIsactive("A");
		scheCharge.setCp_flag_yn("N");
		schemePromotionalService.insertSchemePromotional(scheCharge);
		return ("inserted");
	}
	/* END Insert Scheme Master */

	/* EOI Report */
	@RequestMapping(value = "/getEOIReport", method = RequestMethod.GET, produces = "application/json")
	public String getEOIReportDtl(@RequestParam("projectSfid") String projectSfid,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("userVerticals") String userVerticals) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition = "";
		
		
		// For multiple verticales
		String finalVerticales = "";
		if (userVerticals != null && !userVerticals.equals("null") && !userVerticals.equals("")) {
			String [] multiVerticales= userVerticals.split(",");
			
			StringBuilder modifiedVer = new StringBuilder();
			
			
			for (int i=0;i<multiVerticales.length;i++){
				modifiedVer.append("'"+multiVerticales[i]+"'");
				modifiedVer.append(",");
			}
			
			finalVerticales = modifiedVer.toString();
			
			if (finalVerticales != null && finalVerticales.length() > 0 && finalVerticales.charAt(finalVerticales.length() - 1) == ',') {
				finalVerticales = finalVerticales.substring(0, finalVerticales.length() - 1);
			}
		} else {
			finalVerticales = null;
		}
		// END For multiple verticales
		
		String vertCondition= "";
		
		if (finalVerticales != null) {
			vertCondition = " and verticle__c in ("+finalVerticales+")  ";
		} else {
			vertCondition= "";
		}
		
		
		if ((fromDate != null && fromDate.length() > 0) && (toDate != null && toDate.length() > 0)) {
			whereCondition = " project_sfid= '" + projectSfid + "' and Date(date_of_eoi__c) between '" + fromDate
					+ "' and '" + toDate + "'  "+ vertCondition +"  order by date_of_eoi__c desc ";
		} else if (projectSfid != null && projectSfid.length() > 0) {
			whereCondition = " project_sfid= '" + projectSfid + "' "+ vertCondition +" order by date_of_eoi__c desc ";
		}

		// whereCondition = " project_sfid= '"+projectSfid+"' ";

		return gson.toJson(eOIReportService.getEOIReportDtl(whereCondition));
	}

	@RequestMapping(value = "/getEOIReportSales", method = RequestMethod.GET, produces = "application/json")
	public String getEOIReportDtlSales(@RequestParam("userid") String userid,
			@RequestParam("projectSfid") String projectSfid, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition = "";

		if ((fromDate != null && fromDate.length() > 0) && (toDate != null && toDate.length() > 0) && userid != null
				&& userid.length() > 0) {
			whereCondition = " project_sfid= '" + projectSfid + "' and Date(date_of_eoi__c) between '" + fromDate
					+ "' and '" + toDate + "' and userid = " + userid + "  order by date_of_eoi__c desc ";
		} else if (projectSfid != null && projectSfid.length() > 0 && userid != null && userid.length() > 0) {
			whereCondition = " project_sfid= '" + projectSfid + "' and userid = " + userid
					+ "  order by date_of_eoi__c desc ";
		}
		return gson.toJson(eOIReportService.getEOIReportDtl(whereCondition));
	}

	/* END EOI Report */

	/* Download EOI Report */
	/*
	 * @RequestMapping(value = "/downloadEOICSV") public void
	 * downloadEOICSV(HttpServletResponse response,HttpServletRequest resquest)
	 * throws IOException {
	 * 
	 * response.setContentType("text/csv; charset=UTF-8");
	 * response.setCharacterEncoding("UTF-8"); String reportName =
	 * "EOI_CSV_REPORT.csv";
	 * 
	 * response.setHeader("Content-disposition",
	 * "attachment;filename="+reportName); Object[] state = null;
	 * ArrayList<String> rows = new ArrayList<String>();
	 * rows.add("Project Name,Created,Enquiry Name, " +
	 * "Preferences, Payment Type, Bank Name, Branch, Transaction ID, Transaction Date, Transaction Amount"
	 * ); rows.add("\n");
	 * 
	 * String whereCondition = ""; String fromdate =
	 * resquest.getParameter("fromdate"); String todate =
	 * resquest.getParameter("todate"); String projectSfid =
	 * resquest.getParameter("projectSfid");
	 * 
	 * if((fromdate!=null && fromdate.length()>0) && (todate!=null &&
	 * todate.length()>0)) { whereCondition =
	 * " project_sfid= '"+projectSfid+"' and Date(created) between '"
	 * +fromdate+"' and '"+todate+"' order by created desc "; } else
	 * if(projectSfid!=null && projectSfid.length()>0) { whereCondition =
	 * " project_sfid= '"+projectSfid+"' order by created desc "; }
	 * 
	 * List<EOIReport> mislist =
	 * eOIReportService.getEOIReportDtl(whereCondition); for (int i = 0; i <
	 * mislist.size(); i++) {
	 * 
	 * rows.add(mislist.get(i).getProject_name().replaceAll(",", ""));
	 * rows.add(",");
	 * 
	 * rows.add(mislist.get(i).getCreated().toString()); rows.add(",");
	 * 
	 * if(mislist.get(i).getEnq_sfid()!=null &&
	 * mislist.get(i).getEnq_sfid().length()>0)
	 * rows.add(mislist.get(i).getEnq_sfid()); else rows.add(""); rows.add(",");
	 * 
	 * 
	 * if(mislist.get(i).getPreferences().contains(","))
	 * rows.add(mislist.get(i).getPreferences().replaceAll(",",
	 * "").trim().replaceAll("\\n|\\r", " ")); else
	 * rows.add(mislist.get(i).getPreferences().trim().replaceAll("\\n|\\r",
	 * " ")); rows.add(",");
	 * 
	 * 
	 * if(mislist.get(i).getPayment_type()!=null &&
	 * mislist.get(i).getPayment_type().length()>0)
	 * rows.add(mislist.get(i).getPayment_type()); else rows.add("");
	 * rows.add(",");
	 * 
	 * 
	 * if(mislist.get(i).getBank_name()!=null &&
	 * mislist.get(i).getBank_name().length()>0)
	 * rows.add(mislist.get(i).getBank_name()); else rows.add("");
	 * rows.add(",");
	 * 
	 * if(mislist.get(i).getBranch()!=null &&
	 * mislist.get(i).getBranch().length()>0)
	 * rows.add(mislist.get(i).getBranch()); else rows.add(""); rows.add(",");
	 * 
	 * if(mislist.get(i).getTransaction_id()!=null &&
	 * mislist.get(i).getTransaction_id().length()>0)
	 * rows.add(mislist.get(i).getTransaction_id()); else rows.add("");
	 * rows.add(",");
	 * 
	 * if(mislist.get(i).getTransaction_date()!=null &&
	 * mislist.get(i).getTransaction_date().length()>0)
	 * rows.add(mislist.get(i).getTransaction_date()); else rows.add("");
	 * rows.add(",");
	 * 
	 * if(mislist.get(i).getTransaction_amount()!=null &&
	 * mislist.get(i).getTransaction_amount().length()>0)
	 * rows.add(mislist.get(i).getTransaction_amount()); else rows.add("");
	 * rows.add(",");
	 * 
	 * rows.add("\n"); }
	 * 
	 * ServletOutputStream out = response.getOutputStream(); Iterator<String>
	 * iter = rows.iterator(); while (iter.hasNext()) { String outputString =
	 * (String) iter.next(); out.write(outputString.getBytes("UTF-8")); }
	 * 
	 * out.flush(); }
	 */

	/* END Download EOI Report */
	@RequestMapping(value = "/insertAuditLog", method = RequestMethod.POST, produces = "application/json")
	public String insertAuditLog(@RequestBody AuditLogDto auditLogDto) {
		System.out.println("Entery");
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		try {
			return gson.toJson(auditLogService.insertAuditLog(auditLogDto));
		} catch (Exception e) {
			return gson.toJson(null);
		}
	}

	@RequestMapping(value = "/getbrokercontact", method = RequestMethod.GET)
	public String getbrokercontact(@RequestParam("brokeraccount") String brokeraccount) {
		return userContactService.getBrokerContact(brokeraccount);
	}

	// Bulk insert for scheme mapping
	@RequestMapping(value = "/bulkInsertSchemeMapping", method = RequestMethod.POST, produces = "application/json")
	public String bulkInsertSchemeMapping(@RequestParam("mappingJson") String mappingJson) // add
																							// parameter
	{
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		String str = mappingJson;

		Object object = null;
		JsonArray arrayObj = null;
		JsonParser jsonParser = new JsonParser();
		object = jsonParser.parse(str);
		arrayObj = (JsonArray) object;

		List<SchemeMapping> charges1 = new ArrayList<>();
		if (arrayObj != null && arrayObj.size() > 0) {
			for (int i = 0; i < arrayObj.size(); i++) {
				SchemeMapping ecData1 = new SchemeMapping();
				ecData1 = gson.fromJson(arrayObj.get(i), SchemeMapping.class);

				charges1.add(ecData1);
			}
			ProjectLaunch regionData=projectLaunchService.getprojectDetails(charges1.get(0).getProject_id());
			//charges1.add
			// paymentDtlService.insertPaymentDtl(charges1);

			schemeMappingService.insertBulkMapping(charges1);

		}
		return gson.toJson("");
	}
	// END Bulk insert for scheme mapping

	// check inventory status
	// @RequestParam("customerContact") long contact,
	@RequestMapping(value = { "/getInventoryStatus" }, method = RequestMethod.POST)
	public String getInventoryStatus(@RequestParam("userid") String userid,
			@RequestParam("projectsfid") String projectsfid, @RequestParam("propid") String propid)
			throws JRException, IOException {

		log.info(" Check inventory status Parameters - projectsfid : " + projectsfid + " propid :" + propid);

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		String errorMsg1 = KeyConstants.ERROR_MSG_101; // This unit is no longer
														// available please
														// select another unit.
		String errorMsg2 = KeyConstants.ERROR_MSG_102; // Inventory is not
														// activated
		String errorMsg3 = KeyConstants.ERROR_MSG_103; // Yes, There is some
														// technical problem.
		String successMsg2 = KeyConstants.SUCCESS_MSG_102; // Unit Available
		String errorMsg5 = KeyConstants.ERROR_MSG_105; // No User ID
		String errorMsg6 = KeyConstants.ERROR_MSG_106; // //No Project ID or Unit ID is available, because of enquiry may not sync.
		 
		try {
			
			if (userid != null && !userid.equals("null")  && userid != "") {
				if (!"".equals(projectsfid) && !"".equals(propid)) {
					// Add by A for check unit status
					String propStatus = inventoryStatusController.inventoryStatus(projectsfid, propid);

					JsonElement root = new JsonParser().parse(propStatus);
					JsonArray jsonArray = root.getAsJsonArray();

					// Boolean adminUnitStatus =
					// adminUnitHoldStatusService.getAdminUnitHold(propid);
					Boolean salesUnitStatus = salesUnitHoldStatusService.getSalesUnitHold(propid, userid);

					if (jsonArray.size() > 0) {
						JsonObject jsonObject1 = jsonArray.get(0).getAsJsonObject();
						String propertyoholdforreallocation = jsonObject1.get("propertyoholdforreallocation").getAsString();
						String PropertyForWebsite = jsonObject1.get("PropertyForWebsite").getAsString();
						String PropertyForSales = jsonObject1.get("PropertyForSales").getAsString();
						String PropertyForCP = jsonObject1.get("PropertyForCP").getAsString();
						String Propertyallotedthroughoffer = jsonObject1.get("Propertyallotedthroughoffer").getAsString();
						String alloted = jsonObject1.get("alloted").getAsString();
						String active = jsonObject1.get("active").getAsString();

						if (propertyoholdforreallocation != null && PropertyForWebsite != null && PropertyForSales != null
								&& PropertyForCP != null && Propertyallotedthroughoffer != null && alloted != null
								&& active != null) {
							if (active.equals("true")) {
								if (Propertyallotedthroughoffer.equals("false") && alloted.equals("false")
										&& salesUnitStatus == false) {
									return successMsg2;
								} else {
									return errorMsg1;
								}
							} else {
								return errorMsg2;
							}
						}
					} else {
						log.info(" Check inventory status - Yes, There is some technical problem (code:1) ");
						return errorMsg3;
					}
				} else {
					log.info(" Get Cost Sheet Details : Project ID or Unit ID is not available, because of enquiry may not sync. ");
					return errorMsg6;
				}
			} else {
				log.info(" Get Cost Sheet Details : User ID is null or empty ");

				return errorMsg5;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info(" Check inventory status - Yes, There is some technical problem (code:2) ");

		return errorMsg3;
	}
	// END check inventory status

	// Get enquiry Details
	@RequestMapping(value = { "/getEnquiryDetails" }, method = RequestMethod.POST)
	public String getEnquiryDetails(@RequestParam("enqSFID") String enqSFID) throws JRException, IOException {

		try {
			if (!"".equals(enqSFID)) {

				log.info(" Check inventory status Parameters - enqSFID : " + enqSFID);

				String enq = getEnquiryComments.enquiryData(enqSFID);

				JsonObject jobj = new Gson().fromJson(enq, JsonObject.class);
				String Old_Comments__c = "";
				if (jobj.get("Old_Comments__c") != null)
					Old_Comments__c = jobj.get("Old_Comments__c").getAsString();

				if (Old_Comments__c != null) {
					return Old_Comments__c;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	// END Get enquiry Details

	@RequestMapping(value = "/validateStr", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String validateMobileStr(@ModelAttribute("coapplicantdata") CoApplicant coapp,
			@RequestParam("dncstr") String encstr, @RequestParam("user_entr_no") String userNo,
			@RequestParam("projectid") String projectid, @RequestParam("enqsfid") String enqsfid) {
		// ModelAndView model = new ModelAndView();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		StringEncDec enc = new StringEncDec();
		@SuppressWarnings("static-access")
		String decStr = enc.decrypt(encstr);
		log.info("EncriptString =" + decStr);
		if (decStr.equals(userNo)) {
			log.info("Input Validate");
			log.info("decStr:-" + decStr);
			log.info("userNo:-" + userNo);
			// userEOIService.findMobileNoExist(decStr);
			// model.setViewName("index");
			/*
			 * List<EOIData> eList =
			 * userEOIService.findMobileNoExistEOIForm(decStr,
			 * projectid,enqsfid);
			 */
			EOIData eoiData = new EOIData();
			eoiData.setPhone_number("ok");

			return gson.toJson(eoiData);
		} else {
			return gson.toJson("Invalid input or No Entry Found");
		}

	}

	@RequestMapping(value = "/getccAvenueList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String validateMobileStr(@RequestParam("dncstr") String encstr, @RequestParam("projectid") String projectid,
			@RequestParam("enqsfid") String enqsfid) {

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		StringEncDec enc = new StringEncDec();
		@SuppressWarnings("static-access")
		String decStr = enc.decrypt(encstr);
		log.info("EncriptString =" + decStr);
		log.info("Input Validate");
		log.info("decStr:-" + decStr);
		List<EOIData> eList = userEOIService.findMobileNoExistEOIForm(decStr, projectid, enqsfid);
		return gson.toJson(eList);
	}
	
	@RequestMapping(value = "/projectListForSales", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getProjectListForSalesTnc(@RequestParam("userId") String userId) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		int id=Integer.parseInt(userId);;
		/*ProjectRegion region = projectRegionService.getRegionForTnc(projectid);*/
		List<UserProjectMapping> projectList = userProjectMappingService.getUserProject(id);
		return gson.toJson(projectList);
	}
	
	@RequestMapping(value = "/getSearchTncData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String getSearchTncData(@RequestParam("proId") String proId,
			@RequestParam("towerId") String towerId,@RequestParam("pymtPlanId") String pymtPlanId) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		List<TnC> tncList = tnCService.getSearchTncData(pymtPlanId,proId,towerId);  //getTncData(pymtPlanId,proId,towerId);
		return gson.toJson(tncList);
	}
	
	@RequestMapping(value="/deleteTnc" , method=RequestMethod.POST)
	public @ResponseBody String deleteTnc(@RequestParam("Id") int id) 
	{
		if(id!=0){
			boolean deleteTowerPP=tnCService.deleteTncData(id);	
			if(deleteTowerPP){
				return "{\"status\":\"STATUS_OK\",\"error_msg\":\"Deleted\"}";
			}else{
				return "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\"}";

			}
		} else {
			return "{\"status\":\"STATUS_NOTOK\",\"error_msg\":\"Invalid Data Provide\"}";
		}
	}
	@GetMapping(value = "/getSourcingLeadReport", produces = "application/json;charset=UTF-8")
	public String getSourcingLeadReport(@RequestParam("emailid") String sourcManageremail,
			@RequestParam("projectsfid") String projectsfid,@RequestParam("fromdate") String fromdate,@RequestParam("todate") String todate) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return gson.toJson(enquiryRequestService.getSourcingLeadsEnquiryList(sourcManageremail, projectsfid, fromdate, todate));
	}

	public static void pdfToImage(File file, String folderPath) {
		//private static void pdfToImage(String pdfPath) {
			
			String pdfPath = file.toPath().toString();
					
			try (PDDocument doc = PDDocument.load(new File(pdfPath))) {
				PDFRenderer pdfRenderer = new PDFRenderer(doc); 
				
				for (int page = 0;page<doc.getNumberOfPages();++page) {
					BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 150, ImageType.RGB);
					//String fileName = "D:\\atul_data\\apache-tomcat-9.0.22\\costSheetPDF\\Mumbai\\Godrej City, Panvel, Mumbai\\The Highlands Tower 1\\4th floor\\403\\image-"+page+".png";
					
					String imageName = file.getName();
					imageName = imageName.substring(0, imageName.lastIndexOf(".")+1);
					
					String fileName = folderPath+imageName+"png";
					
					ImageIOUtil.writeImage(bim, fileName, 150);
				}
				doc.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	@RequestMapping(value = { "/getTodayFollowUp" }, method = RequestMethod.GET)
	public String getTodayFollowUp(@RequestParam("projectid") String projectid,
			@RequestParam("user_id") String userId, @RequestParam("fromdate") String fromdate,
			@RequestParam("todate") String todate,@RequestParam("source") String source) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		List<TodaysFollowUp> assign = assignUserService.getData(userId, projectid, fromdate, todate, source);
		if (assign != null) {
			for (int i = 0; assign.size() > i; i++) {
				Timestamp later = new Timestamp(assign.get(i).getStarteddate().getTime() + (330 * 60 * 1000));
				assign.get(i).setStarteddate(later);
				// assign.add(assignData);
			}
		}
		return gson.toJson(assign);
	}
	
}