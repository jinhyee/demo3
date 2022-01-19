package com.spring.web.Service;

import com.spring.web.Domain.User;
import com.spring.web.Dto.UserDto;
import com.spring.web.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    //로그인할 때, 아이디와 비밀번호를 확인하는 메소드
    @Override
    public User loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(id+" : 로그인에 실패했습니다."));
    }

    public String getauth(User user){
        return user.getAuth();

    }

    //회원가입 저장 메소드
    public Long save(UserDto userDto){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        userDto.setPw(encoder.encode(userDto.getPw()));

        return userRepository.save(User.builder()
                .id(userDto.getId())
                .pw(userDto.getPw())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .tel(userDto.getTel())
                .postcode(userDto.getPostcode())
                .address(userDto.getAddress())
                .detailaddress(userDto.getDetailaddress())
                .extraaddress(userDto.getExtraaddress())
                .auth(userDto.getAuth())
                .joindate(LocalDateTime.now()).build()).getCode();
    }
}
