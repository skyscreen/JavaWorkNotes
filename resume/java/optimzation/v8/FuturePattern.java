package resume.java.optimzation.v8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FuturePattern {

	public static void main(String[] args) throws Exception {
		syncFuture();
		asyncFuture();
	}

	/*
	 * below methods are all for async future pattern
	 */

	public static int realLogic(int param) {
		return param * param;

	}

	public static void asyncFuture() throws Exception {

		final CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> realLogic(30)).exceptionally(ex -> {
			System.out.println(ex.toString());
			return 0;
		}).thenApply((i) -> (i) * (i)).thenApply((str) -> "async result is " + str).thenAccept(System.out::println);

		cf.get(); // means async to get

	}

	/*
	 * below methods are all for sync future pattern
	 */
	public static void syncFuture() throws Exception {

		final CompletableFuture<Integer> cf = new CompletableFuture<>();
		new Thread(new needFutureParam(cf)).start();
		// handle your logic for pass params
		Thread.sleep(1000);
		cf.complete(100);
	}

	public static class needFutureParam implements Runnable {
		CompletableFuture<Integer> cf = null;

		public needFutureParam(CompletableFuture<Integer> cf) {
			this.cf = cf;
		}

		@Override
		public void run() {
			int squareResult = -1;
			try {
				squareResult = this.cf.get() * this.cf.get();
			} catch (Exception e) {

				e.printStackTrace();
			}
			System.out.println("sync result is " + squareResult);
		}
	}
}
