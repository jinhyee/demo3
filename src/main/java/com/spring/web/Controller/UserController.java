package com.spring.web.Controller;

import com.spring.web.Domain.User;
import com.spring.web.Dto.UserDto;
import com.spring.web.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserDto userDto, Errors errors, Model model){
        if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("userDto", userDto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/signup";
        }
        userService.save(userDto);

        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(@Valid UserDto userDto, Errors errors, Model model){
        if (errors.hasErrors()) {
            //로그인 실패시, 입력 데이터를 유지하고싶다
            model.addAttribute("userDto",userDto);

            //유효성 통과 못한 필드와 메시지를 핸들링하고싶다 ,,,,
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/login";
        }
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
