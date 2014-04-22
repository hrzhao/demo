package hrzhao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import hrzhao.beans.ProcessBean;
import hrzhao.utils.HiberHelper;

public class ProcessBeanDao {

	public ProcessBeanDao() {
		// TODO Auto-generated constructor stub
	}
	public ProcessBean getProcessBean(int id){
		Session session = HiberHelper.getSession();
		ProcessBean pcsBean = (ProcessBean)session.get(ProcessBean.class, id);
		session.close();
		return pcsBean;
		
	}
	public List<ProcessBean> getProcessList() {
		// TODO Auto-generated method stub
		Session session = HiberHelper.getSession();
		Criteria cr = session.createCriteria(ProcessBean.class);
		@SuppressWarnings("unchecked")
		List<ProcessBean> list = cr.list();
		session.close();
		return list;
	}

}
