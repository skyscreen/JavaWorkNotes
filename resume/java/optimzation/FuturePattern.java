package resume.java.optimzation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Future Pattern with return results for multiple threads
 * 
 */
public class FuturePattern implements Callable<String> {

	private String para;

	public FuturePattern(String para) {
		this.para = para;
	}

	@Override
	public String call() throws Exception {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(para);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		// 构造FutureTask
		FutureTask<String> future = new FutureTask<String>(new FuturePattern("a"));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		// 执行FutureTask发送请求
		// 在这里开启线程进行call()执行
		executor.submit(future);
		System.out.println("请求完毕");
		try {
			// 这里依然可以做额外的数据操作，这里使用sleep代替其他业务逻辑的处理
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		// 相当于上例中得data.getContent()，取得call()方法的返回值
		// 如果此时call()方法没有执行完成，则依然会等待
		try {
			System.out.println("数据 = " + future.get());
		} catch (InterruptedException | ExecutionException e) {

			e.printStackTrace();
		}
	}
}
