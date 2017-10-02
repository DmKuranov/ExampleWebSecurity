package ru.dmkuranov.examplewebsecurity.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dmkuranov.examplewebsecurity.security.service.UserManager;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserManager userManager;

    @RequestMapping
    public ModelAndView serveDefault(ModelMap modelMap, @ModelAttribute("formModel") FormModel formModel) {
        return new ModelAndView("signup", modelMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView servePost(ModelMap modelMap, @ModelAttribute("formModel") FormModel formModel) {
        try {
            userManager.registerUser(formModel.getLogin(), formModel.getPassword());
            formModel.setCompleted(true);
            formModel.setMessageKey("operation.success");
        } catch (Exception e) {
            formModel.setMessageKey(getMessageKeyForException(e));
        }
        return new ModelAndView("signup", modelMap);
    }

    @ModelAttribute("formModel")
    public FormModel getFormModel(@RequestParam(value = "success", required = false) Boolean authenticationSuccessful, HttpServletRequest request) {
        FormModel formModel = new FormModel();
        return formModel;
    }

    private String getMessageKeyForException(Exception exception) {
        if (DuplicateKeyException.class.isAssignableFrom(exception.getClass())) {
            return "signup.user_exists";
        }
        return "operation.unknown_error";
    }

    public static class FormModel {
        private String login;
        private String password;
        private boolean completed;
        private String messageKey;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public String getMessageKey() {
            return messageKey;
        }

        public void setMessageKey(String messageKey) {
            this.messageKey = messageKey;
        }
    }
}
