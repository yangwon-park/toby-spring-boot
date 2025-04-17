package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

	public static void main(String[] args) {
		GenericWebApplicationContext context = new GenericWebApplicationContext();
		context.registerBean(HelloController.class);
		context.registerBean(SimpleHelloService.class);
		context.refresh(); // BeanObject 만들어줌 (초기화 작업)

		ServletWebServerFactory factory = new TomcatServletWebServerFactory();
		WebServer webServer = factory.getWebServer(servletContext -> {
			servletContext.addServlet("dispatcherServlet",
					new DispatcherServlet(context) // Bean을 다 뒤져서 웹 요청을 처리할 수 있 GetMapping 같은게 붙어있으면 요청 정보들을 알아서 추출해줌
			).addMapping("/*");
		});

		webServer.start();
	}
}