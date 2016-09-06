package resume.java.util;

import java.io.Serializable;

public class A implements Serializable{
	public A clone(){
		return new A();
	}
}
