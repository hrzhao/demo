package common.events
{
	import flash.events.Event;
	
	public class ClosingEvent extends Event
	{
		public static const CLOSING:String = "closing";
		public var index:int;
		public function ClosingEvent(type:String, index:int, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.index = index;
		}
		
		override public function clone():Event
		{
			return new ClosingEvent(type, index, bubbles, cancelable);
		}
	}
}