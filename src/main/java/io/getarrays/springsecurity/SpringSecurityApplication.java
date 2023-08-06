package io.getarrays.springsecurity;

import io.getarrays.springsecurity.entity.Role;
import io.getarrays.springsecurity.entity.User;
import io.getarrays.springsecurity.service.RoleService;
import io.getarrays.springsecurity.service.UserService;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    CommandLineRunner runner(RoleService roleService) {
        return args -> {
            roleService.saveRole(new Role(null, "ROLE_USER"));
            roleService.saveRole(new Role(null, "ROLE_MANAGER"));
            roleService.saveRole(new Role(null, "ROLE_ADMIN"));
            roleService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
        };
    }

    CommandLineRunner runnerUser(UserService userService) {
        return args -> {
            userService.saveUser(
                new User(null, "Oleh Noskov", "oleh", "password", new ArrayList<>()));
            userService.saveUser(
                new User(null, "Fedir Kysil", "fedir", "password", new ArrayList<>()));
            userService.saveUser(
                new User(null, "Olha Bondar", "olha", "password", new ArrayList<>()));
            userService.saveUser(
                new User(null, "Natalia Kushnir", "natalia", "password", new ArrayList<>()));

            userService.addRoleToUser("oleh", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("fedir", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("olha", "ROLE_ADMIN");
            userService.addRoleToUser("natalia", "ROLE_MANAGER");
            userService.addRoleToUser("oleh", "ROLE_USER");
            userService.addRoleToUser("fedir", "ROLE_MANAGER");
        };
    }
}
