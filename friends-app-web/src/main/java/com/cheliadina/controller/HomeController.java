package com.cheliadina.controller;

import com.cheliadina.domain.Message;
import com.cheliadina.domain.User;
import com.cheliadina.repositories.MessageRepository;
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

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView printHello(ModelMap model) {
        User user1 = new User();
        user1.setFirstName("Petya");
        userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName("Vasya");
        userRepository.save(user2);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        StringBuilder msgBuilder = new StringBuilder("have: ");
        for (User u : userRepository.findAll()) {
            msgBuilder.append(u.getFirstName() + " ");
        }

        modelAndView.addObject("msg", msgBuilder.toString());

        return modelAndView;
    }

}
