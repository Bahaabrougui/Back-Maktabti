package com.insat.maktabti.services.impl;

import com.insat.maktabti.domain.Role;
import com.insat.maktabti.domain.RoleName;
import com.insat.maktabti.domain.User;
import com.insat.maktabti.repositories.RoleRepository;
import com.insat.maktabti.repositories.UserRepository;
import com.insat.maktabti.services.UserDetailsServiceImpl;
import com.insat.maktabti.services.UserService;
import com.insat.maktabti.services.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service

public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final UserDetailsServiceImpl userDetailsService;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;


    public UserServiceImpl(UserRepository userRepository, UserDetailsServiceImpl userDetailsService, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    /**
     * Save a user.
     *
     * @param user the entity to save
     * @return the persisted entity
     */
    @Override
    public User save(User user) {
        log.debug("Request to save user : {}", user);
        User result = userRepository.save(user);
        return result;
    }

    /**
     * Get all the user.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        log.debug("Request to get all users");
        return userRepository.findAll();
    }


    /**
     * Get one user by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findOne(Long id) {
        log.debug("Request to get user : {}", id);
        return userRepository.findById(id);
    }

    /**
     * Delete the user by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete user : {}", id);
        userRepository.deleteById(id);
    }

    /**
     * Delete the "username" user
     *
     * @param username the username of the entity
     */
    public void deleteByUsername(String username) {

        userRepository.findByUsername(username).ifPresent(user -> {
            userRepository.delete(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public User updateUser(UserDTO userDTO) {

        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAdress());
        user.setBirthDate(userDTO.getBirthDate());
        user.setPassword(encoder.encode(userDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());

        user.setRoles(roles);
        userRepository.save(user);

        return user;


    }

}
