package quvoncuz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quvoncuz.dto.StatisticsResponse;
import quvoncuz.enums.EventType;
import quvoncuz.service.StatisticsService;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    // agency
    @GetMapping("/agencies")
    public ResponseEntity<StatisticsResponse> findAgencyCountByGivenDays(
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to) {

        if (from == null && to == null) {
            return ResponseEntity.ok(statisticsService.findCountByGivenDay(EventType.AGENCY_CREATED, LocalDate.now()));
        }

        from = Optional.ofNullable(from).orElse(LocalDate.now().withDayOfMonth(1));

        to = Optional.ofNullable(to).orElse(LocalDate.now());

        return ResponseEntity.ok(statisticsService.findCountByGivenDays(EventType.AGENCY_CREATED, from, to));
    }

    // booking
    @GetMapping("/bookings")
    public ResponseEntity<StatisticsResponse> findBookingCountByGivenDays(
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to) {

        if (from == null && to == null) {
            return ResponseEntity.ok(statisticsService.findCountByGivenDay(EventType.BOOKING_COMPLETED, LocalDate.now()));
        }

        from = Optional.ofNullable(from).orElse(LocalDate.now().withDayOfMonth(1));

        to = Optional.ofNullable(to).orElse(LocalDate.now());

        return ResponseEntity.ok(statisticsService.findCountByGivenDays(EventType.BOOKING_COMPLETED, from, to));
    }

    // tour
    @GetMapping("/tours")
    public ResponseEntity<StatisticsResponse> findTourCountByGivenDays(
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to) {

        if (from == null && to == null) {
            return ResponseEntity.ok(statisticsService.findCountByGivenDay(EventType.TOUR_CREATED, LocalDate.now()));
        }

        from = Optional.ofNullable(from).orElse(LocalDate.now().withDayOfMonth(1));

        to = Optional.ofNullable(to).orElse(LocalDate.now());

        return ResponseEntity.ok(statisticsService.findCountByGivenDays(EventType.TOUR_CREATED, from, to));
    }

    // user
    @GetMapping("/users")
    public ResponseEntity<StatisticsResponse> findUserCountByGivenDays(
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to) {

        if (from == null && to == null) {
            return ResponseEntity.ok(statisticsService.findCountByGivenDay(EventType.USER_REGISTERED, LocalDate.now()));
        }

        from = Optional.ofNullable(from).orElse(LocalDate.now().withDayOfMonth(1));

        to = Optional.ofNullable(to).orElse(LocalDate.now());

        return ResponseEntity.ok(statisticsService.findCountByGivenDays(EventType.USER_REGISTERED, from, to));
    }
}
