package hrzhao.dao;


import java.util.List;

import hrzhao.utils.HiberHelper;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

public class StatisticsDao {
	public List<?> getMonthStat(int month){
		Session session = HiberHelper.getSession();
		
		String sql = "select * from v_orders_stat where 1=1 ";
		if(month >= 0 && month <=11){
			sql += " and date_format(intime,'%c') = " + month;
		}else{
			return null;
		}
		Query q = session.createSQLQuery(sql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> list = q.list();
		session.close();
		return list;
	}

}
