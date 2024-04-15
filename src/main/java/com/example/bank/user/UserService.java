package com.example.bank.user;

import com.example.bank._core.errors.exception.Exception400;
import com.example.bank._core.errors.exception.Exception401;
import com.example.bank._core.utils.JwtUtil;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public String 로그인(String username, String password){
        User user = userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new Exception401("유저네임 혹은 패스워드가 틀렸습니다"));
        String jwt = JwtUtil.create(user);
        return jwt;
    }

    @Transactional
    public void 회원가입(UserRequest.JoinDTO reqDTO) {
        // 1. 아이디 중복 체크
        if (userRepository.findByUsername(reqDTO.getUsername()).isPresent()) {
            throw new Exception400("이미 사용중인 username입니다.");
        }

        // 2. 아이디 길이 제한
        if (reqDTO.getUsername().length() < 8 || reqDTO.getUsername().length() > 20) {
            throw new Exception400("username의 길이는 8자보다 크거나 20자보다 작아야 합니다.");
        }

        // 3. 특수문자 포함 체크
        if (reqDTO.getUsername().matches(".*[^a-zA-Z0-9].*")) {
            throw new Exception400("username에 특수 문자를 사용할 수 없습니다.");
        }

        userRepository.save(reqDTO.toEntity());
    }
}
