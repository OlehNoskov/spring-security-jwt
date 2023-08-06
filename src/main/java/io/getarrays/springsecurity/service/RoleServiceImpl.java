package io.getarrays.springsecurity.service;

import io.getarrays.springsecurity.entity.Role;
import io.getarrays.springsecurity.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role with name: {}", role.getName());
        return roleRepository.save(role);
    }
}
