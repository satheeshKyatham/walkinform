package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.BSPAgainstPymtPlanDao;
import com.godrej.properties.model.BSPAgainstPymtPlan;
import com.godrej.properties.service.BSPAgainstPymtPlanService;
import com.itextpdf.text.log.SysoCounter;

@Service("bSPAgainstPymtPlanService")
@Transactional
public class BSPAgainstPymtPlanServiceImpl implements BSPAgainstPymtPlanService{
	
	@Autowired
	private BSPAgainstPymtPlanDao dao;
	
	public void insertBSPForPP(BSPAgainstPymtPlan bSPAgainstPymtPlan) {
		dao.insertBSPForPP(bSPAgainstPymtPlan);
	}

	@Override
	public int checkBSPForPP(BSPAgainstPymtPlan bSPAgainstPymtPlan) {
		// TODO Auto-generated method stub
		return dao.checkBSPForPP(bSPAgainstPymtPlan);
	}

	@Override
	public int getPaymentPlanBSPList(String project_code,String unit,String towerCode,String paymentPlanID,String typology) {
		// TODO Auto-generated method stub
		List<BSPAgainstPymtPlan> payBSPList = dao.getPaymentPlanBSPList(paymentPlanID);
		int bspAmountReturn = 0;
		if(payBSPList!=null)
		{
			
			/*for(int i=0;i<payBSPList.size();i++)
			{
				if(payBSPList.get(i).getTowerid().matches("a2F6F000003vxYSUAY"))
				{
					System.out.println("Equal");
					
				}
				else
				{
					System.out.println("Not Equal");
				}
				
				if(payBSPList.get(i).getTowerid().contains("a2F6F000003vxYSUAY"))
				{
					System.out.println("Equal Contains");
					
				}
				else
				{
					System.out.println("Not Equal Contains");
				}
			}*/
			boolean valueCheck=false;
			for(int i=0;i<payBSPList.size();i++)
			{
				if((payBSPList.get(i).getBsp_amount()!=null && payBSPList.get(i).getBsp_amount().length()>0) && (payBSPList.get(i).getProject_id()!=null && payBSPList.get(i).getProject_id().length()>0) && (payBSPList.get(i).getTowerid()!=null && payBSPList.get(i).getTowerid().length()>0) && (payBSPList.get(i).getPymt_plan_id()!=null && payBSPList.get(i).getPymt_plan_id().length()>0) && (payBSPList.get(i).getTypology()!=null && payBSPList.get(i).getTypology().length()>0))
				{
					if(payBSPList.get(i).getTowerid().contains(towerCode) && payBSPList.get(i).getTypology().contains(typology))
					{
						valueCheck =true;
						return bspAmountReturn=Integer.parseInt(payBSPList.get(i).getBsp_amount());
					}
				}
			}
			if(!valueCheck)
			{
				for(int i=0;i<payBSPList.size();i++)
				{
					if((payBSPList.get(i).getBsp_amount()!=null && payBSPList.get(i).getBsp_amount().length()>0) && (payBSPList.get(i).getProject_id()!=null && payBSPList.get(i).getProject_id().length()>0) && (payBSPList.get(i).getTowerid()!=null && payBSPList.get(i).getTowerid().length()>0) && (payBSPList.get(i).getPymt_plan_id()!=null && payBSPList.get(i).getPymt_plan_id().length()>0))
					{
						if((payBSPList.get(i).getTowerid().contains(towerCode)) && (payBSPList.get(i).getTypology().trim().length()<=0))
						{
							valueCheck =true;
							return bspAmountReturn=Integer.parseInt(payBSPList.get(i).getBsp_amount());
						}
					}
				}
			}
			if(!valueCheck)
			{
				for(int i=0;i<payBSPList.size();i++)
				{
					if((payBSPList.get(i).getBsp_amount()!=null && payBSPList.get(i).getBsp_amount().length()>0) && (payBSPList.get(i).getProject_id()!=null && payBSPList.get(i).getProject_id().length()>0) && (payBSPList.get(i).getPymt_plan_id()!=null && payBSPList.get(i).getPymt_plan_id().length()>0))
					{
						
						if( (payBSPList.get(i).getPymt_plan_id().contains(paymentPlanID)) && (payBSPList.get(i).getTowerid()==null) && (payBSPList.get(i).getTypology()==null))
						{
							valueCheck =true;
							return bspAmountReturn=Integer.parseInt(payBSPList.get(i).getBsp_amount());
						}
					}	
				}
			}
		}
		return bspAmountReturn;
	}
	
}
