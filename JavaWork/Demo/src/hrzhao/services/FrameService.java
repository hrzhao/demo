package hrzhao.services;

import hrzhao.beans.AppConfigBean;
import hrzhao.dao.AppConfigBeanDao;
import hrzhao.utils.ResultObject;

public class FrameService {

	public FrameService() {
		// TODO Auto-generated constructor stub
	}
	public ResultObject getAppConfigBean(String appId){
		AppConfigBean appBean;
		AppConfigBeanDao appDao = new AppConfigBeanDao();
		appBean = appDao.getAppConfigBean(appId);
		return new ResultObject(ResultObject.SUCCESS, appBean);
	}

}