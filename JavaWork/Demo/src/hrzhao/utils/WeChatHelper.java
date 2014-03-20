package hrzhao.utils;

import hrzhao.ConfigHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class WeChatHelper {

	public WeChatHelper() {
	}
	public static Boolean checkSafe(String timestamp,String nonce,String signature){
		ArrayList<String> arr = new ArrayList<String>();
		String token = ConfigHelper.getConfig("token");
		arr.add(token);
		arr.add(timestamp);
		arr.add(nonce);
		
		Collections.sort(arr);
		
		Iterator<String> it = arr.iterator();
		String result = "";
		while(it.hasNext()){
			result += it.next().toString();
		}
		if(result != null && signature != null){
			signature.toLowerCase();
			result=EncoderHandler.encodeBySHA1(result);
			if(signature.equals(result)){
				return true;
			}
		}
		return false;
	}

}
