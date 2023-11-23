package com.example.xt3.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping

@Controller
class TweetController{
    @PostMapping("/tweet")
    fun tweet(model: Model): String{
        println("tweet successes!")
        return "timeline"
    }
}