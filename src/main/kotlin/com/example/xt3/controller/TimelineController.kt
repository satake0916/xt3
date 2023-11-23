package com.example.xt3.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TimelineController {
    @GetMapping("/")
    fun timeline(model: Model): String {
        model["title"] = "Timeline"
        return "timeline"
    }
}