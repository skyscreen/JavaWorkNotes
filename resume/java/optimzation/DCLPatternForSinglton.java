package resume.java.optimzation;

/**
 * double check lock for multiple threads for good performance
 * 
 *
 * @author Sky Zhao
 */
public class DCLPatternForSinglton {

	// double check lock
	private volatile static DCLPatternForSinglton dclForSinglton;

	private DCLPatternForSinglton() {
		System.out.println("DCLPatternForSinglton is created");
	}

	public static DCLPatternForSinglton getInstance() {
		try {
			if (dclForSinglton != null) {
				// do nothing
			} else {
				synchronized (DCLPatternForSinglton.class) {
					if (dclForSinglton == null) {
						dclForSinglton = new DCLPatternForSinglton();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dclForSinglton;
	}

}
