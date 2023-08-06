package io.getarrays.springsecurity.service;

import io.getarrays.springsecurity.entity.User;
import io.getarrays.springsecurity.repository.RoleRepository;
import io.getarrays.springsecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} with name: ", user.getName());
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user with name: {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        user.getRoles().add(roleRepository.findByName(roleName));
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }
}
