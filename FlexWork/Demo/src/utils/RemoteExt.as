package utils
{
	import entities.ResultObject;
	import entities.UserBean;
	
	import flash.events.Event;
	import flash.net.URLRequest;
	
	import frame.IAppFrame;
	import frame.LoginAuth;
	
	import mx.controls.Alert;
	import mx.core.FlexGlobals;
	import mx.core.IVisualElement;
	import mx.core.IVisualElementContainer;
	import mx.events.CloseEvent;
	import mx.managers.SystemManager;
	import mx.rpc.AbstractOperation;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	
	public class RemoteExt extends RemoteObject
	{
		public function RemoteExt(destination:String=null)
		{
			super(destination);
		}
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
			call.methodName = methodName;//
			call.resultCallback = resultCallback;//
			call.faultCallback = faultCallback;//
			call.arguments = args;//
			call.addResponder(new Responder(resultCallbackHandler, faultCallbackHandler));
		}
		protected function resultCallbackHandler(event:ResultEvent):void
		{
			if(!cancle)
			{
				var resultCallback:Function = event.token.resultCallback as Function;
				
				var retObj:Object = event.result;
				if (resultCallback != null) 
					resultCallback(retObj);
			}
			else if(event.cancelable && cancle)
			{
				event.preventDefault();
				cancle = false;
			}
		}
		protected function faultCallbackHandler(event:FaultEvent):void 
		{
			if(!cancle)
			{
				var faltCallback:Function = event.token.faultCallback as Function;   
				if(    event.fault.faultCode == "Client.Authorization" 
					|| event.fault.faultCode == "Client.Authentication" 
					|| event.fault.faultCode == "Channel.Call.Failed" 
					|| event.fault.faultCode == "Client.Error.MessageSend"
					|| event.fault.faultCode == "Channel.Authentication.Error"
				)
				{//登录出错
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
//					MessageBox.show(event.message.toString());//
				}
				else if(faltCallback != null)
				{
					faltCallback(event);
				}
				else
				{
					MessageBox.show("获取数据时发生错误"+
						"调用方法" + event.token.methodName +"时发生错误。"
						+"\n[faultCode]: " + event.fault.faultCode + "\n[faultString]: " + event.fault.faultString
						+ "\n[faultDetail]: " + event.fault.faultDetail);
				}
			}
			else if(event.cancelable && cancle)
			{
				event.preventDefault();
				cancle = false;
			}
		}
		private static var wattingCallArray:Array=[];
		private static var islogining:Boolean = false;//正在登录
//		private static var isNologin:Boolean = false;//状态为没有登录
		private static var curCount:int = 0;//自动登录次数
		private function reLogin():void
		{
			if(!islogining) //不是正在自动登录中...
			{
//				if(isNologin) //但是如果是还没登录成功的状态
//				{
//					logoutActionListner(null);
//				}else{
					islogining = true;
					if (this.channelSet){
						var token:AsyncToken = this.channelSet.logout();
						token.addResponder(new AsyncResponder(doLogout_ResultHandler, doLogout_ResultHandler));
					}else{
						doLogin();	
					}
//				}
			}
		}
		private function doLogout_ResultHandler(event:Event, token:Object=null):void
		{
			doLogin();
			
		}
		//byHRZhao
		private var loginAuth:LoginAuth = null;
		private function doLogin():void{
			if(appFrame)
			{
				var user:UserBean = appFrame.user;
				if(this.channelSet && this.channelSet.authenticated){
					loginSuccess();
				}else{
					if(loginAuth == null){
						loginAuth = new LoginAuth();
						loginAuth.onReceive_LogIn = LoginResultEvent;
						loginAuth.onErrorFunction = onLoginFault;
					}
					loginAuth.login(user.username, user.password);
				}
			}
		}
		
		private function LoginResultEvent(result:ResultObject):void
		{
			//登录完成了
			islogining = false;
			var data:Object = result.data;
			if(result.state == "success" && data != null){
				loginSuccess();
			}else{
				onLoginFault(null);
			}
		}
		private function onLoginFault(event:FaultEvent):void
		{
			islogining = false;
			if(curCount < 3) //重试次数
			{
				curCount++;
				reLogin();
			}
			else
			{//超过3次刷新
//				isNologin = true;
				Alert.show("请重新登陆！", "登录超时", Alert.OK, null, logoutActionListner);
			}
		}
		//成功后调用wattingCallArray
		private function loginSuccess():void
		{
			var n:int = wattingCallArray.length;
			for(var i:int=0; i<n; i++)
			{
				var obj:Object = wattingCallArray[i];
				callFunction(obj.remoteObject, obj.methodName, obj.resultCallback, obj.faultCallback, obj.argument);
			}
			wattingCallArray.splice(0);
			islogining = false;
//			isNologin = false;
			curCount = 0;
		}
		private function callFunction(remoteObj:RemoteExt, funcName:String, resultCall:Function, faultCall:Function, argument:Array):void
		{
			if(argument && argument.length != 0)
				remoteObj.callByArray(funcName, resultCall, faultCall, argument);
			else
				remoteObj.call(funcName, resultCall, faultCall);
		}
		
		
		private var  appFrame:IAppFrame = null;
		private function getAppFrame():IAppFrame
		{
			var app:IVisualElementContainer = FlexGlobals.topLevelApplication as IVisualElementContainer;
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
			return null;
		}
		private static function logoutActionListner(event:CloseEvent):void {
			flash.net.navigateToURL(new URLRequest("javascript:location.reload();"), "_self");
		}
	}
}