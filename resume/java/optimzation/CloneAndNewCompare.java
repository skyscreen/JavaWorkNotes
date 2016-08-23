package resume.java.optimzation;

/**
 * 'New' faster than 'Clone' for shallow copy without references in jdk8 Clone
 * time about 4809742 ns New time about 1637566 ns
 *
 * @author Sky Zhao
 */
public class CloneAndNewCompare {

	public static void main(String[] args) {
		final int maxLoops = 10 * 10000;
		int loops = 0;
		long start = System.nanoTime();
		Apple apple = new Apple();
		while (++loops < maxLoops) {
			apple.clone();
		}
		long mid = System.nanoTime();
		System.out.println("Clone time: " + (mid - start) + " ns");

		while (--loops > 0) {
			new Apple();
		}
		long end = System.nanoTime();
		System.out.println("New time: " + (end - mid) + " ns");
	}

	private static class Apple implements Cloneable {
		public Object clone() {
			try {
				return super.clone();
			} catch (Exception e) {
				throw new Error();
			}
		}
	}

}
