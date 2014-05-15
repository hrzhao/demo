package utils
{
	import flash.display.DisplayObject;
	
	import mx.core.FlexGlobals;
	import mx.managers.PopUpManager;

	public class MessageBox
	{
		public function MessageBox()
		{
		}
		public static function show(message:String):void{
			var  msgWindow:MsgTitleWindow = new MsgTitleWindow();
			msgWindow.title = "提示"
			msgWindow.message = message;
			PopUpManager.addPopUp(msgWindow,DisplayObject(FlexGlobals.topLevelApplication),true);
			PopUpManager.centerPopUp(msgWindow);
		}
	}
}