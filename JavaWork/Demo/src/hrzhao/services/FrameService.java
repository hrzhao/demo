package hrzhao.services;

import java.util.List;

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
	public ResultObject getAppConfigBeanList(){
		AppConfigBeanDao appDao = new AppConfigBeanDao();
		List<AppConfigBean> list= appDao.getAppConfigBeanList();
		return new ResultObject(ResultObject.SUCCESS, list);
	}
	public ResultObject delAppConfigBeanByAppId(String appId){
		AppConfigBeanDao appDao = new AppConfigBeanDao();
		appDao.deleteAppConfigBeanByAppId(appId);
		return new ResultObject(ResultObject.SUCCESS, null);
	}

}
