package resume.java.optimzation;

/**
 * Verify inner class singleton
 * average performance faster a bit than DCL
 *
 * @author Sky Zhao
 */
public class InnerClassForSingletonTest implements Runnable{

	 
	@Override
	public void run()  {
		for(int i=0;i<100000;i++){
			InnerClassForSingleton.getInstance();
		}
	}
	public static void main(String[] args) {
		checkSingleton();
		long beginTime = System.nanoTime();
		InnerClassForSingletonTest thread = new InnerClassForSingletonTest();
		new Thread(thread).start();
		System.out.println("singleton created time: " + (System.nanoTime() - beginTime) + " ns");

	}
	
	public static void checkSingleton(){
		InnerClassForSingleton first = InnerClassForSingleton.getInstance();
		InnerClassForSingleton second = InnerClassForSingleton.getInstance();
		if (first == second) {
			System.out.println("Singleton is correct");
		}
	}

}
