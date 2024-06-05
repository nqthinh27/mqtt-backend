package com.project.mqtt.web.rest;

import com.project.mqtt.domain.User;
import com.project.mqtt.repository.UserRepository;
import com.project.mqtt.security.jwt.TokenProvider;
import com.project.mqtt.service.dto.LoginDTO;
import com.project.mqtt.service.dto.UserDTO;
import com.project.mqtt.service.mapper.UserMapper;
import com.project.mqtt.web.rest.vm.LoginVM;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private UserRepository userRepository;

    private final UserMapper userMapper;


    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserRepository userRepository, UserMapper userMapper) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PostMapping("/authenticate")
    public LoginDTO authorize(@Valid @RequestBody LoginVM loginVM) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        User user = userRepository.findByLogin(loginVM.getUsername()).get();
        UserDTO usersDTO = userMapper.userToUserDTO(user);
        if (usersDTO != null) {
            LoginDTO login = new LoginDTO();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + jwt);
            login.setHttpHeaders(httpHeaders);
            login.setCustomUserDetails(usersDTO);
            return login;
        }
        throw new ServiceException("login.pass.false");
    }
    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
