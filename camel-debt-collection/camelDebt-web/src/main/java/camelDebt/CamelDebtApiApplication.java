package camelDebt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@PropertySource(
		value = {
				"classpath:debt.properties",
				"classpath:debt-ext.properties",
		},
		ignoreResourceNotFound = true)
public class CamelDebtApiApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CamelDebtApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CamelDebtApiApplication.class, args);
	}

}
