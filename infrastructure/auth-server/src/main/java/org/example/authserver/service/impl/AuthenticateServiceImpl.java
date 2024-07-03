package org.example.authserver.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.authserver.client.UserCommandClient;
import org.example.authserver.client.UserQueryClient;
import org.example.authserver.domain.entity.UserEntity;
import org.example.authserver.domain.request.AuthenticateRequest;
import org.example.authserver.domain.response.AuthenticateResponse;
import org.example.authserver.service.AuthenticateService;
import org.example.sharedlibrary.response.WrapperResponse;
import org.example.userdomain.command.CreateUserCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

    private final UserCommandClient commandClient;
    private final PasswordEncoder passwordEncoder;
    private final UserQueryClient queryClient;

    @Override
    public WrapperResponse register(CreateUserCommand command) {
        if (command == null
                || command.getUsername() == null
                || command.getPassword() == null
                || command.getUsername().isBlank() || command.getPassword().isBlank()) {
            return new WrapperResponse().fail(
                    "Register Fail!", HttpStatus.BAD_REQUEST
            );
        }
        if (queryClient.existsByUsername(command.getUsername())) {
            return new WrapperResponse().fail(
                    "Username Already Exits", HttpStatus.BAD_REQUEST
            );
        }

        command.setPassword(passwordEncoder.encode(command.getPassword()));
        if (!commandClient.create(command)) {
            return new WrapperResponse().fail(
                    "Register Fail!", HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new WrapperResponse().success(
                "Register Successfully!", null
        );
    }


    @Override
    public WrapperResponse authenticate(AuthenticateRequest authRequest) {
        if (authRequest == null
                || authRequest.getUsername() == null
                || authRequest.getPassword() == null
                || authRequest.getUsername().isBlank() || authRequest.getPassword().isBlank()) {
            return new WrapperResponse().fail(
                    "Login Fail!", HttpStatus.BAD_REQUEST
            );
        }

        Optional<UserEntity> optionalUser = queryClient
                .findUserByUsernameAndSoftDeleteIsFalse(authRequest.getUsername());

        if (optionalUser.isEmpty()
                || !passwordEncoder.matches(authRequest.getPassword(), optionalUser.get().getPassword())) {

            return new WrapperResponse().fail(
                    "Username or Password Are Incorrect!", HttpStatus.BAD_REQUEST
            );
        }
        AuthenticateResponse response = new AuthenticateResponse();
        response.setToken(getToken(optionalUser.get()));

        return new WrapperResponse().success(
                "Login Successfully!", response
        );
    }


    @Override
    public ResponseEntity<?> validateToken(String token) {
        try {
            String jwt = token.substring(7);
            String userUsername = extractUsername(jwt);
            if (userUsername != null) {
                Optional<UserEntity> userDetails = queryClient.findUserByUsernameAndSoftDeleteIsFalse(userUsername);
                if (userDetails.isPresent() && isTokenValid(jwt, userDetails.get())) {
                    UserEntity userDetailsResponse = new UserEntity(userUsername);
                    return ResponseEntity.ok(userDetailsResponse);
                }
            }
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expired");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
//        Is Expired ?
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
//        Get time Expire
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String getToken(UserDetails userDetails) {
        return generateToken(userDetails);
    }

    private String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        String SECRET_KEY = "kVJpUBqivo2EAZHtiIUOBrN//1qdagLBiMbipktTouw=";
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
