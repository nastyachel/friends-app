package com.cheliadina.controller;

import com.cheliadina.domain.User;
import com.cheliadina.filter.AuthorisationFilter;
import com.cheliadina.model.AuthorisationData;
import com.cheliadina.repositories.UserRepository;
import com.cheliadina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @author nastya
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex() {
        return "redirect:/profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(){
        return "profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(name = "error", required = false) boolean error, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return "redirect:/login";
    }


    @RequestMapping(value = "/submit-login", method = RequestMethod.POST)
    public String submitLogin(@ModelAttribute AuthorisationData authData,
                              HttpSession session) {
        User user = userRepository.findByUsernameAndPassword(authData.getUsername(), authData.getPassword());
        if (user != null) {
            session.setAttribute(AuthorisationFilter.AUTH_ATTR, LocalDateTime.now());
            session.setAttribute(AuthorisationFilter.USER_ATTR, user);
            return "redirect:/profile";
        }
        return "redirect:/login?error=true";
    }

    private boolean isLoggedIn(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        LocalDateTime time = (LocalDateTime) httpSession.getAttribute(AuthorisationFilter.AUTH_ATTR);
       return !(time == null || LocalDateTime.now().isAfter(time.plusMinutes(15)));
    }

    @RequestMapping(value = "/addFriend", method = RequestMethod.GET)
    public String addFriendShip(@RequestParam("userId") String userId, @RequestParam("friendId") String friendId){
        service.addFriendShip(Integer.parseInt(userId), Integer.parseInt(friendId));
        return "redirect:/";
    }


}
