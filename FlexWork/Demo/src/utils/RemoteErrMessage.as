package utils
{
	public class RemoteErrMessage
	{
		public static const def_level_normal : int = 0;
		public static const def_level_warnning : int = 1;
		public static const def_level_error : int = 2;
		
		public static const property_error_msg:String = "ErrMessage";
		public static const property_error_lvl:String = "ErrLevel";
		
		private var _errorLvl:int = def_level_normal;	// 0: Normal
		private var _errorMsg:String = "";
		
		public static function showRemoteErrMessage(errMsg:Object):Boolean
		{
			var showInfo:Boolean = false;
			var rst : Boolean = false;
			
			var ret:RemoteErrMessage = new RemoteErrMessage();
			if (errMsg.hasOwnProperty(property_error_msg)
				&& errMsg[property_error_msg] != null)
			{
				ret._errorMsg = errMsg[property_error_msg].toString();
				showInfo = (ret._errorMsg != "");
			}
			if (showInfo
				&& errMsg.hasOwnProperty(property_error_lvl)
				&& errMsg[property_error_lvl] != null)
			{
				ret._errorLvl = int(errMsg[property_error_lvl]);
				if (ret._errorLvl == def_level_error)
				{
					MessageBox.show("后台错误\n"+"WEB后台处理错误，请查看相关详细信息。"+ret._errorMsg);
					rst = true;
				}
				else if (ret._errorLvl == def_level_warnning)
				{
					MessageBox.show("后台警告\n" + ret._errorMsg);
				}
				else if (ret._errorLvl == def_level_normal)
				{
					MessageBox.show("后台消息\n"+ ret._errorMsg);
				}
			}
			
			return rst;
		}
	}
}