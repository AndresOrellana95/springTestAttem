package com.demo.donations.controller;

import java.util.HashMap;
import java.util.Map;

import com.demo.donations.model.objects.CustomResponse;
import com.demo.donations.model.objects.LoginRequest;
import com.demo.donations.model.objects.RegisterUser;
import com.demo.donations.service.UserService;
import com.demo.donations.service.ValidateTokenService;
import com.demo.donations.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    ValidateTokenService validateToken;

    @GetMapping("")
    public ModelAndView home() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("prueba", "prueba");
        return new ModelAndView("/index", model);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomResponse> login(@RequestBody LoginRequest loginRequest) {
        CustomResponse response = null;
        try {
            response = userService.loginUser(loginRequest);
        } catch(Exception e) {
            response = new CustomResponse();
            response.setCode(Utils.CODE_ERROR);
            response.response(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value="/register")
    public ResponseEntity<CustomResponse> postMethodName(@RequestBody RegisterUser request) {
        CustomResponse response = null;
        try {
            response = userService.registerUser(request);
        } catch(Exception e) {
            response = new CustomResponse();
            response.setCode(Utils.CODE_ERROR);
            response.response(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/validateToken")
    public ResponseEntity<CustomResponse> validate( @RequestHeader("token") String token) {
        CustomResponse response = new CustomResponse();
        try {
            boolean active = validateToken.validateToken(token);
            if(active) {
                response.setCode(Utils.CODE_OK);
                response.response("Activo");
            } else {
                response.setCode(Utils.CODE_UNAUTORIZED);
                response.response("Token expirado");
            }
        } catch(Exception e) {
            response = new CustomResponse();
            response.setCode(Utils.CODE_ERROR);
            response.response(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
