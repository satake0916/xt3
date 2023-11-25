package com.example.xt3.controller

import com.example.xt3.domain.model.form.TweetForm
import com.example.xt3.domain.service.TweetCreateRequest
import com.example.xt3.domain.service.TweetService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping

@Controller
class TweetController(
    private val tweetService: TweetService
) {
    @PostMapping("/tweet")
    fun tweet(ignoreModel: Model, tweetForm: TweetForm): String {
        val expectedTweetId = tweetService.create(
            TweetCreateRequest(
                text = tweetForm.text,
            )
        )

        return "redirect:/"
    }
}
