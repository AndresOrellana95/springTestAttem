package com.demo.donations.service;

import com.demo.donations.model.objects.CustomResponse;
import com.demo.donations.model.objects.LoginRequest;
import com.demo.donations.model.objects.RegisterUser;

public interface UserService {
    public CustomResponse loginUser(LoginRequest request);
    public CustomResponse registerUser(RegisterUser request);
}
