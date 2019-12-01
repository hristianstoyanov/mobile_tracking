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
    private final Integer id;

    @Column(name = "latitude")
    private final BigDecimal latitude;

    @Column(name = "longitude")
    private final BigDecimal longitude;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "device_id")
    private final DeviceEntity deviceEntity;

    @Column(name = "time")
    private final LocalDateTime UTCDateTime;

    @Column(name = "timezone")
    private final String timeZone;

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
}
