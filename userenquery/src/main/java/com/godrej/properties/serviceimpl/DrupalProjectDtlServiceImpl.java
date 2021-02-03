package com.godrej.properties.serviceimpl;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dto.DrupalProjectDtlDto;
import com.godrej.properties.service.DrupalProjectDtlService;
import com.godrej.properties.webservices.DrupalBannerImage;
import com.godrej.properties.webservices.DrupalProjectBasicDtl;

@Service("drupalProjectDtlService")
@Transactional
public class DrupalProjectDtlServiceImpl implements DrupalProjectDtlService {
	
	
	@Autowired
	DrupalBannerImage drupalBannerImage;
	
	@Autowired
	DrupalProjectBasicDtl drupalProjectBasicDtl;
	
	@Override
	public DrupalProjectDtlDto getProjectDtl(String projectsfid) {
		
		DrupalProjectDtlDto drupalProjectDtlDto = new DrupalProjectDtlDto();
		
		try {
			String bannerDtl = drupalBannerImage.drupalProjectBanner(projectsfid);
			
			JSONObject jsonBanner = new JSONObject(bannerDtl);
		    JSONObject getBanner = jsonBanner.getJSONObject("maindata");
		    Object bannerUrl = getBanner.get("banner_link");
		    
			drupalProjectDtlDto.setBanner(bannerUrl.toString());
			drupalProjectDtlDto.setBannerStatus("STATUS_OK");
			
		} catch (Exception e) {
			drupalProjectDtlDto.setBannerStatus("STATUS_NOTOK");
		}
		
		try {
			String projectBasicDtl = drupalProjectBasicDtl.drupalProjectDtl(projectsfid);
			
		    JSONObject jsonProject = new JSONObject(projectBasicDtl);
		    JSONArray  getPro = jsonProject.getJSONArray("data");
		    JSONObject result1 = getPro.getJSONObject(0);
		    
		    Object proDesc = result1.get("field_project_details_descriptio");
		    Object brochureUrl = result1.get("brochure_url");
		    Object otherPdf = result1.get("other_pdf_url");
		    Object projVid = result1.get("proj_vid");
		    Object foyrUrl = result1.get("field_foyr_url");
		    
			drupalProjectDtlDto.setProjectStatus("STATUS_OK");
			drupalProjectDtlDto.setProject_dis(proDesc.toString());
			drupalProjectDtlDto.setBrochure_url(brochureUrl.toString());
			drupalProjectDtlDto.setOther_pdf_url(otherPdf.toString());
			drupalProjectDtlDto.setProj_vid(projVid.toString());
			drupalProjectDtlDto.setFoyr_url(foyrUrl.toString());
		} catch (Exception e) {
			drupalProjectDtlDto.setProjectStatus("STATUS_NOTOK"); 
		}
		return drupalProjectDtlDto;
	}
}
