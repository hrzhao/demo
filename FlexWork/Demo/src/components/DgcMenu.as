package components
{
	import flash.display.DisplayObjectContainer;
	
	import mx.controls.Menu;
	import mx.controls.listClasses.IListItemRenderer;
	import mx.core.mx_internal;
	
	public class DgcMenu extends Menu
	{
		public static const sADD:String = "增加";
		public static const ADD:int = 0x0001;
		public static const sVIEW:String = "查看";
		public static const VIEW:int = 0x0002;
		public static const sEDIT:String = "修改";
		public static const EDIT:int = 0x0004;
		public static const sDELETE:String = "删除";
		public static const DELETE:int = 0x0008;
		public function DgcMenu()
		{
			super();
		}
		
		public static function createMenu(parent:DisplayObjectContainer,item:uint,showRoot:Boolean):Menu {
			var menuItem:Array = [];
			if((item & ADD) >0){
				menuItem.push({label:sADD,enabled:true});
			}else{
				menuItem.push({label:sADD,enabled:false});
			}
			if((item & VIEW) >0){
				menuItem.push({label:sVIEW,enabled:true});
			}else{
				menuItem.push({label:sVIEW,enabled:false});
			}
			if((item & EDIT) >0){
				menuItem.push({label:sEDIT,enabled:true});
			}else{
				menuItem.push({label:sEDIT,enabled:false});
			}
			if((item & DELETE) >0){
				menuItem.push({label:sDELETE,enabled:true});
			}else{
				menuItem.push({label:sDELETE,enabled:false});
			}
			
			return Menu.createMenu(parent,menuItem,showRoot);
		}
	}
	
}