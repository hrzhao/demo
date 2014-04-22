package hrzhao.process;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.json.JSONObject;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.ProcessBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.process.base.ProcessBase;
import hrzhao.process.base.ProcessResult;
import hrzhao.utils.DebugHelper;

public class PcsPersonInfo extends ProcessBase {

	public PcsPersonInfo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ProcessResult doProcessExt(ReqMessageBean msgBean) {
		Boolean result = false;
		String msg = "";
		JSONObject jsonObj = super.getParam();
		String fieldName = jsonObj.getString("fieldName");
		String methodName = "set" + fieldName;
		String name = jsonObj.getString("name");
		String content = msgBean.getContent();
		String fromUserName = msgBean.getFromUserName();
		CustomerBeanDao customerDao = new CustomerBeanDao();
		CustomerBean customerBean = customerDao.getCustomer(fromUserName);
		Method method;
		try {
			Method[] methods =  CustomerBean.class.getMethods();
			for(int i=0; i<methods.length;i ++){
				if(methodName.equals(methods[i].getName())){
					method = methods[i];
					Class<?>[] types = method.getParameterTypes();
					if(types[0] == String.class){
						method.invoke(customerBean,content);
						customerDao.saveOrUpdateCustomer(customerBean);
						msg = name+"["+content + "],已保存";
						result = true;
					}else if(types[0] == Integer.class || 
							types[0] == int.class){
						method.invoke(customerBean,Integer.parseInt(content));
						customerDao.saveOrUpdateCustomer(customerBean);
						msg = name+"["+content + "],已保存";
						result = true;
					}else{
						//字段为非Integer,int,String
						msg += "内容无法识别";
					}
					break;
				}
			}
		} catch(NumberFormatException e){
			//数字转换错误
			msg += "请输入数字";
		} catch ( SecurityException |IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			DebugHelper.log("PcsPersonInfo", "doProcessExt()1\n" + e.toString());
			msg += "[系统错误]";
		} 
		return new ProcessResult(msg,result);
	}
	
	

}
