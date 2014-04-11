package common.events
{
	import flash.events.Event;
	
	public class CustomEvent extends Event
	{
		public var data:Object;
		public function CustomEvent(type:String, param:Object = null, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type);
			this.data = param;
		}
	}
}