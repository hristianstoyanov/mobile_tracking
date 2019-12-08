package mobile.tracking.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import mobile.tracking.model.TrackingPoint;
import mobile.tracking.model.TrackingRequest;
import mobile.tracking.service.WebClientTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Hristiyan on 7.12.2019 ã..
 */
@RestController
@RequestMapping("/web/track")
public class WebController {

    private final ObjectMapper objectMapper;
    private final WebClientTrackingService webClientTrackingService;

    @Autowired
    public WebController(final ObjectMapper objectMapper,
                         final WebClientTrackingService webClientTrackingService) {
        this.objectMapper = objectMapper;
        this.webClientTrackingService = webClientTrackingService;
    }

    @PostMapping("/period")
    public ResponseEntity<String> trackPeriod(@RequestBody final String requestBody) {
        try {
            final TrackingRequest trackingRequest = objectMapper.readValue(requestBody, TrackingRequest.class);
            List<TrackingPoint> trackingPoints = webClientTrackingService.trackForPeriod(trackingRequest.getDeviceUniqueCode(), trackingRequest.getStartDateTime(),
                    trackingRequest.getEndDateTime(), trackingRequest.getTimeZoneType());

            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(trackingPoints));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request.");
        }

    }
}
