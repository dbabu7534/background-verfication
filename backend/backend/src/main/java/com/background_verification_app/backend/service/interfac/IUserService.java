package com.background_verification_app.backend.service.interfac;

import com.background_verification_app.backend.dto.request.LoginRequest;
import com.background_verification_app.backend.dto.Response;
import com.background_verification_app.backend.dto.request.UserRegisterDTO;

public interface IUserService {
    Response register(UserRegisterDTO user);
    Response login(LoginRequest loginRequest);
}
