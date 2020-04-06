package com.godrej.properties.model;

import javax.persistence.Column;

public class PaymentPlanJson {

	private String ID;
	private String TypeofCarParking;
	private String Typology;
	private String BasicSalePricepersquarefeet;
	private String CarpetArea;
	private String ExclusiveBalconyAreaTerraceArea;
	private String SaleableArea;
	private String BasicSalePrice;
	private String PreferredLocationCharges;
	private String FloorRiseCharges;
	private String GeneratorCharges;
	private String GasBankCharges;
	private String BESCOMWaterSupplyandSewage;
	private String LegalandKhataCharges;
	private String AdvanceMaintenance;
	private String SinkingFundDeposit;
	private String ABasicSalePrice;
	private String APreferredLocationCharges;
	private String AFloorRiseCharges;
	private String AGeneratorCharges;
	private String AGasBankCharges;
	private String ABESCOMWaterSupplyandSewage;
	private String ALegalandKhataCharges;
	private String ACoveredCarPark;
	private String AClubHouseCharges;
	private String AAdvanceMaintenance;
	private String ASinkingFundDeposit;
	private String ATotle;
	private String SunriseFacing;
	private String GatewayFacing;
	private String EastFacing;
	private String CentralGardenFacing;
	private String CornerApartment;
	private String RearGardenFacing;
	private String TotalPLC;
	private String flexibleYN="N";
	private String BasicSalePricepersquarefeetYN="N";
	private String FloorRiseChargesYN="N";
	private String ACoveredCarParkYN="N";
	private String AClubHouseChargesYN="N";
	private String AdvanceMaintenanceYN="N";
	private String SinkingFundDepositYN="N";
	private String balcony_area_sq_ft__c;
	private String balcony_area_sq_mt__c;
	private String terrace_area_sq_ft__c;
	private String terrace_area_sq_mt__c;
	private String carpet_area_converted__c;
	private String unitsfid;
	private String towerName;
	private String floorName;
	
	private Boolean pmay;
	private String new_tax;
	private String old_tax;
	private String propstrength__gst_status__c;
	
	private String bsp_amount;
	
	private double appurtenant_area_sq_mt__c;
	
	private boolean  propstrength__completion_certificate_received__c;
	private String  propstrength__category__c;
	private boolean  propstrength__pmay_abatement__c;
	private String  bank__c;
	
	private String  property_facing__c;
	private double bsp_per;
	
	private String  paymentTrxdaysVal;
	
	private String nowData;
	
