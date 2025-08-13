package inmobi.guess_number.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GuessNumberResponse implements Serializable {
    boolean correct;
    Integer score;
    String message;
}
