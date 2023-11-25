package com.example.xt3.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping("/login")
    fun login(ignoredModel: Model): String {
        return "login"
    }
}
