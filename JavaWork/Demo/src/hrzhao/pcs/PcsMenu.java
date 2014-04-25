package hrzhao.pcs;

import java.util.Iterator;
import java.util.List;

import hrzhao.beans.MenuBean;
import hrzhao.beans.ReqMessageBean;
import hrzhao.dao.MenuBeanDao;
import hrzhao.pcs.base.PcsBase;

public class PcsMenu extends PcsBase {

	public PcsMenu() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doProcess(ReqMessageBean msgBean) {
		// TODO Auto-generated method stub
		String msg = "";
		Integer selectedId = -1;
		Boolean isfound = false;
		try{
			selectedId = Integer.parseInt(msgBean.getContent());
		}catch(NumberFormatException e){
			//输入非数字
			msg += "请输入数字";
			setNextProcessId(getProcessId());
			return msg;
		}
		MenuBeanDao menuDao = new MenuBeanDao();
		List<MenuBean> list = menuDao.getMenuBeanList(getProcessId());
		if(list != null){
			Iterator<MenuBean> it = list.iterator();
			while(it.hasNext()){
				MenuBean menuBean = it.next();
				if(selectedId == menuBean.getSelectItem()){
					isfound = true;
					setNextProcessId(menuBean.getMappingId());
					msg += "["+ menuBean.getContent() +"]";
					break;
				}
			}
		}
		if(!isfound){
			msg += "此选项无效";
			setNextProcessId(getProcessId());
		}
		return msg;
	}

	@Override
	public String getTips() {
		// TODO Auto-generated method stub
		String tips = super.getTips();
		MenuBeanDao menuDao = new MenuBeanDao();
		List<MenuBean> list = menuDao.getMenuBeanList(this.getProcessId());
		if(list != null && list.size()>0){
			Iterator<MenuBean> it = list.iterator();
			while(it.hasNext()){
				MenuBean menuBean = it.next();
				tips += "\n" + menuBean.getSelectItem() + "、"+menuBean.getContent();
			}
		}
		return tips;
	}
	

}
