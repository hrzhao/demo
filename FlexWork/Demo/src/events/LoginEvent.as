package events
{
	import entities.User;
	
	import flash.events.Event;
	
	public class LoginEvent extends Event
	{
		public var user:User;
		public function LoginEvent(type:String,user:User=null, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.user = user;
		}
	}
}