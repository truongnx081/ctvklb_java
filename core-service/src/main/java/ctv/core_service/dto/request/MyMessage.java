package ctv.core_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyMessage {

    String content;
    String sender;

    @Override
    public String toString() {
        return "MyMessage{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
