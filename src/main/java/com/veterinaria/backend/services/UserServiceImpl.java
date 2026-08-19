package com.veterinaria.backend.services;

import com.veterinaria.backend.domain.Rol;
import com.veterinaria.backend.domain.User;
import com.veterinaria.backend.repositories.UserRep;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRep userRep;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(User user) {
        if (userRep.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRol() == null) {
            user.setRol(Rol.USER);
        }

        userRep.save(user);
    }

    @Transactional(readOnly = true)
    public User buscarPorEmail(String email) {
        return userRep.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    @Transactional(readOnly = true)
    public List<User> listarTodos() {
        return userRep.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRep.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }
}