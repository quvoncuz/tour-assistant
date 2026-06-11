package quvoncuz.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quvoncuz.events.NotificationEvent;
import quvoncuz.events.StatisticsEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EmailService emailService;
    private final StatisticsService statisticsService;

    public void handleNotification(NotificationEvent event) {
        try {
            switch (event.getEventType()) {
                case TOUR_CREATED -> {

                    emailService.sendToMany(
                            event.getMails(),
                            "Yangi tur yaratildi: " + event.getSubjectName(),
                            "Siz kuzatayotgan agentlik yangi tur qo'shdi!\n\n" +
                                    "Tur nomi: " + event.getSubjectName()
                    );
                }

                case TOUR_UPDATED -> {
                    emailService.sendToMany(
                            event.getMails(),
                            "Tur narxi o'zgardi: " + event.getSubjectName(),
                            "Siz band qilgan tur narxi o'zgardi!\n\n" +
                                    "Tur nomi: " + event.getSubjectName() + "\n" +
                                    "Tasdiqlang yoki bekor qiling"
                    );
                }

                case TOUR_CANCELED -> {
                    emailService.sendToMany(
                            event.getMails(),
                            "Tur bekor qilindi: " + event.getSubjectName(),
                            "Siz band qilgan tur bekor qilindi.\n\n" +
                                    "Tur nomi: " + event.getSubjectName()
                    );
                }

                case AGENCY_APPROVED -> {
                    emailService.sendToMany(
                            event.getMails(),
                            "Agentligingiz tasdiqlandi!",
                            "Tabriklaymiz! \"" + event.getSubjectName() + "\" agentligi tasdiqlandi.\n" +
                                    "Endi turlar yaratishingiz mumkin."
                    );
                }
                case BOOKING_COMPLETED -> {
                    emailService.sendToMany(
                            event.getMails(),
                            "Bronlash tasdiqlandi!",
                            "Hurmatli mijoz" + ",\n\n" +
                                    "Bronlashingiz tasdiqlandi!\n" +
                                    "Tur: " + event.getSubjectName()
                    );
                }
                default -> log.warn("Unknown event type");
            }

        } catch (Exception e) {
            log.error("Notification error: {}", e.getMessage());
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void handleStatistics(StatisticsEvent event) {
        try {
            log.info("Received event: {}", event);
            statisticsService.save(event.getEventType(), event);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
