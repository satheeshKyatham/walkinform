package com.godrej.properties.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.vw_project_inventory_uat") 
public class InventoryReport implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_no") private int  row_number;
	@Column(name="gpl_cs_hold_admin_unit_id") private  int id ;
	@Column(name="unit_sfid") private String	unit_sfid;
	@Column(name="admin_userid") private String	admin_userid;
	@Column(name="project_id") private String	project_id;
	@Column(name="hold_reason") private String	hold_reason;
	@Column(name="hold_status") private String	hold_status;
	@Column(name="hold_description") private String	hold_description;
	@Column(name="hold_behalf_username") private String	hold_behalf_username;
	@Column(name="floor_number__c") private double	floor_number__c;
	@Column(name="propstrength__house_unit_no__c") private String	propstrength__house_unit_no__c;
	@Column(name="tower_code__c") private String	tower_code__c;
	@Column(name="propstrength__unit_type__c") private String	propstrength__unit_type__c;
	@Column(name="admin_name") private String	admin_name;
	@Column(name="admin_emailid") private String	admin_emailid;
	@Column(name="propstrength__active__c") private boolean	propstrength__active__c;
	@Column(name="tower_name__c") private String tower_name__c;
	@Column(name="created_at") private Timestamp created_at;
	@Column(name="hold_behalf_email") private String hold_behalf_email;
	
	@Column(name="enq_name") private String enq_name;
	@Column(name="customer_name") private String customer_name;
	@Column(name="customer_mobile") private String customer_mobile;
	@Column(name="enq_sfid") private String enq_sfid;
	
	@Column(name="eoi_unit_locked") private boolean	eoi_unit_locked;
	@Column(name="wing_block__c") private String wing_block__c;
	@Column(name="saleable_area__c", precision = 10, scale = 2)  private BigDecimal saleable_area__c;
	@Column(name="propstrength__rate_per_unit_area__c", precision = 10, scale = 2)  private BigDecimal propstrength__rate_per_unit_area__c;

	@Column(name="propstrength__part_of_cop__c") private Boolean propstrength__part_of_cop__c;
	@Column(name="propstrength__type__c") private String propstrength__type__c;
	@Column(name="othercharge__rate_per_unit_area__c", precision = 10, scale = 2)  private BigDecimal othercharge__rate_per_unit_area__c;
	@Column(name="propstrength__fixed_charge__c", precision = 10, scale = 2)  private BigDecimal propstrength__fixed_charge__c;
	
	@Column(name="verticle__c") private String verticle__c;
	
	@Column(name="closing_manager_name__c") private String closing_manager_name__c;
	
	@Column(name="walk_in_source__c") private String walk_in_source__c;
	@Column(name="sourcing_manager_name__c") private String sourcing_manager_name__c;
	@Column(name="broker_name") private String broker_name;
	//@Column(name="created_at") private Timestamp hold_created_at;
	
	//Parking
	@Column(name="parkingname") private String parkingname;
	//END Parking
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUnit_sfid() {
		return unit_sfid;
	}
	public void setUnit_sfid(String unit_sfid) {
		this.unit_sfid = unit_sfid;
	}
	public String getAdmin_userid() {
		return admin_userid;
	}
	public void setAdmin_userid(String admin_userid) {
		this.admin_userid = admin_userid;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getHold_reason() {
		return hold_reason;
	}
	public void setHold_reason(String hold_reason) {
		this.hold_reason = hold_reason;
	}
	public String getHold_status() {
		return hold_status;
	}
	public void setHold_status(String hold_status) {
		this.hold_status = hold_status;
	}
	public String getHold_description() {
		return hold_description;
	}
	public void setHold_description(String hold_description) {
		this.hold_description = hold_description;
	}
	public String getHold_behalf_username() {
		return hold_behalf_username;
	}
	public void setHold_behalf_username(String hold_behalf_username) {
		this.hold_behalf_username = hold_behalf_username;
	}
	public double getFloor_number__c() {
		return floor_number__c;
	}
	public void setFloor_number__c(double floor_number__c) {
		this.floor_number__c = floor_number__c;
	}
	public String getPropstrength__house_unit_no__c() {
		return propstrength__house_unit_no__c;
	}
	public void setPropstrength__house_unit_no__c(String propstrength__house_unit_no__c) {
		this.propstrength__house_unit_no__c = propstrength__house_unit_no__c;
	}
	public String getTower_code__c() {
		return tower_code__c;
	}
	public void setTower_code__c(String tower_code__c) {
		this.tower_code__c = tower_code__c;
	}
	public String getPropstrength__unit_type__c() {
		return propstrength__unit_type__c;
	}
	public void setPropstrength__unit_type__c(String propstrength__unit_type__c) {
		this.propstrength__unit_type__c = propstrength__unit_type__c;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_emailid() {
		return admin_emailid;
	}
	public void setAdmin_emailid(String admin_emailid) {
		this.admin_emailid = admin_emailid;
	}
	public boolean isPropstrength__active__c() {
		return propstrength__active__c;
	}
	public void setPropstrength__active__c(boolean propstrength__active__c) {
		this.propstrength__active__c = propstrength__active__c;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTower_name__c() {
		return tower_name__c;
	}
	public void setTower_name__c(String tower_name__c) {
		this.tower_name__c = tower_name__c;
	}
	
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public String getHold_behalf_email() {
		return hold_behalf_email;
	}
	public void setHold_behalf_email(String hold_behalf_email) {
		this.hold_behalf_email = hold_behalf_email;
	}
	public String getEnq_name() {
		return enq_name;
	}
	public void setEnq_name(String enq_name) {
		this.enq_name = enq_name;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_mobile() {
		return customer_mobile;
	}
	public void setCustomer_mobile(String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}
	public String getEnq_sfid() {
		return enq_sfid;
	}
	public void setEnq_sfid(String enq_sfid) {
		this.enq_sfid = enq_sfid;
	}
	public boolean isEoi_unit_locked() {
		return eoi_unit_locked;
	}
	public void setEoi_unit_locked(boolean eoi_unit_locked) {
		this.eoi_unit_locked = eoi_unit_locked;
	}
	public String getWing_block__c() {
		return wing_block__c;
	}
	public void setWing_block__c(String wing_block__c) {
		this.wing_block__c = wing_block__c;
	}
	public BigDecimal getSaleable_area__c() {
		return saleable_area__c;
	}
	public void setSaleable_area__c(BigDecimal saleable_area__c) {
		this.saleable_area__c = saleable_area__c;
	}
	public BigDecimal getPropstrength__rate_per_unit_area__c() {
		return propstrength__rate_per_unit_area__c;
	}
	public void setPropstrength__rate_per_unit_area__c(BigDecimal propstrength__rate_per_unit_area__c) {
		this.propstrength__rate_per_unit_area__c = propstrength__rate_per_unit_area__c;
	}
	public Boolean getPropstrength__part_of_cop__c() {
		return propstrength__part_of_cop__c;
	}
	public void setPropstrength__part_of_cop__c(Boolean propstrength__part_of_cop__c) {
		this.propstrength__part_of_cop__c = propstrength__part_of_cop__c;
	}
	public String getPropstrength__type__c() {
		return propstrength__type__c;
	}
	public void setPropstrength__type__c(String propstrength__type__c) {
		this.propstrength__type__c = propstrength__type__c;
	}
	public BigDecimal getOthercharge__rate_per_unit_area__c() {
		return othercharge__rate_per_unit_area__c;
	}
	public void setOthercharge__rate_per_unit_area__c(BigDecimal othercharge__rate_per_unit_area__c) {
		this.othercharge__rate_per_unit_area__c = othercharge__rate_per_unit_area__c;
	}
	public BigDecimal getPropstrength__fixed_charge__c() {
		return propstrength__fixed_charge__c;
	}
	public void setPropstrength__fixed_charge__c(BigDecimal propstrength__fixed_charge__c) {
		this.propstrength__fixed_charge__c = propstrength__fixed_charge__c;
	}
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public String getVerticle__c() {
		return verticle__c;
	}
	public void setVerticle__c(String verticle__c) {
		this.verticle__c = verticle__c;
	}
	public String getClosing_manager_name__c() {
		return closing_manager_name__c;
	}
	public void setClosing_manager_name__c(String closing_manager_name__c) {
		this.closing_manager_name__c = closing_manager_name__c;
	}
	public String getWalk_in_source__c() {
		return walk_in_source__c;
	}
	public void setWalk_in_source__c(String walk_in_source__c) {
		this.walk_in_source__c = walk_in_source__c;
	}
	public String getSourcing_manager_name__c() {
		return sourcing_manager_name__c;
	}
	public void setSourcing_manager_name__c(String sourcing_manager_name__c) {
		this.sourcing_manager_name__c = sourcing_manager_name__c;
	}
	public String getBroker_name() {
		return broker_name;
	}
	public void setBroker_name(String broker_name) {
		this.broker_name = broker_name;
	}
	public String getParkingname() {
		return parkingname;
	}
	public void setParkingname(String parkingname) {
		this.parkingname = parkingname;
	}
}