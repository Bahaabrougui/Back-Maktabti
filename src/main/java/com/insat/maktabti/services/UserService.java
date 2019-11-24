package com.insat.maktabti.services;

import com.insat.maktabti.domain.User;
import com.insat.maktabti.services.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Save a user.
     *
     * @param user the entity to save
     * @return the persisted entity
     */
    User save(User user);


    /**
     * Get all the users.
     *
     * @return the list of entities
     */
    List<User> findAll();


    /**
     * Get the "id" user.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<User> findOne(Long id);

    /**
     * Delete the "id" user.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Delete the "username" user
     *
     * @param username the username of the entity
     */
    void deleteByUsername(String username);

    User updateUser(UserDTO userDTO);

}

