package resume.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class FoldingInV8 {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>(Arrays.asList("mickey", "donald", "pluto"));
		List<String> namesUpper1 = reduce(names, String::toUpperCase);
		System.out.println("namesUpper1 " + namesUpper1);

		List<String> identity = new ArrayList<>(Arrays.asList("mickey", "donald", "pluto"));// as default 0 or empty 
		List<String> namesUpper2 = mapReduce(names, identity, FoldingInV8::add, String::toUpperCase);
		
		//consider names1 is empty
		List<String> names1 = new ArrayList<>();																					// it
		List<String> namesUpper3 = mapReduce(names1, identity, FoldingInV8::add, String::toUpperCase);
		System.out.println("namesUpper2 " + namesUpper2);
		System.out.println("namesUpper3 " + namesUpper3);
		System.out.println("identity " + identity);

		// new collector
		Collector<Integer, StringBuilder, String> stringCollector = new Collector<Integer, StringBuilder, String>() {
			@Override
			public Supplier<StringBuilder> supplier() {
				return StringBuilder::new;
			}

			@Override
			public BiConsumer<StringBuilder, Integer> accumulator() {
				return (sb, i) -> sb.append(sb.length() == 0 ? "" : ", ").append(i);
			}

			@Override
			public BinaryOperator<StringBuilder> combiner() {
				return StringBuilder::append;
			}

			@Override
			public Function<StringBuilder, String> finisher() {
				return sb -> sb.insert(0, '[').append(']').toString();
			}

			@Override
			public Set<Characteristics> characteristics() {
				// return Collections.emptySet();
				return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT));
			}
		};
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
		System.out.println(list.stream().collect(stringCollector));

		//more generic collector
		List<String> listStr = Arrays.asList("a", "b", "c", "d", "e", "f");
		System.out.println(list.stream().collect(toDelimitedString("[", ", ", "]")));
		System.out.println(listStr.stream().collect(toDelimitedString("[", ", ", "]")));

	}

	// example 1
	public static <A, B> List<B> reduce(List<A> as, Function<A, B> f) {
		List<B> bs = new ArrayList<>();
		for (A a : as) {
			bs.add(f.apply(a));
		}
		return bs;
	}

	// example 2
	private static <A> List<A> add(A a, List<A> list) { // like reduce
		list.add(a);
		return list;
	}

	private static <A, B, C> C mapReduce(List<A> as, C identity, BiFunction<B, C, C> accumulator,
			Function<A, B> mapper) {
		C result = identity;
		for (A a : as) {
			result = accumulator.apply(mapper.apply(a), result);
		}
		return result;
	}

	// more generic collector
	public static <T> Collector<T, StringBuilder, String> toDelimitedString(String prefix, String separator,
			String postFix) {
		return new Collector<T, StringBuilder, String>() {
			@Override
			public Supplier<StringBuilder> supplier() {
				return StringBuilder::new;
			}

			@Override
			public BiConsumer<StringBuilder, T> accumulator() {
				return (sb, i) -> sb.append(sb.length() == 0 ? "" : separator).append(i);
			}

			@Override
			public BinaryOperator<StringBuilder> combiner() {
				return StringBuilder::append;
			}

			@Override
			public Function<StringBuilder, String> finisher() {
				return sb -> sb.insert(0, prefix).append(postFix).toString();
			}

			@Override
			public Set<Characteristics> characteristics() {
				return Collections.emptySet();
			}
		};
	}
}
