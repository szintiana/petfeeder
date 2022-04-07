package com.backend.petfeeder.Repository;

import com.backend.petfeeder.DAO.PetDAO;
import com.backend.petfeeder.DAO.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetDAO, Integer> {
//    @Query("select p from PetDAO p where p.owners = ?1")
//    List<PetDAO> findByOwnersEquals(UserDAO owners);
}
