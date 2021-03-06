package hrzhao.process;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.json.JSONObject;
import hrzhao.beans.CustomerBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.CustomerBeanDao;
import hrzhao.process.base.ProcessBase;
import hrzhao.process.base.ProcessResult;
import hrzhao.utils.DebugHelper;

public class PcsPersonInfo extends ProcessBase {

	public PcsPersonInfo() {
	}

	@Override
	protected ProcessResult doProcessExt(ReqMessageBean msgBean) {
		Boolean result = false;
		String msg = "";
		JSONObject jsonObj = super.getParam();
		String fieldName = jsonObj.getString("fieldName");
		String name = jsonObj.getString("name");
		String content = msgBean.getContent();
		String fromUserName = msgBean.getFromUserName();
		CustomerBeanDao customerDao = new CustomerBeanDao();
		CustomerBean customerBean = customerDao.getCustomer(fromUserName);
		try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName,  CustomerBean.class);
			Method method = pd.getWriteMethod();
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
		} catch(NumberFormatException e){
			//数字转换错误
			msg += "请输入数字";
		} catch ( SecurityException |IllegalAccessException | IllegalArgumentException
				| InvocationTargetException |IntrospectionException e) {
			DebugHelper.log("PcsPersonInfo", "doProcessExt()1\n" + e.toString());
			msg += "[系统错误#2]";
		}
		return new ProcessResult(msg,result);
	}

	@Override
	public String getTips(CustomerBean customerBean) {
		String tips = "";
		JSONObject jsonObj = super.getParam();
		String fieldName = jsonObj.getString("fieldName");
		String name = jsonObj.getString("name");
		Object obj = null;
        try {
        	PropertyDescriptor pd = new PropertyDescriptor(fieldName,  CustomerBean.class);
        	Method getMethod = pd.getReadMethod();//获得get方法  
//        	pd.getPropertyType();
			obj = getMethod.invoke(customerBean);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | IntrospectionException e) {
			DebugHelper.log("PcsPersonInfo", "getTips()\n" + e.toString());
		}
        if(obj == null){
        	tips += "无["+name+"]信息\n";
        }else{
        	tips += "原有";
        	if(obj.getClass() == Boolean.class ){
        		if((Boolean)obj){
        			tips += name+ "[是]\n";
        		}else{
        			tips += name+ "[否]\n";
        		}
        	}else{
        		tips += "["+name+"]"+ obj;
        	}
        }
		tips += super.getTips(customerBean);
		return tips;
	}
	public String firstLetterUpper(String str){
		if(str != null && str.length()>0){
			StringBuffer sb = new StringBuffer(str);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			str = sb.toString();
		}
		return str;
	}
	
	

}
