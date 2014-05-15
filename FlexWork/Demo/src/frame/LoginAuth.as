package frame
{
	import entities.UserBean;
	
	import mx.controls.Alert;
	import mx.messaging.ChannelSet;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class LoginAuth
	{
		private var remoteObject:RemoteObject;
		
		public var onReceive_LogIn:Function;
		public var onErrorFunction:Function;
		
		public function LoginAuth()
		{
			remoteObject  = new RemoteObject("userService");
			remoteObject.addEventListener(ResultEvent.RESULT,DoCheckLogIn_ResultHandler,false,0,true);
			remoteObject.addEventListener(FaultEvent.FAULT, onFault,false,0,true);
			onReceive_LogIn = null;
			onErrorFunction = null;
		}
		//=====
		public function logout(resultFunc:Function, faltFunc:Function):void
		{
			if (remoteObject.channelSet)
			{
				var token:AsyncToken = remoteObject.channelSet.logout();
				token.addResponder(new AsyncResponder(resultFunc, faltFunc));
			}
			else
			{
				resultFunc(null);
			}
		}
		private static var channelSet:ChannelSet;
		private static var ro:RemoteObject = null;
		public static function Logout():void
		{
			if(channelSet){
				channelSet.logout();
			}
//			ro  = new RemoteObject("userService");
//			ro.addEventListener(ResultEvent.RESULT,logoutHandler,false,0,true);
//			ro.addEventListener(FaultEvent.FAULT, logoutHandler,false,0,true);
//			ro.checkUser("","");
//			var channelSet:ChannelSet = new ChannelSet(["my-amf"]);
		}
//		public static function logoutHandler(obj:Object):void{
//			ro.channelSet.logout();
//			ro = null;
//		}
		
		//====================
		public function login(uname:String, upwd:String):void
		{
			remoteObject.setCredentials(uname, upwd, "UTF-8");
			remoteObject.checkUser(uname,upwd);
		}
		
		private function DoCheckLogIn_ResultHandler(event:ResultEvent):void
		{
			channelSet = remoteObject.channelSet;//
			if(null != onReceive_LogIn)
				onReceive_LogIn(event.result);
		}
		
		//=========================
		private function onFault (event:FaultEvent):void
		{
			channelSet = remoteObject.channelSet;//
			if(onErrorFunction!=null)
				onErrorFunction(event);
			else
				Alert.show(event.fault.faultString, "Error");
		}
	}
}