package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationTest {
	@Test
	void configuration() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

		ac.register(MyConfig.class);
		ac.refresh();

		Bean1 bean1 = ac.getBean(Bean1.class);
		Bean2 bean2 = ac.getBean(Bean2.class);

		// Spring 내부에서 동작하는 순간 2개의 Bean이 같은 Bean이 됨 -> proxyBeanMethods true
		// Spring 밖에서 동작하는 경우, 일반 java처럼 주솟값이 달라서 다른 객체로 취급함 -> proxyBeanMethods false 설정하면 동일 효과
		assertThat(bean1.common).isSameAs(bean2.common);
	}

	@Test
	void proxyCommonMethod() {
		MyConfigProxy myConfigProxy = new MyConfigProxy();

		Bean1 bean1 = myConfigProxy.bean1();
		Bean2 bean2 = myConfigProxy.bean2();

		assertThat(bean1.common).isSameAs(bean2.common);
	}

	static class MyConfigProxy extends MyConfig {
		private Common common;

		@Override
		Common common() {
			if (this.common == null) this.common = super.common();

			return this.common;
		}
	}

	@Configuration
	static class MyConfig {
		@Bean
		Common common() {
			return new Common();
		}

		@Bean
		Bean1 bean1() {
			return new Bean1(common());
		}

		@Bean
		Bean2 bean2() {
			return new Bean2(common());
		}
	}

	static class Bean1 {
		private final Common common;

		Bean1(Common common) {
			this.common = common;
		}
	}

	static class Bean2 {
		private final Common common;

		Bean2(Common common) {
			this.common = common;
		}
	}

	static class Common {

	}
}
