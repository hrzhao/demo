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
		private var _appFrame:IAppFrame;
		public function set appFrame(value:IAppFrame):void
		{
			this._appFrame = value;
		}
		
		public function get appFrame():IAppFrame
		{
			return this._appFrame;
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