package com.rmeunier.catworld.cat.model.dto;

import com.rmeunier.catworld.cat.model.CatColor;
import com.rmeunier.catworld.cat.model.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CatDto {
    private UUID catId;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Breed id is required")
    private UUID breedId;

    @NotNull(message = "Color is required")
    @NotEmpty(message = "Color cannot be empty")
    private CatColor color;

    private LocalDate birthDate;

    @NotNull(message = "Gender is required")
    @NotEmpty(message = "Gender cannot be empty")
    private Gender gender;

    private int weight;
    private boolean isFixed;

    private int intelligence;
    private int sociability;
    private int activity;
    private int curiosity;
    private int independence;
    private int stubbornness;
    private int playfulness;
    private int affection;
    private int happiness;
    private int hunger;
    private int thirst;
    private int health;
    private int energy;
    private int cleanliness;
}
