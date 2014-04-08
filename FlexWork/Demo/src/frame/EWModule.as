package frame
{
	import spark.modules.Module;
	
	public class EWModule extends Module implements IModule
	{
		private var _appId:String;
		public function EWModule()
		{
			super();
		}
		
		public function get appId():String
		{
			return _appId;
		}
		
		public function set appId(value:String):void
		{
			this._appId = value;
		}
		
		public function set appFrame(value:IAppFrame):void
		{
		}
		
		public function get appFrame():IAppFrame
		{
			return null;
		}
		
		public function set param(value:Object):void
		{
		}
		
		public function set callParam(value:Object):void
		{
		}
	}
}