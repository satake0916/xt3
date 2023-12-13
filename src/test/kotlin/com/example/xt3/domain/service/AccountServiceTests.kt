package com.example.xt3.domain.service

import com.example.xt3.domain.model.dto.AccountDto
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.model.dto.UserId
import com.example.xt3.domain.repository.AccountRepository
import com.example.xt3.domain.repository.FollowerRepository
import com.example.xt3.openapi.generated.model.AccountRes
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime

class AccountServiceTests : FunSpec({
    lateinit var mockAccountRepository: AccountRepository
    lateinit var mockFollowerRepository: FollowerRepository
    lateinit var accountService: AccountService

    beforeTest {
        mockAccountRepository = mockk()
        mockFollowerRepository = mockk()
        accountService = AccountService(mockAccountRepository, mockFollowerRepository)
    }

    context("findByAccountId") {
        test("normal") {
            every {
                mockAccountRepository.getAccountsByAccountId(AccountId(1))
            } returns AccountDto(
                accountId = AccountId(1),
                accountName = "one_1",
                userId = UserId(1),
                displayName = "One First",
                profileDescription = "Hi! Im First",
                profileImageUrl = "https://example.com/profile_image",
                isPrimary = true,
                createdAt = LocalDateTime.of(2023, 12, 9, 23, 3),
                updatedAt = LocalDateTime.of(2023, 12, 9, 23, 3),
            )

            val expectedAccountRes = AccountRes(
                accountId = "1",
                accountName = "one_1",
                displayName = "One First",
                profileDescription = "Hi! Im First",
                profileImageUrl = "https://example.com/profile_image",
                isPrimary = true,
            )

            accountService.findByAccountId("1") shouldBe expectedAccountRes
        }

        test("null") {
            every {
                mockAccountRepository.getAccountsByAccountId(AccountId("42"))
            } returns null

            accountService.findByAccountId("42") shouldBe null
        }
    }

    context("convertAccountDtoToAccountRes") {
        test("normal") {
            val accountDto = AccountDto(
                accountId = AccountId("12"),
                userId = UserId("1"),
                accountName = "jack",
                displayName = "Jack",
                profileDescription = "bitcoin and chill",
                profileImageUrl = "https://example.com/profile_image.jpg",
                isPrimary = true,
                createdAt = LocalDateTime.of(2023, 12, 12, 15, 36),
                updatedAt = LocalDateTime.of(2023, 12, 12, 23, 15),
            )

            val actualAccountRes = accountService.convertAccountDtoToAccountRes(accountDto)

            val expectedAccountRes = AccountRes(
                accountId = "12",
                displayName = "Jack",
                accountName = "jack",
                profileDescription = "bitcoin and chill",
                profileImageUrl = "https://example.com/profile_image.jpg",
                isPrimary = true
            )

            actualAccountRes shouldBe expectedAccountRes
        }
    }
})
