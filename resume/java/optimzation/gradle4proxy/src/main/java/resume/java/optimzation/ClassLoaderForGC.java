package resume.java.optimzation;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

/*
 * Customerize classloader when new class in CGLIB or javaassist
 * GC trigger when classloader is set null.
 * VM argument: -XX:PermSize=2M -XX:MaxPermSize=4M -XX:+PrintGCDetails
 */
public class ClassLoaderForGC {

	public static void main(String[] args) throws Exception {
//		createDynamicForJavaassist();
		createDynamicForClassName();

	}

	public class MyClassLoader extends ClassLoader {
	}

	public static void createDynamicForJavaassist() {
		try {
			ClassLoaderForGC cfg = new ClassLoaderForGC();
			MyClassLoader cl = cfg.new MyClassLoader();
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				CtClass c = ClassPool.getDefault().makeClass("Geym" + i);
				c.setSuperclass(ClassPool.getDefault().get("resume.java.optimzation.JavaBeanObject"));
				Class clz = c.toClass(cl, null);
				JavaBeanObject v = (JavaBeanObject) clz.newInstance();
				if (i % 10 == 0)
					cl = cfg.new MyClassLoader();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createDynamicForClassName() {
		try {
			ClassLoaderForGC cfg = new ClassLoaderForGC();
			MyClassLoader cl = cfg.new MyClassLoader();
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				cl.loadClass("resume.java.optimzation.JavaBeanObject");
				if (i % 10 == 0)
					cl = cfg.new MyClassLoader();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
