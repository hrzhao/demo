package utils
{
	import mx.controls.advancedDataGridClasses.AdvancedDataGridColumn;
	
	import spark.formatters.DateTimeFormatter;

	/**
	 * 未使用
	 */
	public class DateUtil
	{
		private static var _formatter:DateTimeFormatter = null;
		public function DateUtil()
		{
			
		}
		public static function buildFormatter(dateTimePattern:String):DateTimeFormatter{
			var formatter:DateTimeFormatter = new DateTimeFormatter();
			formatter.dateTimePattern = dateTimePattern;
			return formatter;
		}
		
		public static  function dateformate(item:Object, column:AdvancedDataGridColumn):String  
		{
			return DateUtil.formatter.format(item[column.dataField]);
		}

		public static function get formatter():DateTimeFormatter
		{
			if(_formatter == null){
				_formatter = new DateTimeFormatter();
				_formatter.dateTimePattern="yyyy-MM-dd HH:mm:ss";
			}
			return _formatter;
		}

		public static function set formatter1(value:DateTimeFormatter):void
		{
			_formatter1 = value;
		}
		

	}
}