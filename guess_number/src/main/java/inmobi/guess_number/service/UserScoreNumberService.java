package inmobi.guess_number.service;

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

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserScoreNumberService {

    UserRepository userRepository;
    UserScoreNumberRepository userScoreNumberRepository;

    //USER WIN RATES CAN BE MODIFIED
    private static final double USER_WIN_RATES = 0.05;
    private static final double EASY_SECRET_NUMBER_AMOUNT = 5;

    public void generateUserScoreNumber(String userId) {

        UserScoreNumber targetUserScore = new UserScoreNumber();

        UserScoreNumber userScoreNumber = userScoreNumberRepository.findByUserId(userId);

        if (userScoreNumber != null) {
            targetUserScore = userScoreNumber;
        } else {
            targetUserScore.setUserId(userId);
        }

        int maxNumberRange = (int) Math.round(EASY_SECRET_NUMBER_AMOUNT / USER_WIN_RATES) ;

        int number = ThreadLocalRandom.current().nextInt(1, maxNumberRange);

        targetUserScore.setNumber(number);

        userScoreNumberRepository.save(targetUserScore);
    }

    public GuessNumberResponse guessNumber(String userId, GuessNumberRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));
        UserScoreNumber userScoreNumber = userScoreNumberRepository.findByUserId(userId);
        var turns = user.getTurn();
        if (turns == 0) {
            throw new AppException(ErrorCode.NO_TURNS_LEFT);
        }

        var newTurns = turns - 1;
        user.setTurn(newTurns);

        var number = request.getNumber();
        var resultNumber = userScoreNumber.getNumber();
        var isCorrect = number == resultNumber;
        var message = isCorrect ? "The answer is correct." : "The answer is incorrect.";

        userRepository.save(user);

        if (isCorrect) {
            user.setScore(user.getScore() + 1);
            userScoreNumberRepository.save(userScoreNumber);
        }

        //RE-GENERATE NUMBER
        this.generateUserScoreNumber(userId);


        return GuessNumberResponse.builder()
                .message(message)
                .correct(isCorrect)
                .score(user.getScore())
                .build();
    }

}
