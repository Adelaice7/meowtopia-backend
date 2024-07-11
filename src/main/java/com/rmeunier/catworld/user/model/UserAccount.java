package com.rmeunier.catworld.user.model;

import com.rmeunier.catworld.cat.model.Cat;
import com.rmeunier.catworld.shop.model.PetToy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_accounts")
public class UserAccount /* extends User */ {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_account_id")
    private UUID userAccountId;

    @NonNull
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NonNull
    @Column(name = "email", nullable = false)
    private String email;

    @NonNull
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "role")
    private String role;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userAccount")
    private UserProfile userProfile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
    private Set<Cat> cats;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_pet_toys",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_toy_id"))
    private Set<PetToy> petToys;

    public UserAccount(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
