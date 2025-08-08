package com.spring.www.usuarios_backend.services.security;

import com.spring.www.usuarios_backend.entity.Authority;
import com.spring.www.usuarios_backend.repositories.security.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityService implements UserDetailsService {

    @Autowired
    private AuthorityRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Authority authority = repository.findByEmail(email)
                .orElseThrow( () -> new UsernameNotFoundException("usuario con encontrado"));

        List<GrantedAuthority> grantedAuthorities = authority.getRoles()
                .stream()
                .map( role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

        return new User(authority.getEmail(), authority.getPassword(), grantedAuthorities);

    }
}
