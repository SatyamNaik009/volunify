package com.backend.user.mapper;

import com.backend.user.dto.Register;
import com.backend.user.dto.UserProfile;
import com.backend.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-14T21:16:41+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(Register register) {
        if ( register == null ) {
            return null;
        }

        User user = new User();

        user.setName( register.name() );
        user.setEmail( register.email() );
        user.setPhoneNumber( register.phoneNumber() );

        return user;
    }

    @Override
    public UserProfile toUserProfile(User user) {
        if ( user == null ) {
            return null;
        }

        String name = null;
        String email = null;
        String phoneNumber = null;

        name = user.getName();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();

        UserProfile userProfile = new UserProfile( name, email, phoneNumber );

        return userProfile;
    }
}
