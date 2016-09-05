package resume.java.util;

import java.util.regex.Pattern;

public class ValidateEmail {

	public static void main(String[] args) {
		// Compile pattern
		Pattern emailAddressPattern = Pattern.compile(String.format("%1$s(,\\s*%1$s)*", "\\w+@\\w+\\.\\w+"));
		// Validate addresses
		System.out.println(emailAddressPattern.matcher("xyz").matches()); // false
		System.out.println(emailAddressPattern.matcher("foo@bar.com").matches()); // true
		System.out.println(emailAddressPattern.matcher("foo@bar.com, xyz").matches()); // false
		System.out.println(emailAddressPattern.matcher("foo@bar.com, foo@bar.com").matches()); // true
		System.out.println(String.format("%1$s(,\\s*%1$s)*", "\\w+@\\w+\\.\\w+"));
		//\s=[ \t\n\x0B\f\r]  %1=\\w+@\\w+\\.\\w+ $s=string
	}

}
