package hrzhao.pcs;

import java.util.Calendar;
import java.util.Date;

import hrzhao.beans.ReqMessageBean;
import hrzhao.pcs.base.PcsBase;

public class PcsWeather extends PcsBase {

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		String content = msgBean.getContent();
		String reg="星期[一二三四五六日]{1}[\u4e00-\u9fa5]{0,10}";
		String day = null;
		String city = null;
		if(content.matches(reg)){
			day = content.substring(0,3);
			city = content.substring(3, content.length());
			if(day != null && city != null){
				
			}
		}
		return null;
		
	}
	private static String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	public String getWeekOfDate(String day) {
		int iday = 0;
		for(int i =0;i<weekDays.length;i++ ){
			if(weekDays[i] == day){
				iday = i;
				break;
			}
		}
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        
        return null;
       
	    }
//	private String checkFormat(String content){
//		String[] result = null;
//		if(src == null){
//			return result;
//		}
//		String reg="星期[一二三四五六日]{1}[0-9]{1,2}";
//		if(src.matches(reg)){
//			result = src.split("-");
//		}
//	}

	@Override
	public String getTips() {
		// TODO Auto-generated method stub
		return super.getTips();
	}

	
}
