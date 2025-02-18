package ctv.notification_service.controller;


import event.dto.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component

public class NotificationController {
    @KafkaListener(topics = "notification-delivery")
    public void listenNotification(NotificationEvent message){
        log.info("Message received: {}",message);
    }
}

