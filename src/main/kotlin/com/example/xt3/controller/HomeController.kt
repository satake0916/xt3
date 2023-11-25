package com.example.xt3.controller

import com.example.xt3.domain.model.form.TweetForm
import com.example.xt3.domain.service.TweetService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class HomeController(
    private val tweetService: TweetService
) {
    @GetMapping("/")
    fun index(): String {
        return "redirect:/home"
    }

    @GetMapping("/home")
    fun home(model: Model, @ModelAttribute tweetForm: TweetForm): String {
        val tweetArray = tweetService.scanTweet()
        model["tweetForm"] = tweetForm
        model["tweetArray"] = tweetArray
        model["title"] = "Timeline"
        return "home"
    }
}
