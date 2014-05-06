package hrzhao.services;

import hrzhao.dao.ComnMsgBeanDao;
import hrzhao.utils.ResultObject;

import java.util.Date;
import java.util.List;

public class ComnMsgService {

	public ComnMsgService() {
		// TODO Auto-generated constructor stub
	}
	//可能不用
	public ResultObject getComnMsgList(String customerName,Date beginTime,Date endTime,int  firstResult,int size){
		ComnMsgBeanDao comnMsgDao = new ComnMsgBeanDao();
		List <Object> list = comnMsgDao.getComnMsgList(customerName, beginTime, endTime, firstResult, size, null);
		return new ResultObject(ResultObject.SUCCESS,list);
	}
	
	public ResultObject getComnMsgList(String customerName,Date beginTime,Date endTime,int  firstResult,int size,String status,Boolean reverse){
		ComnMsgBeanDao comnMsgDao = new ComnMsgBeanDao();
		List <Object> list = comnMsgDao.getComnMsgList(customerName, beginTime, endTime, -1, -1 , status);
		if(reverse){
			if(list.size() > size ){
				firstResult = list.size() -size;
			}
		}
		list = comnMsgDao.getComnMsgList(customerName, beginTime, endTime, firstResult, size, status );
		return new ResultObject(ResultObject.SUCCESS,list);
	}
	
	public ResultObject sendMsg(String customerName,int userId,String content){
		ComnMsgBeanDao comnMsgDao = new ComnMsgBeanDao();
		Object obj = comnMsgDao.sendMsg(customerName, userId, content);
		return new ResultObject(ResultObject.SUCCESS,obj);
	}

}
