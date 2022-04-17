package com.backend.petfeeder.Repository;

import com.backend.petfeeder.DAO.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Integer> {
    UserDAO findByUsernameEquals(String username);

    UserDAO findByUsernameEqualsAndPasswordEquals(String username, String password);

    UserDAO findByEmailEqualsAndPasswordEquals(String email, String password);

    UserDAO findByTokenEquals(String token);

    UserDAO findByEmailEquals(String email);
}
