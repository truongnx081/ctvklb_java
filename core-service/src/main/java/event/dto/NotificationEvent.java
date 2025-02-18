package event.dto;

import java.util.Map;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class NotificationEvent {
    String chanel;
    String recipient;
    String templateCode;
    Map<String, Object> param;
    String subject;
    String body;
}
