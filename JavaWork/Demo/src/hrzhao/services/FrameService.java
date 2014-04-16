package hrzhao.services;

import java.util.HashMap;
import java.util.List;

import hrzhao.beans.AppConfigBean;
import hrzhao.dao.AppConfigBeanDao;
import hrzhao.data.OrderColumn;
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
		return getAppConfigBeanList(null);
	}
	public ResultObject getAppConfigBeanList(List<OrderColumn> orderColumns){
		AppConfigBeanDao appDao = new AppConfigBeanDao();
		List<AppConfigBean> list= appDao.getAppConfigBeanList(orderColumns);
		return new ResultObject(ResultObject.SUCCESS, list);
	}
	public ResultObject delAppConfigBeanByAppId(String appId){
		AppConfigBeanDao appDao = new AppConfigBeanDao();
		appDao.deleteAppConfigBeanByAppId(appId);
		return new ResultObject(ResultObject.SUCCESS, null);
	}
	public ResultObject saveOrAddAppConfigBean(AppConfigBean appConfigBean){
		AppConfigBeanDao appDao = new AppConfigBeanDao();
		appDao.saveAppConfigBean(appConfigBean);
		return new ResultObject(ResultObject.SUCCESS, null);
	}

}
