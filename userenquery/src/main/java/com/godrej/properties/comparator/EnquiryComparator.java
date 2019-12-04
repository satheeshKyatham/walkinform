package com.godrej.properties.comparator;

import java.util.Comparator;
import java.util.Date;

import com.godrej.properties.dto.EnquiryDto;
import com.godrej.properties.util.CommonUtil;
import com.godrej.properties.util.DateUtil;

public class EnquiryComparator implements Comparator<EnquiryDto> {

	@Override
	public int compare(EnquiryDto o1, EnquiryDto o2) {
		
		if (o1 == o2) {
			return 0;
		}
		if (o1 == null) {
			return -1;
		}
		if (o2 == null) {
			return 1;
		}

		Date lmd1 = o1.getLastModifiedDate();
		Date doe1 = o1.getDateOfEnquiry();

		Date lmd2 = o2.getLastModifiedDate();
		Date doe2 = o2.getDateOfEnquiry();

		String lmdO1 = lmd1 == null ? "" : DateUtil.getDateString(lmd1, null);
		String doeO1 = doe1 == null ? "" : DateUtil.getDateString(doe1, null);
		String lmdO2 = lmd2 == null ? "" : DateUtil.getDateString(lmd2, null);
		String doeO2 = doe2 == null ? "" : DateUtil.getDateString(doe2, null);

		String source1 =  CommonUtil.isStringEmpty(o1.getWalkInSource())?"1":"2";
		String source2 =  CommonUtil.isStringEmpty(o2.getWalkInSource())?"1":"2";
		
		String order1 = source1+"-"+lmdO1 + "-" + doeO1;
		String order2 = source2+"-"+lmdO2 + "-" + doeO2;
		
		return order2.compareTo(order1);
	}

}
