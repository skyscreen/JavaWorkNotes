package resume.java.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public class CreateObjects {

	public static void main(String[] args) throws Exception {
		CreateObjects o = new CreateObjects();
		A a = new A();
		A b = A.class.newInstance();
			Constructor<A> constructor = A.class.getConstructor();
		A c = constructor.newInstance();
		A d = b.clone();
		
		FileOutputStream fs = new FileOutputStream("c:\\tmp\\t1.txt");  
		ObjectOutputStream os = new ObjectOutputStream(fs);  
		os.writeObject(a);
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("c:\\tmp\\t1.txt"));
		A e = (A) in.readObject();
		
		System.out.println("create object done");
	}
/*
	class A{
		public A clone(){
			return new A();
		}
	}
	*/
}
