package com.backend.petfeeder.DTO;

import com.backend.petfeeder.DAO.PetDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDTO {
    private String name;
    private String breed;
    private Date birthDate;

    public PetDAO toDAO() {
        return PetDAO.builder()
                .name(name)
                .breed(breed)
                .birthDate(birthDate)
                .build();
    }
}
