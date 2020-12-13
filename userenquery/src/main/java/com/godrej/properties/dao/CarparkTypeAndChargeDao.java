package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.CarparkCount;
import com.godrej.properties.model.CarparkTypeAndCharge;

public interface CarparkTypeAndChargeDao {
	List<CarparkTypeAndCharge> getCarparkType(String projectSFID);
	List<CarparkCount> getCarparkCount(String projectSFID);
}