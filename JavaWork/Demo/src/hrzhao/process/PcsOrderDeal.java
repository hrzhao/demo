package hrzhao.process;

import java.util.List;

import org.hibernate.Session;

import hrzhao.beans.AcountBean;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.AcountBeanDao;
import hrzhao.process.base.ProcessBase;
import hrzhao.process.base.ProcessResult;

public class PcsOrderDeal extends ProcessBase {

	public PcsOrderDeal() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ProcessResult doProcessExt(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTips(CustomerBean customerBean) {
		// TODO Auto-generated method stub
		AcountBeanDao acountDao = new AcountBeanDao();
		List<AcountBean> list = acountDao.getAcountByCustomer(customerBean.getName());
		String tips = "";
		Boolean emptyAcount = true;
		if(list != null && list.size() >0){
			for(AcountBean acount:list){
				if(acount.getAmount()>0){
					emptyAcount=false; 
					
				}
			}
		}
		if(emptyAcount){
			tips += "您所有的水已配送完毕，欢迎订购";
		}else{
			tips += super.getTips(customerBean);
		}
		return tips;
	}
	
	public void getPorductList(){
	}
	
	@Override
	protected CustomerBean updateNextProcessId(String fromUserName,
			Integer processId) {
		// TODO Auto-generated method stub
		return super.updateNextProcessId(fromUserName, processId);
	}
	
	

}
