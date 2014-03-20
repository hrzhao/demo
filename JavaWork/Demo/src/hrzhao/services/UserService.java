package hrzhao.services;

import hrzhao.ResultObject;
import hrzhao.beans.UserBean;
import hrzhao.dao.UserBeanDao;

public class UserService {

	public UserService() {
		// TODO Auto-generated constructor stub
	}
	public ResultObject checkUser(String username,String password){
		UserBeanDao userDao = new UserBeanDao();
		UserBean userBean = userDao.getUser(username);
		ResultObject rso = new ResultObject("fail", null);
		if(userBean != null){
			if(userBean.getPassword().equals(password)){
				rso.setData(userBean);
				rso.setState(ResultObject.SUCCESS);
			}
		}
		return rso;
	}

}
