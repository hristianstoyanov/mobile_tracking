package mobile.tracking.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Hristiyan on 10.11.2019 ã..
 */
@Entity
@Table(name = "coordinates")
public class CoordinatesEntity {

    @Id
    @SequenceGenerator(name="coordinates_id_seq",
            sequenceName="coordinates_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="coordinates_id_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "device_id")
    private DeviceEntity deviceEntity;

    @Column(name = "time")
    private LocalDateTime UTCDateTime;

    @Column(name = "timezone")
    private String timeZone;

    public CoordinatesEntity() {

    }

    public CoordinatesEntity(final Integer id,
                             final BigDecimal latitude,
                             final BigDecimal longitude,
                             final LocalDateTime UTCDateTime,
                             final String timeZone,
                             final DeviceEntity deviceEntity) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.UTCDateTime = UTCDateTime;
        this.timeZone = timeZone;
        this.deviceEntity = deviceEntity;
    }

    public CoordinatesEntity(final BigDecimal latitude,
                             final BigDecimal longitude,
                             final LocalDateTime UTCDateTime,
                             final String timeZone,
                             final DeviceEntity deviceEntity) {
        this.id = null;
        this.latitude = latitude;
        this.longitude = longitude;
        this.UTCDateTime = UTCDateTime;
        this.timeZone = timeZone;
        this.deviceEntity = deviceEntity;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public DeviceEntity getDeviceEntity() {
        return deviceEntity;
    }

    public LocalDateTime getUTCDateTime() {
        return UTCDateTime;
    }

    public String getTimeZone() {
        return timeZone;
    }
}
