package com.cheliadina.controller;

import com.cheliadina.domain.Message;
import com.cheliadina.domain.Post;
import com.cheliadina.domain.User;
import com.cheliadina.filter.AuthorisationFilter;
import com.cheliadina.model.AuthorisationData;
import com.cheliadina.model.FindFriendsViewType;
import com.cheliadina.model.RegistrationData;
import com.cheliadina.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex() {
        return "redirect:/profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfile(@RequestParam(name = "id", required = false) Integer userId, HttpSession httpSession) {
        int currentUserId = getCurrentUserId(httpSession);
        User currentUser = userService.getFullUser(currentUserId);
        User user;
        if (userId == null) {
            user = currentUser;
        } else {
            user = userService.getFullUser(userId);
        }

        List<Post> postsReverse = user.getPosts();
        Collections.reverse(postsReverse);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        modelAndView.addObject("user", user);
        modelAndView.addObject("postsReverse", postsReverse);
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String signUp() {
        return "sign-up";
    }


    @RequestMapping(value = "/submit-sign-up", method = RequestMethod.POST)
    public ModelAndView submitSignUp(
            @Valid @ModelAttribute RegistrationData registrationData,
            BindingResult bindingResult,
            HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("sign-up");
            modelAndView.addObject("errorMsg", "Some fields are not valid");
            return modelAndView;
        }

        User newUser;
        try {
            newUser = userService.createNewUser(registrationData);
        } catch (DataIntegrityViolationException e) {
            ModelAndView modelAndView = new ModelAndView("sign-up");
            modelAndView.addObject("errorMsg", "User with this username already exists");
            return modelAndView;
        }
        loginUser(newUser, httpSession);
        return new ModelAndView("redirect:/profile");
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
                              HttpSession httpSession) {
        User user = userService.getUserByUsernameAndPassword(authData.getUsername(), authData.getPassword());
        if (user != null) {
            loginUser(user, httpSession);
            return "redirect:/profile";
        }
        return "redirect:/login?error=true";
    }

    @RequestMapping(value = "/add-friend", method = RequestMethod.GET)
    public String addFriendShip(@RequestParam("friendId") int friendId, HttpSession httpSession) {
        userService.addFriendShip(getCurrentUserId(httpSession), friendId);
        return "redirect:/profile?id=" + friendId;
    }

    @RequestMapping(value = "/delete-friend", method = RequestMethod.GET)
    public String removeFriendship(@RequestParam("friendId") int friendId, HttpSession httpSession) {
        userService.removeFriendship(getCurrentUserId(httpSession), friendId);
        return "redirect:/profile?id=" + friendId;

    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public ModelAndView getFriends(
            @RequestParam(name = "id", required = false) Integer userId,
            HttpSession httpSession) {

        int currentUserId = getCurrentUserId(httpSession);
        User currentUser = userService.getFullUser(currentUserId);
        User user;
        if (userId == null) {
            user = currentUser;
        } else {
            user = userService.getFullUser(userId);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("friends");
        modelAndView.addObject("user", user);
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }

    @RequestMapping(value = "/create-post", method = RequestMethod.POST)
    public String createPost(String content, HttpSession httpSession) {
        postService.createPost(content, getCurrentUserId(httpSession));
        return "redirect:/profile";
    }

    @RequestMapping(value = "/delete-post", method = RequestMethod.GET)
    public String deletePost(@RequestParam Integer id, HttpSession httpSession) {
        postService.deletePost(id, getCurrentUserId(httpSession));
        return "redirect:/profile";
    }

    @RequestMapping(value = "/find-friends", method = RequestMethod.GET)
    public ModelAndView findFriends(HttpSession httpSession) {
        int currentUserId = getCurrentUserId(httpSession);
        User currentUser = userService.getFullUser(currentUserId);
        ModelAndView modelAndView = new ModelAndView("find-friends");
        modelAndView.addObject("type", FindFriendsViewType.DEFAULT);
        modelAndView.addObject("users", userService.findFriends(currentUserId));
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }

    @RequestMapping(value = "/create-hobby", method = RequestMethod.POST)
    public String createHobby(String title, HttpSession httpSession) {
        hobbyService.createHobby(title, getCurrentUserId(httpSession));
        return "redirect:/edit-profile";
    }

    @RequestMapping(value = "/create-place", method = RequestMethod.POST)
    public String createPlace(String title, HttpSession httpSession) {
        placeService.createPlace(title, getCurrentUserId(httpSession));
        return "redirect:/edit-profile";
    }

    @RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
    public ModelAndView editHobbies(HttpSession httpSession) {
        User user = userService.getFullUser(getCurrentUserId(httpSession));
        ModelAndView modelAndView = new ModelAndView("edit-profile");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/find-friends-by-hobby", method = RequestMethod.GET)
    public ModelAndView findFriendsByHobby(@RequestParam int id, HttpSession httpSession) {
        int currentUserId = getCurrentUserId(httpSession);
        User currentUser = userService.getFullUser(currentUserId);
        String hobbyTitle = hobbyService.getHobby(id).getTitle();
        ModelAndView modelAndView = new ModelAndView("find-friends");
        modelAndView.addObject("type", FindFriendsViewType.HOBBIES);
        modelAndView.addObject("hobbyTitle", hobbyTitle);
        modelAndView.addObject("users", hobbyService.findFriendsByHobby(hobbyTitle, currentUser));
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }

    @RequestMapping(value = "/find-friends-by-place", method = RequestMethod.GET)
    public ModelAndView findFriendsByPlace(@RequestParam int id, HttpSession httpSession) {
        int currentUserId = getCurrentUserId(httpSession);
        User currentUser = userService.getFullUser(currentUserId);
        String placeTitle = placeService.getPlace(id).getTitle();
        ModelAndView modelAndView = new ModelAndView("find-friends");
        modelAndView.addObject("type", FindFriendsViewType.PLACES);
        modelAndView.addObject("placeTitle", placeTitle);
        modelAndView.addObject("users", placeService.findFriendsByPlace(placeTitle, currentUser));
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }

    @RequestMapping(value = "/delete-hobby", method = RequestMethod.GET)
    public String deleteHobby(@RequestParam int id, HttpSession httpSession) {
        hobbyService.deleteHobby(id, getCurrentUserId(httpSession));
        return "redirect:/edit-profile";
    }

    @RequestMapping(value = "/delete-place", method = RequestMethod.GET)
    public String deletePlace(@RequestParam int id, HttpSession httpSession) {
        placeService.deletePlace(id, getCurrentUserId(httpSession));
        return "redirect:/edit-profile";
    }

    private int getCurrentUserId(HttpSession httpSession) {
        Integer userId = (Integer) httpSession.getAttribute(AuthorisationFilter.USER_ATTR);
        if (userId == null) {
            throw new RuntimeException("No user in the session");
        }
        return userId;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ModelAndView getMessagesWithUser(@RequestParam int id, HttpSession httpSession) {
        int currentUserId = getCurrentUserId(httpSession);
        if (id == currentUserId) {
            return new ModelAndView("redirect:/profile");
        }
        List<Message> dialog = messageService.getDialog(currentUserId, id);
        ModelAndView modelAndView = new ModelAndView("messages");
        modelAndView.addObject("messages", dialog);
        modelAndView.addObject("friend", userService.getUser(id));
        return modelAndView;
    }

    @RequestMapping(value = "/create-message", method = RequestMethod.POST)
    public String createMessage(String content, HttpSession httpSession, HttpServletRequest request) {
        int friendId = Integer.parseInt(request.getParameter("id"));
        messageService.createMessage(content, getCurrentUserId(httpSession), friendId);
        return "redirect:/messages?id=" + friendId;
    }

    @RequestMapping(value = "/like-post", method = RequestMethod.GET)
    public String likePost(@RequestParam int postId, @RequestParam int postOwnerId, HttpSession httpSession) {
        postService.likePost(postId, getCurrentUserId(httpSession));
        return "redirect:/profile?id=" + postOwnerId;
    }

    @RequestMapping(value = "/dialogs", method = RequestMethod.GET)
    public ModelAndView getDialogs(HttpSession httpSession) {
        int currentUserId = getCurrentUserId(httpSession);
        List<Message> dialogs = messageService.getDialogs(currentUserId);
        ModelAndView modelAndView = new ModelAndView("dialogs");
        modelAndView.addObject("currentUser", userService.getFullUser(currentUserId));
        modelAndView.addObject("dialogs", dialogs);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/check-new-messages", method = RequestMethod.GET)
    public boolean checkNewMessages(HttpSession httpSession) {
        return messageService.checkUnseenMessages(getCurrentUserId(httpSession));
    }

    private void loginUser(User user, HttpSession httpSession) {
        httpSession.setAttribute(AuthorisationFilter.AUTH_ATTR, LocalDateTime.now());
        httpSession.setAttribute(AuthorisationFilter.USER_ATTR, user.getId());
    }

}