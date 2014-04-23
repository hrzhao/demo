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
		CustomerBeanDao customerDao = new CustomerBeanDao();
		String fromUserName = msgBean.getFromUserName();
		CustomerBean customerBean = customerDao.getCustomer(fromUserName);
		String msg = "";
		if(customerBean == null){
			customerBean = new CustomerBean();
			customerBean.setIntime(now);
			customerBean.setLasttime(now);
			customerBean.setName(msgBean.getFromUserName());
			customerBean.setProcessId(welPcsId);
			customerBean.setProcessing(true);
			customerDao.saveCustomer(customerBean);
			msg += "[首次到来]\n";
		}
		msg += "欢迎您到来，这里是邑水一方人!\n技术员HRZhao@qq.com";
		return new ProcessResult(msg,true);
	}

}
