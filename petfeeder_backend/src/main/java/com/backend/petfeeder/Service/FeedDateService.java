package com.backend.petfeeder.Service;

import com.backend.petfeeder.DAO.FeedDateDAO;
import com.backend.petfeeder.DTO.FeedDateDTO;
import com.backend.petfeeder.Repository.FeedDateRepository;
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

    public FeedDateDTO addFeedDate(FeedDateDTO feedDateDTO) {
        return feedDateRepository.save(feedDateDTO.toDAO()).toDTO();
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
