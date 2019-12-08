package mobile.tracking;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Hristiyan on 26.10.2019 �..
 */
@Configuration
@SpringBootApplication
@EnableJpaRepositories(basePackages = "mobile.tracking.persistence")
public class MobileTracking {
    public static void main(String... args) {
        SpringApplication.run(MobileTracking.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
