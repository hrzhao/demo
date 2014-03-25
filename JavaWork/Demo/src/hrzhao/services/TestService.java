package hrzhao.services;

import hrzhao.beans.TestMapBean;

public class TestService {

	public TestService() {
		// TODO Auto-generated constructor stub
	}
	public TestMapBean getTestBean(){
		TestMapBean tmBean = new TestMapBean();
		tmBean.setId(1);
		tmBean.setName("tmd");
		tmBean.setAddr("china");
		return tmBean;
	}

}
