package com.demo.donations.service.impl;

import com.demo.donations.model.entity.UserEntity;
import com.demo.donations.model.entity.UserLevelEntity;
import com.demo.donations.model.objects.CustomResponse;
import com.demo.donations.model.objects.LoginRequest;
import com.demo.donations.model.objects.RegisterUser;
import com.demo.donations.model.objects.UserDetails;
import com.demo.donations.model.repository.UserLevelRepo;
import com.demo.donations.model.repository.UserRepo;
import com.demo.donations.service.UserService;
import com.demo.donations.service.ValidateTokenService;
import com.demo.donations.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserLevelRepo levelRepo;

    @Autowired
    ValidateTokenService tokenService;

    public CustomResponse loginUser(LoginRequest request) {
        CustomResponse response = new CustomResponse();
        if(!StringUtils.isEmpty(request.getUser()) && !StringUtils.isEmpty(request.getPassword())) {
            UserEntity user = userRepo.findByEmail(request.getUser());
            if(user != null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				boolean passwordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());
                if(passwordMatch) {
                    UserDetails userDetails = new UserDetails();
                    userDetails.setName(user.getName().trim());
                    userDetails.setEmail(user.getEmail());
                    userDetails.setLevel(user.getUserLevel().getIdLevel());
                    String token = tokenService.generateToken(userDetails);
                    response.setCode(Utils.CODE_OK);
                    response.setResponse("Logueo correcto");
                    response.setObjectResponse(token);
                    return response;
                } else {
                    response.setCode(Utils.CODE_UNAUTORIZED);
                    response.setResponse("Contraseña incorrecta");
                }
            } else {
                response.setCode(Utils.CODE_NOT_FOUND);
                response.setResponse("No existe usuario con el correo ingresado");
            }
        } else {
            response.setCode(Utils.CODE_BAD_REQUEST);
            response.setResponse("Parametros incompletos");
        }
        return response;
    }

    public CustomResponse registerUser(RegisterUser request) {
        CustomResponse response = new CustomResponse();
        if(!StringUtils.isEmpty(request.getNames()) && !StringUtils.isEmpty(request.getLastnames()) 
        && !StringUtils.isEmpty(request.getPassword()) && !StringUtils.isEmpty(request.getEmail()) ) {
            boolean existsDocument = userRepo.existsByDocument(request.getDocument());
            boolean existsEmail = userRepo.existsByEmail(request.getEmail());
            if(!existsDocument && !existsEmail) {
                try {
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    UserLevelEntity userLevel = levelRepo.findByIdLevel(2L);
                    UserEntity newUser = new UserEntity();
                    newUser.setDocument(request.getDocument());
                    newUser.setName(request.getNames());
                    newUser.setPassword(passwordEncoder.encode(request.getPassword()));
                    newUser.setLastname(request.getLastnames());
                    newUser.setEmail(request.getEmail());
                    newUser.setUserLevel(userLevel);
                    userRepo.save(newUser);
                    response.setCode(Utils.CODE_OK);
                    response.setResponse("Usuario creado con éxito");
                    return response;
                } catch(Exception e) {
                    response.setCode(Utils.CODE_ERROR);
                    response.setResponse(e.getMessage());
                }
            } else {
                response.setCode(Utils.CODE_BAD_REQUEST);
                String aux = (existsDocument) ? "documento" : "correo"; 
                response.setResponse("Ya hay un usuario registrado con este " + aux);
            }
        } else {
            response.setCode(Utils.CODE_BAD_REQUEST);
            response.setResponse("Parametros incompletos");
        }
        return response;
    }
}
