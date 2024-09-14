package com.backend.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.user.dto.LoginRequest;
import com.backend.user.dto.LoginResponse;
import com.backend.user.dto.Register;
import com.backend.user.dto.UserProfile;
import com.backend.user.entity.User;
import com.backend.user.mapper.UserMapper;
import com.backend.user.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;

    @Override
    public UserProfile register(Register register) throws Exception {

            if (userRepository.existsByEmail(register.email())) {
                throw new Exception(register.email() + "Already Exists");
            }
            User user=userMapper.toUser(register);
            user.setPassword(register.password());
            User savedUser = userRepository.save(user);
            return userMapper.toUserProfile(savedUser);

    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws Exception {

        String username = loginRequest.email();
        String password = loginRequest.password();

        // Fetch user from the database by username
        User user = userRepository.findByEmail(username).orElse(null);

        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(password);

        if (user != null && user.getPassword().equals(password)) {
            return new LoginResponse(user.getName(),user.getId(),user.getPhoneNumber(), "Successful");
        }

        return new LoginResponse(null,null,null, "Unsuccessful");
    }
}
