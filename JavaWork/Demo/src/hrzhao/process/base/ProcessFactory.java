package hrzhao.process.base;

import java.util.Iterator;
import java.util.List;

import hrzhao.beans.ProcessBean;
import hrzhao.dao.ProcessBeanDao;
import hrzhao.utils.DebugHelper;

public class ProcessFactory {

	public ProcessFactory() {
	}
	public static ProcessInterface createProcess(int processId){
		ProcessInterface pcs = null;
		List<ProcessBean> processList = getProcessList();
		String className = "hrzhao.process.";
		Iterator<ProcessBean> it = processList.iterator();
		while(it.hasNext()){
			ProcessBean pcsBean = it.next();
			if(pcsBean.getId() == processId){
				className += pcsBean.getClassName();
				try {
					@SuppressWarnings("unchecked")
					Class<ProcessInterface> clazz = (Class<ProcessInterface>) Class.forName(className);
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
	public static List<ProcessBean> getProcessList(){
		ProcessBeanDao pcsDao = new ProcessBeanDao();
		List<ProcessBean> list = pcsDao.getProcessList();
		return list;
	}

}
