package com.ghsong.myboard.modules.service;

import com.ghsong.myboard.modules.entiry.User;
import com.ghsong.myboard.modules.entiry.dto.UserDto;
import com.ghsong.myboard.modules.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 사용자 등록처리
     *
     * @param userDto
     * @return
     */
    public User saveUser(UserDto userDto) {
        User user = User.builder()
                .uid(userDto.getUid())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        return userRepository.save(user);
    }

    /**
     * 사용자 업데이트
     *
     * @param id
     * @param userDto
     * @return
     */
    public User updateUser(long id, UserDto userDto) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("dd"));
        user.update(userDto.getName());
        return userRepository.save(user);
    }

    /**
     * 사용자 삭제
     *
     * @param id
     */
    public void deleteUser(long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("dd"));
        userRepository.delete(user);
    }
}
