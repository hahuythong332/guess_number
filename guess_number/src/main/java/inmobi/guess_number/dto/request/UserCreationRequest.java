package inmobi.guess_number.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, max = 16,message = "Username must be at least 3 characters")
    String username;

    @NotNull
    @Size(min = 8, max = 16,message = "Password must be between 8 and 16 characters")
    String password;
    String firstName;
    String lastName;
    Integer score;
    Integer turn;
}
