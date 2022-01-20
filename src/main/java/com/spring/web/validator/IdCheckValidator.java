//package com.spring.web.validator;
//
//import com.spring.web.Dto.UserDto;
//import com.spring.web.Repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//
///**
// * 이메일 중복 확인 유효성 검증을 위한 커스텀 Validator 클래스
// */
//@RequiredArgsConstructor
//@Component
//public class IdCheckValidator extends AbstractValidator<UserDto> {
//
//    private final UserRepository userRepository;
//
//    @Override
//    protected void doValidate(UserDto dto, Errors errors) {
//        if (userRepository.existsById(dto.toEntity().getEmail())) {
//            errors.rejectValue("id", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
//        }
//    }
//}
