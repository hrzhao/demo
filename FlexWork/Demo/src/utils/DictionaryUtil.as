package utils
{
	import flash.utils.Dictionary;
	
	public class DictionaryUtil
	{
		static public function isEmpty(dic:Dictionary):Boolean
		{
			for (var key:Object in dic)
			{
				return false;
			}
			return true;
		}
		//清空数据字典
		static public function clearDictionary(dic:Dictionary):void
		{
			if(dic == null) return;
			for(var key:Object in dic)
			{       
				dic[key] = null;
				delete dic[key];
			}
		}
		
		static public function createByObject(o:Object):Dictionary
		{
			var rst:Dictionary = new Dictionary();
			if (o != null)
			{
				for (var k:* in o)
				{
					rst[k] = o[k];
				}
			}
			return rst;
		}
		
		//数据字典转换成为数组
		static public function Convert2Array(dic:Dictionary):Array
		{
			if(dic == null) return null;
			var arr:Array = new Array();
			for(var key:* in dic)
			{
				var obj:Object = new Object();
				obj[key] = dic[key];
				arr.push(obj);
			}
			return arr;
		}
		
		static public function length(dic:Dictionary):int
		{
			var rst:int=0;
			if (dic != null)
			{
				for each(var k:* in dic)
				{
					rst++;
				}
			}
			return rst;
		}
		
		/**
		 * Dictionary是否包含指定key
		 * @param str
		 * @param dic
		 * @return 
		 * 
		 */		
		static public function containsKey(str:Object, dic:Dictionary):Boolean
		{
			return !(dic == null || dic[str] === undefined);
		}
		
		//		//判断两个数据字典的内容是否星等 (待测试)
		//		static public function equals(dic1:Dictionary, dic2:Dictionary):Boolean
		//		{
		//			for(var key:* in dic1)
		//			{
		//				try
		//				{
		//					if(dic1[key] ! = dic2[key])
		//					{
		//						return false;
		//					}
		//				}
		//				catch()
		//				{
		//					return false;
		//				}
		//			}
		//			return true;
		//		}
	}
}