package hrzhao.process;

import java.util.Iterator;
import java.util.List;

import hrzhao.beans.MenuBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.MenuBeanDao;
import hrzhao.process.base.ProcessBase;
import hrzhao.process.base.ProcessResult;

public class PcsMenu extends ProcessBase {

	public PcsMenu() {
		// TODO Auto-generated constructor stub
	}

	private Boolean isfound= false;
	private int mappingId = -1;
	@Override
	protected ProcessResult doProcessExt(ReqMessageBean msgBean) {
		String msg = "";
		Integer selectedId = -1;
		isfound = false;
		try{
			selectedId = Integer.parseInt(msgBean.getContent());
		}catch(NumberFormatException e){
			//输入非数字
			msg += "请输入数字";
			return new ProcessResult(msg,true);
		}
		List<MenuBean> list = getMenuList();
		if(list != null){
			Iterator<MenuBean> it = list.iterator();
			while(it.hasNext()){
				MenuBean menuBean = it.next();
				if(selectedId == menuBean.getSelectItem()){
					isfound = true;
					mappingId = menuBean.getMappingId();
					msg += "["+ menuBean.getContent() +"]";
					break;
				}
			}
		}
		if(!isfound){
			msg += "此选项无效";
		}
		//isfound = false时，选择错误，重选
		return new ProcessResult(msg,true);
	}
	@Override
	public Integer updateNextProcessId(String fromUserName,Integer processId){
		if(isfound){
			processId = mappingId;
		}
		return super.updateNextProcessId(fromUserName,processId);
	}
	
	@Override 
	public String getTips(){
		String tips = null;
		String superTips = super.getTips();
		if(superTips != null){
			tips = superTips;
		}
		List<MenuBean> list = getMenuList();
		if(list != null && list.size()>0){
			Iterator<MenuBean> it = list.iterator();
			if(tips == null){
				tips = "";
			}
			while(it.hasNext()){
				MenuBean menuBean = it.next();
				tips += "\n" + menuBean.getSelectItem() + "、"+menuBean.getContent();
			}
		}
		return tips;
	}
	public List<MenuBean> getMenuList(){
		MenuBeanDao menuDao = new MenuBeanDao();
		List<MenuBean> list = menuDao.getMenuBeanList(getProcessId());
		return list;
	}
	

}
