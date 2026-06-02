package quvoncuz.events;

import lombok.*;
import quvoncuz.enums.EventType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsEvent {

    private Long superId; // agencyId <- tourId,   tourId <- bookingId

    private Long entityId;

    private EventType eventType;

    private LocalDateTime dateTime;
}
