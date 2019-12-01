package mobile.tracking.persistence.repository;

import mobile.tracking.persistence.entity.CoordinatesEntity;
import mobile.tracking.persistence.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Hristiyan on 10.11.2019 ã..
 */
@Repository
public interface CoordinatesDAO extends JpaRepository<CoordinatesEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO coordinates(latitude,longitude,device_id,time,timezone) VALUES(?1,?2,?3,?4,?5) ON CONFLICT DO NOTHING", nativeQuery = true)
    void saveInto(final BigDecimal latitude, final BigDecimal longitude, final Integer device_id, final LocalDateTime time, final String timezone);
}
