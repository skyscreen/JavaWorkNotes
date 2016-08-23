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

		// ����FutureTask
		FutureTask<String> future = new FutureTask<String>(new FuturePattern("a"));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		// ִ��FutureTask��������
		// �����￪���߳̽���call()ִ��
		executor.submit(future);
		System.out.println("�������");
		try {
			// ������Ȼ��������������ݲ���������ʹ��sleep��������ҵ���߼��Ĵ���
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		// �൱�������е�data.getContent()��ȡ��call()�����ķ���ֵ
		// �����ʱcall()����û��ִ����ɣ�����Ȼ��ȴ�
		try {
			System.out.println("���� = " + future.get());
		} catch (InterruptedException | ExecutionException e) {

			e.printStackTrace();
		}
	}
}
