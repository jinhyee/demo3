package com.spring.web.Controller;

import com.spring.web.Domain.User;
import com.spring.web.Dto.UserDto;
import com.spring.web.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(UserDto userDto){
        userService.save(userDto);
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/main")
    public String main(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return "/user/index";
    }



}
