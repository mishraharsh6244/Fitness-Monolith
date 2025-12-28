package com.project.fitness.service;

import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.dto.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class UserService {
    private final UserRepository userRepository;

    public UserResponse register(RegisterRequest registerRequest) {
        User user = User.builder().
                email(registerRequest.getEmail()).
                password(registerRequest.getPassword()).
                firstName(registerRequest.getFirstName()).
                lastName(registerRequest.getLastName())

                .build();
//        User user = new User (
//                null,
//                registerRequest.getEmail(),
//                registerRequest.getPassword(),
//                registerRequest.getFirstName(),
//                registerRequest.getLastName(),
//                Instant.parse("2025-12-20T21:25:44.884Z").
//                        atZone(ZoneOffset.UTC).toLocalDateTime(),
//                Instant.parse("2025-12-20T21:25:44.884Z").
//                        atZone(ZoneOffset.UTC).toLocalDateTime(),
//                List.of(),
//                List.of()
//        );
          User saved =  userRepository.save(user);
       return  mapToResponse(saved);
    }

    private UserResponse mapToResponse(User saved) {
        UserResponse response = new UserResponse();
        response.setId(saved.getId());
        response.setEmail(saved.getEmail());
        response.setPassword(saved.getPassword());
        response.setFirstName(saved.getFirstName());
        response.setLastName(saved.getLastName());
        response.setCreatedAt(saved.getCreatedAt());
        response.setUpdatedAt(saved.getUpdatedAt());
        return response;
    }
}
