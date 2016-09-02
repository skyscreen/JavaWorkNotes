package resume.java.optimzation.vm;

import java.util.Random;

/*
 * suggest use `-Xmixed` : running time=63072 ns
 * `-Xint` running time=177160 ns
 * `-Xcomp` running time=1495652 ns
 * `-Xmixed -XX:CompileThreshold=1000 -XX:+PrintCompilation -XX:+TieredCompilation`
 * Set Code Cache: -XX:ReservedCodeCacheSize=64m
 * 
 */
public class JITTest {

	public static void main(String[] args) throws InterruptedException {
		
			long startTime = System.nanoTime();
			for (int i = 0; i < 1000; i++) {
				justRun();
			}
			System.out.println("running time=" + (System.nanoTime() - startTime) + " ns");
			Thread.sleep(1000);

		
	}

	public static void justRun() {
		int a = 0, b = 0;
		Random r = new Random();
		int n = r.nextInt(100);
		// for random execution time
		for (int i = 0; i < n; i++) {
			b = a + b + n;
		}

	}

}
