package utils
{
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.net.NetConnection;
	import flash.net.URLRequest;
	
	import frame.IAppFrame;
	
	import mx.controls.Alert;
	import mx.core.IContainer;
	import mx.core.IVisualElement;
	import mx.core.IVisualElementContainer;
	import mx.events.CloseEvent;
	import mx.managers.SystemManager;
	import mx.messaging.Channel;
	import mx.messaging.ChannelSet;
	import mx.rpc.AbstractOperation;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	import spark.components.Application;
	
	public class RemoteObjectExt extends RemoteObject
	{
		public static const destination:String = "helloworld";
		public function RemoteObjectExt(source:String, destination:String=null)
		{
			if(destination)
				this.destination = destination;
			super(this.destination);
			this.source = source;
		}
		
		/**
		 * 是否取消，取消后接后函数不返回任何信息
		 */
		public var cancle:Boolean = false;
		
		/**
		 * 调用后台的方法
		 * @param methodName 调用的后台方法名
		 * @param resultCallback 接收到信息调用的方法
		 * @param faultCallback 发生错误时调用的方法
		 * @param args 参数，可多个
		 * 
		 */
		public function call(methodName:String, resultCallback:Function, faultCallback:Function, ...args):void
		{
			callByArray(methodName, resultCallback, faultCallback, args);
		}
		
		private function callByArray(methodName:String, resultCallback:Function, faultCallback:Function, args:Array):void
		{
			var method:AbstractOperation = getOperation(methodName); 
			method.arguments = args;
			var call:AsyncToken = method.send();
			call.methodName = methodName;
			call.resultCallback = resultCallback;
			call.faultCallback = faultCallback;
			call.arguments = args;
			call.addResponder(new Responder(resultCallbackHandler, faultCallbackHandler));
		}
		
		protected function resultCallbackHandler(event:ResultEvent):void
		{
			if(!cancle)
			{
				var resultCallback:Function = event.token.resultCallback as Function;
				
				var retObj:Object = event.result;
				if (retObj != null && retObj.hasOwnProperty("OrigResult"))
				{
					// 检查结果
					if (retObj.hasOwnProperty(RemoteErrMessage.property_error_msg))
					{
						RemoteErrMessage.showRemoteErrMessage(retObj);
					}
					retObj = event.result.OrigResult;
				}
				
				if (resultCallback != null) 
					resultCallback(retObj);
			}
			else if(event.cancelable && cancle)
			{
				event.preventDefault();
				cancle = false;
			}
		}
		
		private var appFrame:IAppFrame;
		private static var wattingCallArray:Array=[];
		private static var islogining:Boolean = false;
		protected function faultCallbackHandler(event:FaultEvent):void 
		{
			if(!cancle)
			{
				var faltCallback:Function = event.token.faultCallback as Function;   
				if(event.fault.faultCode == "Client.Authorization" || event.fault.faultCode == "Client.Authentication" 
					|| event.fault.faultCode == "Channel.Call.Failed" || event.fault.faultCode == "Client.Error.MessageSend")
				{
					appFrame = getAppFrame();
					if(appFrame)
					{
						wattingCallArray.push({"remoteObject":this, "methodName":event.token.methodName, "resultCallback":event.token.resultCallback,
							"faultCallback":event.token.faultCallback, "argument":event.token.arguments});
						reLogin();
					}
					else
					{//没有找到IFrame时刷新页面
						Alert.show("请重新登陆！", "登录超时", Alert.OK, null, logoutActionListner);
					}
				}
				else if(faltCallback != null)
				{
					faltCallback(event);
				}
				else
				{
					MessageBox.show("获取数据时发生错误\n"+
						"调用方法" + event.token.methodName +"时发生错误。\n"+
						"\n[faultCode]: " + event.fault.faultCode + "\n[faultString]: " + event.fault.faultString
						+ "\n[faultDetail]: " + event.fault.faultDetail);
				}
			}
			else if(event.cancelable && cancle)
			{
				event.preventDefault();
				cancle = false;
			}
		}
		
		private static var isNologin:Boolean = false;
		
		private function reLogin():void
		{
			if(!islogining) //不是正在自动登录中...
			{
				if(isNologin) //但是如果是还没登录成功的状态
				{
					appFrame.popupLoginPanel(); //登录超时处理
					appFrame.addEventListener("LoginSuccess", loginSuccess, false, 0, true);
				}
				else
				{
					islogining = true;
					if (this.channelSet)
					{
						var token:AsyncToken = this.channelSet.logout();
						token.addResponder(new AsyncResponder(doLogout_ResultHandler, doLogout_ResultHandler));
					}
					else
					{
						doLogout_ResultHandler.apply();
					}
				}
			}
		}
		
		private function doLogout_ResultHandler(event:Event, token:Object=null):void
		{
			if(appFrame)
			{
				var user:User = appFrame.user;
				if(this.channelSet.authenticated)
				{
					loginSuccess(event);
				}
				else
				{
					var token1:AsyncToken = this.channelSet.login(user.userName, user.password, "UTF-8");
					token1.addResponder(new AsyncResponder(LoginResultEvent, onLoginFault));
				}
			}
		}
		
		private function LoginResultEvent(event:ResultEvent, token:Object=null):void
		{
			switch(event.result) {
				case "success":
					loginSuccess(event);
					break;
				default:
					onLoginFault(null);
					break;
			}
		}
		
		private function loginSuccess(event:Event):void
		{
			var n:int = wattingCallArray.length;
			for(var i:int=0; i<n; i++)
			{
				var obj:Object = wattingCallArray[i];
				callFunction(obj.remoteObject, obj.methodName, obj.resultCallback, obj.faultCallback, obj.argument);
			}
			wattingCallArray.splice(0);
			islogining = false;
			isNologin = false;
			curCount = 0;
		}
		
		private function callFunction(remoteObj:RemoteObjectExt, funcName:String, resultCall:Function, faultCall:Function, argument:Array):void
		{
			if(argument && argument.length != 0)
				remoteObj.callByArray(funcName, resultCall, faultCall, argument);
			else
				remoteObj.call(funcName, resultCall, faultCall);
		}
		
		
		private function getAppFrame():IAppFrame
		{
			var sysMng:SystemManager = SystemManager.getSWFRoot(this) as SystemManager;
			if(sysMng != null)
			{
				var app:IVisualElementContainer = sysMng.application as IVisualElementContainer;
				if(app != null)
				{
					var n:uint = app.numElements;
					for (var i:int=0; i<n; i++)
					{
						var ele:IVisualElement = app.getElementAt(i);
						if(ele is IAppFrame)
							return IAppFrame(ele);
					}
				}
			}
			return null;
		}
		
		private static function logoutActionListner(eventObj:CloseEvent):void {
			flash.net.navigateToURL(new URLRequest("javascript:location.reload();"), "_self");
		}
	}
}

