<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row ">
	<!-- New EOI Payment Dtl -->
	<div class="col-md-12" style="margin-bottom:50px;">
		<h4 class="mrgT0">
			Payment Details
		</h4>
		<div class="form-group col-md-3" style="padding-left:0;">
			<label>Tower</label>
			<select id="towerMstPayment" class="form-control"> </select>
		 </div>
		<table class="table table-bordered  bgWhite mrgB8" id="csPtColOP">
			<tbody>
				<tr class="subHead">
					<th style="width:150px;">
						Transaction</br>Date
					</th>
					<th style="width:200px;">
						Transaction Amount
					</th>
					<th>
						Description
					</th>
					<th style="width: 140px;">
						Transaction ID
					</th>
					<th style="width: 140px;">
						Transaction Status
					</th>
					<th style="width: 165px;"></th>
				</tr>
				
				<tr class="csPtDataRowOP">
					<td>
						<input type="date" class="form-control input-sm csPtTransactionDateOP requiredField" placeholder="Transaction Date" readonly/>
					</td>
					<td>
						<input maxlength="10" class="numericWithoutDecimal numericField full form-control input-sm csPtTransactionAmountOP requiredField" onkeyup="csPtcalculateGrandTotalOP()" name="amount" placeholder="Transaction Amount"/>
					</td> 
					<td>
						<textarea class="full form-control input-sm csPtDescriptionOP" placeholder="Description"></textarea>
					</td>
					<td> </td>
					<td> </td>
					<td> </td>
				</tr>
				
			</tbody>
		</table>
		<p class="addMoreBtn" onclick="addMorePtBtnOP()">
			<i class="fa fa-plus-circle"></i> Add More
		</p>
		
		<div>
			<span>Total transaction amount: </span><span id="csPtGrandtotalOP"></span>
		</div>
		
		<div style="margin-top: 10px; border-top: 1px dashed #959595; padding-top: 10px;">
			<div class="col-md-2">	
				<button onclick="copyToClipboard('PaymentLinkForSales')" class="btn blue_btn" style="float: left; margin-right: 20px; margin-top: 6px; font-size: 12px; padding: 5px;">
					Copy Payment Link
				</button>
			</div>	
			<div class="col-md-10" id="PaymentLinkForSales" style="word-break: break-all;"></div>
		</div>
		
		<div class="clearfix"></div>
	</div>
	<!-- END New EOI Payment Dtl -->
	<div class="clearfix"></div>
</div>

<div class="clearfix"></div>
<div class="btnWrp" style="z-index: 10;">
	<div class="btnCol">
		<a class="btn btn-primary blue_btn" onclick="csPymtDataOP()"> <span>SAVE & GENERATE PAYMENT LINK</span> </a> 
	</div>
	<div class="clearfix"></div>
</div> 