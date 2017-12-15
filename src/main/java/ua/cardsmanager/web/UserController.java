package ua.cardsmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.cardsmanager.model.Role;
import ua.cardsmanager.model.User;
import ua.cardsmanager.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


@Controller
public class UserController {


    /*protected Validator validator;

    @Autowired
    @Qualifier("userValidator")
        public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }*/

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(HttpServletRequest request) {
        if (isAdmin(request))
            return "redirect:/usersList";
        else
            return "redirect:/cards/cardsList";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public String checkLogin(HttpServletRequest request) {
        String userEmail = request.getParameter("email");
        String password = request.getParameter("pwd");
        if (userEmail.isEmpty()|| password.isEmpty())
            return "redirect:/login";
        User user = userService.getByEmail(userEmail);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getId());
            session.setAttribute("roles", user.getRoles());
            if (user.getRoles().equals(EnumSet.of(Role.ADMIN)))
                return "redirect:/usersList";
            return "redirect:/cards/cardsList/";
        }

        return "loginFailed";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegister(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @RequestMapping(value = "/users/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "registration";
        User userNew = userService.create(user);
        if (userNew == null) {
            bindingResult.rejectValue("email", "user.error.email", "Email already exist");
            return "registration";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/usersList", method = RequestMethod.GET)
    public String userList(Model model, HttpServletRequest request) {
        if (isAdmin(request)) {
            model.addAttribute("usersList", userService.getAll());
            return "usersList";
        }
        return "loginFailed";
    }

    @RequestMapping(value = "/userEdit/{id}", method = RequestMethod.GET)
    public String userEditForm(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        User editedUser = userService.get(id);
        if (isAdmin(request)) {
            model.addAttribute("user", editedUser);
            model.addAttribute("roles", getRoleList());
            return "userEdit";
        }
        return "loginFailed";
    }

    @RequestMapping(value = "/userDelete/{id}", method = RequestMethod.GET)
    public String userDelete(@PathVariable("id") Integer id, HttpServletRequest request) {
        if (isAdmin(request)) {
            userService.delete(id);
            return "redirect:/usersList";
        }
        return "loginFailed";
    }

    @RequestMapping(value = "/userEdit", method = RequestMethod.POST)
    public String userEdit(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", getRoleList());
            return "userEdit";
        }

        userService.update(user);
        return "redirect:/usersList";
    }


    private List<Role> getRoleList() {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ADMIN);
        roles.add(Role.USER);

        return roles;
    }

    /*private boolean isAllowed(HttpServletRequest request, Integer id) {

        HttpSession session = request.getSession(true);

        return !session.isNew() && session.getAttribute("userId") != null && session.getAttribute("userId").equals(id);
    }
*/
    private boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return !session.isNew() && session.getAttribute("roles") != null && session.getAttribute("roles").equals(EnumSet.of(Role.ADMIN));
    }
}
