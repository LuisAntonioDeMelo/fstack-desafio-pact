package com.vagas.app.domain;

import com.vagas.app.application.resources.dto.Register;
import com.vagas.app.domain.model.Pessoa;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "tb_user")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column( name = "user_id")
    private String id;
    private String login;
    private String password;

    @Setter
    private Role role;

    @Setter
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pessoa pessoa;

    public User(String login, String password, Role role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Role.ANALISTA_RH){
            return List.of( new SimpleGrantedAuthority("ROLE_ANALISTA"));
        }
        else return List.of(new SimpleGrantedAuthority("ROLE_CANDIDATO"));
    }

    public static User getUser(Register data, String encryptedPass) {
        return User.builder()
                .login(data.username())
                .role(data.role())
                .password(encryptedPass)
                .build();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
