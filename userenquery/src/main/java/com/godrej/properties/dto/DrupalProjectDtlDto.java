package com.godrej.properties.dto;

import java.io.Serializable;
 
public class DrupalProjectDtlDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String status;
	private String bannerStatus;
	private String projectStatus;
	
	private String banner;
	private String project_dis;
	private String brochure_url;
	private String other_pdf_url;
	private String proj_vid;
	private String foyr_url;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getBannerStatus() {
		return bannerStatus;
	}
	public void setBannerStatus(String bannerStatus) {
		this.bannerStatus = bannerStatus;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getProject_dis() {
		return project_dis;
	}
	public void setProject_dis(String project_dis) {
		this.project_dis = project_dis;
	}
	public String getBrochure_url() {
		return brochure_url;
	}
	public void setBrochure_url(String brochure_url) {
		this.brochure_url = brochure_url;
	}
	public String getOther_pdf_url() {
		return other_pdf_url;
	}
	public void setOther_pdf_url(String other_pdf_url) {
		this.other_pdf_url = other_pdf_url;
	}
	public String getProj_vid() {
		return proj_vid;
	}
	public void setProj_vid(String proj_vid) {
		this.proj_vid = proj_vid;
	}
	public String getFoyr_url() {
		return foyr_url;
	}
	public void setFoyr_url(String foyr_url) {
		this.foyr_url = foyr_url;
	}
}