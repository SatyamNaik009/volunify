package com.backend.user.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.backend.user.dto.Register;
import com.backend.user.dto.UserProfile;
import com.backend.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "password", ignore = true)
    User toUser(Register register);

    UserProfile toUserProfile(User user);
}
