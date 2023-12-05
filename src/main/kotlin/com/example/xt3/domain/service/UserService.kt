package com.example.xt3.domain.service

import com.example.xt3.domain.model.dto.UserId
import com.example.xt3.domain.repository.AccountRepository
import com.example.xt3.domain.repository.UserRepository
import com.example.xt3.openapi.generated.model.AccountRes
import com.example.xt3.openapi.generated.model.UserRes
import com.example.xt3.openapi.generated.model.UserWithAccountsRes
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val accountRepository: AccountRepository
) {
    fun getUserAndAccountsByUserId(userId: Long): UserWithAccountsRes {
        // REVIEW: Exceptionを自作する
        val userDto = userRepository.getUserById(UserId(userId)) ?: throw NotFoundException()

        return UserWithAccountsRes(
            data = UserRes(
                userId = userDto.userId.value
            ),
            include = accountRepository.getAccountsByUserId(UserId(userId)).map {
                AccountRes(
                    accountId = it.accountId.value,
                    displayName = it.displayName,
                    accountName = it.accountName,
                    profileDescription = it.profileDescription,
                    profileImageUrl = it.profileImageUrl,
                    isPrimary = it.isPrimary
                )
            }
        )
    }
}
