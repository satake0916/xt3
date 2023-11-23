package com.example.xt3.controller

import com.example.xt3.domain.form.TweetForm
import com.example.xt3.service.TweetService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class TimelineController(
    private val tweetService: TweetService
) {
    @GetMapping("/")
    fun timeline(model: Model, @ModelAttribute tweetForm: TweetForm): String {
        val tweetArray = tweetService.scanTweet()
        //val tweetForm = TweetForm("今どう？")
        model["tweetForm"] = tweetForm
        model["tweetArray"] = tweetArray
        model["title"] = "Timeline"
        return "timeline"
    }
}