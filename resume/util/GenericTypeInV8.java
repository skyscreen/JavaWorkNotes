package resume.java.util;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

public class GenericTypeInV8 {

	public static void main(String[] args) {
		
		GenericTypeInV8 v = new GenericTypeInV8();
		
		Integer example = 1;
		// convert integer to string
		Supplier<String> s = example::toString;
		System.out.println(s.get());

		// get system functions
		Supplier<Long> f = System::currentTimeMillis;
		System.out.println(f.get());

		// compare 2 numbers
		Function<Integer, Integer> f1 = example::compareTo;
		System.out.println(f1.apply(1));

		
		//Function: has return value
		ClassHasReturn has = v.new ClassHasReturn();
		Function<A, B> reference1 = a -> has.someMethod(a);
		A a = v.new A();
		B b = reference1.apply(a);////invoke
		
		//Consumer: no return value
		ClassNoReturn hasno = v.new ClassNoReturn();
		Consumer<A> reference2 = c -> hasno.someMethod(c);
		reference2.accept(a); //invoke
		
		//Supplier: no args
		ClassNoArgs noargs = v.new ClassNoArgs();
		Supplier<B> reference3 = () -> noargs.noArgsMethod();//reference for class
		B b3 = reference3.get();  //invoke
		
		ClassJustAction justaction = v.new ClassJustAction();
		Runnable reference4 = () -> justaction.actionMethod();
		reference4.run(); //invoke
		
		
		//curring demo
		v.demo();
	}

	class A {
	};

	class B {
	};

	class ClassHasReturn {
		B someMethod(A a) {
			System.out.println("ClassHasReturn.someMethod called");

			B b = new B();
			return b;
		}
	}
	
	class ClassNoReturn {
		void someMethod(A a) {
			System.out.println("ClassNoReturn.someMethod called");
		}
	}
	
	class ClassNoArgs {
	    B noArgsMethod() {
	    	B b = new B();
	    	System.out.println("ClassNoArgs.noArgsMethod called");
	        return b;
	    }
	}
	
	class ClassJustAction {
	    void actionMethod() {
	    	System.out.println("ClassJustAction.actionMethod called");
	    }
	}
	
	/*
	 * Default arguments below as as currying:
	 */
	public class MaleProgrammer {
	    public void performTask(Task task, int durationHours, int womenNearby) {
	        // Perform given task, calculate and print profit
	        String output = "- I've been %s for %d hours.%n";
	        output += "  %d women nearby.%n";
	        output += "  I'm as valuable as $ %.2f.%n";
	        // Calculate initial profit that matches the whole task duration
	        double profit = task.getProfitPerHour() * durationHours;
	        // Now introduce negative factors (each women nearby
	        // implies a cumulative 10% profit reduction)
	        double totalProfit = DoubleStream.iterate(profit, p -> p * 0.90)
	                .limit(womenNearby + 1)
	                .min()
	                .orElse(profit);
	        System.out.printf(output, task.getAction(), durationHours, womenNearby, totalProfit);
	    }
	}
	
	public enum Task {
	    DEVELOP_SOFTWARE("developing software", 100.0),
	    GO_TO_TOILET("in the toilet", 10.0),
	    TALK_ABOUT_SUPERHEROS("talking about superheros", 1.0),
	    FALL_IN_LOVE("in love", -50.0);
	    private final String action;
	    private final double profitPerHour;
	    Task(String action, double profitPerHour) {
	        this.action = action;
	        this.profitPerHour = profitPerHour;
	    }
	    public String getAction() {
	        return action;
	    }
	    public double getProfitPerHour() {
	        return profitPerHour;
	    }
	}
	
	@FunctionalInterface
	public interface DefaultArguments<A, B, C> extends Function<A, Function<B, Consumer<C>>> {
	    default void invoke(A a, B b, C c) {
	        this.apply(a).apply(b).accept(c);
	    }
	    default DefaultArguments<A, B, C> defaultingFirst(A defaultFirst) {
	        return a -> b -> c -> this.invoke(Optional.ofNullable(a).orElse(defaultFirst), b, c);
	    }
	    default DefaultArguments<A, B, C> defaultingSecond(B defaultSecond) {
	        return a -> b -> c -> this.invoke(a, Optional.ofNullable(b).orElse(defaultSecond), c);
	    }
	    default DefaultArguments<A, B, C> defaultingThird(C defaultThird) {
	        return a -> b -> c -> this.invoke(a, b, Optional.ofNullable(c).orElse(defaultThird));
	    }
	}
	
    public void demo() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("DEFAULT ARGUMENTS");
        System.out.println("-----------------------------------------");
        // Create a common male programmer
        MaleProgrammer maleProgrammer = new MaleProgrammer();
        // Create reference pointing to performTask method of maleProgrammer instance
        DefaultArguments<Task, Integer, Integer> reference = task -> duration -> women -> 
            maleProgrammer.performTask(task, duration, women);
        // Create new reference with default arguments
        // As our male programmer has just seen a superhero movie, his defaults will be:
        // - 1st argument:          task = talk about superheros
        // - 2nd argument: durationHours = 4
        // - 3rd argument:   womenNearby = 0
        DefaultArguments<Task, Integer, Integer> referenceAfterMovie = reference
            .defaultingFirst(Task.TALK_ABOUT_SUPERHEROS)
            .defaultingSecond(4)
            .defaultingThird(0);
        // Invoke referenceAfterMovie, overriding 1st argument only
        referenceAfterMovie.invoke(Task.DEVELOP_SOFTWARE, null, null);
        // Invoke referenceAfterMovie, overriding 2nd and 3rd arguments
        referenceAfterMovie.invoke(null, 2, 3);
        // Invoke referenceAfterMovie, defaulting all arguments
        referenceAfterMovie.invoke(null, null, null);
        System.out.println();
        // Create another reference with default arguments
        // Our male programmer is working hard now, but he sits next to a woman,
        // so his defaults will be:
        // - 1st argument:          task = develop software
        // - 2nd argument: durationHours = 8
        // - 3rd argument:   womenNearby = 1
        DefaultArguments<Task, Integer, Integer> hardWorkReference = reference
                .defaultingFirst(Task.DEVELOP_SOFTWARE)
                .defaultingSecond(8)
                .defaultingThird(1);
        // Invoke hardWorkReference, overriding 1st and 3rd arguments
        hardWorkReference.invoke(Task.FALL_IN_LOVE, null, 0);
        // Invoke hardWorkReference, overriding 2nd argument only
        hardWorkReference.invoke(null, 12, null);
        // Invoke hardWorkReference, defaulting all arguments
        hardWorkReference.invoke(null, null, null);
    }	
}
