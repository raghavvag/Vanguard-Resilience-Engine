package org.example.backend.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.auth.entity.User;
import org.example.backend.auth.entity.enums.UserRole;
import org.example.backend.auth.repository.UserRepository;
import org.example.backend.common.exception.BadRequestException;
import org.example.backend.common.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createuser(String name, String email, String rawPassword, UserRole userRole) {
        if(userRepository.existsByEmail(email)){
            throw new BadRequestException("Email already Registered");
        }
        String encodedPassword=passwordEncoder.encode(rawPassword);
        User user =new User(
                name,
                email,
                encodedPassword,
                userRole,
                true

        );

        return userRepository.save(user);

    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }
}
