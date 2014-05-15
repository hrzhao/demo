package hrzhao.frame;

import hrzhao.beans.UserBean;
import hrzhao.dao.UserBeanDao;
import hrzhao.utils.ResultObject;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;

import flex.messaging.io.MessageIOConstants;
import flex.messaging.security.LoginCommand;

public class HRZhaoLoginCommand implements LoginCommand {

	public HRZhaoLoginCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(ServletConfig config) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	//认证
	@Override
	public Principal doAuthentication(String username, Object credentials) {
		String password = extractPassword(credentials);
//	    System.out.println("###Username:"+username+",Password:"+password+"###");
	    UserBeanDao userDao = new UserBeanDao();
		UserBean userBean = userDao.getUser(username);
		if(userBean != null){
			if(userBean.getPassword().equals(password)){
				UserPrincipal principal = new UserPrincipal(username);
				List<String> subjects = new ArrayList<String>();
				subjects.add("admin");
				subjects.add("privilegeduser");
				principal.setSubjects(subjects);
				return principal;
			}
		}
	    return null;
    }

	//进行授权
	@SuppressWarnings("rawtypes")
	@Override
	public boolean doAuthorization(Principal principal, List roles) {
//	   System.out.println(principal+"##########################");
        UserPrincipal p=(UserPrincipal)principal;
        List<String> subjects = p.getSubjects();
        for (int i = 0; i < subjects.size(); i++) {
            String subject= subjects.get(i);
            for (int j = 0; j < roles.size(); j++) {
//                System.out.print(roles.get(j)+"$$$");
                if(subject.equals(roles.get(j))){
                    return true;
                }
            }
        }
        return false;
	}

	
	//释放Principal，关于Principal的概念，直接来自于Java，如需进一步了解，也可以参考JAAS的相关知识
	@Override
	public boolean logout(Principal principal) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private String extractPassword(Object credentials)
	{
	    String password = null;
	    if (credentials instanceof String)
	    {
	        password = (String)credentials;
	    }
	    else if (credentials instanceof Map)
	    {
	        password = (String)((Map<?,?>)credentials).get(MessageIOConstants.SECURITY_CREDENTIALS);
	    }
	    return password;
	}

}
