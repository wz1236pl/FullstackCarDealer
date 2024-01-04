package com.car.dealer.Authentication;



import com.car.dealer.Klasy.User.Role;
import com.car.dealer.Klasy.User.User;
import com.car.dealer.Klasy.User.UserRepo;
import com.car.dealer.Security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(RegisterRequest request) {
        if(userRepo.findByEmail(request.getEmail()).isPresent() || userRepo.findByNick(request.getNick()).isPresent()){
             return AuthenticationResponse.builder().token(null).build();
        }
        var user = User.builder()
                .nick(request.getNick())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(Role.USER)
                .enabled(true)
                .build();
            userRepo.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticatonRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        if(user == null) {
            user = userRepo.findByNick(request.getEmail()).orElseThrow();
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return null;
        }
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    
}
