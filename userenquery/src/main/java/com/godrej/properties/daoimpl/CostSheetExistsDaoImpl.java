package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CostSheetExistsDao;
import com.godrej.properties.model.CostSheet;
import com.godrej.properties.model.ExtraCharges;

@SuppressWarnings("unchecked")
@Repository("costSheetExistsDao")
public class CostSheetExistsDaoImpl extends AbstractDao<Integer, CostSheet> implements CostSheetExistsDao {

	@Autowired
	private SessionFactory sessionFactory;	
	
	public CostSheet getCSData(String contactNo) {	 
		
		Criteria criteria = createEntityCriteria();		
		criteria.add(Restrictions.eq("user_contact", contactNo));
		//criteria.add(Restrictions.eq("isactive", "Y"));
		
		List<CostSheet> req= (List<CostSheet>) criteria.list();
		if(req.size()>0) {
			return req.get(0);
		}
		return null;		
	}
	
	@Override
	public void updateCostSheet(List<CostSheet> charges) {
		
		for (int i = 0; i <charges.size(); i++) {
		//	ExtraCharges charges2=charges.get(i).setSeq();;
			//update(charges2);			
			Session session = this.sessionFactory.getCurrentSession();
			
			//charges.get(i).getDescription();
			Query query = session.createQuery("UPDATE CostSheet SET milestones = '"+charges.get(i).getMilestones()+"', chargeamount = '"+charges.get(i).getChargeAmount()+"', taxableamount = '"+charges.get(i).getTaxableAmount()+"', cgstrate = '"+charges.get(i).getCgstRate()+"', cgstamount = '"+charges.get(i).getCgstAmount()+"', sgstrate = '"+charges.get(i).getSgstRate()+"', sgstamount = '"+charges.get(i).getSgstAmount()+"', total = '"+charges.get(i).getTotal()+"', tds = '"+charges.get(i).getTds()+"', payableto = '"+charges.get(i).getPayableTo()+"', timeid = '"+charges.get(i).getTimeid()+"'  WHERE user_contact = '"+charges.get(i).getUser_contact()+"' AND seq = '"+charges.get(i).getSeq()+"'");
			
			query.executeUpdate();
			
			//System.out.println("charges ::: " + charges2);
		}
	}

}
