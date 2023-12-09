package com.example.xt3.domain.model.dto

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TweetDtoTest : FunSpec({
    test("tweet id from long test") {
        val tweetId = TweetId(123)
        tweetId.valueLong shouldBe 123
        tweetId.getValueStr() shouldBe "123"
    }

    test("tweet id from string test") {
        val tweetId = TweetId("456")
        tweetId.valueLong shouldBe 456
        tweetId.getValueStr() shouldBe "456"
    }
})
