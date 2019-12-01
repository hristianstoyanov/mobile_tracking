package mobile.tracking.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import mobile.tracking.Constants;
import mobile.tracking.ResponseType;
import mobile.tracking.controller.TrackingController;
import mobile.tracking.model.Coordinates;
import mobile.tracking.service.MobileDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Hristiyan on 27.10.2019 �..
 */
@RestController
@RequestMapping("/tracking")
public class MobileTrackingController implements TrackingController{

    private final MobileDeviceService mobileDeviceService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MobileTrackingController(final MobileDeviceService mobileDeviceService,
                                    final ObjectMapper objectMapper) {
        this.mobileDeviceService = mobileDeviceService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/get")
    public String trackingCode() {

        return "aaaa";
    }

    @PostMapping("/add/coordinates")
    public ResponseEntity receiveCoordinates(@RequestBody final String coordinatesString) throws IOException {
        final Coordinates coordirdinates = objectMapper.readValue(coordinatesString, Coordinates.class);
        final ResponseType responseType = mobileDeviceService.storeCoordinatesInDB(coordirdinates);

        return new ResponseEntity(Constants.responseTypeMap.get(responseType));
    }

    @PostMapping("/uniqueCodes")
    public ResponseEntity changeUniqueCode(@RequestParam("oldCode") final String oldUniqueCode,
                                           @RequestParam("newCode") final String newUniqueCode) {

        return null;
    }

}
