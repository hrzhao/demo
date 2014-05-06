package events
{
	import components.DgcMenu;
	
	import flash.events.Event;

	public class DgcMenuEvent extends Event
	{
		public static const DGCMENUCLICK:String = "dgcMenuClick";
		public var selectedItem:Object;
		public var menuItem:Object;
		public var selectedIndex:int;
		public function DgcMenuEvent(type:String,menuItem:Object ,selectedItem:Object = null, selectedIndex:int = -2,bubbles:Boolean=false, cancelable:Boolean=false)
		{
			this.menuItem = menuItem
			this.selectedItem = selectedItem;
			this.selectedIndex = selectedIndex;
			super(type, bubbles, cancelable);
		}
		
		override public function clone():Event
		{
			// TODO Auto Generated method stub
//			return super.clone();
			return new DgcMenuEvent(type,menuItem,selectedItem,selectedIndex,bubbles,cancelable);
		}
		
		
	}
}