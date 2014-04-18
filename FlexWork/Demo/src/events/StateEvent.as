package events
{
	import flash.events.Event;
	
	public class StateEvent extends Event
	{
		public static const WAITINGRESULT:String = "waitingResult";
		public static const GOTRESULT:String = "getResult";
		public function StateEvent(type:String, msg:String = null,bubbles:Boolean=false, cancelable:Boolean=false)
		{
			this.msg = msg;
			super(type, bubbles, cancelable);
		}
		public var msg:String;
		
		override public function clone():Event
		{
			// TODO Auto Generated method stub
			return new StateEvent(type,msg,bubbles,cancelable);
		}
		
		
		
	}
}