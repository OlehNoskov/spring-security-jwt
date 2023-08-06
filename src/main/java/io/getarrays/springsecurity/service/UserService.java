package io.getarrays.springsecurity.service;

import io.getarrays.springsecurity.entity.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);

    User getUser(String username);

    void addRoleToUser(String username, String roleName);

    List<User> getUsers();
}
