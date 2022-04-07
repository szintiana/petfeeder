package com.backend.petfeeder.DAO;

import com.backend.petfeeder.DTO.PetDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="pets")
public class PetDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private String breed;
    @Column
    private Date birthDate;
    @ManyToMany(mappedBy = "pets", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("pets")
    private List<UserDAO> owners;

    public PetDTO toDTO() {
        return PetDTO.builder()
                .name(name)
                .breed(breed)
                .birthDate(birthDate)
                //.owners(owners.stream().map(UserDAO::toDTO).collect(Collectors.toList()))
                .build();
    }
}
