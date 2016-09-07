package resume.java.util;

import java.util.Optional;

public class OptionalAsGet {

	public static void main(String[] args) {
		OptionalAsGet o = new OptionalAsGet();

		/*
		 * myString = Obj.getString(); if(myString != null){
		 * doSomething(myString); }
		 */
		Optional<String> petName1 = Optional.of("Bobby");
		System.out.println(petName1.orElse(""));

		// filter
		String notNull = "notNull";
		String petName4 = Optional.of(notNull).filter(name -> !name.trim().isEmpty())
				.orElseThrow(IllegalArgumentException::new);

		petName1.ifPresent(val -> doSomething(val));
		petName1.ifPresent(OptionalAsGet::doSomething);

		// used for class : check null
		LoyaltyCard lc = o.new LoyaltyCard();
		Optional<LoyaltyCard> loyaltyCard = Optional.of(lc);
		loyaltyCard.ifPresent(c -> c.addPoints(3));

		int point = loyaltyCard.map(LoyaltyCard::getPoints).orElse(0);
		System.out.println("point get " + point);
		int flatpoint = loyaltyCard.flatMap(LoyaltyCard::getFlatMapPoints).orElse(0);
		System.out.println("flatpoint get " + flatpoint);

		// throws here for testing above
		Optional<String> petName2 = Optional.empty();
		petName2.orElseThrow(IllegalArgumentException::new);
		Optional<String> petName3 = Optional.of(notNull);
		petName3.orElseThrow(IllegalArgumentException::new);

	}

	/*
	public static void doSomething(String a) {
		System.out.println("doSomething called " + a);
	}
	*/
	
	public static<T extends Object> void doSomething(T a) {
		System.out.println("doSomething called " + a);
	}	

	public class LoyaltyCard {
		private String cardNumber;
		private int points;

		public LoyaltyCard() {

		}

		public LoyaltyCard(String cardNumber, int points) {
			this.cardNumber = cardNumber;
			this.points = points;
		}

		public int addPoints(int pointToAdd) {
			return points += pointToAdd;
		}

		// Getters
		public int getPoints() {
			return points;
		}

		public Optional<Integer> getFlatMapPoints() {
			Optional<Integer> returnOptional = Optional.of(points);
			return returnOptional;
		}
	}
}
