<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>












<!-- <div class="tab-pane active" id="tab1"> -->
	<form:form id="bankPaymentForm" modelAttribute="EnquiryRequest"
		enctype="multipart/form-data">



		
		
		



		 <input id="pageContext" type="hidden" value="${pageContext.request.contextPath}"/>
		<input type="hidden" class="enquiryId" name="enquiryId">
		<!-- <input type="hidden" class="unitCustom" name="enquiryReport.unit"> -->
		<input type="hidden" class="enquiryReportId"
			name="enquiryReport.enquiryReportId">

		<div class="row ">
			<!-- bounceInLeft animated -->
			<div class="col-md-12 commonErrorCol commonErrorDiv"
				style="display: none">
				<div class="alert alert-danger">
					Kindly fill the <strong>required</strong> fields.
				</div>
			</div>
			<div class="col-md-12 commonErrorCol commonMessageDiv"
				style="display: none">
				<div class="alert alert-danger">
					<strong id="commonBoldMessage"></strong>
				</div>
			</div>
			
			
			
			
			
			
			
			
			
			
			
			
			<!-- commented by a - DO NOT REMOVE -->
			<!-- <div class="col-md-2 col-xs-6">
				<div class="group">
					<select id="tower" class="eoi_submitted requiredField" name="EOI_Tower_Series__c">
						<option value=""></option>

					</select>
					<input type="hidden"  class="towername" name="enquiryReport.tower"> 
					<span class="highlight"></span><span class="bar"></span> <label
						class="select-label">Tower<strong class="mndt"></strong></label>
				</div>
			</div> -->
			
			<!-- <div class="col-md-2 col-xs-6" >
				<div class="group">
					<select id="fband" class="eoi_submitted" name ="EOI_Preferred_Floor_Band__c">
					</select>
					<input type="hidden"  class="bandname" >
					 <span class="highlight"></span><span class="bar"></span> <label
						class="select-label">Floor Band 123</label>
				</div>
			</div> -->
			<!-- <div class="col-md-2 col-xs-6" >
				<div class="group">
					<select id="unit" class="eoi_submitted" name ="Preferred_Unit__c">
					</select>
					<input type="hidden"  class="unitname" name="enquiryReport.unit">
					 <span class="highlight"></span><span class="bar">
					
					</span> <label
						class="select-label">Unit</label>
				</div>
			</div> -->
			<!-- <div class="col-md-2 col-xs-6" style="display: none">
				<div class="group">
					<select id="floor" class="eoi_submitted">
					</select> <span class="highlight"></span><span class="bar"></span> <label
						class="select-label">Floor <strong class="mndt"></strong></label>
				</div>
			</div> -->
			<!-- <div class="col-md-12 col-sm-12 col-xs-12 radioBtnWrp  padMLR15"
				radioName="desiredUnitType" id="desiredUnitType">
				<div class="titleF">
					Typology Requirement <strong class="mndt">*</strong>
				</div>
				<div class="radioBtnCol requiredRadio" data-toggle="buttons" id="radioBtnCol">

				</div>
				<label class="select-label fnt14 padMLR15">Typology Requirement<strong class="mndt">*</strong></label>
				<div class="claerfix"></div>
			</div> -->
			
			
			
			
			
			
			<div class="clearfix"></div>
			<div class="col-md-12" style="margin-bottom:20px;">
				<h4>Select EOI Token Type</h4>
				<select class="form-control tokenTypeEOI requiredField" style="width: 200px"  onchange="tokenTypeChangeEOI(this)">
					<option value="">Select Token Type</option>
				</select>
			</div>
			<!-- New EOI Dtl -->
			<div class="col-md-12" style="margin-bottom:20px;">
				<h4 class="mrgT0">
					Preference Details
				</h4>
				
				<table class="table table-bordered  bgWhite mrgB8" id="EOIMultipleTable">
					<tbody>
						<tr class="subHead">
							<th>
								Tower
							</th>
							<th>
								Typology 
							</th>
							<th>
								Unit
							</th>
							<th>
								Floor Band
							</th>
							<th>
								Car Park Type
							</th>
							<th>
								Description
							</th>
							<th style="width:32px;"></th>
						</tr>
						
						<tr class="EOIDtlRow"> <!-- csPtDataRowEoi -->
							<td>
								<input class="csPtEnqSfidEoi" style="display:none;" />
								<select class="full form-control input-sm towerListEOI requiredField"  onchange="getTypologyEOI(this); getUnitEOI(this); getfbandEOI(this); getCarparkEOIMst(this);">
									<option value="">Select Tower</option>
								</select>
							</td>
							<td>
								<select class="full form-control input-sm typologyListEOI requiredField" onchange="getUnitEOI(this)">
									<option value="">Select Typology</option>
								</select>
							</td>
							<td>
								<select class="full form-control input-sm unitListEOI" onchange="unitChangeConditionEOI(this)">
									<option value="0">Select Unit</option>
								</select>
							</td>
							<td>
								<select class="full form-control input-sm floorListEOI">
									<option value="">Select Floor Band</option>
								</select>
							</td>
							<td>
								<select class="full form-control input-sm carparkListEOI">
									<option data-carparkname="" value="-1">Select Car Park</option>
								</select>
							</td>
							<td style="width:150px;">
								<textarea class="full form-control input-sm descriptionEOI" placeholder="Description"></textarea>
							</td>
							<td> </td>
						</tr>
					</tbody>
				</table>
				
				<p class="addMoreBtn" onclick="addMoreEoiRowBtn()">
					<i class="fa fa-plus-circle"></i> Add More
				</p>
			</div>
			<!-- END EOI Dtl -->
			
			<!-- <button onclick="insertEOIPreference()">Insert</button> -->
			
			
			<!-- New EOI Payment Dtl -->
			<div class="col-md-12" style="margin-bottom:50px;">
				<h4 class="mrgT0">
					Payment Details
				</h4>
				
				<table class="table table-bordered  bgWhite mrgB8" id="csPtColEoi">
					<tbody>
						<tr class="subHead">
							<th>Status</th>
							<th style="width:130px;">
								Payment Type
							</th>
							<th style="width:100px;">
								Bank Name
							</th>
							<th style="width:100px;">
								Branch
							</th>
							<th>
								Transaction ID
							</th>
							<th style="width:100px;">
								Transaction</br>Date
							</th>
							<th>
								Transaction Amount
							</th>
							
							<th style="width:250px;">
								PAN Attachment 
							</th>
							<th style="width:250px;">
								Receipt/</br>Cheque Attachment
							</th>
							
							<th style="width:200px;">
								Description
							</th>
							<th style="width:32px;"></th>
						</tr>
						
						<tr class="csPtDataRowEoi">
							<td></td>
							<td>
								<input class="csPtEnqSfidEoi" style="display:none;" />
								<select onchange="csPtDdEoi(this)" class="full form-control input-sm csPtDropDownEoi requiredField">
									<option value="">Select</option>
									<option value="Cheque">Cheque</option>
									<option value="NEFT">NEFT/Credit</option>
									<option value="Swipe">Swipe</option>
									<option value="Wire Transfer">Wire Transfer (PayZap, Google Pay)</option>
									<!--<option value="Online">Online</option>-->
								</select>
							</td>
							<td>
								<input class="full form-control input-sm csPtBankNameEoi requiredField" placeholder="Bank Name"/>
							</td>
							<td>
								<input class="full form-control input-sm csPtBranchEoi requiredField" placeholder="Branch Name"/>
							</td>
							<td>
								<input class="full form-control input-sm csPtTransactionIdEoi requiredField" placeholder="Transaction ID"/>
							</td>
							<td>
								<input type="date" class="full form-control input-sm csPtTransactionDateEoi requiredField" placeholder="Transaction Date"/>
							</td>
							<td>
								<input maxlength="10" class="numericWithoutDecimal numericField full form-control input-sm csPtTransactionAmountEoi requiredField" onkeyup="csPtcalculateGrandTotalEoi()" name="amount" placeholder="Transaction Amount"/>
							</td>
							
							<td>
								<input type="file" class="full form-control input-sm panAttachEoi" accept="application/pdf,image/*"/>
							</td>
							<td>
								<input type="file" class="numericField full form-control input-sm receiptAttachEoi requiredField" accept="application/pdf,image/*"/>
							</td>
							
							<td>
								<textarea class="full form-control input-sm csPtDescriptionEoi" placeholder="Description"></textarea>
							</td>
							<td> </td>
						</tr>
						
					</tbody>
				</table>
				<p class="addMoreBtn" onclick="addMorePtBtnEoi()">
					<i class="fa fa-plus-circle"></i> Add More
				</p>
				
				<div style="display:none;">
					<span>Total transaction amount: </span><span id="csPtGrandtotalEoi"></span>
				</div>
				
				 <!-- <button type='button' onclick="csPymtDataEoi ('99999')">Test Btn</button> -->
				
				<div class="clearfix"></div>
			</div>
			<!-- END New EOI Payment Dtl -->
		
			
			
			
			
			
			<div class="col-md-2 col-xs-6" style="display:none;">
				<div class="group">
					<select id="paymentopt" class="eoi_submitted" name="transactionType">
						<option value=""></option>
						<option value="Bank">Cheque</option>
						<option value="NEFT">NEFT/Credit</option>
						   <option value="Online">Online</option>
					</select> <span class="highlight"></span><span class="bar"></span> <label
						class="select-label">Payment type <strong class="mndt"></strong></label>
				</div>
			</div>
			<div class="clearfix"></div>
			<div id="bankdetails" style="display: none">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="group">
						<input type="text" id="eoiBankName" name="eoiBankName"
							class="autocomplete-off bankdetails  eoi_submitted"
							required="required" /> <span class="highlight"></span><span
							class="bar"></span> <label>Bank name <strong class="mndt">*</strong></label>
						<!-- <small class="errorMsg">Error message</small> -->
					</div>
				</div>

				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="group">
						<input type="text" id="branch" name="branch"
							class="autocomplete-off bankdetails  eoi_submitted"
							required="required" /> <span class="highlight"></span><span
							class="bar"></span> <label>Branch<strong class="mndt">*</strong></label>
					</div>
				</div>


				<div class="col-md-2 col-sm-6 col-xs-12">
					<div class="group">
						<input type="text" id="chequeno" name="micRChequeNoNEFTRTGS"
							class="autocomplete-off  bankdetails  eoi_submitted" /> <span
							class="highlight"></span><span class="bar"></span> <label>Cheque
							No<strong class="mndt">*</strong>
						</label>
					</div>
				</div>

				<!-- <div class="col-md-3 col-sm-6 col-xs-12">
						 <div class="input-group date" id='datetimepicker1'>
							<input type="text" id="chequedate" name="" class="form-control autocomplete-off  bankdetails " />
							<span class="highlight"></span><span class="bar"></span>
							<label>Cheque Date<strong class="mndt">*</strong></label>
						</div> 
						
					</div> -->

				<!-- Added By A -->
				<div class='col-md-2'>
					<div class="form-group" id="divchequedate">
						<label>Cheque Date<strong class="mndt">*</strong></label>
						<div class='input-group date datetimepicker'>
							<input type='text' name="transactionDate"
								class="form-control autocomplete-off   bankdetails eoi_submitted"
								id="chequedate" />
								 <span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="form-group" id="divchequevalue" style="display: none">
						<label>Cheque Date<strong class="mndt">*</strong></label>
						<div class='input-group'>
							<input type='text' 
								class="form-control autocomplete-off bankdetails eoi_submitted"
								id="chequedatevalue" />
								 <span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<!-- END Addd By A -->


				<div class="col-md-2 col-sm-6 col-xs-12">
					<div class="group">
						<input type="text" id="amount" name="transactionAmount"
							class="numericField autocomplete-off   bankdetails  eoi_submitted" />
						<span class="highlight"></span><span class="bar"></span> <label>Amount<strong
							class="mndt">*</strong></label>
					</div>
				</div>

				<!-- <div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<input type="text" id="panno" class="autocomplete-off requiredField  " />
							<span class="highlight"></span><span class="bar"></span>
							<label>Pan No<strong class="mndt">*</strong></label>
						</div>
					</div> -->
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="group">
						<input type="file" id="chequeFile" name="enquiryReport.chequeFile"
							accept="image/*" class="autocomplete-off  bankdetails eoi_submitted" /> <span
							class="highlight"></span><span class="bar"></span> <label>Cheque
							Image<strong class="mndt">*</strong>
						</label>
					</div>
				</div>

				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="group">
						<input type="file" id="panFile" name="enquiryReport.panFile"
							accept="image/*" class="autocomplete-off  bankdetails eoi_submitted" /> <span
							class="highlight"></span><span class="bar"></span> <label>Upload
							Pan Image<strong class="mndt">*</strong>
						</label>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="txtCenter" style="display: none">
					<a class="btn btn-primary btnNext"
						onclick="savePaymentInfo(event,this);"> <span>Submit</span>
					</a>
					<div class="clearfix"></div>
				</div>
			</div>

			<div class="clearfix"></div>
			<div id="neftdetails" style="display: none">
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="group">
						<input type="text" id="nefttransactionID" name="nefttransactionID"
							class="autocomplete-off neftdetails  eoi_submitted"
							 /> <span class="highlight"></span><span
							class="bar"></span> <label>Transaction ID<strong
							class="mndt">*</strong></label>
					</div>
				</div>
				<!-- <div class="col-md-3 col-sm-6 col-xs-6">
					<div class="group">
						<input type="text" id="transId" name="transactionID"
							class="autocomplete-off neftdetails requiredField eoi_submitted"
							required="required" /> <span class="highlight"></span><span
							class="bar"></span> <label>Transaction ID<strong
							class="mndt">*</strong></label>
					</div>
				</div> -->
				<!-- <div class="col-md-3 col-sm-6 col-xs-6">
						<div class="group">
							<input id="transDate" name="" class="autocomplete-off neftdetails " required="required" />
							<span class="highlight"></span><span class="bar"></span>
							<label>Transaction Date<strong class="mndt">*</strong></label>
						</div>
					</div> -->
				<div class='col-md-2'>
					<div class="form-group" id="divtransDate">
						<label>Transaction Date<strong class="mndt">*</strong></label>
						<div class='input-group date datetimepicker'>
							<input type='text' name="transactionDate"
								class="form-control autocomplete-off   neftdetails eoi_submitted"
								id="transDate" /> <span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="form-group" id="divtransDateValue" style="display: none">
						<label>Transaction Date<strong class="mndt">*</strong></label>
						<div class='input-group'>
							<input type='text' 
								class="form-control autocomplete-off  neftdetails eoi_submitted"
								id="transDatevalue" /> <span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					
					
					
				</div>
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="group">
						<input type="text" id="transamt" name="transactionAmount"
							class="numericField autocomplete-off neftdetails  eoi_submitted"
							 /> <span class="highlight"></span><span
							class="bar"></span> <label>Transaction Amount<strong
							class="mndt">*</strong></label>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">

					<div class="group">
						<input type="file" id="swapFile" name="enquiryReport.swapFile"
							accept="image/*" class="autocomplete-off  neftdetails eoi_submitted" /> <span
							class="highlight"></span><span class="bar"></span> <label>NEFT/Swap
							Image<strong class="mndt">*</strong>
						</label>
					</div>
				</div>
				<div class="clearfix"></div>
				
				
				<div class="txtCenter" style="display: none">
					<a class="btn btn-primary btnNext"
						onclick="savePaymentInfo(event,this);"> <span>Submit</span>
					</a>
					<div class="clearfix"></div>
				</div>
				
			</div>
			
			
				
				
				<!-- <div  >
						<div class="col-md-12 col-xs-12">
							<div class="group">
								<input type="text" class="eoi_submitted" id="bankotherinfo" name="EOI_Remarks__c"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Other informations</label>
							</div>
						</div>	
						<div class="clearfix"></div>
					</div> --> 
			
			<div class="clearfix"></div>



			<div id="onlinedetails" style="display: none">

				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="group">
						<input type="text" id="amountinLink"
							class="autocomplete-off  onlinedetails eoi_submitted"
							 /> <span class="highlight"></span><span
							class="bar"></span> <label>Transaction Amount<strong
							class="mndt">*</strong></label>
					</div>
				</div>

				<div class="col-md-3 col-sm-6 col-xs-12">

					<div class="group">
						<a class="btn btn-primary btnNext eoi_submitted"> <span>Pay Now</span>
						</a>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">

					<div class="group">
						<a class="btn btn-primary btnNext eoi_submitted"
							onclick="generatePaymentLink();"> <span>Generate Link	For Payment</span>
						</a>
					</div>
				</div>

				<div class="col-md-3 col-sm-6 col-xs-12">

					<div class="group">
						<a class="btn btn-primary btnNext eoi_submitted" onclick="checkStatus();"> <span>Check
								Payment Status</span>
						</a>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>

		<div class="clearfix"></div>
		<div class="btnWrp" style="z-index: 10;">
			<div class="btnCol">
				<a class="btn btn-primary btnPrevious"> <span>Previous</span>
				
				</a> 
				
				<!-- <a class="btn btn-primary blue_btn  mrgR15"	 id="eoiclose" style="display: none;"
				onclick="savePaymentInfo(event,this);"> <span> Close</span></a> -->
				<a class="btn blue_btn  mrgR15 eoi_submitted"	 id="eoisaveclose" onclick="savePaymentInfo(event,this);"> 
				<%
				
				if(request.getParameterMap().containsKey("roleid"))
				{
					String roleIdString = (String) request.getParameter("roleid");
					int roleid = 0;
					if(roleIdString !=null){
						roleid = Integer.parseInt(roleIdString);
					}
					if(roleid==17)
					{
						%>
						<span>Save EOI, Generate KYC Link & Go To Offline EOI</span>
				<%}
					else{%>
						<span>Save EOI & Generate KYC Link</span>
				<%}}
				else{%>
						<span>Save EOI & Generate KYC Link</span>
				<%}%>
				</a>
				
				<!--17-12-2019 Requested from - Prakash -- Hide Generate KYC button from UI and backed functionality added on Save EOI button Click And Exit & Close buttons also hide -->
				<!-- <a class="btn blue_btn  mrgR15" style="padding: 7px 10px;" data-toggle="modal" data-target="#myModal"> <span>Exit & Close</span></a> -->
				<!-- <a id="btnkyclink" class="btn blue_btn btn-primary mrgR15"
					onclick="generateKYCLink(event,this,'Y');"> <span>Generate
						KYC Link</span>
				</a> -->
				
				
				
				
				
				
				
			</div>
			<div class="clearfix"></div>
		</div>
