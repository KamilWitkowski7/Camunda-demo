package pl.xyz.demo.camunda;

import org.camunda.bpm.spring.boot.starter.rest.CamundaJerseyResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/resources")
public class RESTconfig extends CamundaJerseyResourceConfig {

}
