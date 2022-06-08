package com.backend.petfeeder.Repository;

import com.backend.petfeeder.DAO.FeedDateDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FeedDateRepository extends JpaRepository<FeedDateDAO, Integer> {
    @Query("select f from FeedDateDAO f inner join f.userDAO.pets pets " +
            "where f.userDAO.email = ?1 and pets.name = ?2 and f.date = ?3")
    FeedDateDAO findByUserDAO_EmailEqualsAndUserDAO_Pets_NameEqualsAndDateEquals(String email, String name, LocalDateTime date);

    List<FeedDateDAO> findByUserDAO_Username(String username);
}
