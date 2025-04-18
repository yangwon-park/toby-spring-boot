package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

// SimpleHelloService 의존함
@RestController // Dispatcher Servlet이 RequestMapping이 없어도 알아서 매핑 정보가 있을거라고 판단해줌
public class HelloController {
	private final HelloService helloService;

	public HelloController(HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping("/hello")
	public String hello(String name) {
 		return helloService.sayHello(Objects.requireNonNull(name));
	}
}