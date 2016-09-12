package resume.java.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.amazonaws.util.StringUtils;

/*
 * http://mvnrepository.com/artifact/javax.validation/validation-api/1.1.0.Final
 * http://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-core/1.11.33
 */
public class ValidateEmail {

	public static void main(String[] args) {
		valid1();
		valid2();
	}

	public static void valid1(){
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
	
	
	public static void valid2(){
		ValidatorFactory  validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
	     final TestEntity emails = new TestEntity(Collections.singletonList("tjancz@gmail.com"));
	        final Set<ConstraintViolation<TestEntity>> violations = validator.validate(emails);
	        System.out.println(violations.size());
	}
	
	private static class TestEntity {
        @EmailList
        private final List<String> emails;
        public TestEntity(final List<String> emails) {
            this.emails = emails;
        }
    }	
	@Constraint(validatedBy = {EmailListValidator.class})
	@Target({ElementType.FIELD, ElementType.PARAMETER})
	@Retention(value = RetentionPolicy.RUNTIME)
	@Documented
	public @interface EmailList {
	    String message() default "Invalid Email Address";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
	}	
	
	
	

}


