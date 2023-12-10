package com.example.xt3.domain.service

import com.example.xt3.domain.model.dto.AccountId
import com.example.xt3.domain.repository.AccountRepository
import com.example.xt3.openapi.generated.model.AccountRes
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
}
