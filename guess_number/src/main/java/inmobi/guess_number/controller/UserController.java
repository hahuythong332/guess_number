package inmobi.guess_number.controller;

import java.util.List;

import inmobi.guess_number.dto.request.ApiResponse;
import inmobi.guess_number.dto.request.GuessNumberRequest;
import inmobi.guess_number.dto.request.UserUpdateRequest;
import inmobi.guess_number.dto.response.GuessNumberResponse;
import inmobi.guess_number.dto.response.UserResponse;
import inmobi.guess_number.entity.UserScoreNumber;
import inmobi.guess_number.service.UserScoreNumberService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import inmobi.guess_number.dto.request.UserCreationRequest;
import inmobi.guess_number.entity.User;
import inmobi.guess_number.service.UserService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("user")
public class UserController {


    UserService userService;
    UserScoreNumberService userScoreNumberService;

    @GetMapping("me")
    ApiResponse<UserResponse> profile(@AuthenticationPrincipal Jwt jwt) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        String username = jwt.getSubject();

        response.setCode(200);
        response.setResult(userService.getProfileByUserName(username));

        return response;
    }

    @GetMapping("leaderboard")
    ApiResponse<List<UserResponse>> getLeaderboard() {
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();

        response.setMessage("success");
        response.setCode(200);
        response.setResult(userService.getLeaderBoard());

        return response;
    }

    @GetMapping
    ApiResponse<List<User>> getUsers() {
        ApiResponse<List<User>> response = new ApiResponse<>();

        response.setMessage("success");
        response.setCode(200);
        response.setResult(userService.getUsers());

        return response;
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        ApiResponse<UserResponse> response = new ApiResponse<>();

        response.setMessage("success");
        response.setCode(200);
        response.setResult(userService.getUser(userId));

        return response;
    }

    @PutMapping("/{userId}")
    ApiResponse<User> updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        ApiResponse<User> response = new ApiResponse<>();

        response.setMessage("success");
        response.setCode(200);
        response.setResult(userService.updateUser(userId, request));


        return response;
    }

    @PostMapping("/{userId}/generate-number")
    ApiResponse<String> generateNumber(@PathVariable("userId") String userId) {
        ApiResponse<String> response = new ApiResponse<>();

        response.setMessage("success");
        response.setCode(200);
        try {
            userScoreNumberService.generateUserScoreNumber(userId);
            response.setResult("Generate Successfully");
        } catch (Exception e) {
            response.setMessage("Generate Failed");
            response.setCode(404);
        }

        return response;
    }

    @PostMapping("/{userId}/buy-turns")
    ApiResponse<UserResponse> buyTurn(@PathVariable("userId") String userId) {
        ApiResponse<UserResponse> response = new ApiResponse<>();

        response.setMessage("success");
        response.setCode(200);
        response.setResult(userService.buyTurns(userId));

        return response;
    }

    @PostMapping("/{userId}/guess")
    ApiResponse<GuessNumberResponse> guess(@PathVariable("userId") String userId, @RequestBody GuessNumberRequest request) {
        ApiResponse<GuessNumberResponse> response = new ApiResponse<>();

        response.setMessage("success");
        response.setCode(200);
        response.setResult(userScoreNumberService.guessNumber(userId, request));

        return response;
    }

}
