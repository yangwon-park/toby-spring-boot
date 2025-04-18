package tobyspring.helloboot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

// SimpleHelloService 의존함
@RestController // Dispatcher Servlet이 RequestMapping이 없어도 알아서 매핑 정보가 있을거라고 판단해줌
public class HelloController {
	private final HelloService helloService;

	// ApplicationContext, BeanFactory, ResourceLoader, ApplicationEventPublisher 등 몇 가지 타입에 대해
	// “특수 의존성”(Resolvable Dependency) 으로 미리 등록해 두기 때문에, ApplicationContext는 초기화가 필요없음
	public HelloController(HelloService helloService, ApplicationContext applicationContext) {
		this.helloService = helloService;

		System.out.println("applicationContext = " + applicationContext);
	}

	@GetMapping("/hello")
	public String hello(String name) {
		return helloService.sayHello(Objects.requireNonNull(name));
	}
}