package a;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import mobile.tracking.model.Coordinates;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Created by Hrisi on 10.11.2019 ã..
 */
public class Test {

    ObjectMapper objectMapper = new ObjectMapper();

    @org.junit.Test
    public void Test1() throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        Coordinates coordinates = new Coordinates("aaaa33", new BigDecimal("90.0"), new BigDecimal("150.0"), LocalDateTime.now().toString(), TimeZone.getDefault().getDisplayName(true, TimeZone.SHORT));
        System.out.println(this.objectMapper.writeValueAsString(coordinates));
    }
}
