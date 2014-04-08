package layer
{
	import com.esri.ags.SpatialReference;
	import com.esri.ags.geometry.Extent;
	import com.esri.ags.geometry.MapPoint;
	import com.esri.ags.layers.TiledMapServiceLayer;
	import com.esri.ags.layers.supportClasses.LOD;
	import com.esri.ags.layers.supportClasses.TileInfo;
	
	import flash.net.URLRequest;
	import mx.resources.IResourceBundle;
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	/**
	 * GoogleMap图层封装，在线离线合一
	 * 
	 */
	public class WrapGoogleTilesMapServiceLayer extends TiledMapServiceLayer{        
		function WrapGoogleTilesMapServiceLayer(){                  
			buildTileInfo();   
			setLoaded(true);
			applyTextTipBoundle();
		}
		
		private function applyTextTipBoundle():void
		{
			var rb:IResourceBundle=resourceManager.getResourceBundle("en_US", "ESRIMessages") as IResourceBundle;
			rb.content["drawTooltipMappoint"]="点击鼠标添加点"; //Click to add a point
			rb.content["drawTooltipMultipointStart"]="点击鼠标开始添加"; //Click to start adding points
			rb.content["drawTooltipMultipointFinish"]="双击完成操作"; //Double-click to finish
			rb.content["drawTooltipPolylinePolygonStart"]="点击鼠标开始操作"; //Click to start drawing
			rb.content["drawTooltipPolygonContinueDrawing"]="点击鼠标继续绘制节点"; //Click to continue drawing
			rb.content["drawTooltipPolylinePolygonComplete"]="双击完成操作"; //Double-click to complete
			rb.content["drawTooltipOther"]="按下鼠标开始，放开后结束操作"; //"Press down to start and let go to finish";    
		}
		
		private var _offlineUrls:Array = [
			"/mtnoh/vt/gtile.ashx",
			"/mtnoh/vt/gtile.ashx"];
		/**
		 * 二维普通地图url
		 */
		private  var _baseUrls:Array = [
			"http://mt0.google.cn/vt/lyrs=m@225000000&hl=zh-CN&gl=cn&src=app&s=Galile",
			"http://mt1.google.cn/vt/lyrs=m@225000000&hl=zh-CN&gl=cn&src=app&s=Gali",
			"http://mt2.google.cn/vt/lyrs=m@225000000&hl=zh-CN&gl=cn&src=app&s=Ga",
			"http://mt3.google.cn/vt/lyrs=m@225000000&hl=zh-CN&gl=cn&src=app&s=Galileo"];
		
		
		/**
		 * 卫星地图地图要素Url
		 */
		private  var _satelliteFeatureUrls:Array = [
			"http://mt0.google.cn/vt/imgtp=png32&lyrs=h@225063023&hl=zh-CN&gl=cn&src=app&s=Gali",
			"http://mt1.google.cn/vt/imgtp=png32&lyrs=h@225063023&hl=zh-CN&gl=cn&src=app&s=Galile",
			"http://mt2.google.cn/vt/imgtp=png32&lyrs=h@225063023&hl=zh-CN&gl=cn&src=app&s=Ga",
			"http://mt3.google.cn/vt/imgtp=png32&lyrs=h@225063023&hl=zh-CN&gl=cn&src=app&s=Galileo"]
		
		/**
		 * 卫星地图的底图
		 */
		private var _satelliteUrls:Array = [
			"http://mt0.google.cn/vt/lyrs=s@132&hl=zh-CN&gl=cn&src=app&s=Ga",
			"http://mt1.google.cn/vt/lyrs=s@132&hl=zh-CN&gl=cn&src=app&s=Gal",
			"http://mt2.google.cn/vt/lyrs=s@132&hl=zh-CN&gl=cn&src=app&s=Gali",
			"http://mt3.google.cn/vt/lyrs=s@132&hl=zh-CN&gl=cn&src=app&s=Galile"]
		
		
		/**
		 * 设置显示卫星地图上的地图要素
		 * 
		 */
		public function setDisplaySatelliteFeature():void{
			//设置当前显示卫星地图上地图要素
			_mapType = 1;
			_baseUrls = _satelliteFeatureUrls;
			_googleOnlineSetelliteFeatureLayer = true;
		}
		private var _googleOnlineSetelliteFeatureLayer:Boolean = false;
		/**
		 * 地图显示方式
		 * 0:普通二维地图 1：卫星地图
		 */
		private var _mapType:int = 0;
		/**
		 * 地图离线模式
		 * true:离线模式（用缓存地图） false:在线Google浏览
		 * */
		private var _offlineMode:Boolean = true;
		
		/**
		 * 地图显示方式
		 * 0:普通二维地图 1：卫星地图
		 */
		public function get mapType():int{
			return _mapType;
		}
		
		private var _satellitFeatureLayer:WrapGoogleTilesMapServiceLayer = null;
		
		[Bindable]
		/**
		 * 地图显示方式
		 * 0:普通二维地图 1：卫星地图
		 */
		public function set mapType(value:int):void{
			_mapType = value;
			if(_mapType<0||_mapType>1){
				_mapType = 0;
			}
			freshSetModes();
			this.refresh();
		}
		[Bindable]
		public function set offlineUrlArray(urls:Array):void
		{
			this._offlineUrls = urls;
		}
		public function get offlineUrlArray():Array{
			return _offlineUrls;
		}
		private function freshSetModes():void
		{
			if(_offlineMode)
			{
				if(_satellitFeatureLayer!=null)
				{
					this.map.removeLayer(_satellitFeatureLayer);
					_satellitFeatureLayer = null;
				}
			}
			else
			{
				if(_satellitFeatureLayer!=null)
				{
					this.map.removeLayer(_satellitFeatureLayer);
					_satellitFeatureLayer = null;
				}
				
				if(_mapType==1){//如果是卫星地图，则还要显示其上的地理要素
					_satellitFeatureLayer = new WrapGoogleTilesMapServiceLayer();
					_satellitFeatureLayer.setDisplaySatelliteFeature();
					var currentLayerIndx:int = (this.map.layers as ArrayCollection).getItemIndex(this);
					this.map.addLayer(_satellitFeatureLayer,currentLayerIndx+1);
				}else if(_satellitFeatureLayer!=null){
					this.map.removeLayer(_satellitFeatureLayer);
					_satellitFeatureLayer = null;
				}   
			}
		}
		[Bindable]
		/**
		 * 设置地图离线模式
		 * true:离线模式（用缓存地图） false:在线Google浏览
		 */
		public function set offlineMode(value:Boolean):void
		{
			this._offlineMode = value;
			freshSetModes();
			this.refresh();
		}
		public function get offlineMode():Boolean{
			return _offlineMode;
		}
		
		private var _tileInfo:TileInfo = new TileInfo(); 
		override public function get tileInfo():TileInfo{
			return _tileInfo;
		}
		
		/**
		 * 分图范围
		 */
		override public function get fullExtent():Extent{
			return new Extent(-22041257.773878, -32673939.6727517, 22041257.773878, 20851350.0432886, new SpatialReference(102113));
		}
		
		/**
		 * 初使范围
		 */
		override public function get initialExtent():Extent    {
			return new Extent(-22041257.773878, -32673939.6727517, 22041257.773878, 20851350.0432886, new SpatialReference(102113));
		}
		
		/**
		 *空间参考
		 */
		override public function get spatialReference():SpatialReference{
			return new SpatialReference(102113);//空间参考
		}
		
		
		/**
		 * 得到切片url 
		 * @param level 显示级别
		 * @param row 行
		 * @param col 列
		 * @return 
		 * 
		 */        
		override protected function getTileURL(level:Number, row:Number, col:Number):URLRequest{    
			var url:String = null;
			if(_googleOnlineSetelliteFeatureLayer)
			{
				var num:int = (col + (2 * row)) % 4;
				url = _satelliteFeatureUrls[num];
				url = url+"&x="+col+"&y="+row+"&z="+level;
			}
			else
			{
				if(_offlineMode)
				{
					var tok:int =0;
					var ilevel:int = level;
					var irow:int = row;
					var icol:int = col;
					var type:int = 22;//普通
					if(_mapType==1)
					{
						type = 24;//卫星
					}
					var rem:int = (icol+ (8*irow)*5+ ilevel*(icol+39)+(type*(icol+2))) % 3;
					if (rem == 0)
					{
						tok = icol * irow - 283 + ilevel * 159 + icol * 13 + irow *23 + icol * ilevel - 15 + type*(ilevel+12) + type*(icol+391);
					}
					else if (rem == 1)
					{
						tok = (icol - 28301) * irow + (ilevel + icol) * 19 + (icol - 7) * 13 + irow *271 + ((icol + 13) * ilevel) + 11 + type*(irow+31)+type*(icol+221);
					}
					else
					{
						tok = (icol - 81) * (irow * 14) + (ilevel + 3 + icol) * 22 + (icol - 21) * 13 + (irow + icol) *215 + ((icol + 11) * ilevel) + 1642+type*(irow+icol*2+313);
					}
					if(_offlineUrls.length>0)
					{
						var nm:int = (icol + irow*2) % _offlineUrls.length;
						url = _offlineUrls[nm];
						url = url+"?t="+type+"&x="+col+"&y="+row+"&z="+level+ "&m="+tok;
					}
					else//Set a default
					{
						url = "http://localhost/mtnoh/vt/gtile.ashx+"+"?t="+type+"&x="+col+"&y="+row+"&z="+level+ "&m="+tok;
					}
				}
				else
				{
					var numx:int = (col + (2 * row)) % 4;
					if(_mapType==0){
						url = _baseUrls[numx];
					}else if(_mapType==1){
						url = _satelliteUrls[numx];
					}
					url = url+"&x="+col+"&y="+row+"&z="+level;
				}
			}
			return new URLRequest(url);
		}
		
		/**
		 *初使化切片信息
		 */
		private function buildTileInfo():void{
			_tileInfo.height=256;//切片大小
			_tileInfo.width=256;
			_tileInfo.origin=new MapPoint(-20037508.342787, 20037508.342787);
			_tileInfo.spatialReference=new SpatialReference(102113);
			
			_tileInfo.lods = [  //比例尺 ：比例级别，切片分辨雍，比例尺
				/*new LOD(0, 156543.03392800014, 591657527.591555),
				new LOD(1, 78271.516963999937, 295828763.79577702),
				new LOD(2, 39135.758482000092, 147914381.89788899),
				new LOD(3, 19567.879240999919, 73957190.948944002),//*/
				new LOD(4, 9783.9396204999593, 36978595.474472001),
				new LOD(5, 4891.9698102499797, 18489297.737236001),
				new LOD(6, 2445.9849051249898, 9244648.8686180003),
				new LOD(7, 1222.9924525624949, 4622324.4343090001),
				new LOD(8, 611.49622628138, 2311162.217155),
				new LOD(9, 305.748113140558, 1155581.108577),
				new LOD(10, 152.874056570411, 577790.554289),
				new LOD(11, 76.4370282850732, 288895.277144),
				new LOD(12, 38.2185141425366, 144447.638572),
				new LOD(13, 19.1092570712683, 72223.819286),
				new LOD(14, 9.55462853563415, 36111.909643),
				new LOD(15,4.7773142679493699, 18055.954822),
				new LOD(16, 2.3886571339746849, 9027.9774109999998),
				new LOD(17, 1.1943285668550503, 4513.9887049999998)/*,
				new LOD(18, 0.59716428355981721, 2256.994353),
				new LOD(19, 0.29858214164761665, 1128.4971760000001)//*/
			]; 
			
		}
	}
}