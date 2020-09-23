package com.ghsong.myboard.modules.repository;

import com.ghsong.myboard.modules.entiry.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void saveUserTest() {
        String uid = "ghsong";
        String name = "송건호";

        // given
        userRepository.save(
                User.builder()
                .uid(uid)
                .password(passwordEncoder.encode("ghsong1234"))
                .name(name)
                .roles(Collections.singletonList("USER_ROLE"))
                .build()
        );

        // when
        Optional<User> user = userRepository.findByUid(uid);

        // then
        assertNotNull(user);
        assertTrue(user.isPresent());
        assertEquals(user.get().getName(), name);
    }

}