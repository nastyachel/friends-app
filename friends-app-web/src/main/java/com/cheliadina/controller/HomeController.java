package com.cheliadina.controller;

import com.cheliadina.domain.User;
import com.cheliadina.filter.AuthorisationFilter;
import com.cheliadina.model.AuthorisationData;
import com.cheliadina.service.PostService;
import com.cheliadina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex() {
        return "redirect:/profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfile(@RequestParam(name = "id", required = false) Integer userId, HttpSession httpSession) {
        User currentUser = userService.getFullUser(getCurrentUserId(httpSession));
        User user = (userId == null) ? currentUser : userService.getUser(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        modelAndView.addObject("user", user);
        modelAndView.addObject("currentId", currentUser.getId());
        modelAndView.addObject("isFriend", userService.checkFriendship(currentUser.getId(), user.getId()));
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(name = "error", required = false) boolean error) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return "redirect:/login";
    }


    @RequestMapping(value = "/submit-login", method = RequestMethod.POST)
    public String submitLogin(@ModelAttribute AuthorisationData authData,
                              HttpSession session) {
        User user = userService.getUserByUsernameAndPassword(authData.getUsername(), authData.getPassword());
        if (user != null) {
            session.setAttribute(AuthorisationFilter.AUTH_ATTR, LocalDateTime.now());
            session.setAttribute(AuthorisationFilter.USER_ATTR, user.getId());
            return "redirect:/profile";
        }
        return "redirect:/login?error=true";
    }

    @RequestMapping(value = "/addFriend", method = RequestMethod.GET)
    public String addFriendShip(@RequestParam("id") int userId, @RequestParam("friendId") int friendId) {
        userService.addFriendShip(userId, friendId);
        return "redirect:/profile?id=" + friendId;
    }

    @RequestMapping(value = "/removeFriend", method = RequestMethod.GET)
    public String removeFriendship(@RequestParam("id") int userId, @RequestParam("friendId") int friendId) {
        userService.removeFriendship(userId, friendId);
        return "redirect:/profile?id=" + friendId;

    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public ModelAndView getFriends(
            @RequestParam(name = "id", required = false) Integer userId,
            HttpSession httpSession) {

        if(userId == null){
            userId = getCurrentUserId(httpSession);
        }
        User user = userService.getFullUser(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("friends");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value="/create-post", method = RequestMethod.POST)
    public String createPost(String content, HttpSession httpSession){
        postService.createPost(content, getCurrentUserId(httpSession));
        return "redirect:/profile";
    }

    private int getCurrentUserId(HttpSession httpSession){
        Integer userId = (Integer) httpSession.getAttribute(AuthorisationFilter.USER_ATTR);
        if(userId == null){
            throw new RuntimeException("No user in the session");
        }
        return userId;
    }


}