package hrzhao.dao;

import hrzhao.beans.WorkerBean;
import hrzhao.utils.HiberHelper;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class WorkerBeanDao {
	public List<WorkerBean> getWorkerListByType(int type){
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(WorkerBean.class);
		cr.add(Restrictions.eqOrIsNull("type", type));
		@SuppressWarnings("unchecked")
		List<WorkerBean> list = cr.list();
		session.close();
		return list;
	}
}
