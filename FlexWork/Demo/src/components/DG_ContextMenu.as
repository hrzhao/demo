package components
{
	import flash.display.DisplayObjectContainer;
	
	import mx.controls.Menu;
	import mx.controls.listClasses.IListItemRenderer;
	import mx.core.mx_internal;
	
	public class DG_ContextMenu extends Menu
	{
		public static const MENU_ADD:String = "增加";
		public static const MENUADD:int = 0x0001;
		public static const MENU_VIEW:String = "查看";
		public static const MENUVIEW:int = 0x0002;
		public static const MENU_EDIT:String = "修改";
		public static const MENUEDIT:int = 0x0004;
		public static const MENU_DELETE:String = "删除";
		public static const MENUDELETE:int = 0x0008;
		public function DG_ContextMenu()
		{
			super();
		}
		
		public static function createMenu(parent:DisplayObjectContainer,item:uint,showRoot:Boolean):Menu {
			var menuItem:Array = [];
			if((item & MENUADD) >0){
				menuItem.push({label:MENU_ADD,enabled:true});
			}else{
				menuItem.push({label:MENU_ADD,enabled:false});
			}
			if((item & MENUVIEW) >0){
				menuItem.push({label:MENU_VIEW,enabled:true});
			}else{
				menuItem.push({label:MENU_VIEW,enabled:false});
			}
			if((item & MENUEDIT) >0){
				menuItem.push({label:MENU_EDIT,enabled:true});
			}else{
				menuItem.push({label:MENU_EDIT,enabled:false});
			}
			if((item & MENUDELETE) >0){
				menuItem.push({label:MENU_DELETE,enabled:true});
			}else{
				menuItem.push({label:MENU_DELETE,enabled:false});
			}
			return Menu.createMenu(parent,menuItem,showRoot);
		}
	}
	
}