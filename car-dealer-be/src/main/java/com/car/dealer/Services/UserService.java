package com.car.dealer.Services;

import java.util.Objects;

import com.car.dealer.Security.JwtService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.car.dealer.Klasy.User.User;
import com.car.dealer.Klasy.User.UserRepo;
import com.car.dealer.Requests.EditPasswordRequest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private static final HttpStatusCode HTTP_OK = HttpStatusCode.valueOf(200);
    private static final HttpStatusCode HTTP_BAD = HttpStatusCode.valueOf(400);



    public <T> ResponseEntity changePassword(EditPasswordRequest passwordRequest) {
        if(Strings.isBlank(passwordRequest.getOldPassword()) || Strings.isBlank(passwordRequest.getNewPassword())){
            return createResponseEntity("Puste hasła!", HTTP_BAD);
        }
        if(!passwordRequest.passwordMatch()) {
            return createResponseEntity("Podane hasła są niepoprawne!", HTTP_BAD);
        }
        final User user = userRepo.findByEmail(passwordRequest.getEmail()).orElse(null);
        if(Objects.isNull(user)){
            return createResponseEntity("Nie znaleziono użytkownika w bazie!", HTTP_BAD);
        }
        if(passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())){
            return createResponseEntity("Podane hasło jest niepoprawne!", HTTP_BAD);
        }
        user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
        userRepo.save(user);
        return createResponseEntity("Zmieniono hasło użytkownika: ".concat(user.getNick()), HTTP_OK);
    }

    private <T> ResponseEntity createResponseEntity(String msg, HttpStatusCode httpCode) {
        log.info(msg);
        return new ResponseEntity<T>((T) msg, httpCode);
    }

    public User getUserFromJwt(HttpServletRequest servletRequest){
        final String authHeader = servletRequest.getHeader("Authorization");
        System.out.println(authHeader);
        final String jwt = authHeader.substring(7);
        System.out.println(jwt);
        return userRepo.findByEmail(jwtService.extractUsername(jwt)).orElse(null);
    }

}