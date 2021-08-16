package com.godrej.properties.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.dto.LWWebhookDto;

@CrossOrigin(origins = "*")
@RestController
public class LitmusWorldWebhookService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@PostMapping(value = "/lwWebhook", produces = "application/json")
	public String insertLWWebhook(@RequestBody LWWebhookDto lwWebhookDto) {
		log.info("Feed_id:{}",lwWebhookDto.getFeed_id());
		log.info("Action_by_name:{}",lwWebhookDto.getAction_by_name());
		log.info("Brand_id:{}",lwWebhookDto.getBrand_id());
		log.info("Comment:{}",lwWebhookDto.getComment());
		log.info("Current_issue_category:{}",lwWebhookDto.getCurrent_issue_category());
		log.info("Current_issue_status:{}",lwWebhookDto.getCurrent_issue_status());
		log.info("Invitation_email_delivery_failure_reason:{}",lwWebhookDto.getInvitation_email_delivery_failure_reason());
		log.info("Invitation_email_delivery_status:{}",lwWebhookDto.getInvitation_email_delivery_status());
		log.info("Invitation_sms_delivery_failure_reason:{}",lwWebhookDto.getInvitation_sms_delivery_failure_reason());
		log.info("Invitation_sms_delivery_status:{}",lwWebhookDto.getInvitation_sms_delivery_status());
		log.info("Issue_age_in_hours:{}",lwWebhookDto.getIssue_age_in_hours());
		log.info("Issue_age_in_minutes:{}",lwWebhookDto.getIssue_age_in_minutes());
		log.info("Last_issue_status:{}",lwWebhookDto.getLast_issue_status());
		log.info("Note:{}",lwWebhookDto.getNote());
		log.info("Primary_rating:{}",lwWebhookDto.getPrimary_rating());
		log.info("Project_name:{}",lwWebhookDto.getProject_name());
		log.info("Reminder_1_email_delivery_failure_reason:{}",lwWebhookDto.getReminder_1_email_delivery_failure_reason());
		log.info("Reminder_1_email_delivery_status():{}",lwWebhookDto.getReminder_1_email_delivery_status());
		log.info("Responded_date:{}",lwWebhookDto.getResponded_date());
		log.info("Ratings:{}",lwWebhookDto.getRatings());
		
		//Change Rating resp to json array
		return "{\"resp\":\"success\"}";
	}
}
