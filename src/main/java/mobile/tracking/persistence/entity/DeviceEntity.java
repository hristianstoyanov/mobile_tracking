package mobile.tracking.persistence.entity;

import javax.persistence.*;

/**
 * Created by Hristiyan on 10.11.2019 ã..
 */
@Entity
@Table(name = "devices")
public class DeviceEntity {

    public DeviceEntity() {}

    @Id
    @SequenceGenerator(name="devices_id_seq",
            sequenceName="devices_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="devices_id_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "device_unique_code")
    private String uniqueCode;

    public DeviceEntity(final String uniqueCode) {
        this.id = null;
        this.uniqueCode = uniqueCode;
    }

    public DeviceEntity(final int id, final String uniqueCode) {
        this.id = id;
        this.uniqueCode = uniqueCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}
