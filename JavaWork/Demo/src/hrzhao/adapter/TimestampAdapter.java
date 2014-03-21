package hrzhao.adapter;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampAdapter extends XmlAdapter<Long, Date> {

	@Override
	public Long marshal(Date v) throws Exception {
		// TODO Auto-generated method stub
		return v.getTime()/1000;
	}

	@Override
	public Date unmarshal(Long v) throws Exception {
		// TODO Auto-generated method stub
		return new Date(v*1000);
	}

	

}
