package fr.chatop.portail.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@Entity
@SuperBuilder
@Getter @Setter
@NoArgsConstructor
@Table(name = "USERS", indexes = @Index(name = "USERS_index", columnList = "email", unique = true))
public class AppUser extends Auditable implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> authorities = new HashSet<>();

    public AppUser(String email, String password, Collection<AppRole> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
