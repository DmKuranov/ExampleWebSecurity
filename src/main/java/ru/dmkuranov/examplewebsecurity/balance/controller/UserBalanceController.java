package ru.dmkuranov.examplewebsecurity.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.dmkuranov.examplewebsecurity.balance.service.BalanceService;

@Controller
@RequestMapping("/balance")
public class UserBalanceController {

    @Autowired
    private BalanceService balanceService;

    @RequestMapping
    public ModelAndView serveDefault(ModelMap modelMap) {
        modelMap.addAttribute("balance", balanceService.getCurrentUserBalance());
        return new ModelAndView("balance", modelMap);
    }
}
