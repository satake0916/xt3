package com.example.xt3.domain.model.dto

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class UserDtoTest : FunSpec({
    test("user id from long test") {
        val userId = UserId(123)
        userId.valueLong shouldBe 123
        userId.getValueStr() shouldBe "123"
    }

    test("user id from string test") {
        val userId = UserId("456")
        userId.valueLong shouldBe 456
        userId.getValueStr() shouldBe "456"
    }
})
