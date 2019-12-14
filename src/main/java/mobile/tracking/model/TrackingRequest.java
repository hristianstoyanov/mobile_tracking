package mobile.tracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import mobile.tracking.TrackingTimeZoneType;

import java.time.LocalDateTime;

/**
 * Created by Hristiyan on 8.12.2019 ã..
 */
public class TrackingRequest {

    @JsonProperty("deviceUniqueCode")
    private final String deviceUniqueCode;

    @JsonProperty("startDateTime")
    private final LocalDateTime startDateTime;

    @JsonProperty("endDateTime")
    private final LocalDateTime endDateTime;
    @JsonProperty("timeZoneType")
    private final TrackingTimeZoneType timeZoneType;

    public TrackingRequest(@JsonProperty("deviceUniqueCode") final String deviceUniqueCode,
                           @JsonProperty("startDateTime") final LocalDateTime startDateTime,
                           @JsonProperty("endDateTime") final LocalDateTime endDateTime,
                           @JsonProperty("timeZoneType") final TrackingTimeZoneType timeZoneType) {
        this.deviceUniqueCode = deviceUniqueCode;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.timeZoneType = timeZoneType;
    }

    public String getDeviceUniqueCode() {
        return deviceUniqueCode;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public TrackingTimeZoneType getTimeZoneType() {
        return timeZoneType;
    }
}
