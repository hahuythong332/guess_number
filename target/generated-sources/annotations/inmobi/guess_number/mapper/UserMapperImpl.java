package inmobi.guess_number.mapper;

import inmobi.guess_number.dto.request.UserCreationRequest;
import inmobi.guess_number.dto.request.UserUpdateRequest;
import inmobi.guess_number.dto.response.UserResponse;
import inmobi.guess_number.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-08T18:19:14+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserCreationRequest userCreationRequest) {
        if ( userCreationRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userCreationRequest.getUsername() );
        user.setPassword( userCreationRequest.getPassword() );
        user.setFirstName( userCreationRequest.getFirstName() );
        user.setLastName( userCreationRequest.getLastName() );
        user.setScore( userCreationRequest.getScore() );
        user.setTurn( userCreationRequest.getTurn() );

        return user;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.firstName( user.getFirstName() );
        userResponse.lastName( user.getLastName() );
        userResponse.score( user.getScore() );
        userResponse.turn( user.getTurn() );

        return userResponse.build();
    }

    @Override
    public void updateUser(User user, UserUpdateRequest userUpdateRequest) {
        if ( userUpdateRequest == null ) {
            return;
        }

        user.setPassword( userUpdateRequest.getPassword() );
        user.setFirstName( userUpdateRequest.getFirstName() );
        user.setLastName( userUpdateRequest.getLastName() );
        user.setScore( userUpdateRequest.getScore() );
        user.setTurn( userUpdateRequest.getTurn() );
    }
}