/**
 *
 package MTNOH.AppFx.utils
{
	import MTNOH.AppFx.Frame.IAppFrame;
	import MTNOH.AppFx.Frame.User;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.net.NetConnection;
	import flash.net.URLRequest;
	
	import mx.controls.Alert;
	import mx.core.IContainer;
	import mx.core.IVisualElement;
	import mx.core.IVisualElementContainer;
	import mx.events.CloseEvent;
	import mx.managers.SystemManager;
	import mx.messaging.Channel;
	import mx.messaging.ChannelSet;
	import mx.rpc.AbstractOperation;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	import spark.components.Application;
	
	public class RemoteObjectExt extends RemoteObject
	{
		public static const FLUORINE:String = "fluorine";
		public function RemoteObjectExt(source:String, destination:String=null)
		{
			if(!destination)
				destination = FLUORINE;
			super(destination);
			this.source = source;
		}
		
		public var cancle:Boolean = false;

		public function call(methodName:String, resultCallback:Function, faultCallback:Function, ...args):void
		{
			callByArray(methodName, resultCallback, faultCallback, args);
		}
		
		private function callByArray(methodName:String, resultCallback:Function, faultCallback:Function, args:Array):void
		{
			var method:AbstractOperation = getOperation(methodName); 
			method.arguments = args;
			var call:AsyncToken = method.send();
			call.methodName = methodName;
			call.resultCallback = resultCallback;
			call.faultCallback = faultCallback;
			call.arguments = args;
			call.addResponder(new Responder(resultCallbackHandler, faultCallbackHandler));
		}
			
		protected function resultCallbackHandler(event:ResultEvent):void
		{
			if(!cancle)
			{
				var resultCallback:Function = event.token.resultCallback as Function;
				
				var retObj:Object = event.result;
				if (retObj != null && retObj.hasOwnProperty("OrigResult"))
				{
					// 检查结果
					if (retObj.hasOwnProperty(RemoteErrMessage.property_error_msg))
					{
						RemoteErrMessage.showRemoteErrMessage(retObj);
					}
					retObj = event.result.OrigResult;
				}
				
				if (resultCallback != null) 
					resultCallback(retObj);
			}
			else if(event.cancelable && cancle)
			{
				event.preventDefault();
				cancle = false;
			}
		}
		
		private var appFrame:IAppFrame;
		private static var wattingCallArray:Array=[];
		private static var islogining:Boolean = false;
		protected function faultCallbackHandler(event:FaultEvent):void 
		{
			if(!cancle)
			{
				var faltCallback:Function = event.token.faultCallback as Function;   
				if(event.fault.faultCode == "Client.Authorization" || event.fault.faultCode == "Client.Authentication" 
					|| event.fault.faultCode == "Channel.Call.Failed" || event.fault.faultCode == "Client.Error.MessageSend")
				{
					appFrame = getAppFrame();
					if(appFrame)
					{
						wattingCallArray.push({"remoteObject":this, "methodName":event.token.methodName, "resultCallback":event.token.resultCallback,
							"faultCallback":event.token.faultCallback, "argument":event.token.arguments});
						reLogin();
					}
					else
					{//没有找到IFrame时刷新页面
						Alert.show("请重新登陆！", "登录超时", Alert.OK, null, logoutActionListner);
					}
				}
				else if(faltCallback != null)
				{
					faltCallback(event);
				}
				else
				{
					MessageBox.error("获取数据时发生错误",
						"调用方法" + event.token.methodName +"时发生错误。",
						"\n[faultCode]: " + event.fault.faultCode + "\n[faultString]: " + event.fault.faultString
						+ "\n[faultDetail]: " + event.fault.faultDetail);
				}
			}
			else if(event.cancelable && cancle)
			{
				event.preventDefault();
				cancle = false;
			}
		}
		
		private function reLogin():void
		{
			if(!islogining) //不是正在自动登录中...
			{
				if(isNologin) //但是如果是还没登录成功的状态
				{
					appFrame.popupLoginPanel(); //登录超时处理
					appFrame.addEventListener("LoginSuccess", loginSuccess, false, 0, true);
				}
				else
				{
					islogining = true;
					if (this.channelSet)
					{
						var token:AsyncToken = this.channelSet.logout();
						token.addResponder(new AsyncResponder(doLogout_ResultHandler, doLogout_ResultHandler));
					}
					else
					{
						doLogout_ResultHandler.apply();
					}
				}
			}
		}
		
		private function doLogout_ResultHandler(event:Event, token:Object=null):void
		{
			if(appFrame)
			{
				var user:User = appFrame.user;
				if(this.channelSet.authenticated)
				{
					loginSuccess(event);
				}
				else
				{
					var token1:AsyncToken = this.channelSet.login(user.userName, user.password, "UTF-8");
					token1.addResponder(new AsyncResponder(LoginResultEvent, onLoginFault));
				}
			}
		}
		
		private function LoginResultEvent(event:ResultEvent, token:Object=null):void
		{
			switch(event.result) {
				case "success":
					loginSuccess(event);
					break;
				default:
					onLoginFault(null);
					break;
			}
		}

		private function loginSuccess(event:Event):void
		{
			var n:int = wattingCallArray.length;
			for(var i:int=0; i<n; i++)
			{
				var obj:Object = wattingCallArray[i];
				callFunction(obj.remoteObject, obj.methodName, obj.resultCallback, obj.faultCallback, obj.argument);
			}
			wattingCallArray.splice(0);
			islogining = false;
			isNologin = false;
			curCount = 0;
		}
		
		private function callFunction(remoteObj:RemoteObjectExt, funcName:String, resultCall:Function, faultCall:Function, argument:Array):void
		{
			if(argument && argument.length != 0)
				remoteObj.callByArray(funcName, resultCall, faultCall, argument);
			else
				remoteObj.call(funcName, resultCall, faultCall);
		}
		
		//自动登录失败后弹出登录窗口
		private static var isNologin:Boolean = false;
		private static var curCount:int = 0;
		private function onLoginFault(event:FaultEvent, token:Object=null):void
		{
			islogining = false;
			if(curCount < 3) //重试次数
			{
				curCount++;
				reLogin();
			}
			else
			{
				if(appFrame)
				{
					curCount = 0;
					isNologin = true;
					appFrame.popupLoginPanel();
					appFrame.addEventListener("LoginSuccess", loginSuccess, false, 0, true);
				}
			}
		}
		
		private function getAppFrame():IAppFrame
		{
			var sysMng:SystemManager = SystemManager.getSWFRoot(this) as SystemManager;
			if(sysMng != null)
			{
				var app:IVisualElementContainer = sysMng.application as IVisualElementContainer;
				if(app != null)
				{
					var n:uint = app.numElements;
					for (var i:int=0; i<n; i++)
					{
						var ele:IVisualElement = app.getElementAt(i);
						if(ele is IAppFrame)
							return IAppFrame(ele);
					}
				}
			}
			return null;
		}
		
		private static function logoutActionListner(eventObj:CloseEvent):void {
			flash.net.navigateToURL(new URLRequest("javascript:location.reload();"), "_self");
		}
	}
} 
 */