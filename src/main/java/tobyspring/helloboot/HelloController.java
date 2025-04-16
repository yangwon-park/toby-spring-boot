package tobyspring.helloboot;

import java.util.Objects;

// SimpleHelloService 의존함
public class HelloController {
	private final HelloService helloService;

	public HelloController(HelloService helloService) {
		this.helloService = helloService;
	}

	public String hello(String name) {
 		return helloService.sayHello(Objects.requireNonNull(name));
	}
}
