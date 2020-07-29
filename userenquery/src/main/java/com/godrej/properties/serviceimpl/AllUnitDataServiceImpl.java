package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.AllUnitDataDao;
import com.godrej.properties.model.AllUnitData;
import com.godrej.properties.service.AllUnitDataService;

@Service("allInventoryReportService")
@Transactional
public class AllUnitDataServiceImpl implements AllUnitDataService{
	@Autowired
	AllUnitDataDao  allUnitDataDao;

	@Override
	public List<AllUnitData> getInventoryReportDtl(String whereCondition) {
		return allUnitDataDao.getInventoryReportDtl(whereCondition);
	}
}