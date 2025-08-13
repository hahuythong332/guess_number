package inmobi.guess_number.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse implements Serializable {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer score;
    private Integer turn;
}
