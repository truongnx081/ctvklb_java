package ctv.core_service.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterRequest implements Serializable {
    private String username;
    private String password;
    private String email;
}
