package quvoncuz.events.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import quvoncuz.config.RabbitMQConfig;
import quvoncuz.events.StatisticsEvent;
import quvoncuz.service.StatisticsService;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticsListener {

    private final StatisticsService statisticsService;
    private final ObjectMapper mapper;

    @RabbitListener(queues = RabbitMQConfig.STATISTICS_QUEUE)
    public void handleStatistics(Message message) {
        try {
            JsonNode root = mapper.readTree(message.getBody());
            StatisticsEvent event = mapper.treeToValue(root, StatisticsEvent.class);

            log.info("Received event: {}", event);
            statisticsService.save(event.getEventType(), event);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
