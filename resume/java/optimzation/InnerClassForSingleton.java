package resume.java.optimzation;

/**
 * Singlton with inner class for multiple threads for good performance
 * 
 *
 * @author Sky Zhao
 */
public class InnerClassForSingleton {

	private InnerClassForSingleton(){
		System.out.println("InnerClassForSingleton is created");
	}
	
	private static class SingletonHolder{
		private static InnerClassForSingleton instance = new InnerClassForSingleton();
	}
	
	public static InnerClassForSingleton getInstance() {
		return SingletonHolder.instance;

	}

}
