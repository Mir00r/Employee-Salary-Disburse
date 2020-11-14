package com.mir00r.salarydisburse.domains.banks.models.mappers

import com.mir00r.salarydisburse.domains.banks.models.dtos.BankDto
import com.mir00r.salarydisburse.domains.banks.models.entities.Bank
import com.mir00r.salarydisburse.domains.common.models.mapper.BaseMapper
import org.springframework.stereotype.Component

@Component
class BankMapper : BaseMapper<Bank, BankDto> {

    override fun map(entity: Bank): BankDto {
        val dto = BankDto()

        dto.apply {
            id = entity.id
            created = entity.created
            uuid = entity.uuid
            lastUpdated = entity.lastUpdated
            name = entity.name
            branchName = entity.branchName
            address = entity.address
            balance = entity.balance
        }

        return dto
    }

    override fun map(dto: BankDto, exEntity: Bank?): Bank {
        val entity = exEntity ?: Bank()

        entity.apply {
            name = dto.name
            branchName = dto.branchName
            address = dto.address
            balance = dto.balance
        }
        return entity
    }
}
