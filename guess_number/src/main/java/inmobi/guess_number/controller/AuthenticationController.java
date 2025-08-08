package inmobi.guess_number.controller;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import inmobi.guess_number.dto.request.ApiResponse;
import inmobi.guess_number.dto.request.AuthenticationRequest;
import inmobi.guess_number.dto.request.IntrospectRequest;
import inmobi.guess_number.dto.request.UserCreationRequest;
import inmobi.guess_number.dto.response.AuthenticationResponse;
import inmobi.guess_number.dto.response.IntrospectResponse;
import inmobi.guess_number.entity.User;
import inmobi.guess_number.service.AuthenticationService;
import inmobi.guess_number.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;
    UserService userService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws KeyLengthException {
        var result = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/register")
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> response = new ApiResponse<>();

        response.setMessage("success");
        response.setCode(200);
        response.setResult(userService.createUser(request));

        return response;
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws JOSEException, ParseException {
        var result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }
}
