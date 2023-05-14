package com.example.bibafrica.controller;

import com.example.bibafrica.model.Signup;
import com.example.bibafrica.services.SignupInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class SignupController {
    @Autowired
    SignupInterface signupInterface;

//     @RequestMapping(method = RequestMethod.GET)
//     @GetMapping("/login-page")
//     public String home(Model model){
//         Signup signup=new Signup();
//         model.addAttribute("login",signup);
//         return "login";
//     }

    @GetMapping("/login-page")
    public String homepage(Model model){
        Signup signup=new Signup();
        model.addAttribute("login",signup);
       return "loginn";
    }

    @GetMapping("/Signup")
    public String registration(Model model){
        Signup signup=new Signup();
        model.addAttribute("signup",signup);
        return "signup";
    }
@PostMapping("/registerAccount")
    public String saveAccount(@ModelAttribute("signup") Signup theSignup ) {
    Signup signup = signupInterface.saveAccount(theSignup);
    if (signup != null) {
        return "redirect:/login-page";
    } else {
        return "redirect:/Signup";
    }
}
    @PostMapping("/authentication")
    public String userAuthentication(@RequestParam String email,@RequestParam String password){
          signupInterface.userCheck(email,password);
        if (signupInterface.userCheck(email,password)) {

            if (email.contains("biba") && password.contains("@")) {
                return "homes";
            } else {
                return "redirect:/tem";
            }
        }
        return "redirect:/login-page";
    }

}
