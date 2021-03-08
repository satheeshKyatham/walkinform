package com.godrej.properties.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "salesforce.nv_token_screen_config")
public class TokenScreenConfig {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id") private  int id;
	@Column(name="createdby") private Integer createdby;
	@Column(name="updatedby") private Integer updatedby;
	@Column(name="created") private Timestamp created;
	@Column(name="updated") private Timestamp updated;
	@Column(name="projectsfid") private String projectsfid;
	@Column(name="token_type") private String token_type;
	@Column(name="key_cont1") private String key_cont1;
	@Column(name="key_cont2") private String key_cont2;
	@Column(name="video_url") private String video_url;
	@Column(name="image_url") private String image_url;
	@Column(name="ticker_label") private String ticker_label;
	@Column(name="ticker_text") private String ticker_text;
	
	@Transient private String status;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getCreatedby() {
		return createdby;
	}
	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}
	public Integer getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(Integer updatedby) {
		this.updatedby = updatedby;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public String getProjectsfid() {
		return projectsfid;
	}
	public void setProjectsfid(String projectsfid) {
		this.projectsfid = projectsfid;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getKey_cont1() {
		return key_cont1;
	}
	public void setKey_cont1(String key_cont1) {
		this.key_cont1 = key_cont1;
	}
	public String getKey_cont2() {
		return key_cont2;
	}
	public void setKey_cont2(String key_cont2) {
		this.key_cont2 = key_cont2;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getTicker_label() {
		return ticker_label;
	}
	public void setTicker_label(String ticker_label) {
		this.ticker_label = ticker_label;
	}
	public String getTicker_text() {
		return ticker_text;
	}
	public void setTicker_text(String ticker_text) {
		this.ticker_text = ticker_text;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}