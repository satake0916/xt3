package com.example.xt3.domain.service

import com.example.xt3.domain.model.dto.AccountDto
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.repository.AccountRepository
import com.example.xt3.domain.repository.FollowerRepository
import com.example.xt3.openapi.generated.model.AccountRes
import com.example.xt3.openapi.generated.model.GetAccountsRes
import com.example.xt3.openapi.generated.model.UnfollowRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AccountService(
    private val accountRepository: AccountRepository,
    private val followerRepository: FollowerRepository,
) {
    fun findByAccountId(accountId: String): AccountRes? {
        return accountRepository.getAccountsByAccountId(AccountId(accountId))?.let {
            AccountRes(
                accountId = it.accountId.getValueStr(),
                accountName = it.accountName,
                displayName = it.displayName,
                profileDescription = it.profileDescription,
                profileImageUrl = it.profileImageUrl,
                isPrimary = it.isPrimary
            )
        }
    }

    fun findFollowingAccountsByAccountId(
        accountId: String,
        limit: Int,
        offset: Long?
    ): GetAccountsRes {
        val accounts = accountRepository.selectAccountsFollowedByAccountId(
            accountId = AccountId(accountId),
            limit = limit,
            offset = offset ?: 0,
        ).map {
            convertAccountDtoToAccountRes(it)
        }

        return GetAccountsRes(
            total = accounts.size,
            accounts = accounts,
        )
    }

    fun followFollowedIdAccountByFollowingAccountId(
        followingAccountId: String,
        followedAccountId: String,
    ) {
        followerRepository.insertFollowers(
            followingAccountId = AccountId(followingAccountId),
            followedAccountId = AccountId(followedAccountId),
        )
    }

    fun convertAccountDtoToAccountRes(accountDto: AccountDto): AccountRes {
        return AccountRes(
            accountId = accountDto.accountId.getValueStr(),
            accountName = accountDto.accountName,
            displayName = accountDto.displayName,
            profileDescription = accountDto.profileDescription,
            profileImageUrl = accountDto.profileImageUrl,
            isPrimary = accountDto.isPrimary
        )
    }

    fun sourceAccountIdUnfollowTargetAccountId(
        sourceAccountId: String,
        targetAccountId: String,
    ): UnfollowRes {
        val unfollowedCount = followerRepository.deleteFollowers(
            sourceAccountId = AccountId(sourceAccountId),
            targetAccountId = AccountId(targetAccountId),
        )
        return UnfollowRes(unfollowedCount > 0)
    }
}
