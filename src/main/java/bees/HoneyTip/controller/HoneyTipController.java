package bees.HoneyTip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HoneyTipController {

    @GetMapping("index")
    public String indexPage(Model model) {
        model.addAttribute("data","index page");
        return "index";
    }
}