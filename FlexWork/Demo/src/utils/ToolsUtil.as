package utils
{
	import flash.utils.getDefinitionByName;

	public class ToolsUtil
	{
		public function ToolsUtil()
		{
		}
		
		public static function getClassByName(name:String):Class
		{
			if(name)
			{
				try
				{
					return getDefinitionByName(name) as Class;
				}
				catch(err:Error) { }
			}
			return null;
		}
		
		public static function createInstance(className:String):Object
		{
			if(className)
			{
				try
				{
					var moduleClass:Class = getDefinitionByName(className) as Class;
					if(moduleClass != null)
					{
						return new moduleClass();
					}
				}
				catch(e:Error) { }
			}
			return null;
		}
		
		public static function jsonToObject(str:String):Object
		{
			var o:Object = null;
			if(str)
			{
				try
				{
					o = JSON.parse(str);
				}
				catch(error:Error)
				{
					var w:int =0;
				}
			}
			
			return o;
		}
	}
}