package com.godrej.properties.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name="salesforce.propstrength__offer__c")
public class EnqAndOfferDtl implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private int id;
	@Column(name="propstrength__enquiry_type__c") private String propstrength__enquiry_type__c;
	@Column(name="propstrength__request__c") private String enquiry18digit__c;
	//@Column(name="application_booking_id_18__c") private String application_booking_id_18__c;
	@Column(name="broker_name") private String broker_name;
	@Column(name="broker_mobile") private String broker_mobile;
	@Column(name="appurtenant_area_sq_mt__c") private double appurtenant_area_sq_mt__c;
	@Column(name="carpet_area_converted__c") private double carpet_area_converted__c;
	@Column(name="propstrength__tower_name__c") private String propstrength__tower_name__c;
	@Column(name="propstrength__property_name__c") private String propstrength__property_name__c;
	//@Column(name="propstrength__revised_agreement_amount__c") private double propstrength__revised_agreement_amount__c;
	@Column(name="propstrength__floor_name__c") private String propstrength__floor_name__c;
	//@Column(name="sap_customer_code__c") private String sap_customer_code__c;
	@Column(name="name") private String name;
	@Column(name="offer_date__c") private Date propstrength__booking_date__c;
	@Column(name="project_phases__c") private String project_phases__c;
	//@Column(name="project_name__c") private String project_name__c;
	@Column(name="propstrength__description__c") private String propstrength__description__c;
	@Column(name="jv_name__c") private String jv_name__c;
	@Column(name="jv_street__c") private String jv_street__c;
	@Column(name="jv_state__c") private String jv_state__c;
	@Column(name="jv_country__c") private String jv_country__c;
	@Column(name="jv_city__c") private String jv_city__c;
	@Column(name="is_purchase_for_self_use_or_investment__c") private String is_purchase_for_self_use_or_investment__c;
	@Column(name="walk_in_source__c") private String walk_in_source__c;
	
	@Column(name="marketing_project_name__c") private String marketing_project_name__c;
	
	@Column(name="propstrength__total_basic_sale_price__c") private double propstrength__total_basic_sales_price__c;
	
	//New -- (below name replace with the "application_booking_id_18__c")
	@Column(name="sfid") private String offer_sfid;
	@Column(name="propstrength__net_revenue__c") private double propstrength__net_revenue__c;
	@Column(name="propstrength__total_other_charges__c") private double propstrength__total_other_charges__c;
	
	@Column(name="propstrength__primary_contact__c") private String propstrength__primary_contact__c;
	@Column(name="rera_registration_number__c") private String rera_registration_number__c;
	
	@Column(name="prop_sfid") private String prop_sfid;
	
	@Column(name="open_balc_sq_mt__c", precision = 10, scale = 2) private BigDecimal open_balc_sq_mt__c;
	@Column(name="saleable_area__c", precision = 10, scale = 2)  private BigDecimal saleable_area__c;
	
	@Column(name="wing_block__c") private String wing_block__c;
	@Column(name="tower_rera_no") private String tower_rera_no;
	
	@Transient
	private Map<String, InventoryOtherCharges> mappedCharges;
	
	@Column(name="appurtenant_area_sq_ft__c") private double appurtenant_area_sq_ft__c;
	@Column(name="open_balc_sq_ft__c") private double open_balc_sq_ft__c;
	
	
	@Column(name="length_sqm__c", precision = 10, scale = 2)  private BigDecimal length_sqm__c;
	@Column(name="breadth_sqm__c", precision = 10, scale = 2)  private BigDecimal breadth_sqm__c;
	@Column(name="plot_area_sqyd__c", precision = 10, scale = 2)  private BigDecimal plot_area_sqyd__c;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPropstrength__enquiry_type__c() {
		return propstrength__enquiry_type__c;
	}
	public void setPropstrength__enquiry_type__c(String propstrength__enquiry_type__c) {
		this.propstrength__enquiry_type__c = propstrength__enquiry_type__c;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getBroker_name() {
		return broker_name;
	}
	public void setBroker_name(String broker_name) {
		this.broker_name = broker_name;
	}
	public String getBroker_mobile() {
		return broker_mobile;
	}
	public void setBroker_mobile(String broker_mobile) {
		this.broker_mobile = broker_mobile;
	}
	public double getAppurtenant_area_sq_mt__c() {
		return appurtenant_area_sq_mt__c;
	}
	public void setAppurtenant_area_sq_mt__c(double appurtenant_area_sq_mt__c) {
		this.appurtenant_area_sq_mt__c = appurtenant_area_sq_mt__c;
	}
	public double getCarpet_area_converted__c() {
		return carpet_area_converted__c;
	}
	public void setCarpet_area_converted__c(double carpet_area_converted__c) {
		this.carpet_area_converted__c = carpet_area_converted__c;
	}
	public String getPropstrength__tower_name__c() {
		return propstrength__tower_name__c;
	}
	public void setPropstrength__tower_name__c(String propstrength__tower_name__c) {
		this.propstrength__tower_name__c = propstrength__tower_name__c;
	}
	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}
	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}
	public String getPropstrength__floor_name__c() {
		return propstrength__floor_name__c;
	}
	public void setPropstrength__floor_name__c(String propstrength__floor_name__c) {
		this.propstrength__floor_name__c = propstrength__floor_name__c;
	}
	
	public String getProject_phases__c() {
		return project_phases__c;
	}
	public void setProject_phases__c(String project_phases__c) {
		this.project_phases__c = project_phases__c;
	}
	
	public String getPropstrength__description__c() {
		return propstrength__description__c;
	}
	public void setPropstrength__description__c(String propstrength__description__c) {
		this.propstrength__description__c = propstrength__description__c;
	}
	public String getJv_name__c() {
		return jv_name__c;
	}
	public void setJv_name__c(String jv_name__c) {
		this.jv_name__c = jv_name__c;
	}
	public String getJv_street__c() {
		return jv_street__c;
	}
	public void setJv_street__c(String jv_street__c) {
		this.jv_street__c = jv_street__c;
	}
	public String getJv_state__c() {
		return jv_state__c;
	}
	public void setJv_state__c(String jv_state__c) {
		this.jv_state__c = jv_state__c;
	}
	public String getJv_country__c() {
		return jv_country__c;
	}
	public void setJv_country__c(String jv_country__c) {
		this.jv_country__c = jv_country__c;
	}
	public String getJv_city__c() {
		return jv_city__c;
	}
	public void setJv_city__c(String jv_city__c) {
		this.jv_city__c = jv_city__c;
	}
	public String getIs_purchase_for_self_use_or_investment__c() {
		return is_purchase_for_self_use_or_investment__c;
	}
	public void setIs_purchase_for_self_use_or_investment__c(String is_purchase_for_self_use_or_investment__c) {
		this.is_purchase_for_self_use_or_investment__c = is_purchase_for_self_use_or_investment__c;
	}
	public String getWalk_in_source__c() {
		return walk_in_source__c;
	}
	public void setWalk_in_source__c(String walk_in_source__c) {
		this.walk_in_source__c = walk_in_source__c;
	}
	public String getMarketing_project_name__c() {
		return marketing_project_name__c;
	}
	public void setMarketing_project_name__c(String marketing_project_name__c) {
		this.marketing_project_name__c = marketing_project_name__c;
	}
	public String getEnquiry18digit__c() {
		return enquiry18digit__c;
	}
	public void setEnquiry18digit__c(String enquiry18digit__c) {
		this.enquiry18digit__c = enquiry18digit__c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPropstrength__booking_date__c() {
		return propstrength__booking_date__c;
	}
	public void setPropstrength__booking_date__c(Date propstrength__booking_date__c) {
		this.propstrength__booking_date__c = propstrength__booking_date__c;
	}
	public String getOffer_sfid() {
		return offer_sfid;
	}
	public void setOffer_sfid(String offer_sfid) {
		this.offer_sfid = offer_sfid;
	}
	public double getPropstrength__total_basic_sales_price__c() {
		return propstrength__total_basic_sales_price__c;
	}
	public void setPropstrength__total_basic_sales_price__c(double propstrength__total_basic_sales_price__c) {
		this.propstrength__total_basic_sales_price__c = propstrength__total_basic_sales_price__c;
	}
	public double getPropstrength__net_revenue__c() {
		return propstrength__net_revenue__c;
	}
	public void setPropstrength__net_revenue__c(double propstrength__net_revenue__c) {
		this.propstrength__net_revenue__c = propstrength__net_revenue__c;
	}
	public double getPropstrength__total_other_charges__c() {
		return propstrength__total_other_charges__c;
	}
	public void setPropstrength__total_other_charges__c(double propstrength__total_other_charges__c) {
		this.propstrength__total_other_charges__c = propstrength__total_other_charges__c;
	}
	public String getPropstrength__primary_contact__c() {
		return propstrength__primary_contact__c;
	}
	public void setPropstrength__primary_contact__c(String propstrength__primary_contact__c) {
		this.propstrength__primary_contact__c = propstrength__primary_contact__c;
	}
	public String getRera_registration_number__c() {
		return rera_registration_number__c;
	}
	public void setRera_registration_number__c(String rera_registration_number__c) {
		this.rera_registration_number__c = rera_registration_number__c;
	}
	public Map<String, InventoryOtherCharges> getMappedCharges() {
		return mappedCharges;
	}
	public void setMappedCharges(Map<String, InventoryOtherCharges> mappedCharges) {
		this.mappedCharges = mappedCharges;
	}
	public String getProp_sfid() {
		return prop_sfid;
	}
	public void setProp_sfid(String prop_sfid) {
		this.prop_sfid = prop_sfid;
	}
	public BigDecimal getOpen_balc_sq_mt__c() {
		return open_balc_sq_mt__c;
	}
	public void setOpen_balc_sq_mt__c(BigDecimal open_balc_sq_mt__c) {
		this.open_balc_sq_mt__c = open_balc_sq_mt__c;
	}
	public BigDecimal getSaleable_area__c() {
		return saleable_area__c;
	}
	public void setSaleable_area__c(BigDecimal saleable_area__c) {
		this.saleable_area__c = saleable_area__c;
	}
	public String getTower_rera_no() {
		return tower_rera_no;
	}
	public void setTower_rera_no(String tower_rera_no) {
		this.tower_rera_no = tower_rera_no;
	}
	public double getAppurtenant_area_sq_ft__c() {
		return appurtenant_area_sq_ft__c;
	}
	public void setAppurtenant_area_sq_ft__c(double appurtenant_area_sq_ft__c) {
		this.appurtenant_area_sq_ft__c = appurtenant_area_sq_ft__c;
	}
	public double getOpen_balc_sq_ft__c() {
		return open_balc_sq_ft__c;
	}
	public void setOpen_balc_sq_ft__c(double open_balc_sq_ft__c) {
		this.open_balc_sq_ft__c = open_balc_sq_ft__c;
	}
	public BigDecimal getLength_sqm__c() {
		return length_sqm__c;
	}
	public void setLength_sqm__c(BigDecimal length_sqm__c) {
		this.length_sqm__c = length_sqm__c;
	}
	public BigDecimal getBreadth_sqm__c() {
		return breadth_sqm__c;
	}
	public void setBreadth_sqm__c(BigDecimal breadth_sqm__c) {
		this.breadth_sqm__c = breadth_sqm__c;
	}
	public BigDecimal getPlot_area_sqyd__c() {
		return plot_area_sqyd__c;
	}
	public void setPlot_area_sqyd__c(BigDecimal plot_area_sqyd__c) {
		this.plot_area_sqyd__c = plot_area_sqyd__c;
	}
}