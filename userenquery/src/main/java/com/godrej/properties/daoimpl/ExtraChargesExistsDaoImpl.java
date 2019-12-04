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
import com.godrej.properties.dao.ExtraChargesExistsDao;
import com.godrej.properties.model.ExtraCharges;

@SuppressWarnings("unchecked")
@Repository("extraChargesExistsDao")
public class ExtraChargesExistsDaoImpl extends AbstractDao<Integer, ExtraCharges> implements ExtraChargesExistsDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ExtraCharges getECData(String contactNo) {
		Criteria criteria = createEntityCriteria();		
		criteria.add(Restrictions.eq("user_contact", contactNo));
		//criteria.add(Restrictions.eq("isactive", "Y"));
		
		List<ExtraCharges> req= (List<ExtraCharges>) criteria.list();
		if(req.size()>0) {
			return req.get(0);
		}
		return null;		
	}

	@Override
	public void updateExtraCharges(List<ExtraCharges> charges) {
		
		for (int i = 0; i <charges.size(); i++) {
		//	ExtraCharges charges2=charges.get(i).setSeq();;
			//update(charges2);			
			Session session = this.sessionFactory.getCurrentSession();
			
			charges.get(i).getDescription();
			Query query = session.createQuery("UPDATE ExtraCharges SET chargeamount = '"+charges.get(i).getChargeAmount()+"', taxableamount = '"+charges.get(i).getTaxableAmount()+"', cgstamount = '"+charges.get(i).getCGSTAmount()+"', sgstamount = '"+charges.get(i).getSGSTAmount()+"', total = '"+charges.get(i).getTotal()+"', timeid = '"+charges.get(i).getTimeid()+"'  WHERE user_contact = '"+charges.get(i).getUser_contact()+"' AND seq = '"+charges.get(i).getSeq()+"'");
			
			query.executeUpdate();
			
			//System.out.println("charges ::: " + charges2);
		}
	}
}
