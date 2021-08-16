package com.godrej.properties.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class LWWebhookDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String feed_id;// Id of the feed 
	@JsonProperty("ratings")
	private JsonNode ratings;// ratings 
	private String comment;// Primary Comment 
	private String primary_rating;// primary_rating

	private String note;// Note only when event is note addition
	private String reply_text;// Reply text only when event is customer/manager reply
	private String project_name;// project_name
	private String shop_id;// TouchpointId 
	private String brand_id;// brand_id
	private String responded_date;// Feedback Date
	private String request_date;// Feedback Requested Date
	private String current_issue_status;// Issue Status
	private String last_issue_status;// Last issue status,only when event is status change
	private String tag_param_;// All possible tag fields
	
	
	private String action_by_name;// Name of the manager who made any change e.g. status/category
	private String issue_age_in_hours;// only when event is issue age
	private String issue_age_in_minutes;// only when event is issue age 
	private String current_issue_category;// Issue category 
	private String invitation_sms_delivery_status;
	
	private String Invitation_sms_delivery_failure_reason;
	private String invitation_email_delivery_status;
	private String Invitation_email_delivery_failure_reason;
	private String reminder_1_sms_delivery_status;
	private String reminder_1_sms_delivery_failure_reason;
	private String reminder_1_email_delivery_status;
	private String reminder_1_email_delivery_failure_reason;
	private String reminder_2_sms_delivery_status;
	private String reminder_2_sms_delivery_failure_reason;
	private String reminder_2_email_delivery_status;
	private String reminder_2_email_delivery_failure_reason;
	public String getFeed_id() {
		return feed_id;
	}
	public void setFeed_id(String feed_id) {
		this.feed_id = feed_id;
	}
	
	
	public JsonNode getRatings() {
		return ratings;
	}
	public void setRatings(JsonNode ratings) {
		this.ratings = ratings;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPrimary_rating() {
		return primary_rating;
	}
	public void setPrimary_rating(String primary_rating) {
		this.primary_rating = primary_rating;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getReply_text() {
		return reply_text;
	}
	public void setReply_text(String reply_text) {
		this.reply_text = reply_text;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	public String getResponded_date() {
		return responded_date;
	}
	public void setResponded_date(String responded_date) {
		this.responded_date = responded_date;
	}
	public String getRequest_date() {
		return request_date;
	}
	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}
	public String getCurrent_issue_status() {
		return current_issue_status;
	}
	public void setCurrent_issue_status(String current_issue_status) {
		this.current_issue_status = current_issue_status;
	}
	public String getLast_issue_status() {
		return last_issue_status;
	}
	public void setLast_issue_status(String last_issue_status) {
		this.last_issue_status = last_issue_status;
	}
	public String getTag_param_() {
		return tag_param_;
	}
	public void setTag_param_(String tag_param_) {
		this.tag_param_ = tag_param_;
	}
	public String getAction_by_name() {
		return action_by_name;
	}
	public void setAction_by_name(String action_by_name) {
		this.action_by_name = action_by_name;
	}
	public String getIssue_age_in_hours() {
		return issue_age_in_hours;
	}
	public void setIssue_age_in_hours(String issue_age_in_hours) {
		this.issue_age_in_hours = issue_age_in_hours;
	}
	public String getIssue_age_in_minutes() {
		return issue_age_in_minutes;
	}
	public void setIssue_age_in_minutes(String issue_age_in_minutes) {
		this.issue_age_in_minutes = issue_age_in_minutes;
	}
	public String getCurrent_issue_category() {
		return current_issue_category;
	}
	public void setCurrent_issue_category(String current_issue_category) {
		this.current_issue_category = current_issue_category;
	}
	public String getInvitation_sms_delivery_status() {
		return invitation_sms_delivery_status;
	}
	public void setInvitation_sms_delivery_status(String invitation_sms_delivery_status) {
		this.invitation_sms_delivery_status = invitation_sms_delivery_status;
	}
	public String getInvitation_sms_delivery_failure_reason() {
		return Invitation_sms_delivery_failure_reason;
	}
	public void setInvitation_sms_delivery_failure_reason(String invitation_sms_delivery_failure_reason) {
		Invitation_sms_delivery_failure_reason = invitation_sms_delivery_failure_reason;
	}
	public String getInvitation_email_delivery_status() {
		return invitation_email_delivery_status;
	}
	public void setInvitation_email_delivery_status(String invitation_email_delivery_status) {
		this.invitation_email_delivery_status = invitation_email_delivery_status;
	}
	public String getInvitation_email_delivery_failure_reason() {
		return Invitation_email_delivery_failure_reason;
	}
	public void setInvitation_email_delivery_failure_reason(String invitation_email_delivery_failure_reason) {
		Invitation_email_delivery_failure_reason = invitation_email_delivery_failure_reason;
	}
	public String getReminder_1_sms_delivery_status() {
		return reminder_1_sms_delivery_status;
	}
	public void setReminder_1_sms_delivery_status(String reminder_1_sms_delivery_status) {
		this.reminder_1_sms_delivery_status = reminder_1_sms_delivery_status;
	}
	public String getReminder_1_sms_delivery_failure_reason() {
		return reminder_1_sms_delivery_failure_reason;
	}
	public void setReminder_1_sms_delivery_failure_reason(String reminder_1_sms_delivery_failure_reason) {
		this.reminder_1_sms_delivery_failure_reason = reminder_1_sms_delivery_failure_reason;
	}
	public String getReminder_1_email_delivery_status() {
		return reminder_1_email_delivery_status;
	}
	public void setReminder_1_email_delivery_status(String reminder_1_email_delivery_status) {
		this.reminder_1_email_delivery_status = reminder_1_email_delivery_status;
	}
	public String getReminder_1_email_delivery_failure_reason() {
		return reminder_1_email_delivery_failure_reason;
	}
	public void setReminder_1_email_delivery_failure_reason(String reminder_1_email_delivery_failure_reason) {
		this.reminder_1_email_delivery_failure_reason = reminder_1_email_delivery_failure_reason;
	}
	public String getReminder_2_sms_delivery_status() {
		return reminder_2_sms_delivery_status;
	}
	public void setReminder_2_sms_delivery_status(String reminder_2_sms_delivery_status) {
		this.reminder_2_sms_delivery_status = reminder_2_sms_delivery_status;
	}
	public String getReminder_2_sms_delivery_failure_reason() {
		return reminder_2_sms_delivery_failure_reason;
	}
	public void setReminder_2_sms_delivery_failure_reason(String reminder_2_sms_delivery_failure_reason) {
		this.reminder_2_sms_delivery_failure_reason = reminder_2_sms_delivery_failure_reason;
	}
	public String getReminder_2_email_delivery_status() {
		return reminder_2_email_delivery_status;
	}
	public void setReminder_2_email_delivery_status(String reminder_2_email_delivery_status) {
		this.reminder_2_email_delivery_status = reminder_2_email_delivery_status;
	}
	public String getReminder_2_email_delivery_failure_reason() {
		return reminder_2_email_delivery_failure_reason;
	}
	public void setReminder_2_email_delivery_failure_reason(String reminder_2_email_delivery_failure_reason) {
		this.reminder_2_email_delivery_failure_reason = reminder_2_email_delivery_failure_reason;
	}
	
	
	
	
}