	private String wing_block__c;
	
	
	public String getBalcony_area_sq_ft__c() {
		return balcony_area_sq_ft__c;
	}
	public void setBalcony_area_sq_ft__c(String balcony_area_sq_ft__c) {
		this.balcony_area_sq_ft__c = balcony_area_sq_ft__c;
	}
	public String getBalcony_area_sq_mt__c() {
		return balcony_area_sq_mt__c;
	}
	public void setBalcony_area_sq_mt__c(String balcony_area_sq_mt__c) {
		this.balcony_area_sq_mt__c = balcony_area_sq_mt__c;
	}
	public String getTerrace_area_sq_ft__c() {
		return terrace_area_sq_ft__c;
	}
	public void setTerrace_area_sq_ft__c(String terrace_area_sq_ft__c) {
		this.terrace_area_sq_ft__c = terrace_area_sq_ft__c;
	}
	public String getTerrace_area_sq_mt__c() {
		return terrace_area_sq_mt__c;
	}
	public void setTerrace_area_sq_mt__c(String terrace_area_sq_mt__c) {
		this.terrace_area_sq_mt__c = terrace_area_sq_mt__c;
	}
	public String getCarpet_area_converted__c() {
		return carpet_area_converted__c;
	}
	public void setCarpet_area_converted__c(String carpet_area_converted__c) {
		this.carpet_area_converted__c = carpet_area_converted__c;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getTypeofCarParking() {
		return TypeofCarParking;
	}
	public void setTypeofCarParking(String typeofCarParking) {
		TypeofCarParking = typeofCarParking;
	}
	public String getTypology() {
		return Typology;
	}
	public void setTypology(String typology) {
		Typology = typology;
	}
	public String getBasicSalePricepersquarefeet() {
		return BasicSalePricepersquarefeet;
	}
	public void setBasicSalePricepersquarefeet(String basicSalePricepersquarefeet) {
		BasicSalePricepersquarefeet = basicSalePricepersquarefeet;
	}
	public String getCarpetArea() {
		return CarpetArea;
	}
	public void setCarpetArea(String carpetArea) {
		CarpetArea = carpetArea;
	}
	public String getExclusiveBalconyAreaTerraceArea() {
		return ExclusiveBalconyAreaTerraceArea;
	}
	public void setExclusiveBalconyAreaTerraceArea(String exclusiveBalconyAreaTerraceArea) {
		ExclusiveBalconyAreaTerraceArea = exclusiveBalconyAreaTerraceArea;
	}
	public String getSaleableArea() {
		return SaleableArea;
	}
	public void setSaleableArea(String saleableArea) {
		SaleableArea = saleableArea;
	}
	public String getBasicSalePrice() {
		return BasicSalePrice;
	}
	public void setBasicSalePrice(String basicSalePrice) {
		BasicSalePrice = basicSalePrice;
	}
	public String getPreferredLocationCharges() {
		return PreferredLocationCharges;
	}
	public void setPreferredLocationCharges(String preferredLocationCharges) {
		PreferredLocationCharges = preferredLocationCharges;
	}
	public String getFloorRiseCharges() {
		return FloorRiseCharges;
	}
	public void setFloorRiseCharges(String floorRiseCharges) {
		FloorRiseCharges = floorRiseCharges;
	}
	public String getGeneratorCharges() {
		return GeneratorCharges;
	}
	public void setGeneratorCharges(String generatorCharges) {
		GeneratorCharges = generatorCharges;
	}
	public String getGasBankCharges() {
		return GasBankCharges;
	}
	public void setGasBankCharges(String gasBankCharges) {
		GasBankCharges = gasBankCharges;
	}
	public String getBESCOMWaterSupplyandSewage() {
		return BESCOMWaterSupplyandSewage;
	}
	public void setBESCOMWaterSupplyandSewage(String bESCOMWaterSupplyandSewage) {
		BESCOMWaterSupplyandSewage = bESCOMWaterSupplyandSewage;
	}
	public String getLegalandKhataCharges() {
		return LegalandKhataCharges;
	}
	public void setLegalandKhataCharges(String legalandKhataCharges) {
		LegalandKhataCharges = legalandKhataCharges;
	}
	public String getAdvanceMaintenance() {
		return AdvanceMaintenance;
	}
	public void setAdvanceMaintenance(String advanceMaintenance) {
		AdvanceMaintenance = advanceMaintenance;
	}
	public String getSinkingFundDeposit() {
		return SinkingFundDeposit;
	}
	public void setSinkingFundDeposit(String sinkingFundDeposit) {
		SinkingFundDeposit = sinkingFundDeposit;
	}
	public String getABasicSalePrice() {
		return ABasicSalePrice;
	}
	public void setABasicSalePrice(String aBasicSalePrice) {
		ABasicSalePrice = aBasicSalePrice;
	}
	public String getAPreferredLocationCharges() {
		return APreferredLocationCharges;
	}
	public void setAPreferredLocationCharges(String aPreferredLocationCharges) {
		APreferredLocationCharges = aPreferredLocationCharges;
	}
	public String getAFloorRiseCharges() {
		return AFloorRiseCharges;
	}
	public void setAFloorRiseCharges(String aFloorRiseCharges) {
		AFloorRiseCharges = aFloorRiseCharges;
	}
	public String getAGeneratorCharges() {
		return AGeneratorCharges;
	}
	public void setAGeneratorCharges(String aGeneratorCharges) {
		AGeneratorCharges = aGeneratorCharges;
	}
	public String getAGasBankCharges() {
		return AGasBankCharges;
	}
	public void setAGasBankCharges(String aGasBankCharges) {
		AGasBankCharges = aGasBankCharges;
	}
	public String getABESCOMWaterSupplyandSewage() {
		return ABESCOMWaterSupplyandSewage;
	}
	public void setABESCOMWaterSupplyandSewage(String aBESCOMWaterSupplyandSewage) {
		ABESCOMWaterSupplyandSewage = aBESCOMWaterSupplyandSewage;
	}
	public String getALegalandKhataCharges() {
		return ALegalandKhataCharges;
	}
	public void setALegalandKhataCharges(String aLegalandKhataCharges) {
		ALegalandKhataCharges = aLegalandKhataCharges;
	}
	public String getACoveredCarPark() {
		return ACoveredCarPark;
	}
	public void setACoveredCarPark(String aCoveredCarPark) {
		ACoveredCarPark = aCoveredCarPark;
	}
	public String getAClubHouseCharges() {
		return AClubHouseCharges;
	}
	public void setAClubHouseCharges(String aClubHouseCharges) {
		AClubHouseCharges = aClubHouseCharges;
	}
	public String getAAdvanceMaintenance() {
		return AAdvanceMaintenance;
	}
	public void setAAdvanceMaintenance(String aAdvanceMaintenance) {
		AAdvanceMaintenance = aAdvanceMaintenance;
	}
	public String getASinkingFundDeposit() {
		return ASinkingFundDeposit;
	}
	public void setASinkingFundDeposit(String aSinkingFundDeposit) {
		ASinkingFundDeposit = aSinkingFundDeposit;
	}
	public String getATotle() {
		return ATotle;
	}
	public void setATotle(String aTotle) {
		ATotle = aTotle;
	}
	public String getSunriseFacing() {
		return SunriseFacing;
	}
	public void setSunriseFacing(String sunriseFacing) {
		SunriseFacing = sunriseFacing;
	}
	public String getGatewayFacing() {
		return GatewayFacing;
	}
	public void setGatewayFacing(String gatewayFacing) {
		GatewayFacing = gatewayFacing;
	}
	public String getEastFacing() {
		return EastFacing;
	}
	public void setEastFacing(String eastFacing) {
		EastFacing = eastFacing;
	}
	public String getCentralGardenFacing() {
		return CentralGardenFacing;
	}
	public void setCentralGardenFacing(String centralGardenFacing) {
		CentralGardenFacing = centralGardenFacing;
	}
	public String getCornerApartment() {
		return CornerApartment;
	}
	public void setCornerApartment(String cornerApartment) {
		CornerApartment = cornerApartment;
	}
	public String getRearGardenFacing() {
		return RearGardenFacing;
	}
	public void setRearGardenFacing(String rearGardenFacing) {
		RearGardenFacing = rearGardenFacing;
	}
	public String getTotalPLC() {
		return TotalPLC;
	}
	public void setTotalPLC(String totalPLC) {
		TotalPLC = totalPLC;
	}
	public String getFlexibleYN() {
		return flexibleYN;
	}
	public void setFlexibleYN(String flexibleYN) {
		this.flexibleYN = flexibleYN;
	}
	public String getBasicSalePricepersquarefeetYN() {
		return BasicSalePricepersquarefeetYN;
	}
	public void setBasicSalePricepersquarefeetYN(String basicSalePricepersquarefeetYN) {
		BasicSalePricepersquarefeetYN = basicSalePricepersquarefeetYN;
	}
	public String getFloorRiseChargesYN() {
		return FloorRiseChargesYN;
	}
	public void setFloorRiseChargesYN(String floorRiseChargesYN) {
		FloorRiseChargesYN = floorRiseChargesYN;
	}
	public String getACoveredCarParkYN() {
		return ACoveredCarParkYN;
	}
	public void setACoveredCarParkYN(String aCoveredCarParkYN) {
		ACoveredCarParkYN = aCoveredCarParkYN;
	}
	public String getAClubHouseChargesYN() {
		return AClubHouseChargesYN;
	}
	public void setAClubHouseChargesYN(String aClubHouseChargesYN) {
		AClubHouseChargesYN = aClubHouseChargesYN;
	}
	public String getAdvanceMaintenanceYN() {
		return AdvanceMaintenanceYN;
	}
	public void setAdvanceMaintenanceYN(String advanceMaintenanceYN) {
		AdvanceMaintenanceYN = advanceMaintenanceYN;
	}
	public String getSinkingFundDepositYN() {
		return SinkingFundDepositYN;
	}
	public void setSinkingFundDepositYN(String sinkingFundDepositYN) {
		SinkingFundDepositYN = sinkingFundDepositYN;
	}
	public String getUnitsfid() {
		return unitsfid;
	}
	public void setUnitsfid(String unitsfid) {
		this.unitsfid = unitsfid;
	}
	public String getTowerName() {
		return towerName;
	}
	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public Boolean getPmay() {
		return pmay;
	}
	public void setPmay(Boolean pmay) {
		this.pmay = pmay;
	}
	public String getNew_tax() {
		return new_tax;
	}
	public void setNew_tax(String new_tax) {
		this.new_tax = new_tax;
	}
	public String getOld_tax() {
		return old_tax;
	}
	public void setOld_tax(String old_tax) {
		this.old_tax = old_tax;
	}
	public String getPropstrength__gst_status__c() {
		return propstrength__gst_status__c;
	}
	public void setPropstrength__gst_status__c(String propstrength__gst_status__c) {
		this.propstrength__gst_status__c = propstrength__gst_status__c;
	}
	public String getBsp_amount() {
		return bsp_amount;
	}
	public void setBsp_amount(String bsp_amount) {
		this.bsp_amount = bsp_amount;
	}
	public double getAppurtenant_area_sq_mt__c() {
		return appurtenant_area_sq_mt__c;
	}
	public void setAppurtenant_area_sq_mt__c(double appurtenant_area_sq_mt__c) {
		this.appurtenant_area_sq_mt__c = appurtenant_area_sq_mt__c;
	}
	public boolean isPropstrength__completion_certificate_received__c() {
		return propstrength__completion_certificate_received__c;
	}
	public void setPropstrength__completion_certificate_received__c(
			boolean propstrength__completion_certificate_received__c) {
		this.propstrength__completion_certificate_received__c = propstrength__completion_certificate_received__c;
	}
	public String getPropstrength__category__c() {
		return propstrength__category__c;
	}
	public void setPropstrength__category__c(String propstrength__category__c) {
		this.propstrength__category__c = propstrength__category__c;
	}
	public boolean isPropstrength__pmay_abatement__c() {
		return propstrength__pmay_abatement__c;
	}
	public void setPropstrength__pmay_abatement__c(boolean propstrength__pmay_abatement__c) {
		this.propstrength__pmay_abatement__c = propstrength__pmay_abatement__c;
	}
	public String getBank__c() {
		return bank__c;
	}
	public void setBank__c(String bank__c) {
		this.bank__c = bank__c;
	}
	public String getProperty_facing__c() {
		return property_facing__c;
	}
	public void setProperty_facing__c(String property_facing__c) {
		this.property_facing__c = property_facing__c;
	}
	public double getBsp_per() {
		return bsp_per;
	}
	public void setBsp_per(double bsp_per) {
		this.bsp_per = bsp_per;
	}
	public String getPaymentTrxdaysVal() {
		return paymentTrxdaysVal;
	}
	public void setPaymentTrxdaysVal(String paymentTrxdaysVal) {
		this.paymentTrxdaysVal = paymentTrxdaysVal;
	}
	public String getNowData() {
		return nowData;
	}
	public void setNowData(String nowData) {
		this.nowData = nowData;
	}
	public String getWing_block__c() {
		return wing_block__c;
	}
	public void setWing_block__c(String wing_block__c) {
		this.wing_block__c = wing_block__c;
	}
}