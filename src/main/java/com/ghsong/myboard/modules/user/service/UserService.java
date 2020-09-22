package com.ghsong.myboard.modules.user.service;

import com.ghsong.myboard.modules.user.User;
import com.ghsong.myboard.modules.user.dto.UserDto;
import com.ghsong.myboard.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 사용자 등록처리
     *
     * @param userDto
     * @return
     */
    public User saveUser(UserDto userDto) {
        return userRepository.save(userDto.toEntity());
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
