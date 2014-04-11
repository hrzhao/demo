package frame
{
	import mx.modules.IModuleInfo;
	import mx.modules.Module;
	
	import spark.components.NavigatorContent;
	
	public class MainNavigatorContent extends NavigatorContent
	{
		private var _appId:String;
		private var _module:IModule;
		private var _iconPath:String;
		public var moduleInfo:IModuleInfo;
		
		public function MainNavigatorContent()
		{
			super();
		}
		
		public function get appId():String
		{
			return _appId;
		}

		public function set appId(value:String):void
		{
			_appId = value;
		}

		public function get module():IModule
		{
			return _module;
		}

		public function set module(value:IModule):void
		{
			_module = value;
		}

		public function get iconPath():String
		{
			return _iconPath;
		}

		public function set iconPath(value:String):void
		{
			_iconPath = value;
		}


	}
}