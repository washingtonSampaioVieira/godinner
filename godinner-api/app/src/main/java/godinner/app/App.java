package godinner.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class App {
    public static void main( String[] args ){
    	SpringApplication.run(App.class, args);
    }
}