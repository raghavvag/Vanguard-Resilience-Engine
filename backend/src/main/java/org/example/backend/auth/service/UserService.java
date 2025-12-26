package org.example.backend.auth.service;

import org.example.backend.auth.entity.User;
import org.example.backend.auth.entity.enums.UserRole;

public interface UserService {
    User createuser(String name, String email, String rawPassword, UserRole userRole);
    User getByEmail(String email);
    User authenticate(String email, String rawPassword);

}
