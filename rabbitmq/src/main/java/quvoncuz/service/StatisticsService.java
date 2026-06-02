package quvoncuz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quvoncuz.dto.StatisticsResponse;
import quvoncuz.entities.AgencyStatistics;
import quvoncuz.entities.BookingStatistics;
import quvoncuz.entities.TourStatistics;
import quvoncuz.entities.UserStatistics;
import quvoncuz.enums.EventType;
import quvoncuz.events.StatisticsEvent;
import quvoncuz.repository.AgencyStatsRepository;
import quvoncuz.repository.BookingStatsRepository;
import quvoncuz.repository.TourStatsRepository;
import quvoncuz.repository.UserStatsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final AgencyStatsRepository agencyStatsRepository;
    private final BookingStatsRepository bookingStatsRepository;
    private final TourStatsRepository tourStatsRepository;
    private final UserStatsRepository userStatsRepository;

    public void save(EventType type, StatisticsEvent event) {

        switch (type) {
            case AGENCY_CREATED -> {
                AgencyStatistics statistics = AgencyStatistics.builder()
                        .id(event.getEntityId())
                        .createdAt(event.getDateTime())
                        .build();

                agencyStatsRepository.save(statistics);
            }
            case BOOKING_COMPLETED -> {
                BookingStatistics statistics = BookingStatistics.builder()
                        .id(event.getEntityId())
                        .tourId(event.getSuperId())
                        .createdAt(event.getDateTime())
                        .build();

                bookingStatsRepository.save(statistics);
            }
            case TOUR_CREATED -> {
                TourStatistics statistics = TourStatistics.builder()
                        .id(event.getEntityId())
                        .agencyId(event.getSuperId())
                        .createdAt(event.getDateTime())
                        .build();

                tourStatsRepository.save(statistics);
            }
            case USER_REGISTERED -> {
                UserStatistics statistics = UserStatistics.builder()
                        .id(event.getEntityId())
                        .createdAt(event.getDateTime())
                        .build();

                userStatsRepository.save(statistics);
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }

    }

    public StatisticsResponse findCountByGivenDay(EventType type, LocalDate date) {
        LocalDateTime from = date.atStartOfDay();
        LocalDateTime to = date.atTime(LocalTime.MAX);
        long count = switch (type) {
            case AGENCY_CREATED -> agencyStatsRepository.findCountByGivenDays(from, to);
            case BOOKING_COMPLETED -> bookingStatsRepository.findCountByGivenDays(from, to);
            case TOUR_CREATED -> tourStatsRepository.findCountByGivenDays(from, to);
            case USER_REGISTERED -> userStatsRepository.findCountByGivenDays(from, to);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };

        return StatisticsResponse.builder()
                .count(count)
                .eventType(type)
                .build();
    }

    public StatisticsResponse findCountByGivenDays(EventType type, LocalDate from, LocalDate to) {
        LocalDateTime fromDate = from.atStartOfDay();
        LocalDateTime toDate = to.atTime(LocalTime.MAX);

        long count = switch (type) {
            case AGENCY_CREATED -> agencyStatsRepository.findCountByGivenDays(fromDate, toDate);
            case BOOKING_COMPLETED -> bookingStatsRepository.findCountByGivenDays(fromDate, toDate);
            case TOUR_CREATED -> tourStatsRepository.findCountByGivenDays(fromDate, toDate);
            case USER_REGISTERED -> userStatsRepository.findCountByGivenDays(fromDate, toDate);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };

        return StatisticsResponse.builder()
                .count(count)
                .eventType(type)
                .build();
    }

}
