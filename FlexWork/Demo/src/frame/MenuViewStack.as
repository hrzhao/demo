package frame
{
	import entities.AppConfig;
	
	import events.MenuClickEvent;
	
	import flash.events.MouseEvent;
	import flash.utils.Dictionary;
	
	import mx.collections.ArrayCollection;
	import mx.containers.ViewStack;

	[Event(name="menuClick", type="events.MenuClickEvent")]
	public class MenuViewStack extends ViewStack
	{
		private var _dataProvider:ArrayCollection = new ArrayCollection();
		private var dataProviderChanged:Boolean = false;
		private var menuButtonDic:Dictionary = new Dictionary();
		public function MenuViewStack()
		{
			super();
		}

		public function set selectedAppId(value:String):void{
			for each(var button:MenuButton in menuButtonDic){
				button.selected = false;
			}
			if(menuButtonDic != null && menuButtonDic.hasOwnProperty(value)){
				(menuButtonDic[value] as MenuButton).selected = true;
			}
			
		}
		public function get dataProvider():ArrayCollection
		{
			return _dataProvider;
		}
		
		override protected function commitProperties():void
		{
			// TODO Auto Generated method stub
			if(dataProviderChanged){
				createNavigator();
				dataProviderChanged = false;
			}
			super.commitProperties();
		}
		private var menuDic:Dictionary;
		private function createNavigator():void{
			menuDic = new Dictionary();
			this.removeAllElements();
			if(dataProvider != null){
				createMenu(null,null);
			}
			
		}
		private function createMenu(parventContent:MenuNavigatorContent,categoryId:String):void{
			for each(var appConfig:AppConfig in dataProvider){
				if(appConfig.categoryId == categoryId){
					if(appConfig.categoryId == null){
						var naviCont:MenuNavigatorContent = new MenuNavigatorContent();
						naviCont.left = 0;
						naviCont.right = 0;
						naviCont.top = 0;
						naviCont.bottom = 0;
						naviCont.label = appConfig.name;
						naviCont.initialize();
						this.addElement(naviCont);
						createMenu(naviCont,appConfig.appId);
					}else if(appConfig.visible && parventContent != null){
						var menuButton:MenuButton = new MenuButton();
						menuButton.label = appConfig.name;
						menuButton.setStyle("icon",appConfig.icon);
						menuButton.enabled = appConfig.executePower;
						menuButton.appId = appConfig.appId;
						menuButtonDic[appConfig.appId] = menuButton;
						menuButton.addEventListener(MouseEvent.CLICK, menuButtonClick, false, 0, true);
						parventContent.addContentChild(menuButton);
					}
				}
			}
		}
		protected function menuButtonClick(event:MouseEvent):void{
//			var i:int = this.selectedIndex;
			var appId:String = (event.target as MenuButton).appId;
			this.dispatchEvent(new MenuClickEvent(MenuClickEvent.MENUCLICK,appId,false));
			
		}

		public function set dataProvider(value:ArrayCollection):void
		{
			dataProviderChanged = true;
			_dataProvider = value;
			invalidateProperties();
		}

	}
}