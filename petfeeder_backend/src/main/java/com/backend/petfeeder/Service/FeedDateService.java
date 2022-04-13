package com.backend.petfeeder.Service;

import com.backend.petfeeder.DAO.FeedDateDAO;
import com.backend.petfeeder.DAO.PetDAO;
import com.backend.petfeeder.DAO.UserDAO;
import com.backend.petfeeder.DTO.FeedDateDTO;
import com.backend.petfeeder.Repository.FeedDateRepository;
import com.backend.petfeeder.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedDateService {
    @Autowired
    @Lazy
    FeedDateRepository feedDateRepository;
    @Autowired
    @Lazy
    UserRepository userRepository;

    public FeedDateDTO addFeedDate(String email, String petName, String date) {
        UserDAO userDAO = userRepository.findByEmailEquals(email);
        for (PetDAO petDAO : userDAO.getPets()) {
            if (petDAO.getName().equals(petName)) {
                FeedDateDAO feedDateDAO = new FeedDateDAO();
                feedDateDAO.setPetDAO(petDAO);
                feedDateDAO.setUserDAO(userDAO);
                feedDateDAO.setDate(LocalDateTime.parse(date));
                return feedDateRepository.save(feedDateDAO).toDTO();
            }
        }
        return null;
    }

    public void removeFeedDate(String email, LocalDateTime date, String petName) {
        FeedDateDAO feedDateDAO = feedDateRepository
                .findByUserDAO_EmailEqualsAndUserDAO_Pets_NameEqualsAndDateEquals(email, petName, date);
        if (feedDateDAO != null) {
            feedDateRepository.delete(feedDateDAO);
        }
    }

    public List<FeedDateDTO> getAll() {
        List<FeedDateDAO> feedDateList = feedDateRepository.findAll();
        if (!feedDateList.isEmpty()) {
            return feedDateList.stream().map(FeedDateDAO::toDTO).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
