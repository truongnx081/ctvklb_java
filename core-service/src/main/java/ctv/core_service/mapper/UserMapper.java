package ctv.core_service.mapper;

import ctv.core_service.dto.request.UserCreationRequest;
import ctv.core_service.dto.request.UserUpdationRequest;
import ctv.core_service.dto.response.UserResponse;
import ctv.core_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdationRequest request);
}
