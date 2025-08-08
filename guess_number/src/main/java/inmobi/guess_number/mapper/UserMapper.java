package inmobi.guess_number.mapper;

import inmobi.guess_number.dto.request.UserCreationRequest;
import inmobi.guess_number.dto.request.UserUpdateRequest;
import inmobi.guess_number.dto.response.UserResponse;
import inmobi.guess_number.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
