package hrzhao.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

public class WeatherUtil {

	public WeatherUtil() {
		// TODO Auto-generated constructor stub
	}
	public String getCityCodeByName(String name){
		Session session = HiberHelper.getSession();
		String sql = "Select * from weather_city where city like %"+ name + "%";
		Query q = session.createSQLQuery(sql);
		q.setMaxResults(1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> list = q.list();
		String code = null;
		if(list != null && list.size()>0){
//			code = list.get(0).get("code");
		}
		return "";
	}
	private String getRequest(String cityCode){
		String urlStr = "http://m.weather.com.cn/data/"+cityCode+".html";
		String result = "";
        BufferedReader in = null;
		 try {
	            URL url = new URL(urlStr);
	            URLConnection con = url.openConnection();
	            con.setDoOutput(true);
	            con.setRequestProperty("Pragma:", "no-cache");
	            con.setRequestProperty("Cache-Control", "no-cache");
	            con.setRequestProperty("Content-Type", "text/xml");
	            Map<String, List<String>> map = con.getHeaderFields();
	            // 遍历所有的响应头字段
	            for (String key : map.keySet()) {
	                System.out.println(key + "--->" + map.get(key));
	            }
	            // 定义 BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(
	            		con.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
                if (in != null) {
                    try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
	        }
		 return result;
	}

}
