package com.hmsapp.controller;

import com.hmsapp.entity.User;
import com.hmsapp.payload.JwtToken;
import com.hmsapp.payload.LoginDto;
import com.hmsapp.payload.ProfileDto;
import com.hmsapp.payload.UserDto;
import com.hmsapp.service.AuthService;
import com.hmsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    private UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    ///api/auth/sign-up
    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody UserDto dto) {
        try {
            UserDto userDto = authService.createUser(dto);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            String msg = ex.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.CONFLICT); // HTTP 409 for conflict
        }
    }

    @PostMapping("/property/sign-up")
    public ResponseEntity<?> createPropertyOwnerAccount(@RequestBody UserDto dto) {
        try {
            UserDto userDto = authService.createPropertyOwnerAccount(dto);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            String msg = ex.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.CONFLICT); // HTTP 409 for conflict
        }
    }

    @PostMapping("/blog/sign-up")
    public ResponseEntity<?> createBlogManagerAccount(@RequestBody UserDto dto) {
        try {
            UserDto userDto = authService.createBlogManagerAccount(dto);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            String msg = ex.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.CONFLICT); // HTTP 409 for conflict
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        String token = userService.verifyLogin(loginDto);
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(token);
        jwtToken.setType("JWT");
        if (token!=null) {
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ProfileDto> getUserProfile(@AuthenticationPrincipal User user){
        ProfileDto profileDto = new ProfileDto();
        profileDto.setName(user.getName());
        profileDto.setUsername(user.getUsername());
        profileDto.setMobile(user.getMobile());
        profileDto.setEmail(user.getEmail());
        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
//    }


}
