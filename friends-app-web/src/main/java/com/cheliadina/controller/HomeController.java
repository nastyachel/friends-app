package com.cheliadina.controller;

import com.cheliadina.domain.User;
import com.cheliadina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        User user = new User();
        user.setName("Petya");
        userRepository.save(user);

        user = new User();
        user.setName("Vasya");
        userRepository.save(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        StringBuilder msgBuilder = new StringBuilder("have: ");
        for (User u : userRepository.findAll())
        {
            msgBuilder.append(u.getName() + " ");
        }
        modelAndView.addObject("msg", msgBuilder.toString());
        return modelAndView;
    }

}
