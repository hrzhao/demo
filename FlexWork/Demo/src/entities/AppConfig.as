package entities
{
	[RemoteClass(alias="hrzhao.beans.AppConfigBean")]
	public class AppConfig
	{
		public var appId:String;
		public var name:String;
		public var visible:Boolean;
		public var categoryId:String;
		public var param:String;
		public var mutliInstance:Boolean;
		public var description:String;
		
		public function AppConfig()
		{
		}
	}
}