package mobile.tracking;

/**
 * Created by Hristiyan on 8.12.2019 ã..
 */
public enum TrackingTimeZoneType {

    DEVICE("device"),
    UTC("utc");

    private final String timeZoneType;

    private TrackingTimeZoneType(final String timeZoneType) {
        this.timeZoneType = timeZoneType;
    }
}
