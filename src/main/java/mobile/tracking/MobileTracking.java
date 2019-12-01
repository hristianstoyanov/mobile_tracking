package mobile.tracking;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Hristiyan on 26.10.2019 ã..
 */
@Configuration
@SpringBootApplication
@EnableJpaRepositories(basePackages = "mobile.tracking.persistence")
public class MobileTracking {
    public static void main(String... args) {
        SpringApplication.run(MobileTracking.class, args);
    }
}
