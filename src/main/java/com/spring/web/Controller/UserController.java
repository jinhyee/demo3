package com.spring.web.Controller;

import com.spring.web.Domain.User;
import com.spring.web.Dto.UserDto;
import com.spring.web.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    // 아이디 체크
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("id") String id){
        System.out.println("userIdCheck 진입");
        System.out.println("전달받은 id:"+id);
        int cnt = userService.idCheck(id);
        System.out.println("확인 결과:"+cnt);
        return cnt;
    }

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
    public String login(/*HttpServletRequest request, Model model Principal principal*/) {

//        HttpSession session = request.getSession();
//        UserDto userDto=userService.findCode(SecurityContextHolder.getContext().getAuthentication().getName());
//        model.addAttribute("userList", userDto);
//        String loginId = principal.getName();
//        session.setAttribute("sessionId",userDto);
//        String sessionId = (String)session.getAttribute("sessionId");
//        System.out.println("유저아이디" + sessionId);

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
                .getContext().getAuthentication());
        return  "redirect:/login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/main")
    public String main(Model model){
        UserDto userDto=userService.findCode(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("userList", userDto);
        return "/user/index";
    }

    @GetMapping("/user/index")
    public String userIndex(Model model){
        UserDto userDto=userService.findCode(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("userList", userDto);
        return "/user/index";
    }

    @GetMapping("/user/usermain1")
    public String naverMap(){
        return "/user/usermain1";
    }

    @GetMapping("/user/usermain2")
    public String kakaoMap(){
        return "/user/usermain2";
    }

    @GetMapping("/user/usermain3")
    public String googleMap(){
        return "/user/usermain3";
    }

    @GetMapping("/user/usermain4")
    public String vworldMap(){
        return "/user/usermain4";
    }

    @CrossOrigin
    @GetMapping("/user/usermain5")
    public String openstreetMap(){
        return "/user/usermain5";
    }

    @GetMapping("/user/usermain6")
    public String openstreetMap2(){
        return "/user/usermain6";
    }

}