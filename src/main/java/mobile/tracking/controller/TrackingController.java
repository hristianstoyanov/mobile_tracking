package mobile.tracking.controller;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * Created by Hristiyan on 10.11.2019 ã..
 */
public interface TrackingController {

    ResponseEntity receiveCoordinates(final String coordinates) throws IOException;

    ResponseEntity<String> changeUniqueCode(final String oldUniqueCode, final String newUniqueCode);
}
