package resume.java.optimzation;

import java.util.Map;

/**
 * Verify parent thread passes child thread for multiple threads
 * 
 */
public class Cthread implements Runnable {

	protected Map<String, Object> resultMap;
	protected StringBuffer sb;

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public void setSB(StringBuffer sb) {
		this.sb = sb;
	}	

	@Override
	public void run() {
		Integer i=0;
	//	while (true) {
			i++;
			resultMap.put(i.toString(), "test");
			sb.append("again stringbuffer");
	//	}
	}
}
