package com.spring.web.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class UserDto {
    private String id;
    private String pw;
    private String name;
    private String email;
    private String tel;
    private String postcode;
    private String address;
    private String detailaddress;
    private String extraaddress;
    private String auth;
    private LocalDateTime joindate;
}