</form:form>
<!-- </div> -->

<!-- </div> -->


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      
      <div class="modal-body">
       		<h2 style=" text-align: center; color: #e98300;  ">
       			<span style=" border: 2px solid; border-radius: 100px;  width: 70px; height: 70px; display: inline-block; font-size: 50px;">!</span>
       		</h2>
       		<h3 style="text-align:center;"> Data will not be saved</h3>
       		<h4 style="text-align:center;"> Are you sure you want to exit without saving?</h4>
      </div>
      <div class="modal-footer" style="text-align:center;">
        <button type="button" class="btn blue_btn" onclick="exitAndCloseEOI()">Yes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
      </div>
    </div>
  </div>
</div>


<!-- <button onclick="getEOIPreferencPrint();">Test Print</button> -->

<!-- EOI Print Form -->

<div id="printEOIForm" class="col-md-8" style="border: 1px dotted #ccc; margin: 0 auto; float: none; margin-top: 40px; padding-top: 15px;">
		<!-- <div style="padding-bottom:10px;"><b style="font-size:18px; text-align:center;">EOI Form: <span id="projectNameEOIPrint"></span></b></div> -->
		
		<table class="table table-bordered" style="margin-bottom:20px; border-color: #ffffff; padding:0px;">
		    <tbody style="border-color: #ffffff; padding:0px;">
		    	<tr style="border-color: #ffffff; padding:0px;">
		    		<td style="border-color: #ffffff; padding:0px;"><b style="font-size:18px; text-align:left;">EOI Form: <span id="projectNameEOIPrint"></span></b></td>
		    		<td class="dateOfEOIPrint" style="text-align:right; border-color: #ffffff; padding:0px;"></td>
		    	</tr>
		    </tbody>
		 </table>
		
		
		<div style="padding-bottom:10px;"><b>Applicant Details</b></div>
		<table id="" style="margin-bottom:20px;" class="table table-bordered">
		    <tbody>
				<tr>
					<td>Name of Primary Applicant</td> <td id="priAppNameEOIPrint"></td>
				</tr>
				<tr>
					<td>Email Address of Primary Applicant</td> <td id="priAppEmailEOIPrint"></td>
				</tr>
				<tr>
					<td>Mobile No. of Primary Applicant</td> <td id="priAppMobileEOIPrint"></td>
				</tr>
				<tr>
					<td>Name of Second Applicant</td> <td id="secondAppNameEOIPrint"></td>
				</tr>
				<tr>
					<td>Name of Third Applicant</td> <td id="thirdAppNameEOIPrint"></td>
				</tr>
				
				
				<tr>
					<td>PAN Card No.</td> <td id="priAppPANEOIPrint"></td>
				</tr>
				<tr>
					<td>Aadhar Card No.</td> <td id="priAppAadharEOIPrint"></td>
				</tr>
				<tr>
					<td>Residence Address</td> <td id="priAppResAddsEOIPrint"></td>
				</tr>
				
				
				<tr>
					<td>Area / Location</td> <td id="priAppAreaLocationEOIPrint"></td>
				</tr>
				<tr>
					<td>City</td> <td id="priAppCityEOIPrint"></td>
				</tr>
				<tr>
					<td>PIN Code.</td> <td id="priAppPinCodeEOIPrint"></td>
				</tr>
		    </tbody>
		  </table>
		
		<table style="margin-bottom:20px;" class="table table-bordered">
			<tr>
				<td colspan='2'><b>EOI Token Type:</b> <span id="tokenTypeEOIPrint"></span></td>
			</tr>
		</table>
		<div  style="padding-bottom:10px;"><b>Preference(s)</b></div>
		<table id="EOIMultipleTablePrint" style="margin-bottom:20px;" class="table table-bordered">
		    <thead>
				<tr>
					<th>Typology</th>
					<th>Ticket Size</th>
					<th>Floor Band</th>
					<th>Tower</th>
					<th>Unit No.</th>
					<th>Car Park Type</th>
					<th>Description</th>
		      	</tr>
		    </thead>
		    <tbody>
		      
		    </tbody>
		 </table>
	
	
		<div style="padding-bottom:10px;"><b>Payment Details</b></div>
		<table id="EOIMultiplePaymentPrint" style="margin-bottom:20px;" class="table table-bordered">
			<thead>
				<tr>
					<th>Name of Payer</th>
					<th>Mode of Payment</th>
					<th>Amount (in Rs.)</th>
					<th>Bank Name</th>
					<th>Branch Name</th>
					<th>Cheque No./ Txn ID</th>
					<th>Description</th>
		      	</tr>
			</thead>
		    <tbody>
		      
		    </tbody>
		</table>
		
		<table style="margin-bottom:20px;" class="table table-bordered">
			<tr>
				<td colspan='2'><b>Closing Manager Name:</b> <span id="closingMangrEOIPrint"></span></td>
			</tr>
			<tr>
				<td style="height:40px; width:60%;"><b>Signature:</b></td>
				<td><b>Date:</b> <span class="dateOfEOIPrint"></span></td>
			</tr>
		</table>
		
		
		<table id="" style="margin-bottom:20px;" class="table table-bordered">
		    <tbody>
				<tr>
					<td><b>Source:</b> <span id="enqTypeEOIPrint"></span></td>
					<td><b>Vertical:</b> <span id="verticalEOIPrint"></span></td>
				</tr>		
				<tr>	
					<!-- <th colspan="2">RERA ID (Channel Partner):</th> -->
					<td colspan="2"><b>Channel Partner :</b>  <span id="channelpartnerNameEOIPrint"></span></td>
		      	</tr>
		      	<tr>	
					<td colspan="2"><b>Sourcing Manager Name:</b>  <span id="sourcingMngrEOIPrint"></span></td>
		      	</tr>		      
		    </tbody>
		</table>
		<div style="page-break-before: always"></div>
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th>
						Terms and Conditions
					</th>
				</tr>
				<tr>
					<td id="tncDataEOI">									
					</td>
				</tr>
			</tbody>
		</table>
	
</div>
<!-- END EOI Print Form -->