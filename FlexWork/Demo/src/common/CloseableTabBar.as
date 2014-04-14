package common
{
	import common.events.ClosingEvent;
	import common.skins.LockedTabBarButtonSkin;
	
	import flash.events.MouseEvent;
	
	import mx.controls.Alert;
	import mx.core.ClassFactory;
	import mx.core.IVisualElement;
	import mx.events.CloseEvent;
	
	import spark.components.IItemRenderer;
	import spark.components.TabBar;
	import spark.events.RendererExistenceEvent;
	
	[Event(name="close", type="mx.events.CloseEvent")]
	[Event(name="closing", type="common.events.ClosingEvent")]
	public class CloseableTabBar extends TabBar
	{
		public function CloseableTabBar()
		{
			super();
			this.itemRenderer = new ClassFactory(CloseableTabBarButton); 
		}
		
		private var _lockedTabCount:int =0;
		public function set lockedTabCount(value:int):void
		{
			_lockedTabCount = value;
		}
		
		public function get lockedTabCount():int
		{
			return _lockedTabCount;
		}
		
		/**
		 *  @private
		 */
		override protected function dataGroup_rendererAddHandler(event:RendererExistenceEvent):void
		{
			super.dataGroup_rendererAddHandler(event);
			
			const renderer:IVisualElement = event.renderer; 
			if (renderer)
			{
				if(event.index < this._lockedTabCount && renderer is CloseableTabBarButton)
				{
					(renderer as CloseableTabBarButton).setStyle("skinClass",Class(LockedTabBarButtonSkin));
				}
				renderer.addEventListener(MouseEvent.MOUSE_OVER, item_mouseOverHandler);
				renderer.addEventListener(MouseEvent.MOUSE_OUT, item_mouseOutHandler);
				renderer.addEventListener(CloseEvent.CLOSE, item_mouseCloseHandler);
			}
		}
		
		/**
		 *  @private
		 */
		override protected function dataGroup_rendererRemoveHandler(event:RendererExistenceEvent):void
		{   
			super.dataGroup_rendererRemoveHandler(event);
			
			const renderer:IVisualElement = event.renderer;
			if (renderer)
			{
				renderer.removeEventListener(MouseEvent.MOUSE_OVER, item_mouseOverHandler);
				renderer.removeEventListener(MouseEvent.MOUSE_OUT, item_mouseOutHandler);
				renderer.removeEventListener(CloseEvent.CLOSE, item_mouseCloseHandler);
			}
		}
		
		protected function item_mouseOverHandler(event:MouseEvent):void
		{
			var index:int;
			if (event.currentTarget is IItemRenderer)
				index = IItemRenderer(event.currentTarget).itemIndex;
			if(index >= lockedTabCount)
			{
				var button:CloseableTabBarButton = CloseableTabBarButton(event.currentTarget);
				if(button && button.closeImg)
				{
					button.closeImg.visible = true;
				}
			}
		}
		
		protected function item_mouseOutHandler(event:MouseEvent):void
		{
			var index:int;
			var button:CloseableTabBarButton = CloseableTabBarButton(event.currentTarget);
			if(button && button.closeImg)
			{
				button.closeImg.visible = false;
			}
		}
		
		public var cancle:Boolean = false;
		protected function item_mouseCloseHandler(event:CloseEvent):void
		{
			var index:int;
			if (event.currentTarget is IItemRenderer)
				index = IItemRenderer(event.currentTarget).itemIndex;
			cancle = false;
			if(hasEventListener(ClosingEvent.CLOSING))
				dispatchEvent(new ClosingEvent(ClosingEvent.CLOSING, index, false, false));
			else
				closeTabbar(index);
		}
		
		public function closeTabbar(index:int):void
		{
			if(dataProvider && index >= 0 && index < dataProvider.length)
			{
				dataProvider.removeItemAt(index);
				dispatchEvent(new CloseEvent(CloseEvent.CLOSE, false, false, index));
			}
		}
	}
}