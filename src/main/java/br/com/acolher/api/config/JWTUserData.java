package br.com.acolher.api.config;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Builder
public record JWTUserData(Long userId, String email, List<String> roles) {
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles != null ? roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList() : List.of();
    }
}
