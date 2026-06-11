package quvoncuz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quvoncuz.events.NotificationEvent;
import quvoncuz.events.StatisticsEvent;
import quvoncuz.service.EventService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @PostMapping("/notification")
    public ResponseEntity<Void> publishNotificationEvent(@RequestBody NotificationEvent event) {
        eventService.handleNotification(event);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/statistics")
    public ResponseEntity<Void> publishStatisticsEvent(@RequestBody StatisticsEvent event) {
        eventService.handleStatistics(event);
        return ResponseEntity.noContent().build();
    }

}
