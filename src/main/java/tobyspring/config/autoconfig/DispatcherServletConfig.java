package tobyspring.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
public class DispatcherServletConfig {
	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet(); // ApplicationContextAware를 구현한 클래스 => 스프링에 Bean으로 등록됨 => 컨테이너에 등록된 후 인터페이스이 setter 메소드를 이용해서 자동 주입해줌
	}
}