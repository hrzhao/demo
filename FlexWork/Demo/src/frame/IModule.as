package frame
{
	public interface IModule
	{
		function get appId() : String;
		function set appId(value:String) : void;
		
		function set appFrame(value:IAppFrame):void;
		function get appFrame():IAppFrame;
		
		/**
		 *定义在数据库中的参数 
		 * @param value
		 * 
		 */
		function set param(value:Object):void;
	}
}