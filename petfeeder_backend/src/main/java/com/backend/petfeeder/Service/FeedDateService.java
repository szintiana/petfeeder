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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedDateService {
    @Autowired
    @Lazy
    FeedDateRepository feedDateRepository;
    @Autowired
    UserRepository userRepository;

    public FeedDateDTO addFeedDate(FeedDateDTO feedDateDTO) {
        UserDAO userDAO = userRepository.findByEmailEquals(feedDateDTO.getUserDTO().getUsername());
        for (PetDAO petDAO : userDAO.getPets()) {
            if (petDAO.getName().equals(feedDateDTO.getPetDTO().getName())) {
                FeedDateDAO feedDateDAO = feedDateDTO.toDAO();
                feedDateDAO.setPetDAO(petDAO);
                feedDateDAO.setUserDAO(userDAO);
                return feedDateRepository.save(feedDateDTO.toDAO()).toDTO();
            }
        }
        return null;
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
