<div class="row">
<div>
	<div class="col-md-12 radioBtnWrp mrgT0">
		<div class="titleF" id="enquirySourceTextDiv">Is accompanied/ referred by a channel partner? <strong class="mndt">*</strong></div>
		<input type="hidden" name="isReferredByChannelPartner" id="isReferredByChannelPartnerInput" >
		<input type="hidden" id="hiddenEnquiryType" value="${enquiryType}">
		<input type="hidden" id="hiddenEnquiryTypeOTP">
		<div class="radioBtnCol" data-toggle="buttons">
		  <label class="btn btn-primary active hideChannelPartnerType enquiryFields" labelName="isReferredByChannelPartner" value="CP">
			<input type="radio" id="isReferredByChannelPartnerRadioCP" propValue="Partner" class="hideChannelPartnerType" name="isReferredByChannelPartnerFlag" checked value="CP"> Yes
		  </label>
		  <label class="btn btn-primary hideDirectType enquiryFields" labelName="isReferredByChannelPartner" value="D" >
			<input type="radio" id="isReferredByChannelPartnerRadioD" propValue="Direct" class="hideDirectType" name="isReferredByChannelPartnerFlag" value="D"> No
		  </label>
		  <label class="btn btn-primary hideChannelPartnerType enquiryFields" labelName="isReferredByChannelPartner"  value="O"">
			<input type="radio" id="isReferredByChannelPartnerRadioO" propValue="Partner" class="hideChannelPartnerType" name="isReferredByChannelPartnerFlag" value="O"> Other
		  </label>
		</div>
	</div>
	<div class="clearfix"></div>
	<!-- <div id="isReferredByChannelPartnerCP" class="sourceCol animated">
		<div class="col-md-6 col-xs-12">
			<div class="group posRelative">
				<div class="commonLoad" id="channelPartnerLoader" style="display:none;"></div>
				<input type="text" id="channelPartnerNameSearch" onkeyup="getChannelPartners(event,this);" data-id="channelPartnerName" class= "requiredHidden enquiryFields autocomplete-off isReferredByChannelPartnerCP requiredField"  required="required"  />
				<input type="hidden" id="channelPartnerSfid" name="channelPartner.sfid" class="isReferredByChannelPartnerCP"/>
				<input type="hidden" id="channelPartnerId" name="channelPartner.channelPartnerId" class="isReferredByChannelPartnerCP"/>
				
				<input type="hidden"  id="channelPartnerName" name="channelPartner.name" class="isReferredByChannelPartnerCP"/>
				
				<span class="highlight"></span><span class="bar"></span>
				<label>Enter channel partner name <strong class="mndt">*</strong></label>
			</div>
		</div>


		<div class="col-md-6 col-xs-12" style="display: none">
			<div class="group">
				<input type="hidden" id="brokerContactId" name="brokerContact.contactId" >
				<select class="isReferredByChannelPartnerCP enquiryFields" id="brokerContact"  name="brokerContact.sfid" onchange="brokerContactChanged(event,this);">
					<option value="" ></option>
					<option value="1">Option 1</option>
					<option value="2">Option 2</option>
					<option value="3">Option 3</option>
				</select>
				<span class="highlight"></span><span class="bar"></span>
				<label class="select-label">Select broker contact <strong class="mndt">*</strong></label>
			</div>
		</div>
		<div class="clearfix"></div>
	</div> -->
	
	<div id="isReferredByChannelPartnerCP" class="sourceCol animated">
		<div class="col-md-6 col-xs-12">
			<div class="group posRelative">
				<div class="commonLoad" id="channelPartnerLoader" style="display:none;"></div>
				<input type="text" id="channelPartnerNameSearch" onkeyup="getChannelPartners(event,this);" data-id="channelPartnerName" class= "requiredHidden enquiryFields autocomplete-off isReferredByChannelPartnerCP requiredField"  required="required"  /><!--  -->
				<input type="hidden" id="channelPartnerSfid" name="channelPartner.sfid" class="isReferredByChannelPartnerCP"/>
				<input type="hidden" id="channelPartnerId" name="channelPartner.channelPartnerId" class="isReferredByChannelPartnerCP"/>
				
				<input type="hidden"  id="channelPartnerName" name="channelPartner.name" class="isReferredByChannelPartnerCP"/>
				
				<span class="highlight"></span><span class="bar"></span>
				<label>Enter channel partner name <strong class="mndt">*</strong></label>
			</div>
		</div>


		<div class="col-md-6 col-xs-12" style="display: none">
			<div class="group">
				<input type="hidden" id="brokerContactId" name="brokerContact.contactId" >
				<select class="isReferredByChannelPartnerCP enquiryFields" id="brokerContact"  name="brokerContact.sfid" onchange="brokerContactChanged(event,this);">
				</select>
				<span class="highlight"></span><span class="bar"></span>
				<label class="select-label">Select broker contact </label>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
					
	<div id="isReferredByChannelPartnerD" class="sourceCol animated" style="display: none;">
		<div class="col-md-6 col-xs-12">
			<div class="group">
				<select class="isReferredByChannelPartnerD requiredField enquiryFields" disabled="disabled" id="walkInSource" name="walkInSource" onchange="onSelectWalkinSrcReferral(event,this);">
				    <option value=""></option>
					<c:forEach var="communcationMediumList" items="${communcationMediumList}">

                                   <option value="${communcationMediumList.code}">${communcationMediumList.name}</option>

                                 </c:forEach>
				</select>
				<span class="highlight"></span><span class="bar"></span>
				<label class="select-label">How did you come to know about this project? <strong class="mndt">*</strong></label>
			</div>
		</div>
		<!--  Referred by added on Enquiry page, on select of walk-in source as referral -  
						* Change By Satheesh Kyatham- 25-12-2019
						* Request From - Prakash Idnani -->
						<!-- Start -->
		<div class="referred_by_name ccol-md-3 col-xs-6">
			<div class="group">
				<input type="text" class="autocomplete-off xss_input_txt_validation_optional" id="referredbyId" name="enquiryReport.referredby"/>
				<span class="highlight"></span><span class="bar"></span>
				<label>Referred by</label>
			</div>
		</div>
		<!-- END -->
		<!-- Instracted BY Ganesh -- comment by A -->
		<!-- <div class="col-md-6 col-xs-12">
			<div class="group">
				<input class="autocomplete-off isReferredByChannelPartnerD requiredField enquiryFields" type="text" disabled="disabled" required="required" id="walkInSourceDetail" name="walkInSourceDetail"/>
				<span class="highlight"></span><span class="bar"></span>
				<label>Details of Newspaper, Website, Hoarding etc. <strong class="mndt">*</strong></label>
			</div>
		</div>	 -->
		<div class="clearfix"></div>
	</div>
					
	<div id="isReferredByChannelPartnerO" class="sourceCol animated" style="display: none;">
		<div class="col-md-6 col-xs-12">
			<div class="group">
				<input class="autocomplete-off isReferredByChannelPartnerO requiredField enquiryFields xss_input_txt_validation_optional" type="text" required="required" disabled="disabled" name="cpComment" id="otherChannelPartnerName"/>
				<span class="highlight"></span><span class="bar"></span>
				<label>Enter channel partner name <strong class="mndt">*</strong></label>
			</div>
		</div>	
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
</div>
		<div class="clearfix"></div>
		<!-- <div class="form-group col-md-2" id="nextInputColDiv" style="display: none;">
			<div class="group" style="min-height: auto; margin-bottom: 0;">
				<div class="input-group" id="nextInputCol_Div">
					<button class="btn blue_btn square_btn nextbuttnInputCol_Div" onclick="verifyNext();">
						<span>Next</span>
					</button>
				</div>
				
			</div>
		</div> -->
</div>

<!-- Added By Satheesh - Adding CP name before generate OTP END-->