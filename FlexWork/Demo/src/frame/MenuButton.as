package frame
{
	import skins.MenuButtonSkin;
	
	import spark.components.supportClasses.ToggleButtonBase;
	
	public class MenuButton extends ToggleButtonBase
	{
		public var appId:String;
		public function MenuButton()
		{
			super();
			this.buttonMode = true;
			this.setStyle("skinClass",skins.MenuButtonSkin);
		}
	}
}