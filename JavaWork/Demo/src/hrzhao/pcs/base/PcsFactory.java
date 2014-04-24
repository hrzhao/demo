package hrzhao.pcs.base;

import hrzhao.beans.ProcessBean;
import hrzhao.dao.ProcessBeanDao;
import hrzhao.utils.DebugHelper;

import java.util.Iterator;
import java.util.List;

public class PcsFactory {

	public PcsFactory() {
		// TODO Auto-generated constructor stub
	}
	public static PcsInterface createPcs(int processId){
		PcsInterface pcs = null;
		ProcessBeanDao pcsDao = new ProcessBeanDao();
		List<ProcessBean> processList = pcsDao.getProcessList();
		String className = "hrzhao.pcs.";
		Iterator<ProcessBean> it = processList.iterator();
		while(it.hasNext()){
			ProcessBean pcsBean = it.next();
			if(pcsBean.getId() == processId){
				className += pcsBean.getClassName();
				try {
					@SuppressWarnings("unchecked")
					Class<PcsInterface> clazz = (Class<PcsInterface>) Class.forName(className);
					pcs = clazz.newInstance();
					pcs.setProcessId(processId);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					DebugHelper.log("ProcessFactory",e.toString());
				}
				break;
			}
		}
		return pcs;
	}

}
