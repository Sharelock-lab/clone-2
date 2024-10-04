package com.place4code.clone.service;

import com.place4code.clone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Użytkownik nie istnieje."));
    }

    public void updateUserContext(final String userName) {
        final UserDetails userDetails = loadUserByUsername(userName);
        final Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        final Authentication updatedAuth = new UsernamePasswordAuthenticationToken(userDetails, currentAuth.getCredentials(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(updatedAuth);
    }

}
