package ru.hse.esadykov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ernest Sadykov
 * @since 14.06.2014
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout) {
        ModelMap model = new ModelMap();
        if (error != null) {
            model.addAttribute("error", "Invalid username and/or password");
        }
        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully");
        }

        return new ModelAndView("login", model);
    }
}
