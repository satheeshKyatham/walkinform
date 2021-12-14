<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="clearfix"></div>

<div class="col-md-12" style="margin: 0 auto; float: none;">
	<div class="mainCont " style="padding-top: 10px;">
		<div id=""> 
			<div class="col-md-12" style="margin-bottom:15px;">
				<div class="row">

					<div class="form-group col-md-3">
						<label>Project</label>
						<select class="form-control" id="projectDataListForSM" onchange="getTowerList()"> </select>
						<label>Tower</label>
						<select class="form-control" id="towerChargeMst" onchange="getCarParkingCombination()"> </select>
						<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
						<div id="headerCarParkingTable">
							<div class="clearfix"></div>
						</div>
						<button type="button" onclick="onCarParkingSubmit()" style="display: none" class="submit_carpark">Submit/Update</button>
						<!-- <button type="button" class="delete_carpark">Delete</button> -->
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="clearfix"></div>
</div>
			