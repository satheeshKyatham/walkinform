package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.CarparkTypeAndCharge;

public interface CarparkTypeAndChargeService {
	List<CarparkTypeAndCharge> getCarparkType(String projectSFID);
}