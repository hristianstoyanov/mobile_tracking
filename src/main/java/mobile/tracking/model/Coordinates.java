package mobile.tracking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by Hristiyan on 10.11.2019 �..
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Coordinates {

    @JsonProperty("deviceUniqueCode")
    private final String deviceUniqueCode;
    @JsonProperty("latitude")
    private final BigDecimal latitude;
    @JsonProperty("longitude")
    private final BigDecimal longitude;
    @JsonProperty("localDateTimeString")
    private final String localDateTimeString;
    @JsonProperty("timeZone")
    private final String timeZone;

    @JsonCreator
    public Coordinates(@JsonProperty("deviceUniqueCode") final String deviceUniqueCode,
                       @JsonProperty("latitude") final BigDecimal latitude,
                       @JsonProperty("longitude") final BigDecimal longitude,
                       @JsonProperty("time") final String localDateTimeString,
                       @JsonProperty("timezone") final String timeZone) {
        this.deviceUniqueCode = deviceUniqueCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.localDateTimeString = localDateTimeString;
        this.timeZone = timeZone;
    }

    public String getDeviceUniqueCode() {
        return deviceUniqueCode;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public String getTime() {
        return localDateTimeString;
    }

    public String getTimeZone() { return timeZone;}
}
