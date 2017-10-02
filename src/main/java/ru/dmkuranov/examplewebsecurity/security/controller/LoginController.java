package ru.dmkuranov.examplewebsecurity.security.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping
    public ModelAndView serveDefault(ModelMap modelMap, @ModelAttribute("formModel") FormModel formModel) {
        return new ModelAndView("login", modelMap);
    }

    @ModelAttribute("formModel")
    public FormModel getFormModel(@RequestParam(value = "success", required = false) Boolean authenticationSuccessful, HttpServletRequest request) {
        FormModel formModel = new FormModel();
        if (authenticationSuccessful != null) {
            formModel.setAuthenticationSuccessful(authenticationSuccessful);
            if (authenticationSuccessful) {
                formModel.setMessageKey("operation.success");
            } else {
                formModel.setMessageKey(getMessageKeyForException(
                        (Exception) request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")));
            }
        }
        return formModel;
    }

    private String getMessageKeyForException(Exception exception) {
        if (BadCredentialsException.class.isAssignableFrom(exception.getClass())) {
            return "auth.bad_credentials";
        }
        if (UsernameNotFoundException.class.isAssignableFrom(exception.getClass())) {
            return "auth.user_not_found";
        }
        return "operation.unknown_error";
    }

    public static class FormModel {
        private String login;
        private String password;
        private Boolean authenticationSuccessful;
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

        public Boolean getAuthenticationSuccessful() {
            return authenticationSuccessful;
        }

        public void setAuthenticationSuccessful(Boolean authenticationSuccessful) {
            this.authenticationSuccessful = authenticationSuccessful;
        }

        public String getMessageKey() {
            return messageKey;
        }

        public void setMessageKey(String messageKey) {
            this.messageKey = messageKey;
        }
    }
}
