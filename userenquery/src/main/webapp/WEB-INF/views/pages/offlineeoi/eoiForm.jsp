	
		<input id="pageContext" type="hidden" value="${pageContext.request.contextPath}"/>
		<input type="hidden" class="enquiryId" name="enquiryId">
		<input type="hidden" class="enquiryReportId" name="enquiryReport.enquiryReportId">

		<input type="hidden" class="projectSfid" value="a1l6F000008DnniQAC">


		<div class="row">
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
			
			<div class="clearfix"></div>
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
								Description
							</th>
							<th style="width:32px;"></th>
						</tr>
						
						<tr class="EOIDtlRow"> <!-- csPtDataRowEoi -->
							<td>
								<input class="csPtEnqSfidEoi" style="display:none;" />
								<select class="full form-control input-sm towerListEOI requiredField"  onchange="getTypologyEOI(this); getUnitEOI(this); getfbandEOI(this);">
									<option value="">Select Tower</option>
								</select>
							</td>
							<td>
								<select class="full form-control input-sm typologyListEOI">
									<option value="">Select Tyology</option>
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
								<input type="file" class="numericField full form-control input-sm receiptAttachEoi" accept="application/pdf,image/*"/>
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
				<div class="clearfix"></div>
			</div>
			<!-- END New EOI Payment Dtl -->
			
		</div>

		<div class="clearfix"></div>
		<div class="btnWrp" style="z-index: 10;">
			<div class="btnCol">
				<a class="btn blue_btn  mrgR15 eoi_submitted"	 id="eoisaveclose" onclick="savePaymentInfo(event,this);"> 
					<span>Save EOI & Generate KYC Link</span>
				</a>
			</div>
			<div class="clearfix"></div>
		</div>