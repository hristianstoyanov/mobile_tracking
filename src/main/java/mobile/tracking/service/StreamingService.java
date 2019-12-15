package mobile.tracking.service;

import mobile.tracking.model.TrackingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by Hristiyan on 14.12.2019 ã..
 */
@Service
public class StreamingService {

    @Autowired
    private SimpMessagingTemplate template;

    public void streamCoordinates(final TrackingPoint trackingPoint, final String deviceUniqueCode) {
        template.convertAndSend("/topic/messages/" + deviceUniqueCode, trackingPoint);
    }

//    public void streamTest() {
//        template.convertAndSend("/topic/messages", new OutputMessage("aaa", "bbb", LocalDateTime.now().toString()));
//    }

}
