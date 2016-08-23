package resume.java.optimzation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Verify parent thread passes child thread for multiple threads
 * 
 */
public class TestParentChiledThread {

	public static void main(String[] args) {

		// 测试传参到子线程，并获取值

		Pthread p = new Pthread(new Cthread());
		p.setResultMap();
		p.execute();
		Map<String, Object> resultMap = p.getResultMap();
		System.out.println("value changed1 " + p.resultMap.size());
		System.out.println("value changed2 " + p.sb.toString());

	}

}
