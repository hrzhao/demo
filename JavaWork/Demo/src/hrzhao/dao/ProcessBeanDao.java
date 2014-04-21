package hrzhao.dao;

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
		return pcsBean;
		
	}

}
