<div class="row">
	<input type="text" id="recordid" class="form-control" style="display:none;">
	<div class="form-group col-md-2">
		<label>Token Type:</label>
		<select id="tokenType" class="form-control">
			<option value="All" selected="">All</option>
			<option value="W">W</option>
			<option value="E">E</option>
			<option value="G">G</option>
			<option value="MS">MS</option>
			<option value="MF">MF</option>
		</select> 
	</div>
	<div class="form-group col-md-2">
		<label>Submitted Token Type:</label>
		<input type="text" id="tokenTypeVal" class="form-control" readonly>
	</div>
	<div class="clearfix"></div>
	<div class="form-group col-md-5">
		<label>Showcase key content line 1 (Count):</label>
		<input type="text" id="keyCont1" class="form-control">
	</div>
	<div class="form-group col-md-5">
		<label>Showcase key content line 2:</label>
		<input type="text" id="keyCont2" class="form-control">
	</div>
	<div class="form-group col-md-10">
		<label>Video URL (.mp4 format only):</label>
		<input type="text" id="avUrl" class="form-control">
	</div>
	<div class="clearfix"></div>
	<div class="form-group col-md-10">
		<label>OR</label>
	</div>
	<div class="clearfix"></div>	
	<div class="form-group col-md-10">
		<label>Image URL:</label>
		<input type="text" id="imageUrl" class="form-control">
	</div>
	<div class="clearfix"></div>
	<div class="form-group col-md-2">
		<label>Footer marquee label:</label>
		<input type="text" class="form-control" id="footerUpdateLabel">
	</div>
	<div class="form-group col-md-8">
		<label>Footer marquee text:</label>
		<textarea class="form-control" id="footerUpdate"></textarea>
	</div>
  	<div class="clearfix"></div> 
  	<div class="col-md-12">
		<button class="btn blue_btn" id="tcInsertBtn" onclick="insertScreenConfig();">Insert</button>
		<button class="btn blue_btn" id="tcUpdateBtn" onclick="updateScreenConfig();">Update</button>
		<a id="generateScreenBtn" class="btn blue_btn" href="#" target="_blank">Generate Token Screen</a>
	</div>		
</div>	