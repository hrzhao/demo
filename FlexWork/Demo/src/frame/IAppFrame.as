package frame
{
	import entities.AppConfig;
	import entities.UserBean;
	
	import flash.utils.Dictionary;

	public interface IAppFrame
	{
		/**
		 *登录的用户信息 
		 * @return 
		 * 
		 */
		function get user() : UserBean;
		/**
		 * 选择工作台的分组，如果int为int.max则为选择最后一项
		 * @return 
		 * 
		 */
		function set selectedTabIndex(value:int):void
		/**
		 * 当前选择的模块
		 * @return 
		 * 
		 */
		function get selectedModule():IModule
		/**
		 * 弹出登录窗口
		 * @return 
		 * 
		 */
		function popupLoginPanel():void
	}
}