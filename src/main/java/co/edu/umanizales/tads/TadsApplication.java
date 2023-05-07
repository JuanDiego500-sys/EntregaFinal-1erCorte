package co.edu.umanizales.tads;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class TadsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TadsApplication.class, args);
    }

}
