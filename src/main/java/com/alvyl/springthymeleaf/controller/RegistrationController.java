package com.alvyl.springthymeleaf.controller;

import com.alvyl.springthymeleaf.model.User;
import com.alvyl.springthymeleaf.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        String sessionCaptcha = (String) session.getAttribute("CAPTCHA");
        if(user.getCaptcha().equals(sessionCaptcha)){
            User userExists = registerService.fetchUser(user.getUsername());
            if (userExists != null) {
                bindingResult
                        .rejectValue("email", "error.user",
                                "There is already a user registered with the UserName provided");
            }
            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("register");
            } else {
                registerService.registerUser(user);
                modelAndView.addObject("successMessage", "User has been registered successfully");
                modelAndView.addObject("user", new User());
                modelAndView.setViewName("register");

            }
        }else{
            bindingResult
                    .rejectValue("email", "error.user",
                            "Invalid Captcha");

        }
        return modelAndView;
    }
    @RequestMapping(value="/accountUpdate", method = RequestMethod.GET)
    public ModelAndView displayUserData(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = registerService.fetchUser(springUser.getUsername());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userPage");
        return modelAndView;
    }
    @RequestMapping(value="/accountUpdate", method = RequestMethod.POST)
    public ModelAndView updateUser(@Valid User user, BindingResult bindingResult, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        String sessionCaptcha = (String) session.getAttribute("CAPTCHA");
        if(user.getCaptcha().equals(sessionCaptcha)) {
            User userExists = registerService.fetchUser(user.getUsername());
            if (userExists == null) {
                bindingResult
                        .rejectValue("email", "error.user",
                                "There is already a user registered with the User ID provided");
            }
            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("register");
            } else {
                userExists.setPassword(user.getPassword());
                userExists.setEmail(user.getEmail());
                userExists.setName(user.getName());
                registerService.updateUser(userExists);
                modelAndView.addObject("successMessage", "User has been updated successfully");
                modelAndView.addObject("user", userExists);
                modelAndView.setViewName("userPage");

            }
        }
        return modelAndView;
    }
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = registerService.fetchUser(springUser.getUsername());
        modelAndView.addObject("userName", "Welcome " + user.getName()  + " (" + user.getEmail() + ")");
        modelAndView.addObject("HomeMessage","Content Available Only for all Users ");
        modelAndView.setViewName("home");
        return modelAndView;
    }


}
