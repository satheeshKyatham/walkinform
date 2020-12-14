package com.godrej.properties.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.VW_MISReportDao;
import com.godrej.properties.model.Vw_MISReport;
import com.godrej.properties.service.VW_MISReportService;

@Service("vW_MISReportService")
@Transactional
public class VW_MISReportServiceImpl implements VW_MISReportService {

	
	@Autowired
	VW_MISReportDao vW_MISReportDao;
	
	@Override
	public List<Vw_MISReport> getUserProjectList(String projectid,String userid, String fromDate, String toDate, String userVerticals) {
		 
		
		// For multiple project report
		String [] multiProjectid= projectid.split(",");
		
		StringBuilder modifiedProid = new StringBuilder();
		String finalProjectid = "";
		
		for (int i=0;i<multiProjectid.length;i++){
			modifiedProid.append("'"+multiProjectid[i]+"'");
			modifiedProid.append(",");
		}
		
		finalProjectid = modifiedProid.toString();
		
		if (finalProjectid != null && finalProjectid.length() > 0 && finalProjectid.charAt(finalProjectid.length() - 1) == ',') {
			finalProjectid = finalProjectid.substring(0, finalProjectid.length() - 1);
		}
		// END For multiple project report
		
		
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
		
		
		List<Vw_MISReport> finalMislist = new ArrayList<Vw_MISReport>();
		List<Vw_MISReport> mislist  = vW_MISReportDao.getUserProjectList(finalProjectid,userid, fromDate, toDate, finalVerticales);
		if(mislist!=null && mislist.size()>0)
		{
			for (int i = 0; i < mislist.size(); i++) {
				Vw_MISReport mis = new Vw_MISReport();
				mis.setProjectname(checkNull(mislist.get(i).getProjectname()));
				mis.setTokenno(checkNull(mislist.get(i).getTokenno()));
				mis.setCreated(mislist.get(i).getCreated());
				mis.setEnquiryname(checkNull(mislist.get(i).getEnquiryname()));
				mis.setMobilephone(checkNull(mislist.get(i).getMobilephone()));
				mis.setName(checkNull(mislist.get(i).getName()));
				mis.setEmail(checkNull(mislist.get(i).getEmail()));
				mis.setHave_we_met_before(checkNull(mislist.get(i).getHave_we_met_before()));
				mis.setAge_a__c(checkNull(mislist.get(i).getAge_a__c()));
				mis.setResidenceaddress(checkNull(mislist.get(i).getResidenceaddress()));
				mis.setOfficelocation(checkNull(mislist.get(i).getOfficelocation()));
				mis.setEmpstatus(checkNull(mislist.get(i).getEmpstatus()));
				mis.setCompany_name__c(checkNull(mislist.get(i).getCompany_name__c()));
				mis.setIs_purchase_for_self_use_or_investment__c(checkNull(mislist.get(i).getIs_purchase_for_self_use_or_investment__c()));
				mis.setBudget(checkNull(mislist.get(i).getBudget()));
				mis.setCarpet_area_requirement(checkNull(mislist.get(i).getCarpet_area_requirement()));
				mis.setTypology_requirement(checkNull(mislist.get(i).getTypology_requirement()));
				mis.setWalk_in_source__c(checkNull(mislist.get(i).getWalk_in_source__c()));
				mis.setAdvertisementname(checkNull(mislist.get(i).getAdvertisementname()));
				mis.setBrokername(checkNull(mislist.get(i).getBrokername()));
				mis.setCurrent_residence_configuration(checkNull(mislist.get(i).getCurrent_residence_configuration()));
				mis.setCurrent_residence_ownership(checkNull(mislist.get(i).getCurrent_residence_ownership()));
				mis.setSource_of_funding(checkNull(mislist.get(i).getSource_of_funding()));
				mis.setCustomer_classification(checkNull(mislist.get(i).getCustomer_classification()));
				mis.setEthnicity(checkNull(mislist.get(i).getEthnicity()));
				mis.setUnit_availability(checkNull(mislist.get(i).getUnit_availability()));
				mis.setAccompanied_by(checkNull(mislist.get(i).getAccompanied_by()));
				mis.setDeal_negotiation(checkNull(mislist.get(i).getDeal_negotiation()));
				mis.setConstruction_status(checkNull(mislist.get(i).getConstruction_status()));
				mis.setTimeframe_to_book(checkNull(mislist.get(i).getTimeframe_to_book()));
				mis.setEnquirynoneditcomment(checkNull(mislist.get(i).getEnquirynoneditcomment()));
				mis.setClosingname(checkNull(mislist.get(i).getClosingname()));
				mis.setClosingemail(checkNull(mislist.get(i).getClosingemail()));
				mis.setOwn_contribution_receipt(checkNull(mislist.get(i).getOwn_contribution_receipt()));
				mis.setLoan_eligibility(checkNull(mislist.get(i).getLoan_eligibility()));
				mis.setAttended(checkNull(mislist.get(i).getAttended()));
				mis.setUser_name(checkNull(mislist.get(i).getUser_name()));
				mis.setCp_comments__c(checkNull(mislist.get(i).getCp_comments__c()));
				mis.setFollowtype(checkNull(mislist.get(i).getFollowtype()));
				mis.setFollowdate(mislist.get(i).getFollowdate());
				mis.setTrigger1(checkNull(mislist.get(i).getTrigger1()));
				mis.setTrigger2(checkNull(mislist.get(i).getTrigger2()));
				mis.setBarrier1(checkNull(mislist.get(i).getBarrier1()));
				mis.setBarrier2(checkNull(mislist.get(i).getBarrier2()));
				mis.setLost_reason_c__c(checkNull(mislist.get(i).getLost_reason_c__c()));
				mis.setVerticle__c(checkNull(mislist.get(i).getVerticle__c()));
				mis.setSourcingname(checkNull(mislist.get(i).getSourcingname()));
				mis.setDesignation__c(checkNull(mislist.get(i).getDesignation__c()));
				mis.setQry_count(checkNull(mislist.get(i).getQry_count()));
				mis.setQry_msg(checkNull(mislist.get(i).getQry_msg()));
				mis.setMedia_type__c(checkNull(mislist.get(i).getMedia_type__c()));
				mis.setMedia_sub_type__c(checkNull(mislist.get(i).getMedia_sub_type__c()));
				mis.setIs_revisit(checkNull(mislist.get(i).getIs_revisit()));
				mis.setLastvisitdate(checkNull(mislist.get(i).getLastvisitdate()));
				mis.setType_of_visit(checkNull(mislist.get(i).getType_of_visit()));
				mis.setD4u_comments(checkNull(mislist.get(i).getD4u_comments()));
				finalMislist.add(mis);
			}
		}
		return finalMislist;
	}
	public String checkNull(String inputVal)
	{
		if(inputVal!=null && inputVal.length()>0)
			return inputVal;
		else
			return "";
	}

}
