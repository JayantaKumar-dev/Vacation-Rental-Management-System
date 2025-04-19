package com.hmsapp.service;

import com.hmsapp.entity.User;
import com.hmsapp.payload.UserDto;
import com.hmsapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public AuthService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    User mapToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    UserDto mapToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public UserDto createUser(UserDto dto) {
        User user = mapToEntity(dto);
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        Optional<User> opMobile = userRepository.findByMobile(user.getMobile());
        if (opMobile.isPresent()) {
            throw new RuntimeException("Mobile already exists");
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        user.setRole("ROLE_USER"); //the ROLE_ prefix is bound for all roles
        User saveUser = userRepository.save(user);
        UserDto userDto = mapToDto(saveUser);
        return userDto;
    }

    public UserDto createPropertyOwnerAccount(UserDto dto) {
        User user = mapToEntity(dto);
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        Optional<User> opMobile = userRepository.findByMobile(user.getMobile());
        if (opMobile.isPresent()) {
            throw new RuntimeException("Mobile already exists");
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        user.setRole("ROLE_OWNER"); //the ROLE_ prefix is bound for all roles
        User saveUser = userRepository.save(user);
        return mapToDto(saveUser);
    }

    public UserDto createBlogManagerAccount(UserDto dto) {
        User user = mapToEntity(dto);
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        Optional<User> opMobile = userRepository.findByMobile(user.getMobile());
        if (opMobile.isPresent()) {
            throw new RuntimeException("Mobile already exists");
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        user.setRole("ROLE_BLOGMANAGER"); //the ROLE_ prefix is bound for all roles
        User saveUser = userRepository.save(user);
        return mapToDto(saveUser);
    }
}
