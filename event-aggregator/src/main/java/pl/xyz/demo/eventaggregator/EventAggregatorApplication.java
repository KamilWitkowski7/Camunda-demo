package pl.xyz.demo.eventaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
@PropertySource(
        value = {
                "classpath:eventagg.properties",
                "classpath:eventagg-ext.properties",
        },
        ignoreResourceNotFound = true)
public class EventAggregatorApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EventAggregatorApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EventAggregatorApplication.class);
    }

}
