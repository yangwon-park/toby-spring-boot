package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class HellobootApplication {
	@Bean
	public HelloController helloController(HelloService helloService) {
		return new HelloController(helloService);
	}

	@Bean
	public HelloService helloService() {
		return new SimpleHelloService();
	}

	public static void main(String[] args) {
		// Spring Container를 만드는 작업
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				//  Servlet Container를 코드로 실행하면서 Servlet을 등록하는 작업
				ServletWebServerFactory factory = new TomcatServletWebServerFactory();
				WebServer webServer = factory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this) // Bean을 다 뒤져서 웹 요청을 처리할 수 있 GetMapping 같은게 붙어있으면 요청 정보들을 알아서 추출해줌
					).addMapping("/*");
				});

				webServer.start();
			}
		};

		applicationContext.register(HellobootApplication.class); // 구성 정보를 갖고 있는 Class 자체를 등록해줌
		applicationContext.refresh(); // BeanObject 만들어줌 (초기화 작업)
	}
}