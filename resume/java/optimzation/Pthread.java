package resume.java.optimzation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Verify parent thread passes child thread for multiple threads
 * 
 */
public class Pthread {

	protected StringBuffer sb = new StringBuffer();
	protected Map<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
	protected Cthread c;
	protected Map<String, Thread> threadMap = new HashMap<String, Thread>();

	public Pthread(Cthread c) {
		this.c = c;

		threadMap.put("1", new Thread(c));
	}

	public void execute() {
		for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
			entry.getValue().start();
		}
		// new Thread(c).start();
		try {
			// 需要执行一段时间，否则子线程还没启动，父线程已运行完，无法打印，所以这里要进行sleep
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setResultMap() {
		c.setResultMap(resultMap);
		c.setSB(sb);

	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}
}
