package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PropOtherCharges;

public interface PropOtherChargesService {
	List<PropOtherCharges> getCharges(String propSfid, String projectId);
	String getPropertyTypeStatus(String propSfid);
	/*Changes By Satheesh for Inventory Update*/
	String updatePropertyStatus(String propSfid);
}
