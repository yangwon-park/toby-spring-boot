package tobyspring.helloboot;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.assertThat;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest {

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Test
@interface UnitTest{

}

public class HelloServiceTest {
	@UnitTest
	void simpleHelloService() {
		SimpleHelloService helloService = new SimpleHelloService();

		String ret =  helloService.sayHello("Test");

		assertThat(ret).isEqualTo("Hello Test");
	}

	@FastUnitTest
	void helloDecorator() {
		HelloDecorator helloDecorator = new HelloDecorator(name -> name);

		String ret = helloDecorator.sayHello("Test");

		assertThat(ret).isEqualTo("*Test*");
	}
}