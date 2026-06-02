package quvoncuz.events;

import lombok.*;
import quvoncuz.enums.EventType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {

    private Long entityId;

    private EventType eventType;

    private String subjectName;

    private List<String> mails;

    private LocalDateTime dateTime;
}
