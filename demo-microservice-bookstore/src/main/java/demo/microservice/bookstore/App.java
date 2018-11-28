package demo.microservice.bookstore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("demo.microservice.bookstore.model")
@EnableSwagger2
public class App
{
	@RequestMapping("/")
	String home() {
		return "Books Service Demo.";
	}
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}