package com.backend.petfeeder.DTO;

import com.backend.petfeeder.DAO.FeedDateDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedDateDTO {
    private PetDTO petDTO;
    private UserDTO userDTO;
    private LocalDateTime date;

    public FeedDateDAO toDAO() {
        return FeedDateDAO.builder()
                .petDAO(petDTO.toDAO())
                .userDAO(userDTO.toDAO())
                .date(date)
                .build();
    }
}
