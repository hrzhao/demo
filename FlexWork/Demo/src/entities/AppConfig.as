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
		public var multiInstance:Boolean;
		public var description:String;
		public var path:String;
		public var priority:int;
		public var icon:String;
		public var executePower:Boolean;
		
		public function AppConfig()
		{
		}
	}
}