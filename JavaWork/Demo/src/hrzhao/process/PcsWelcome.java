package hrzhao.process;

import java.util.Date;

import hrzhao.beans.CustomerBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.process.base.ProcessBase;
import hrzhao.process.base.ProcessResult;
import hrzhao.utils.ConfigHelper;

public class PcsWelcome extends ProcessBase {

	public PcsWelcome() {
	}

	private Date now;
	private int welPcsId = ConfigHelper.welPcsId;
	@Override
	protected ProcessResult doProcessExt(ReqMessageBean msgBean) {
		now = new Date();
		CustomerBean customerBean = new CustomerBean();
		customerBean.setIntime(now);
		customerBean.setLasttime(now);
		customerBean.setName(msgBean.getFromUserName());
		customerBean.setProcessId(welPcsId);
		customerBean.setProcessing(true);
		CustomerBeanDao customerDao = new CustomerBeanDao();
		customerDao.saveCustomer(customerBean);
		
		String msg = "欢迎您到来，这里是邑水一方人!\n若有Bug,请反馈rong17173@139.com";
		return new ProcessResult(msg,true);
	}

}
