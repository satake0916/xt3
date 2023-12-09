package com.example.xt3.domain.model.dto

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AccountDtoTests : FunSpec({
    test("account id from long test") {
        val accountId = AccountId(123)
        accountId.valueLong shouldBe 123
        accountId.getValueStr() shouldBe "123"
    }

    test("account id from string test") {
        val accountId = AccountId("456")
        accountId.valueLong shouldBe 456
        accountId.getValueStr() shouldBe "456"
    }
})
