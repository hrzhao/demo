package events
{
	import flash.events.Event;
	
	public class MenuClickEvent extends Event
	{
		public static const MENUCLICK:String = "menuClick";
		public var appId:String;
		public function MenuClickEvent(type:String, appId:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			this.appId = appId;
			super(type, bubbles, cancelable);
		}
		override public function clone():Event
		{
			return new MenuClickEvent(type, appId ,bubbles, cancelable);
		}
	}
}