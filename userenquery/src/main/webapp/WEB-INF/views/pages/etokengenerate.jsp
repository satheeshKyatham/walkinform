
<div class="row">
	
		
			<div class="group col-md-3 col-sm-6 col-xs-12">
				<div class="input-group" id="contactDivENew">
					<input type="hidden" id="hiddenEMobileNo" value="${mobileNo}">
					<input type="text" value="${countryCode}"
						class="autocomplete-off form-control mobile requiredField contactNoEDiv"
						id="enMobileNoEtoken" name="enMobileNoEtoken" tabindex="-1"><!-- maxlength="10" -->
					 <span class="highlight"></span><span class="bar"></span> <label>Contact
						no.<strong class="mndt">*</strong>
					</label>
				</div>
			</div>	
			<div class="form-group col-md-2" id="generateEToken_btn_Div">
				<div class="group" style="min-height: auto; margin-bottom: 0;">
					<div class="input-group" id="otpInputDiv">
						<button class="btn blue_btn square_btn generateEToken_btn" onclick="searchEToken()"><!-- getGeneratedEtoken() -->
							<span>Search</span><!-- /Generate E Token -->
						</button>
					</div>
					
				</div>
			</div>
		
			<div class="clearfix"></div>
				
			<div  id="unitDtls" class="col-md-9">
				<h5 class="">Enquiry Details</h5>
				<div class="">
					<table class="table table-bordered bgWhite">
						<tbody>
							<tr>
								<th class="subHead towerNameCS">Enquiry No.</th>
								<td><span class="towerTval"></span><span class="wingVal"></span></td>
								<th class="subHead carpetSqmlabel">Customer Name</th>
								<td class="carpetSqm"></td>
							</tr>
							<tr>
								<th class="subHead">Customer Mobile No</th>
								<td>
									<span class="unitTval"></span>
								</td>
								<th class="subHead balTerSqmLabel">Enquiry Type</th>
								<td class="balTerSqm"></td>
							</tr>
							<tr>
								<th class="subHead floorNameCS">Walk-in Source</th>
								<td class="floorTval"></td>
								<th class="subHead">CP Name</th>
								<td class="totalSqm"></td>
							</tr>
							<tr class="hideForPlot">
								<th class="subHead typologyNameCS">Date of EOI</th>
								<td   class="typologyTval"></td>
								<!-- <th class="subHead noOfCarParkLabel">No. of Car Park</th>
								<td  class="noOfCarPark ifCarParkZero"></td> -->
							</tr>
						</tbody>
					</table>
				</div>
				<div class="clearfix"></div>
			</div>
				
		
	
    <div class="clearfix"></div>
	
	
	

	<div class="form-group col-md-2" id="generateEToken_btn_Div">
		<div class="group" style="min-height: auto; margin-bottom: 0;">
			<div class="input-group" id="otpInputDiv">
				<button class="btn blue_btn square_btn generateEToken_btn" onclick="getGeneratedEtoken()">
					<span>Generate E Token</span><!-- /Generate E Token -->
				</button>
			</div>
			
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="col-md-4"  id="etokengenratedCol">
		<div style="border: 1px solid #ccc; box-shadow: 4px 3px 4px #999; padding: 8px; background-color: #fff; border-radius: 10px;">
			
			<ul class="basicDetailsCS" >
				<li>
					<label>Token No.</label> <span>: </span><span class="etokenNo"></span>
				</li>
				<li>
					<label>Name<span>: </span><span class="ename"></span>
				</li>
				<li>
					<label>Mobile No.</label> <span>: </span><span class="emobile"></span>
				</li>
				<li>
					<label>Enquiry No.</label> <span>: </span><span class="eenquiry"></span>
				</li>
				<li>
					<label>Priority No.</label> <span>: </span><span class="epriority"></span>
				</li>
			</ul>
			
			<div class="clearfix"></div>
		</div>	
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
</div>
