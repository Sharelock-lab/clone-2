package com.place4code.clone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 6, max = 64, message = "E-Mail musi mieć od 6 do 64 znaków")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Nazwa konta jest wymagana")
    @Size(max = 64)
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Hasło jest wymagane")
    private String password;

    @NotBlank(message = "Kraj jest wymagany")
    private String country;

    @NotBlank(message = "Opis konta jest wymagany")
    private String description;

    private String activationToken;

    private String resetPasswordToken;

    private boolean enabled;

    @CreatedDate
    private LocalDateTime createdDate;

    @ManyToMany
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public String getCreatedDateAsString() {
        return createdDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
