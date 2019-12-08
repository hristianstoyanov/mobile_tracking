package mobile.tracking.service;

import mobile.tracking.TrackingTimeZoneType;
import mobile.tracking.model.TrackingPoint;
import mobile.tracking.persistence.entity.CoordinatesEntity;
import mobile.tracking.persistence.entity.DeviceEntity;
import mobile.tracking.persistence.repository.CoordinatesDAO;
import mobile.tracking.persistence.repository.DevicesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Hristiyan on 8.12.2019 ã..
 */
@Service
public class WebClientTrackingService {

    private final CoordinatesDAO coordinatesDAO;
    private final DevicesDAO devicesDAO;

    @Autowired
    public WebClientTrackingService(final CoordinatesDAO coordinatesDAO,
                                    final DevicesDAO devicesDAO) {
        this.coordinatesDAO = coordinatesDAO;
        this.devicesDAO = devicesDAO;
    }

    public List<TrackingPoint> trackForPeriod(final String deviceUniqueCode,
                                              final LocalDateTime startDateTime,
                                              final LocalDateTime endDateTime,
                                              final TrackingTimeZoneType trackingTimeZoneType) {
        List<TrackingPoint> trackingPoints = new ArrayList<>();

        final DeviceEntity requestedDeviceEntity = devicesDAO.findDeviceByUniqueCode(deviceUniqueCode);
        if (requestedDeviceEntity != null) {
            final Integer requestedDeviceId = requestedDeviceEntity.getId();
            final List<CoordinatesEntity> coordinatesByDeviceCode = coordinatesDAO.findAll();

            if (endDateTime != null) {
                trackingPoints = filterCoordinatesForPeriod(startDateTime, endDateTime, trackingTimeZoneType, coordinatesByDeviceCode)
                        .filter(coordinatesEntity -> coordinatesEntity.getDeviceEntity().getId().equals(requestedDeviceId))
                        .map(coordinatesEntity -> convertCoordinatesEntityToTrackingPoint(coordinatesEntity, trackingTimeZoneType))
                        .collect(Collectors.toList());
            } else {
                Optional<CoordinatesEntity> entityAtTimePoint = findCoordinatesAtDate(startDateTime, trackingTimeZoneType, coordinatesByDeviceCode)
                        .filter(coordinatesEntity -> coordinatesEntity.getDeviceEntity().getId().equals(requestedDeviceId))
                        .findFirst();

                if (entityAtTimePoint.isPresent()) {
                    final TrackingPoint trackingPoint = convertCoordinatesEntityToTrackingPoint(entityAtTimePoint.get(), trackingTimeZoneType);
                    trackingPoints = Collections.singletonList(trackingPoint);
                }
            }

            trackingPoints.sort(Comparator.comparing(TrackingPoint::getTrackingTime));
        }

        return trackingPoints;
    }

    private Stream<CoordinatesEntity> filterCoordinatesForPeriod(final LocalDateTime startDateTime,
                                                               final LocalDateTime endDateTime,
                                                               final TrackingTimeZoneType timeZoneType,
                                                               final List<CoordinatesEntity> coordinatesByDeviceCode) {
        return coordinatesByDeviceCode.
                stream()
                .filter(coordinatesEntity -> {
                    if (timeZoneType == TrackingTimeZoneType.UTC) {
                        return isDateTimeInPeriod(startDateTime, endDateTime, coordinatesEntity.getUTCDateTime());
                    } else {
                        final LocalDateTime dbDateTimeWithAppliedZone = ZonedDateTime.of(coordinatesEntity.getUTCDateTime(), ZoneId.of("UTC"))
                                .withZoneSameInstant(ZoneId.of(coordinatesEntity.getTimeZone()))
                                .toLocalDateTime();
                        return isDateTimeInPeriod(startDateTime, endDateTime, dbDateTimeWithAppliedZone);
                    }
                });
    }

    private boolean isDateTimeInPeriod(final LocalDateTime startDateTime,
                                    final LocalDateTime endDateTime,
                                    final LocalDateTime targetDateTime) {
        final LocalDateTime modifiedStartDate = startDateTime.withSecond(0).withNano(0);
        final LocalDateTime modifiedEndDate = endDateTime.withSecond(0).withNano(0);
        final LocalDateTime modifiedTargetDateTime = targetDateTime.withSecond(0).withNano(0);

        return !modifiedTargetDateTime.isBefore(modifiedStartDate) && !modifiedTargetDateTime.isAfter(modifiedEndDate);
    }

    private TrackingPoint convertCoordinatesEntityToTrackingPoint(final CoordinatesEntity coordinatesEntity,
                                                                  final TrackingTimeZoneType timeZoneType) {
        final LocalDateTime modifiedLocalDateTime;
        if (timeZoneType == TrackingTimeZoneType.DEVICE) {
            modifiedLocalDateTime = ZonedDateTime.of(coordinatesEntity.getUTCDateTime(), ZoneId.of("UTC"))
                    .withZoneSameInstant(ZoneId.of(coordinatesEntity.getTimeZone()))
                    .toLocalDateTime();
        } else {
            modifiedLocalDateTime = coordinatesEntity.getUTCDateTime();
        }

        return new TrackingPoint(coordinatesEntity.getLatitude(),
                coordinatesEntity.getLongitude(), modifiedLocalDateTime);
    }

    private Stream<CoordinatesEntity> findCoordinatesAtDate(final LocalDateTime startDateTime,
                                                          final TrackingTimeZoneType timeZoneType,
                                                          final List<CoordinatesEntity> coordinatesEntities) {

        return coordinatesEntities
                .stream()
                .filter(coordinatesEntity -> {
                    if (timeZoneType == TrackingTimeZoneType.UTC) {
                        return compareDateTimesRegardlessOfSeconds(startDateTime, coordinatesEntity.getUTCDateTime());
                    } else {
                        final LocalDateTime dbDateTimeWithAppliedZone = ZonedDateTime.of(coordinatesEntity.getUTCDateTime(), ZoneId.of("UTC"))
                                .withZoneSameInstant(ZoneId.of(coordinatesEntity.getTimeZone()))
                                .toLocalDateTime();
                        return compareDateTimesRegardlessOfSeconds(startDateTime, dbDateTimeWithAppliedZone);
                    }
                });

    }

    private boolean compareDateTimesRegardlessOfSeconds(final LocalDateTime dateTime1, final LocalDateTime dateTime2) {
        final LocalDateTime modifiedDateTime1 = dateTime1.withSecond(0).withNano(0);
        final LocalDateTime modifiedDateTime2 = dateTime2.withSecond(0).withNano(0);

        return modifiedDateTime1.equals(modifiedDateTime2);
    }
}
