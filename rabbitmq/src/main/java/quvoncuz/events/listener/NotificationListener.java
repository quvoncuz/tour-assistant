package quvoncuz.events.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import quvoncuz.config.RabbitMQConfig;
import quvoncuz.enums.EventType;
import quvoncuz.events.*;
import quvoncuz.service.EmailService;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final EmailService emailService;
    private final ObjectMapper mapper;

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void handleNotification(Message message) {
        try {

            JsonNode root = mapper.readTree(message.getBody());

            NotificationEvent event = mapper.treeToValue(root, NotificationEvent.class);
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
                default -> log.warn("Noma'lum event type");
            }

        } catch (IOException e) {
            log.error("Notification xatosi: {}", e.getMessage());
            throw new RuntimeException("Xato: " + e.getMessage());
        }
    }

}
