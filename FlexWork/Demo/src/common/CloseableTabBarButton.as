package common
{
	
	import common.skins.CloseableTabBarButtonSkin;
	
	import flash.events.MouseEvent;
	
	import mx.controls.Alert;
	import mx.events.CloseEvent;
	
	import spark.components.ButtonBarButton;
	import spark.components.Image;
	
	[Event(name="close", type="mx.events.CloseEvent")]
	public class CloseableTabBarButton extends ButtonBarButton
	{
		public function CloseableTabBarButton()
		{
			super();
			this.mouseChildren = true;
			this.setStyle("skinClass",Class(CloseableTabBarButtonSkin));
		}
		
		[SkinPart(required="true")]
		public var closeImg:Image;
		
		override protected function partAdded(partName:String, instance:Object):void
		{
			super.partAdded(partName, instance);
			
			if (instance == closeImg)
				closeImg.addEventListener(MouseEvent.CLICK, closeImg_clickHandler);
		}
		
		override protected function partRemoved(partName:String, instance:Object):void
		{
			super.partRemoved(partName, instance);
			
			if(instance == closeImg)
				closeImg.removeEventListener(MouseEvent.CLICK, closeImg_clickHandler);
		}
		
		protected function closeImg_clickHandler(event:MouseEvent):void
		{
			event.preventDefault();
			dispatchEvent(new CloseEvent(CloseEvent.CLOSE));
		}
	}
}