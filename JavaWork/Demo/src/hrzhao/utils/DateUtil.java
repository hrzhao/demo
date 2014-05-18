package hrzhao.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String getMonthBegin(int month){
		if(month<0 || month >11)
			return null;
		DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(new Date());
		 cal.set(Calendar.MONTH, month);
		 cal.set(Calendar.DAY_OF_MONTH, 0);
		 cal.set(Calendar.HOUR_OF_DAY, 0);
		 cal.set(Calendar.MINUTE, 0);
		 cal.set(Calendar.SECOND, 0);
		return dateFormate.format(cal.getTime());
	}
	public static String getNextMonthBegin(int month){
		if(month<0 || month >11)
			return null;
		DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(new Date());
		 cal.set(Calendar.MONTH, month==11?1:month +1);
		 cal.set(Calendar.DAY_OF_MONTH, 0);
		 cal.set(Calendar.HOUR_OF_DAY, 0);
		 cal.set(Calendar.MINUTE, 0);
		 cal.set(Calendar.SECOND, 0);
		return dateFormate.format(cal.getTime());
		
	}


}
