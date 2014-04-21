package hrzhao.process;

import hrzhao.beans.CustomerBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.process.base.ProcessBase;
import hrzhao.process.base.ProcessResult;

public class PcsSaveName extends ProcessBase {

	public PcsSaveName() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ProcessResult doProcessExt(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		String msg = null;
		Boolean result = true;
		String fromUserName = msgBean.getFromUserName();
		CustomerBeanDao customerDao = new CustomerBeanDao();
		CustomerBean customerBean = customerDao.getCustomer(fromUserName);
		if(customerBean == null){
			customerBean = new CustomerBean();
			customerBean.setName(fromUserName);
		}
		customerBean.setRealname(msgBean.getContent().trim());
		customerDao.saveOrUpdateCustomer(customerBean);
		msg = "名称["+customerBean.getRealname()+"]，已被保存";
		return new ProcessResult(msg,result);
	}
}
