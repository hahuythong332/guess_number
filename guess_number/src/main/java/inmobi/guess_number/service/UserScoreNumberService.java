package inmobi.guess_number.service;

import com.nimbusds.jose.JOSEException;
import inmobi.guess_number.dto.request.GuessNumberRequest;
import inmobi.guess_number.dto.response.GuessNumberResponse;
import inmobi.guess_number.entity.User;
import inmobi.guess_number.entity.UserScoreNumber;
import inmobi.guess_number.exception.AppException;
import inmobi.guess_number.exception.ErrorCode;
import inmobi.guess_number.repository.UserRepository;
import inmobi.guess_number.repository.UserScoreNumberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserScoreNumberService {

    UserRepository userRepository;
    UserScoreNumberRepository userScoreNumberRepository;

    public UserScoreNumber generateUserScoreNumber(String userId) {

        UserScoreNumber targetUserScore = new UserScoreNumber();

        UserScoreNumber userScoreNumber = userScoreNumberRepository.findByUserId(userId);

        if (userScoreNumber != null) {
            targetUserScore = userScoreNumber;
        } else {
            targetUserScore.setUserId(userId);
        }

        //Random number from 1 to 5
        targetUserScore.setNumber((int) (Math.random() * 5) + 1);

        return userScoreNumberRepository.save(targetUserScore);
    }

    public GuessNumberResponse guessNumber(String userId, GuessNumberRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));
        UserScoreNumber userScoreNumber = userScoreNumberRepository.findByUserId(userId);

        if(user.getTurn() == 0){
            throw new AppException(ErrorCode.NO_TURNS_LEFT);
        }

        var number = request.getNumber();
        var resultNumber = userScoreNumber.getNumber();
        var isCorrect = number == resultNumber;
        var message = isCorrect ? "The answer is correct." : "The answer is incorrect.";

        if (isCorrect) {
            user.setScore(user.getScore() + 1);
            //RESET VALUE NUMBER
            userScoreNumber.setNumber(0);

            userScoreNumberRepository.save(userScoreNumber);
            userRepository.save(user);
        }

        return GuessNumberResponse.builder()
                .message(message)
                .correct(isCorrect)
                .score(user.getScore())
                .build();
    }

}
