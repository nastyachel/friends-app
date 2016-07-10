package com.cheliadina.controller;

import com.cheliadina.domain.User;
import com.cheliadina.filter.AuthorisationFilter;
import com.cheliadina.model.AuthorisationData;
import com.cheliadina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView printHello(ModelMap model, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        addLoginOptions(modelAndView, httpServletRequest);
        modelAndView.setViewName("index");
        return modelAndView;
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
        return "redirect:/";
    }


    @RequestMapping(value = "/submit-login", method = RequestMethod.POST)
    public String submitLogin(@ModelAttribute AuthorisationData authData,
                              HttpSession session) {
        User user = userRepository.findByUsernameAndPassword(authData.getUsername(), authData.getPassword());
        if (user != null) {
            session.setAttribute(AuthorisationFilter.AUTH_ATTR, LocalDateTime.now());
            session.setAttribute(AuthorisationFilter.USER_ATTR, user);
            return "redirect:/";
        }
        return "redirect:/login?error=true";
    }

    @RequestMapping(value = "/create-data", method = RequestMethod.GET)
    public String createFakeData() {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setFirstName("Petya");
            user1.setUsername("admin");
            user1.setPassword("123");
            userRepository.save(user1);
        }
        return "redirect:/";
    }

    private void addLoginOptions(ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        modelAndView.addObject("logged", isLoggedIn(httpServletRequest));
    }

    private boolean isLoggedIn(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        LocalDateTime time = (LocalDateTime) httpSession.getAttribute(AuthorisationFilter.AUTH_ATTR);
       return !(time == null || LocalDateTime.now().isAfter(time.plusMinutes(15)));
    }


}