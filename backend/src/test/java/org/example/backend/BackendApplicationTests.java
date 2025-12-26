package org.example.backend;


import org.example.backend.auth.entity.User;
import org.example.backend.auth.entity.enums.UserRole;
import org.example.backend.auth.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void shouldCreateUserWithEncodedPassword() {
        User user = userService.createuser(
                "Test User",
                "test@example.com",
                "plainPassword",
                UserRole.ADMIN
        );

        assertNotEquals("plainPassword", user.getPassword());
        assertTrue(user.getPassword().startsWith("$2")); // BCrypt prefix
    }

}
