package com.backend.petfeeder.Repository;

import com.backend.petfeeder.DAO.PetDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetDAO, Integer> {
    List<PetDAO> findByNameEqualsAndBreedEqualsAndBirthDateEquals(String name, String breed, Date birthDate);

}
