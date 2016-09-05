package resume.java.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class ListMapInV8 {

	public static void main(String[] args) {
		// mapTolist();
		listTomap();

	}

	public static void mapTolist() {
		Map<Integer, String> map = new HashMap<>();
		map.put(10, "apple");
		map.put(20, "orange");
		map.put(30, "banana");
		map.put(40, "watermelon");
		map.put(50, "dragonfruit");
		System.out.println("\n1. Export Map Key to List...");
		List<Integer> result = map.entrySet().stream().map(x -> x.getKey()).collect(Collectors.toList());
		result.forEach(System.out::println);
		System.out.println("\n2. Export Map Value to List...");
		List<String> result2 = map.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
		result2.forEach(System.out::println);

		System.out.println("filter for list stream:");
		result.stream().filter(i -> i == 30).forEach(System.out::println);
		result2.stream().filter(s -> s.equals("apple")).forEach(System.out::println);

		result2.forEach(item -> System.out.println("Item : " + item));
	}

	public static void listTomap() {

		List<String> list = new ArrayList<>();
		list.add("apple");
		list.add("orange");
		list.add("watermelon");
		list.add("dragonfruit");
		final Random r = new Random();
		final int n = list.size();
		// example 1
		Map<String, String> result1 = list.stream().collect(Collectors.toMap(s -> s.toString(), s -> s.toString()));

		System.out.println("Result 1 : " + result1);

		// example 2, although random integer, but no duplicate value for list
		Map<Integer, String> result2 = list.stream()
				.collect(Collectors.toMap(x -> r.nextInt(x.length() + x.length()), x -> x.toString()));

		System.out.println("Result 2 : " + result2);

		result2.forEach((k, v) -> System.out.println("key : " + k + " value : " + v));

	}

}
