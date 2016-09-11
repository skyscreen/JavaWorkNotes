package resume.java.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;

import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

public class ListMapInV8 {

	public static void main(String[] args) {
		// mapTolist();
		listTomap();
		forbiddenV8Stream();
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
	
	//using http://mvnrepository.com/artifact/org.jooq/jool/0.9.12
	public static void forbiddenV8Stream(){
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(10, "apple");
        map1.put(20, "orange");
        map1.put(30, "banana");
        map1.put(40, "watermelon");
        map1.put(50, "dragonfruit");
        
        //JOOL lib
        Tuple2<List<Integer>, List<String>> result1 = 
        		map1.entrySet()
        		    .stream()
        		    .collect(Tuple.collectors(
        		        Collectors.mapping(Entry::getKey, Collectors.toList()),
        		        Collectors.mapping(Entry::getValue, Collectors.toList())
        		    ));
        System.out.println(result1.v1);
        System.out.println(result1.v2);
        
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(10, "apple");
        map2.put(20, "orange");
        map2.put(30, "banana");
        map2.put(40, "watermelon");
        map2.put(50, "dragonfruit");
        Tuple2<List<Integer>, List<String>> result2 = 
        		Seq.seq(map2)
        		   .collect(
        		        Collectors.mapping(Tuple2::v1, Collectors.toList()),
        		        Collectors.mapping(Tuple2::v2, Collectors.toList())
        		   );
        System.out.println(result2.v1);
        System.out.println(result2.v2);
        
	}

}
