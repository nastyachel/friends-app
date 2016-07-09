package com.cheliadina.controller;

import com.cheliadina.domain.User;
import com.cheliadina.filter.AuthorisationFilter;
import com.cheliadina.model.AuthorisationData;
import com.cheliadina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
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
    public ModelAndView printHello(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        StringBuilder msgBuilder = new StringBuilder("have: ");
        for (User u : userRepository.findAll()) {
            msgBuilder.append(u.getFirstName() + " ");
        }
        modelAndView.addObject("msg", msgBuilder.toString());

        return modelAndView;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(name = "error", required = false) boolean error) {
        createFakeData();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }


    @RequestMapping(value = "/submit-login", method = RequestMethod.POST)
    public String submitLogin(@ModelAttribute AuthorisationData authData, HttpServletResponse response,
                              HttpSession session) {
        User user = userRepository.findByUsernameAndPassword(authData.getUsername(), authData.getPassword());
        if (user != null) {
            session.setAttribute(AuthorisationFilter.AUTH_ATTR, LocalDateTime.now());
            session.setAttribute(AuthorisationFilter.USER_ATTR, user);
            return "redirect:/";
        }
        return "redirect:/login?error=true";
    }

    private void createFakeData() {
        User user1 = new User();
        user1.setFirstName("Petya");
        user1.setUsername("admin");
        user1.setPassword("123");
        try {
            userRepository.save(user1);

        } catch (DataIntegrityViolationException exception) {
            // todo handle this
        }
    }


}
