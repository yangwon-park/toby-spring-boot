package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan // 해당 Class 부터 그 하위 패키지까지 Component Scan 동작 -> 등록할 Bean이 너무 많아지면 등록되는 Bean을 찾아보는게 힘듦
public class HellobootApplication {
	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet(); // ApplicationContextAware를 구현한 클래스 => 스프링에 Bean으로 등록됨 => 컨테이너에 등록된 후 인터페이스이 setter 메소드를 이용해서 자동 주입해줌
	}

	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}
}