package hrzhao;
import hrzhao.beans.ConfigBean;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
public final class ConfigHelper {
	public static String getConfig(String name){
		String value ="";
		Session session = HiberHelper.getSession();
		Criteria criteria = session.createCriteria(ConfigBean.class);
		criteria.add(Restrictions.eq("name", name));//eq是等于，gt是大于，lt是小于,or是或
		criteria.setMaxResults(1);
		List<ConfigBean> rs = criteria.list();
		Iterator<ConfigBean> it = rs.iterator();
		if(it.hasNext()){
			value = it.next().getValue();
		}
		HiberHelper.closeSession(session);
		HiberHelper.closeFactory();
		return value;
	}
	private static String originalId = null;
	public static String getOriginalId(){
		if(originalId == null || originalId.equals("")){
			originalId = getConfig("original_id");
		}
		return originalId;
	}
}
