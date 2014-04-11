package common.UI
{
	//import flash.geom.*;
	 
	
	import common.events.CustomEvent;
	
	import flash.display.DisplayObject;
	import flash.display.GradientType;
	import flash.display.SpreadMethod;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.geom.*;
	
	import mx.containers.DividedBox;
	import mx.containers.DividerState;
	import mx.containers.dividedBoxClasses.*;
	import mx.controls.Button;
	import mx.core.mx_internal;
	import mx.events.DividerEvent;
	import mx.events.ResizeEvent;
	
	import spark.components.Image;
	
	use namespace mx_internal;
	
	[Style(name="barFillColors",type="Array",format="Color",inherit="no")]
	[Style(name="barBorderColor",type="uint",format="Color",inherit="no")]
	[Event(name="buttonClick", type="common.events.CustomEvent")]
	public class ExtendedDividedBox extends DividedBox
	{
						 
		private var fillType:String = GradientType.LINEAR;
		private var alphas:Array = [1,1];
		private var ratios:Array = [0,255];
		private var spreadMethod:String = SpreadMethod.PAD;
		
		//private var _button:Button; 
		
		private var mBoxDivider:Array = new Array();
		
		private var _barFillColors:Array;	
		
		private var _barBorderColor:uint;	
				
		[Embed(source="/assets/Arrow_Down.png")]
		private var Arrow_Down:Class;
		
		[Embed(source="/assets/Arrow_Up.png")]
		private var Arrow_Up:Class;
				
		[Embed(source="/assets/Arrow_Close.png")]
		private var Arrow_Close:Class;
		
		[Embed(source="/assets/Arrow_Open.png")]
		private var Arrow_Open:Class;
						
		[Embed(source="/assets/divMoveHor.gif")]
		private var bar_move_hori:Class;
		
		[Embed(source="/assets/divMoveVer.gif")]
		private var bar_move_vert:Class;
		
		private var _ButtonOnIndexs:Array;
		private var _showButton:Boolean=false;
		private var _isOverButton:Boolean;
		private var _hideLeftBottom:Boolean=true;
	 
		
		public function ExtendedDividedBox():void{
			super();
			this.setStyle("horizontalGap" ,5);
			this.setStyle("verticalGap" ,5);
		}
		
		public function get hideLeftBottom():Boolean
		{
			return _hideLeftBottom;
		}

		public function set hideLeftBottom(value:Boolean):void
		{
			_hideLeftBottom = value;
		}

		public function set ButtonOnIndexs(value:Array):void{
			_ButtonOnIndexs=value
		}
		
		public function get ButtonOnIndexs():Array{
			return _ButtonOnIndexs;	
		}
		
		public function set showButton(value:Boolean):void{
			_showButton=value
		}
		
		public function get showButton():Boolean{
			return _showButton;	
		}
		
		private var _frameVisible:Boolean=true;
	 	public function set frameVisible(value:Boolean):void{
			_frameVisible=value;
		}
		/**
		 *边框的可见性 
		 * @return 
		 * 
		 */		
		public function get frameVisible():Boolean{ 
			return _frameVisible;	 
		}
 

		/**
		 * don't allow dragging if over a button
		 * */		
		
		override mx_internal function startDividerDrag(divider:BoxDivider,trigger:MouseEvent):void{
			
		
			if(_showButton && _isOverButton){
				return;			
			}
	
			super.mx_internal::startDividerDrag(divider,trigger);
			
        }
        
        /**
		 * don't show splitter cursor when over a button
		 * */	
        override mx_internal function changeCursor(divider:BoxDivider):void{
			 
			if(_showButton && _isOverButton){
				return;			
			}
			
			super.mx_internal::changeCursor(divider);
			
		}
		
		private function verifyButtonIndex(value:int):Boolean{
			
			for(var i:int=0;i < _ButtonOnIndexs.length;i++) {
				if (value == _ButtonOnIndexs[i]){
					return true;
				}
			}
			
			return false;
			
		}		
		
		private var _button:Image;
		
		 
		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void{
			
			super.updateDisplayList(unscaledWidth, unscaledHeight);
			var divwidth:Number= getStyle("dividerThickness");
			if (divwidth<5){divwidth=5;}
			
			
			if (mBoxDivider.length == 0){
				
				for(var i:int=0;i < numDividers;i++) {
					var divbar:BoxDivider = getDividerAt(i); 
					divbar.addEventListener("resize",handleResize);
					mBoxDivider.push(divbar); 
					var _hasbutton:Boolean=true; 
					if (_ButtonOnIndexs){
						if (_ButtonOnIndexs.length !=0){
							_hasbutton= verifyButtonIndex(i)
						}
					}
												
					if(_showButton && _hasbutton){
						_button = new Image();
						_button.name = "SplitterImage" + i;
						_button.buttonMode=true; 
						_button.width = 80;
						 
						_button.height=divwidth;
						//_button.toggle =true;
						_button.setStyle("cornerRadius",getStyle("cornerRadius")); 
						divbar.setStyle("dividerSkin",null);					
						if (direction == "vertical"){
							_button.width = 80;
							_button.height=5;
							_button.x = (unscaledWidth/2) - (_button.width/2);
							if(_hideLeftBottom){
								_button.source=Arrow_Down;
								_button.toolTip="隐藏下边栏";
								//_button.setStyle("icon",Arrow_Down);  
							}
							else{ 
								_button.source=Arrow_Up;
								_button.toolTip="隐藏上边栏";
								//_button.setStyle("icon",Arrow_Up); 
							} 
						}
						else{
							_button.height = 80;
							_button.width= 5;
							_button.y = (unscaledHeight/2) - (_button.height/2);
							if(_hideLeftBottom){
								_button.source=Arrow_Close;
								_button.toolTip="隐藏左边栏";
								//_button.setStyle("icon",Arrow_Close); 
							}
							else{
								_button.source=Arrow_Open;
								_button.toolTip="隐藏右边栏";
								//_button.setStyle("icon",Arrow_Open); 
							}

						}
						_button.addEventListener(MouseEvent.CLICK, handleClick);
						_button.addEventListener(MouseEvent.MOUSE_OVER, handleOver);
						_button.addEventListener(MouseEvent.MOUSE_OUT, handleOut);
						divbar.addChild(_button);
					}
					else
					{
						_button = new Image();
						_button.name = "SplitterImage" + i;
						_button.buttonMode=true; 
						_button.width = 80;
						
						_button.height=divwidth;
						//_button.toggle =true;
						_button.setStyle("cornerRadius",getStyle("cornerRadius")); 
						divbar.setStyle("dividerSkin",null);					
						if (direction == "vertical"){
							_button.width = 80;
							_button.height=5;
							_button.x = (unscaledWidth/2) - (_button.width/2);
							_button.source=bar_move_vert;
						}
						else{
							_button.height = 80;
							_button.width= 5;
							_button.y = (unscaledHeight/2) - (_button.height/2);
							_button.source=bar_move_hori;
							
						}
						divbar.addChild(_button);
					}
					
				}
				
			}
			
			Draw_Gradient_Fill();
			
		}
		
		private function handleResize(event:ResizeEvent):void{
			
			if(!_showButton){return;}
			
			if (event.currentTarget.width != event.oldWidth || event.currentTarget.height != event.oldHeight){
			
				for(var i:int=0;i < numDividers;i++) {
					var divbar:BoxDivider = getDividerAt(i);
				
					var tempButton:Image = Image(divbar.getChildByName("SplitterImage" + i));
					
					if (tempButton){
						if (direction == "vertical"){
							tempButton.x = (unscaledWidth/2) - (tempButton.width/2);
						}
						else{
							tempButton.y = (unscaledHeight/2) - (tempButton.height/2);
						}
						
					}	
					
				}
			}
			
			
		}
				
		//event handlers for the button 
		private var preHeight:Number=0;
		private var preWidth:Number=0;
		private var preOtherHeight:Number=0;
		private var preOtherWidth:Number=0;
		private function handleClick(event:MouseEvent):void{
			showHideFrame();
			dispatchEvent(new CustomEvent("buttonClick",event.currentTarget)); 
		}
		/**
		 *是否显示边框 
		 * if(!***.frameVisible){***.showHideFrame();} 
		 */		
		public function showHideFrame():void{
			
			var displayObject:DisplayObject=null;
			var displayObjectOther:DisplayObject=null;
			if(this.getChildren().length==2){ 
				if(direction == "vertical"){ 
					if(_hideLeftBottom){ 
						displayObject=this.getChildAt(1); 
						displayObjectOther=this.getChildAt(0); 
						_button.toolTip=_button.toolTip=="隐藏下边栏"?"展开下边栏":"隐藏下边栏";
						
					}
					else{
						displayObject=this.getChildAt(0); 
						displayObjectOther=this.getChildAt(1); 
						_button.toolTip=_button.toolTip=="隐藏上边栏"?"展开上边栏":"隐藏上边栏";
					}
					//displayObject.visible=!displayObject.visible;  
					if(frameVisible&&preHeight==0){
						preHeight=displayObject.height;
					}
					if(preOtherHeight==0){
						preOtherHeight=displayObjectOther.height;
					}
					if(!frameVisible){
						displayObject.height=preHeight; 
						displayObject.visible = true;
					}
					else{
						displayObject.height=0;
						displayObject.visible = false;
					}
					if(frameVisible){
						displayObjectOther.height=preOtherHeight+preHeight;
					} 
					else{
						displayObjectOther.height=preOtherHeight;
					}
				}
				else{
					if(_hideLeftBottom){ 
						displayObject=this.getChildAt(0); 
						displayObjectOther=this.getChildAt(1); 
						_button.toolTip=_button.toolTip=="隐藏左边栏"?"展开左边栏":"隐藏左边栏";
					}
					else{
						displayObject=this.getChildAt(1); 
						displayObjectOther=this.getChildAt(0); 
						_button.toolTip=_button.toolTip=="隐藏右边栏"?"展开右边栏":"隐藏右边栏";
					}
					if(frameVisible&&preWidth==0){
						preWidth=displayObject.width;
					}
					if(preOtherWidth==0){
						preOtherWidth=displayObjectOther.width;
					}
					//displayObject.visible=!displayObject.visible; 
					//	displayObject.width==0?preWidth:0;  这样设置居然不起作用
					
					if(!frameVisible){
						displayObject.width=preWidth ;
						displayObject.visible = true;
					}
					else{
						displayObject.width=0; 
						displayObject.visible = false;
					}
					
					if(frameVisible){
						displayObjectOther.width=preOtherWidth+preWidth;
					} 
					else{
						displayObjectOther.width=preOtherWidth;
					}
				}
			}
			
			if(_button){
				var icon:Class=Class(_button.source);
				if(direction == "vertical"){
					if(Arrow_Up==icon){
						_button.source=Arrow_Down;
					}
					else{
						_button.source=Arrow_Up;
						//_button.setStyle("icon",Arrow_Up);
					}
				}
				else{
					if(Arrow_Close==icon){
						_button.source=Arrow_Open;
						//	_button.setStyle("icon",Arrow_Open);
					}
					else{
						_button.source=Arrow_Close;
						//_button.setStyle("icon",Arrow_Close);
					}
				}
			}  
			_frameVisible=!_frameVisible;
		}
	 
		private function handleOut(event:MouseEvent):void{
			_isOverButton=false;
		}
		private function handleOver(event:MouseEvent):void{
			_isOverButton=true;
		}
		
		override public function styleChanged(styleProp:String):void {

			super.styleChanged(styleProp);
 
            if (styleProp=="barFillColors" || styleProp=="barBorderColor") 
            {
            	_barBorderColor=0;
            	_barFillColors=null;
                invalidateDisplayList();
                return;
            } 
        } 		
		private function Draw_Gradient_Fill():void{
			
			graphics.clear();
				
			for(var i:int=0;i < mBoxDivider.length;i++) {
					
				if (!_barFillColors){
					_barFillColors = getStyle("barFillColors");
					if (!_barFillColors){
						_barFillColors =[0xEBF4FF,0xEBF4FF]; // 背景颜色
					}
				}
				
				if (!_barBorderColor){
					_barBorderColor = getStyle("barBorderColor");
					if (!_barBorderColor){
						_barBorderColor =0xEBF4FF; // 背景颜色
					}
				}
				
				graphics.lineStyle(1,_barBorderColor);
				
				var divwidth:Number = mBoxDivider[i].getStyle("dividerThickness");
				
				if (divwidth<5){divwidth=5;}
				
				var matr:Matrix = new Matrix();
				
				if (direction == "vertical"){
					matr.createGradientBox(mBoxDivider[i].width,divwidth,Math.PI/2, mBoxDivider[i].x, mBoxDivider[i].y);
					
					graphics.beginGradientFill(fillType, _barFillColors, alphas, ratios, matr,spreadMethod);
					graphics.drawRect(mBoxDivider[i].x,mBoxDivider[i].y,mBoxDivider[i].width,divwidth);
				}
				else{
					matr.createGradientBox(divwidth,mBoxDivider[i].height ,0, mBoxDivider[i].x, mBoxDivider[i].x+5);
					graphics.beginGradientFill(fillType, _barFillColors, alphas, ratios, matr,spreadMethod);
					graphics.drawRect(mBoxDivider[i].x,mBoxDivider[i].y,divwidth, mBoxDivider[i].height);
				}
				
								
			}			
		}
		
		/**
		 * vertical 垂直方向  horizontal 水平方向
		 */
		override public function set direction(value:String):void
		{
			super.direction = value;
		}
		
		
	}
}