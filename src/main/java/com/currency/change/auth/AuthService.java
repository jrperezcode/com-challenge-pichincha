package com.currency.change.auth;

import com.currency.change.jwt.JwtService;
import com.currency.change.User.Role;
import com.currency.change.User.User;
import com.currency.change.User.UserRepository;
import com.currency.change.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private WebClient.Builder webclient;

    public AuthResponse register(RegisterRequest request){

        Flux<UserGoRest> fluxUserGoRest = webclient.build().get().uri("https://gorest.co.in/public/v2/users").retrieve().bodyToFlux(UserGoRest.class);
        List<UserGoRest> listGoRestMatchUser = fluxUserGoRest.collectList().block();
        boolean isMatchUser =  listGoRestMatchUser.stream().anyMatch(user -> user.getName().equalsIgnoreCase(request.getUsername()));
        if(isMatchUser){
            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode( request.getPassword()))
                    .firstname(request.getFirstname())
                    .lastname(request.lastname)
                    .country(request.getCountry())
                    .role(Role.USER)
                    .build();

            userRepository.save(user);

            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
        } else {
            throw new BusinessException("ERROR_AUTH_01",
                                        "username " + request.getUsername() + " no se encontro en el servicio gorest.com.in",
                                        HttpStatus.UNAUTHORIZED);
        }
    }

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

}
