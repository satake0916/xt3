package com.example.xt3.controller

import com.example.xt3.domain.form.TweetForm
import com.example.xt3.service.TweetCreateRequest
import com.example.xt3.service.TweetService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping

@Controller
class TweetController(
    private val tweetService: TweetService
) {
    data class UserResponse(
        val id: Long,
        val name: String,
        val age: Int,
    )
    @PostMapping("/tweet")
    fun tweet(model: Model, tweetForm: TweetForm): String{
        val _tweetId = tweetService.create(
            TweetCreateRequest(
                content = tweetForm.content,
            )
        )

        return "redirect:/"
    }
}