package com.ghsong.myboard.config.security;

import com.ghsong.myboard.advice.exception.CSignInFailedException;
import com.ghsong.myboard.modules.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        return userRepository.findByUid(uid).orElseThrow(CSignInFailedException::new);
    }
}
