package mobile.tracking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Hristiyan on 8.12.2019 ã..
 */
public class TrackingPoint {

    @JsonProperty("latitude")
    private final BigDecimal latitude;
    @JsonProperty("longitude")
    private final BigDecimal longitude;
    @JsonProperty("trackingTime")
    private final LocalDateTime trackingTime;

    public TrackingPoint(@JsonProperty("latitude") final BigDecimal latitude,
                         @JsonProperty("longitude") final BigDecimal longitude,
                         @JsonProperty("trackingTime") final LocalDateTime trackingTime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.trackingTime = trackingTime;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public LocalDateTime getTrackingTime() {
        return trackingTime;
    }
}
