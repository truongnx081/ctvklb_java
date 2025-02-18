package ctv.event.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class NotificationEvent {
    String chanel;
    String recipient;
    String templateCode;
    Map<String, Object>param;
    String subject;
    String body;
}
