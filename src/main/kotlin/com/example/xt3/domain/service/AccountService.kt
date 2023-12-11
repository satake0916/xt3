package com.example.xt3.domain.service

import com.example.xt3.domain.model.dto.AccountDto
import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.repository.AccountRepository
import com.example.xt3.openapi.generated.model.AccountRes
import com.example.xt3.openapi.generated.model.GetAccountsRes
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AccountService(
    private val accountRepository: AccountRepository,
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
}
