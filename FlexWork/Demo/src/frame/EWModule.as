package frame
{
	import spark.modules.Module;
	
	public class EWModule extends Module implements IModule
	{
		private var _appId:String;
		private var _param:Object;
		public function EWModule()
		{
			super();
			this.setStyle("backgroundColor",0xE9E9E9);//背景色
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
			_param = value;
		}
		public function get param():Object{
			return _param;
		}
		
	}
}