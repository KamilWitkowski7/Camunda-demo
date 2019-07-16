package pl.xyz.demo.camunda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(
        value = {
                "classpath:camunda.properties",
                "classpath:camunda-ext.properties",
                },
        ignoreResourceNotFound = true)
public class CamundaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CamundaApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CamundaApplication.class);
    }
}
