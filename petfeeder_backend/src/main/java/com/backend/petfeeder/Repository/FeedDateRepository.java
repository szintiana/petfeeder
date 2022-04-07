package com.backend.petfeeder.Repository;

import com.backend.petfeeder.DAO.FeedDateDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedDateRepository extends JpaRepository<FeedDateDAO, Integer> {

}
