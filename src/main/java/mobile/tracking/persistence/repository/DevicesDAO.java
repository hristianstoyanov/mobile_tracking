package mobile.tracking.persistence.repository;

import mobile.tracking.persistence.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Hrisi on 9.11.2019 ã..
 */
@Repository
public interface DevicesDAO extends JpaRepository<DeviceEntity, Integer> {

    @Query("SELECT d FROM DeviceEntity d WHERE d.uniqueCode=?1")
    DeviceEntity findDeviceByUniqueCode(final String uniqueCode);
}
