package ctv.core_service.dto.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest implements Serializable {
    private String username;
    private String password;
    private String email;
}
