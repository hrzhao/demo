package frame
{
	import entities.AppConfig;
	
	import flash.utils.Dictionary;
	
	import mx.collections.ArrayCollection;
	import mx.containers.ViewStack;
	
	import spark.components.NavigatorContent;
	
	public class MenuViewStack extends ViewStack
	{
		private var _dataProvider:ArrayCollection = new ArrayCollection();
		private var dataProviderChanged:Boolean = false;
		public function MenuViewStack()
		{
			super();
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
		private function createMenu(parventContent:NavigatorContent,categoryId:String):void{
			for each(var appConfig:AppConfig in dataProvider){
				
			}
			
		}

		public function set dataProvider(value:ArrayCollection):void
		{
			dataProviderChanged = true;
			_dataProvider = value;
			invalidateProperties();
		}

	}
}