package mobile.tracking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mobile.tracking.ResponseType;
import mobile.tracking.model.Coordinates;
import mobile.tracking.persistence.entity.CoordinatesEntity;
import mobile.tracking.persistence.entity.DeviceEntity;
import mobile.tracking.persistence.repository.CoordinatesDAO;
import mobile.tracking.persistence.repository.DevicesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by Hristiyan on 10.11.2019 �..
 */
@Service
public class MobileDeviceService {

    private final DevicesDAO devicesDAO;
    private final CoordinatesDAO coordinatesDAO;
    private final ObjectMapper objectMapper;

    @Autowired
    public MobileDeviceService(final DevicesDAO devicesDAO,
                               final CoordinatesDAO coordinatesDAO,
                               final ObjectMapper objectMapper) {
        this.devicesDAO = devicesDAO;
        this.coordinatesDAO = coordinatesDAO;
        this.objectMapper = objectMapper;
    }

    public ResponseType storeCoordinatesInDB(final Coordinates coordinates) throws IOException {
        final DeviceEntity deviceEntity = findDeviceEntity(coordinates.getDeviceUniqueCode());
        final LocalDateTime localDateTime = objectMapper.readValue(coordinates.getTime(), LocalDateTime.class);
//        coordinatesDAO.save(new CoordinatesEntity(coordinates.getLatitude(), coordinates.getLongitude(), localDateTime, coordinates.getTimeZone(), deviceEntity));
        try {

            coordinatesDAO.saveInto(coordinates.getLatitude(), coordinates.getLongitude(), deviceEntity.getId(), localDateTime, coordinates.getTimeZone());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseType.SUCCESS;
    }

    private DeviceEntity findDeviceEntity(final String deviceUniqueCode) {
        DeviceEntity deviceEntity = devicesDAO.findDeviceByUniqueCode(deviceUniqueCode);
        if (deviceEntity == null) {
            deviceEntity = devicesDAO.save(new DeviceEntity(deviceUniqueCode));
        }
        return deviceEntity;
    }
}
