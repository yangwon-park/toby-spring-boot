package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		GenericApplicationContext	 context = new GenericApplicationContext();
		context.registerBean(HelloController.class);
		context.registerBean(SimpleHelloService.class);
		context.refresh(); // BeanObject 만들어줌 (초기화 작업)

		ServletWebServerFactory factory = new TomcatServletWebServerFactory();
		WebServer webServer = factory.getWebServer(servletContext -> {

					servletContext.addServlet("hello", new HttpServlet() {
						@Override
						protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
							// 인증, 보안, 다국어, 공통 기능 => FrontController가 담당 (Mapping, Binding 담당)
							// 실질적인 Application 로직은 다른 Object가 담당하게 해야함
							if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
								String name = req.getParameter("name");

								HelloController helloController = context.getBean(HelloController.class);
								String ret = helloController.hello(name);

								resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
								resp.getWriter().print(ret);
							} else {
								resp.setStatus(HttpStatus.NOT_FOUND.value());
							}
						}
					}).addMapping("/hello");
				});

		webServer.start();
	}
}