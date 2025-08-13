package inmobi.guess_number.service;

import java.util.List;

import inmobi.guess_number.dto.request.UserUpdateRequest;
import inmobi.guess_number.entity.UserScoreNumber;
import inmobi.guess_number.exception.AppException;
import inmobi.guess_number.exception.ErrorCode;
import inmobi.guess_number.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import inmobi.guess_number.dto.request.UserCreationRequest;
import inmobi.guess_number.dto.response.UserResponse;
import inmobi.guess_number.entity.User;
import inmobi.guess_number.repository.UserRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Cacheable(value = "users", key = "#username")
    public UserResponse getProfileByUserName(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Cacheable(value = "users")
    public List<UserResponse> getLeaderBoard() {
        return userRepository.findTop10ByOrderByScoreDesc().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found!")
        ));
    }

    public User updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found!")
        );

        userMapper.updateUser(user, request);
        ;
        return userRepository.save(user);
    }

    public UserResponse buyTurns(String id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));

        var TURNS = 5;

        user.setTurn(user.getTurn() + TURNS);
        return userMapper.toUserResponse(userRepository.save(user));
    }


}
