package inmobi.guess_number.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserResponse {
    String id;
    String username;
    String firstName;
    String lastName;
    Integer score;
    Integer turn;
}
