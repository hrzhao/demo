package events
{
	import entities.UserBean;
	
	import flash.events.Event;
	
	public class LoginEvent extends Event
	{
		public var user:UserBean;
		public function LoginEvent(type:String,user:UserBean=null, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.user = user;
		}
	}
}