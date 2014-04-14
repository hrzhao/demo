package mx.core
{
	import com.siloon.plugin.rightClick.RightClickManager;
	
	import flash.display.Sprite;
	
	import mx.utils.NameUtil;
	
	[Event(name="rightClick",type="flash.events.MouseEvent")]
	public dynamic class FlexSprite extends Sprite
	{
		private var rightClickRegisted:Boolean = false;

        public function FlexSprite()
        {
            if (!rightClickRegisted)
            {
                RightClickManager.regist();
                rightClickRegisted = true;
            }
            try
            {
                name = NameUtil.createUniqueName(this);
            }
            catch (e:Error)
            {
            }
            return;
        }

        public override function toString() : String
        {
            return NameUtil.displayObjectToString(this);
        }

	}
}