package com.background_verification_app.backend.service.impl;

import com.background_verification_app.backend.dto.request.LoginRequest;
import com.background_verification_app.backend.dto.Response;
import com.background_verification_app.backend.dto.request.UserRegisterDTO;
import com.background_verification_app.backend.dto.response.UserDTO;
import com.background_verification_app.backend.entity.User;
import com.background_verification_app.backend.exception.OwnException;
import com.background_verification_app.backend.mapper.Mapper;
import com.background_verification_app.backend.repo.PageOrderRepository;
import com.background_verification_app.backend.repo.UserRepository;
import com.background_verification_app.backend.service.interfac.IUserService;
import com.background_verification_app.backend.utils.JWTUtils;
import com.background_verification_app.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PageOrderRepository pageOrderRepository;


    @Override
    public Response register(UserRegisterDTO userDTO) {
        Response response = new Response();

        User user = Mapper.mapToUser(userDTO);

        try {
            if(user.getEmail().equals("admin@gmail.com")){
                user.setRole("ADMIN");
            }
            if (user.getRole() == null || user.getRole().isBlank()) {
                user.setRole("USER");
            }

            if (userRepository.existsByEmail(user.getEmail())) {
                throw new OwnException(user.getEmail() + " " + "Already Exists");
            }

            user.setPageOrder(pageOrderRepository.findMaxPageOrderId().orElse(0));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);


            UserDTO userDTO1 = Utils.mapUserEntityToUserDTO(savedUser);


            response.setStatusCode(200);
            response.setUser(userDTO1);
            response.setMessage("successful");

        } catch (OwnException exception) {
            response.setStatusCode(400);
            response.setMessage(exception.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Saving a " + e.getMessage());

        }
        return response;
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        Response response = new Response();

        try {
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new OwnException("User Not Found"));

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var token = jwtUtils.generateToken(user);


            if(user.getRole().equals("USER")){
                response.setPageOrders(pageOrderRepository.findPageOrder(user.getPageOrder()));
            }

            response.setToken(token);
            response.setExpirationTime("7 days");

            response.setRole(user.getRole());
            response.setId(user.getId());

            response.setMessage("successfully " + user.getRole() + "Login" );
            response.setStatusCode(200);


        } catch (OwnException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Internal Error : " + e.getMessage());
        }
        return response;

    }


}