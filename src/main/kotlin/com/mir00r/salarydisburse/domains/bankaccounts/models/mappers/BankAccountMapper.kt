package com.mir00r.salarydisburse.domains.bankaccounts.models.mappers

import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.bankaccounts.models.dtos.BankAccountDto
import com.mir00r.salarydisburse.domains.bankaccounts.models.entities.BankAccount
import com.mir00r.salarydisburse.domains.bankaccounts.models.enums.AccountType
import com.mir00r.salarydisburse.domains.banks.services.BankService
import com.mir00r.salarydisburse.domains.common.models.mapper.BaseMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BankAccountMapper @Autowired constructor(
        private val bankService: BankService
) : BaseMapper<BankAccount, BankAccountDto> {

    override fun map(entity: BankAccount): BankAccountDto {
        val dto = BankAccountDto()
        dto.apply {
            id = entity.id
            created = entity.created
            uuid = entity.uuid
            lastUpdated = entity.lastUpdated
            name = entity.name
            accountNumber = entity.accountNumber
            currentBalance = entity.currentBalance
            accountType = AccountType.get(entity.accountType).id
            bankId = entity.bank.id
        }

        return dto
    }

    override fun map(dto: BankAccountDto, exEntity: BankAccount?): BankAccount {
        val entity = exEntity ?: BankAccount()
        entity.apply {
            bank = bankService.find(dto.bankId).orElseThrow { ExceptionUtil.getNotFound(Constants.BANK, dto.bankId) }
            name = dto.name
            accountNumber = dto.accountNumber
            currentBalance = dto.currentBalance
            accountType = AccountType.get(dto.accountType).id
        }
        return entity
    }
}
