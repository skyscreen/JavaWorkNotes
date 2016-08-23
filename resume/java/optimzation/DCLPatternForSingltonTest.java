package resume.java.optimzation;

/**
 * Verify the singleton and test performance
 * 
 *
 * @author Sky Zhao
 */
public class DCLPatternForSingltonTest implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			DCLPatternForSinglton.getInstance();
		}
	}

	public static void main(String[] args) {
		checkSingleton();
		long beginTime = System.nanoTime();
		DCLPatternForSingltonTest thread = new DCLPatternForSingltonTest();
		new Thread(thread).start();
		System.out.println("singleton created time: " + (System.nanoTime() - beginTime) + " ns");

	}

	public static void checkSingleton() {
		DCLPatternForSinglton first = DCLPatternForSinglton.getInstance();
		DCLPatternForSinglton second = DCLPatternForSinglton.getInstance();
		if (first == second) {
			System.out.println("Singleton is correct");
		}
	}
}
