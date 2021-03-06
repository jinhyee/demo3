package com.spring.web.Dto;

import com.spring.web.Domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.net.ssl.SSLSession;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class UserDto {

    private Long code;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,12}",
            message = "비밀번호는 영문자와 숫자, 특수기호가 적어도 1개 이상 포함된 6자~12자의 비밀번호여야 합니다.")
    private String pw;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Pattern(regexp="^[가-힣]{2,8}$",
            message = "이름은 한글 2자~8자 사이의 이름이여야 합니다.")
    @Size(min = 2, max = 8, message = "이름을 2~8자 사이로 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @Pattern(regexp="^[0-9]{9,11}$",
            message = "전화번호는 숫자로만 입력해야 합니다.")
    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    private String tel;

    private String postcode;
    private String address;
    private String detailaddress;
    private String extraaddress;
    private String auth;
    private LocalDateTime joindate;


    ////////////////////////////////////////////
    @Builder
    public UserDto(Long code, String id, String pw, String name, String email, String tel, String postcode, String address, String detailaddress, String extraaddress, String auth, LocalDateTime joindate) {
        this.code = code;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.postcode = postcode;
        this.address = address;
        this.detailaddress = detailaddress;
        this.extraaddress = extraaddress;
        this.auth = auth;
        this.joindate = joindate;
    }

    public User toEntity(){
        return User.builder()
                .code(code)
                .id(id)
                .pw(pw)
                .name(name)
                .email(email)
                .tel(tel)
                .postcode(postcode)
                .address(address)
                .detailaddress(detailaddress)
                .extraaddress(extraaddress)
                .build();
    }

}