package hrzhao.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DataUtil {

	public DataUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<HashMap<String, Object>> listToMap(List<Object> list,String[] columns){
		if(columns == null || columns.length == 0 || list == null){
			return null;
		}
		List<HashMap<String, Object>> rsList = new ArrayList<HashMap<String, Object>>();
		Iterator<Object> it = list.iterator();
		while(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			HashMap<String, Object> item = new HashMap<String,Object>();
			for(int i = 0;i<columns.length;i++){
				item.put(columns[i], obj[i]);
			}
			rsList.add(item);
		}
		return rsList;
	}

}
