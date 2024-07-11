package com.rmeunier.catworld.cat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmeunier.catworld.user.model.UserAccount;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @NonNull
    @NotNull
    @Column(name = "cat_id")
    private UUID catId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id")
    @JsonBackReference("user-cats")
    private UserAccount userAccount;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // Basic information

    @NonNull
    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id", nullable = false)
    @JsonBackReference("breed-cats")
    private Breed breed;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Color is required")
    @Column(name = "color")
    private CatColor color;

    @Column(name = "birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    // Age in days
    @Transient
    private int age;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender is required")
    @NotEmpty(message = "Gender cannot be empty")
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "weight")
    private int weight;

    @Column(name = "is_fixed")
    private boolean isFixed;

    // Personality traits

    @Column(name = "intelligence")
    private int intelligence;

    @Column(name = "sociability")
    private int sociability;

    @Column(name = "activity")
    private int activity;

    @Column(name = "curiosity")
    private int curiosity;

    @Column(name = "independence")
    private int independence;

    @Column(name = "stubbornness")
    private int stubbornness;

    @Column(name = "playfulness")
    private int playfulness;

    @Column(name = "affection")
    private int affection;

    // Health and well-being, pre-set

    @Column(name = "happiness")
    private int happiness;

    @Column(name = "hunger")
    private int hunger;

    @Column(name = "thirst")
    private int thirst;

    @Column(name = "health")
    private int health;

    @Column(name = "energy")
    private int energy;

    @Column(name = "cleanliness")
    private int cleanliness;

    // Constructors

    public Cat() {
        this.age = calculateAge();
        setBasicStats();
    }

    // Methods

    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        if (birthDate != null) {
            return (int) ChronoUnit.DAYS.between(birthDate, currentDate);
        } else {
            return 0;
        }
    }

    private void setBasicStats() {
        this.happiness = 50;
        this.hunger = 50;
        this.thirst = 50;
        this.health = 50;
        this.energy = 50;
        this.cleanliness = 50;
    }

    public int feedCat(int foodAmount) {
        hunger -= foodAmount;
        if (hunger < 0) {
            hunger = 0;
        }
        return hunger;
    }

    public int waterCat(int drinkAmount) {
        thirst -= drinkAmount;
        if (thirst < 0) {
            thirst = 0;
        }
        return thirst;
    }

    // TODO: Implement playWithCat method
    public void playWithCat() {
        // Energy should lower
        // Happiness should increase
        // Cleanliness decreases depending on toy

    }
}
