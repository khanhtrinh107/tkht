package com.example.tkht_backend.controller;

import com.example.tkht_backend.entity.User;
import com.example.tkht_backend.entity.dto.LoginRequest;
import com.example.tkht_backend.entity.dto.ResponseUser;
import com.example.tkht_backend.entity.dto.SignUpRequest;
import com.example.tkht_backend.jwt.JwtTokenProvider;
import com.example.tkht_backend.repository.UserRepository;
import com.example.tkht_backend.security.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest sign){
        User user = new User();
        user.setUsername(sign.getUsername());
        user.setPassword(passwordEncoder.encode(sign.getPassword()));
        user.setFullname(sign.getFullname());
        user.setRole("ROLE_USER");
        userRepository.save(user);
        UserDetail userDetail = new UserDetail();
        userDetail.setUser(user);
        String token = jwtTokenProvider.generateToken(userDetail);
        return new ResponseEntity<>(new ResponseUser(token , user.getUsername()  , (Set<GrantedAuthority>) userDetail.getAuthorities()) , HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        System.out.println("check");
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername() , loginRequest.getPassword()));
            UserDetail user = (UserDetail) authentication.getPrincipal();
            System.out.println("ok");
            String token = jwtTokenProvider.generateToken(user);
            return new ResponseEntity<>(new ResponseUser(token , user.getUsername()  , (Set<GrantedAuthority>) user.getAuthorities()) , HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
