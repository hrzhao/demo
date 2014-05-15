package entities
{
	[RemoteClass(alias="hrzhao.utils.ResultObject")]
	public class ResultObject
	{
		public var state:String;
		public var data:Object;
		public var message:String;
		public static var SUCCESS:String = "success";
		public static var FAIL:String = "fail";
		public function ResultObject()
		{
		}
	}
}