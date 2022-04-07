package com.backend.petfeeder.DAO;

import com.backend.petfeeder.DTO.FeedDateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="feed")
public class FeedDateDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private PetDAO petDAO;
    @ManyToOne
    private UserDAO userDAO;
    @Column
    private LocalDateTime date;
    
    public FeedDateDTO toDTO() {
        return FeedDateDTO.builder()
                .petDTO(petDAO.toDTO())
                .userDTO(userDAO.toDTO())
                .date(date)
                .build();
    }
}
