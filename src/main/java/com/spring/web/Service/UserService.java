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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    //중복회원 인건데 엥 엥 이러면 중복확인 버튼이랑은 아무 소용이 없구나 이친구는
    private void valicateDuplicateUser(User user){
        Optional<User> findUser = userRepository.findById(user.getId());
        if(findUser != null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
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

    // 회원가입 시, 유효성 체크
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    ///////////////////////////////////
    @Transactional(readOnly = true)
    public void checkId(UserDto dto){
        boolean IdDuplicate = userRepository.existsById(dto.toEntity().getId());
        if (IdDuplicate) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

}
