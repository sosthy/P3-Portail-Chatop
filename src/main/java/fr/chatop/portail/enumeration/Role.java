package fr.chatop.portail.enumeration;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Getter
public enum Role implements GrantedAuthority {
    ROLE_USER("USER"), ROLE_ADMIN("ADMIN");

    private String value;

    private Role(String value) {
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}