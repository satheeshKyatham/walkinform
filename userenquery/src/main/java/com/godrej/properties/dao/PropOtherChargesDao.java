package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PropOtherCharges;

public interface PropOtherChargesDao {
	List<PropOtherCharges> getCharges(String propSfid, String projectId);
	String getPropertyTypeStatus(String propSfid);
	/*Changes By Satheesh for Inventory Update*/
	String updatePropertyStatus(String propSfid);
}
