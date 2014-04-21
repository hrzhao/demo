package hrzhao.process.base;

import hrzhao.process.PcsHomePage;
import hrzhao.process.PcsSaveName;

public class ProcessFactory {

	public ProcessFactory() {
	}
	public static ProcessInterface createProcess(int processId){
		ProcessInterface pcs = null;
		switch(processId)
		{
		case 0:
			break;
		case 1:
			pcs = new PcsHomePage();
			break;
		case 201://姓名
			pcs = new PcsSaveName();
			break;
		case 202://电话
			pcs = new PcsSaveName();
			break;
		case 203://住址
			pcs = new PcsSaveName();
			break;
		default :
			break;
		}
		if(pcs != null){
			pcs.setProcessId(processId);
		}
		return pcs;
	}

}
