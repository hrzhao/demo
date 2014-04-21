package hrzhao.process.base;

import hrzhao.beans.ReqMessageBean;

public interface ProcessInterface {
	public String doProcess(ReqMessageBean msgBean);
	public void setProcessId(Integer processId);

}
