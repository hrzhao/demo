package hrzhao.services;

import java.util.List;

import hrzhao.dao.StatisticsDao;
import hrzhao.utils.ResultObject;

public class StatisticsService {
	public ResultObject getMonthStat(int month){
		StatisticsDao statDao = new StatisticsDao();
		List<?> list = statDao.getMonthStat(month);
		if(list == null){
			return new ResultObject(ResultObject.FAIL,list);
		}
		return new ResultObject(ResultObject.SUCCESS,list);
	}
}
